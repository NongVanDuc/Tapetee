package com.vanduc.tapetee.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vanduc.tapetee.Fragment.Fragment_list_category;
import com.vanduc.tapetee.Interface.itemClickListener;
import com.vanduc.tapetee.Model.category.Category;
import com.vanduc.tapetee.R;
import com.vanduc.tapetee.ViewHolder.CategoryViewHolder;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    Context context;
    Category category;

    public CategoryAdapter(Context context, Category category) {
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        View view=layoutInflater.inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int position) {
        holder.tvName.setText(category.getHDWALLPAPER().get(position).getCategoryName());
        holder.tvCount.setText("("+category.getHDWALLPAPER().get(position).getTotalWallpaper()+")");
        Glide.with(context).load(category.getHDWALLPAPER().get(position).getCategoryImage())
                .apply(new RequestOptions().placeholder(R.drawable.ic_image_black))
                .into(holder.imgItem);
        holder.setListener(new itemClickListener() {
            @Override
            public void onClick(View view, int i) {
                Bundle bundle = new Bundle();
                String id =category.getHDWALLPAPER().get(position).getCid();
                String name =category.getHDWALLPAPER().get(position).getCategoryName();
                bundle.putString("id", id );
                bundle.putString("name",name);
                Fragment fragment = new Fragment_list_category();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

//        Picasso.get().load(category.getHDWALLPAPER().get(position).getCategoryImage())
//                .placeholder(R.drawable.ic_image_black)
//                .error(R.drawable.ic_image_black)
//                .into(holder.imgItem);
    }

    @Override
    public int getItemCount() {
        return category.getHDWALLPAPER().size();
    }
}
