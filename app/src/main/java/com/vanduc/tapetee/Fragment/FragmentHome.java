package com.vanduc.tapetee.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vanduc.tapetee.Common.Common;
import com.vanduc.tapetee.ImageDetail;
import com.vanduc.tapetee.Interface.CategoryService;
import com.vanduc.tapetee.Interface.itemClickListener;
import com.vanduc.tapetee.Model.gif.Gif;
import com.vanduc.tapetee.Model.latest.Latest;
import com.vanduc.tapetee.R;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    CategoryService mService;
    private SectionedRecyclerViewAdapter sectionAdapter;
    private List<Category> categoryList;
    private List<Category> categoryList2;
    private AlertDialog alertDialog;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        categoryList=new ArrayList<>();
        categoryList2=new ArrayList<>();
        mService= Common.getCategoryService();
        alertDialog=new SpotsDialog(getContext());
        sectionAdapter = new SectionedRecyclerViewAdapter();
        getLatest();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.setLayoutManager(glm);
        alertDialog.show();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(R.string.home);
            }
        }
    }

    private void getLatest() {

        mService.getLatest().enqueue(new Callback<Latest>() {
            @Override
            public void onResponse(Call<Latest> call, Response<Latest> response) {
                getLatestList(response.body());
                alertDialog.dismiss();
            }



            @Override
            public void onFailure(Call<Latest> call, Throwable t) {
                alertDialog.dismiss();
            }

        });
    }
    private void getLatestList(Latest body) {
        for (int i=0;i<4;i++){
            categoryList.add(new Category(body.getHDWALLPAPER().get(i).getTotalViews().toString(),"0",body.getHDWALLPAPER().get(i).getWallpaperImageThumb().toString()));
        }
        sectionAdapter.addSection(new CategorySection(getString(R.string.latest), categoryList));
        recyclerView.setAdapter(sectionAdapter);
        sectionAdapter.notifyDataSetChanged();
        alertDialog.dismiss();
       getGifs();
    }

    private void getGifs() {
        mService.getGif().enqueue(new Callback<Gif>() {
            @Override
            public void onResponse(Call<Gif> call, Response<Gif> response) {
                getGifList(response.body());
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Gif> call, Throwable t) {
                alertDialog.dismiss();
            }
        });



    }

    private void getGifList(Gif body) {
        for(int i=0;i<4;i++){
            categoryList2.add(new Category(body.getHDWALLPAPER().get(i).getTotalViews().toString(),"0",body.getHDWALLPAPER().get(i).getGifImage().toString()));
        }
        sectionAdapter.addSection(new CategorySection(getString(R.string.gif), categoryList2));
        recyclerView.setAdapter(sectionAdapter);
    }

    private class CategorySection extends StatelessSection {

        String title;
        List<Category> list;

        CategorySection(String title, List<Category> list) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.section_item_menu)
                    .headerResourceId(R.layout.section_header)
                    .build());

            this.title = title;
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }
// onBind Item
        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String countView = list.get(position).getCountView();
            String countFavorite = list.get(position).getCountFavorite();
            Glide.with(getContext()).load(list.get(position).getUrlImage())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_black))
                    .into(itemHolder.imgView);
            itemHolder.tvView.setText(countView);
            itemHolder.tvFavorate.setText(countFavorite);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url =list.get(position).getUrlImage();
                    Intent intent=new Intent(getContext(), ImageDetail.class);
                    intent.putExtra("url",url);
                    getContext().startActivity(intent);
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }
// onBind header
        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);

            headerHolder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    String myMessage =title;
                    bundle.putString("message", myMessage );
                    Fragment fragment = new FragmentCategory();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
    }
// header Holder
    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final Button btnMore;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            btnMore = (Button) view.findViewById(R.id.btnMore);
        }
    }
// item Holder
    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View rootView;
        private final TextView tvView;
        private final TextView tvFavorate;
        private final ImageView imgView;
        private itemClickListener clickListener;

    public void setClickListener(itemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    ItemViewHolder(View view) {
            super(view);
            rootView = view;
            tvView = (TextView) view.findViewById(R.id.tvViewCount);
            tvFavorate = (TextView) view.findViewById(R.id.tvFavorateCount);
            imgView=(ImageView)view.findViewById(R.id.imgItem);
            view.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view,getAdapterPosition());
    }
}
// class common wallpaper
    private class Category {
        String countView;
        String countFavorite;
        String urlImage;

    public Category(String countView, String countFavorite, String urlImage) {
        this.countView = countView;
        this.countFavorite = countFavorite;
        this.urlImage = urlImage;
    }

    public String getCountView() {
        return countView;
    }

    public void setCountView(String countView) {
        this.countView = countView;
    }

    public String getCountFavorite() {
        return countFavorite;
    }

    public void setCountFavorite(String countFavorite) {
        this.countFavorite = countFavorite;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
}
