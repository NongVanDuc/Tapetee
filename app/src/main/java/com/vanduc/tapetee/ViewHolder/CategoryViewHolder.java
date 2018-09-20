package com.vanduc.tapetee.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vanduc.tapetee.Interface.itemClickListener;
import com.vanduc.tapetee.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public View rootView;
    public TextView tvName;
    public TextView tvCount;
    public ImageView imgItem;
    itemClickListener listener;

    public void setListener(itemClickListener listener) {
        this.listener = listener;
    }

    public CategoryViewHolder(View view) {
        super(view);
        rootView = view;
       tvName = (TextView) view.findViewById(R.id.tvName);
       tvCount = (TextView) view.findViewById(R.id.tvCount);
       imgItem=(ImageView)view.findViewById(R.id.imgItem);
       view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition());
    }
}
