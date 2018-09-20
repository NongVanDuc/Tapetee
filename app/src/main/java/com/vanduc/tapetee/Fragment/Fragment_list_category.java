package com.vanduc.tapetee.Fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanduc.tapetee.Adapter.ListCategoryAdapter;
import com.vanduc.tapetee.Common.Common;
import com.vanduc.tapetee.Interface.CategoryService;
import com.vanduc.tapetee.Model.listcategory.ListCategory;
import com.vanduc.tapetee.R;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_list_category extends Fragment {
    CategoryService categoryService;
    RecyclerView recyclerView;
    ListCategoryAdapter listCategoryAdapter;
    private AlertDialog alertDialog;
    public Fragment_list_category() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getActivity() instanceof AppCompatActivity) {
            String name=this.getArguments().getString("name");
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(name);
            }
        }
        return inflater.inflate(R.layout.fragment_list_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id=this.getArguments().getString("id");
        alertDialog=new SpotsDialog(getContext());
        alertDiaLog(true);
        Log.e("onViewCreated: ",id );
        recyclerView=view.findViewById(R.id.recyclerview);
        categoryService= Common.getCategoryService();
        categoryService.getListCategory(id).enqueue(new Callback<ListCategory>() {
            @Override
            public void onResponse(Call<ListCategory> call, Response<ListCategory> response) {
                disPlayData(response.body());
                alertDiaLog(false);
            }

            @Override
            public void onFailure(Call<ListCategory> call, Throwable t) {
                alertDiaLog(false);
            }
        });
    }
    private void alertDiaLog(boolean isShow){
        if (isShow){
            alertDialog.show();
        }
        else
            alertDialog.dismiss();
    }
    private void disPlayData(ListCategory body) {
        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(glm);
        listCategoryAdapter =new ListCategoryAdapter(getContext(),body);
        recyclerView.setAdapter(listCategoryAdapter);
        listCategoryAdapter.notifyDataSetChanged();
    }
}
