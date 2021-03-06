package com.vanduc.tapetee.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vanduc.tapetee.ImageDetail;
import com.vanduc.tapetee.Interface.itemClickListener;
import com.vanduc.tapetee.Model.latest.Latest;
import com.vanduc.tapetee.R;
import com.vanduc.tapetee.ViewHolder.ItemViewHolder;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    Context context;
    Latest latest;

    public ItemAdapter(Context context, Latest latest) {
        this.context = context;
        this.latest = latest;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        View view=layoutInflater.inflate(R.layout.section_item_menu,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.tvView.setText(latest.getHDWALLPAPER().get(position).getTotalViews());
        holder.tvFavorate.setText("0");
        Glide.with(context).load(latest.getHDWALLPAPER().get(position).getWallpaperImage())
                .apply(new RequestOptions().placeholder(R.drawable.ic_image_black))
                .into(holder.imgItem);
        holder.setListener(new itemClickListener() {
            @Override
            public void onClick(View view, int i) {
                String url =latest.getHDWALLPAPER().get(position).getWallpaperImage();
                Intent intent=new Intent(context, ImageDetail.class);
                intent.putExtra("url",url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return latest.getHDWALLPAPER().size();
    }
}
