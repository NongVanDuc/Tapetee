package com.vanduc.tapetee.Fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanduc.tapetee.Adapter.ItemAdapter;
import com.vanduc.tapetee.Adapter.ItemGifAdapter;
import com.vanduc.tapetee.Common.Common;
import com.vanduc.tapetee.Interface.CategoryService;
import com.vanduc.tapetee.Model.gif.Gif;
import com.vanduc.tapetee.Model.latest.Latest;
import com.vanduc.tapetee.R;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategory extends Fragment {
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    CategoryService categoryService;
    String category;
    private AlertDialog alertDialog;
    public FragmentCategory() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        category  = this.getArguments().getString("message");
        alertDialog=new SpotsDialog(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest, container, false);
    }
    private void alertDiaLog(boolean isShow){
        if (isShow){
            alertDialog.show();
        }
        else
            alertDialog.dismiss();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerview);
        categoryService= Common.getCategoryService();
        alertDiaLog(true);
        if(category.equalsIgnoreCase("latest")){
            if (getActivity() instanceof AppCompatActivity) {
                AppCompatActivity activity = ((AppCompatActivity) getActivity());
                if (activity.getSupportActionBar() != null) {
                    activity.getSupportActionBar().setTitle(R.string.latest);
                }
            }
            categoryService.getLatest().enqueue(new Callback<Latest>() {
                @Override
                public void onResponse(Call<Latest> call, Response<Latest> response) {
                    disPlayData(response.body());
                    alertDiaLog(false);
                }

                @Override
                public void onFailure(Call<Latest> call, Throwable t) {
                    alertDiaLog(false);
                }
            });
        }
        else if(category.equalsIgnoreCase("GIFs")){
            if (getActivity() instanceof AppCompatActivity) {
                AppCompatActivity activity = ((AppCompatActivity) getActivity());
                if (activity.getSupportActionBar() != null) {
                    activity.getSupportActionBar().setTitle(R.string.gif);
                }
            }
            categoryService.getGif().enqueue(new Callback<Gif>() {
                @Override
                public void onResponse(Call<Gif> call, Response<Gif> response) {
                    disPlayDataGif(response.body());
                    alertDiaLog(false);
                }

                @Override
                public void onFailure(Call<Gif> call, Throwable t) {
                    alertDiaLog(false);
                }
            });
        }

    }

    private void disPlayDataGif(Gif body) {

        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(glm);
        ItemGifAdapter itemGifAdapter=new ItemGifAdapter(getContext(),body);
        recyclerView.setAdapter(itemGifAdapter);
        itemGifAdapter.notifyDataSetChanged();
    }

    private void disPlayData(Latest body) {

        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(glm);
        itemAdapter=new ItemAdapter(getContext(),body);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
    }
}
