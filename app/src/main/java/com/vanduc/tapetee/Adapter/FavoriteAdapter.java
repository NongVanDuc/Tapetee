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
import com.vanduc.tapetee.Database.DatabaseHelper;
import com.vanduc.tapetee.ImageDetail;
import com.vanduc.tapetee.Interface.itemClickListener;
import com.vanduc.tapetee.Model.Favorite;
import com.vanduc.tapetee.R;
import com.vanduc.tapetee.ViewHolder.ItemViewHolder;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    Context context;
    List <Favorite> favorite;
    DatabaseHelper databaseHelper;
    public FavoriteAdapter(Context context, List<Favorite> favorite,DatabaseHelper databaseHelper) {
        this.context = context;
        this.favorite = favorite;
        this.databaseHelper=databaseHelper;
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
        holder.tvView.setText(favorite.get(position).getCountView());
        holder.tvFavorate.setText(favorite.get(position).getCountLike());
        Glide.with(context).load(favorite.get(position).getLink())
                .apply(new RequestOptions().placeholder(R.drawable.ic_image_black))
                .into(holder.imgItem);

            holder.setListener(new itemClickListener() {
                @Override
                public void onClick(View view, int i) {
                    String url = favorite.get(position).getLink();
                    Intent intent = new Intent(context, ImageDetail.class);
                    intent.putExtra("url", url);
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return favorite.size();
    }
}
