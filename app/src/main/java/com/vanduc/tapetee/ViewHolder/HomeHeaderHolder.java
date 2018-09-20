package com.vanduc.tapetee.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vanduc.tapetee.R;

public class HomeHeaderHolder extends RecyclerView.ViewHolder{
    public TextView tvTitle;
    public Button btnMore;

    HomeHeaderHolder(View view) {
        super(view);

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        btnMore = (Button) view.findViewById(R.id.btnMore);
    }
}
