package com.vanduc.tapetee.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vanduc.tapetee.Interface.itemClickListener;
import com.vanduc.tapetee.Interface.onItemLogClickListener;
import com.vanduc.tapetee.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public View rootView;
    public TextView tvView;
    public TextView tvFavorate;
    public ImageView imgItem;
    private itemClickListener listener;
    private onItemLogClickListener onItemLogClickListener;

    public void setOnItemLogClickListener(com.vanduc.tapetee.Interface.onItemLogClickListener onItemLogClickListener) {
        this.onItemLogClickListener = onItemLogClickListener;
    }

    public void setListener(itemClickListener listener) {
        this.listener = listener;
    }

    public ItemViewHolder(View view) {
        super(view);
        rootView = view;
        tvView = (TextView) view.findViewById(R.id.tvViewCount);
        tvFavorate = (TextView) view.findViewById(R.id.tvFavorateCount);
        imgItem=(ImageView)view.findViewById(R.id.imgItem);
        view.setOnClickListener(this);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int adapterPos = getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (onItemLogClickListener != null) {
                        onItemLogClickListener.onLogClick(view,adapterPos);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        int adapterPos = getAdapterPosition();
        if (adapterPos != RecyclerView.NO_POSITION) {
            if (listener != null) {
                listener.onClick(view,adapterPos);
            }
        }
    }
}
