package com.vanduc.tapetee.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vanduc.tapetee.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetail extends Fragment {
    ImageView imgDetail;

    public FragmentDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgDetail=view.findViewById(R.id.imgDetail);
        String url=this.getArguments().getString("url");
        Log.e( "onViewCreated: ", url);
        Glide.with(getContext()).load(url)
                .apply(new RequestOptions().placeholder(R.drawable.ic_image_black))
                .into(imgDetail);
    }
}
