package com.vanduc.tapetee.Fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanduc.tapetee.Adapter.CategoryAdapter;
import com.vanduc.tapetee.Adapter.ItemAdapter;
import com.vanduc.tapetee.Common.Common;
import com.vanduc.tapetee.Interface.CategoryService;
import com.vanduc.tapetee.Model.category.Category;
import com.vanduc.tapetee.R;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategories extends Fragment {
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    CategoryService categoryService;
    private AlertDialog alertDialog;
    public FragmentCategories() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(R.string.category);
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alertDialog= new SpotsDialog(getContext());
        alertDiaLog(true);
        recyclerView=view.findViewById(R.id.recyclerview);
        categoryService= Common.getCategoryService();
        categoryService.getCategory().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                disPlayCategory(response.body());
                alertDiaLog(false);
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
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

    private void disPlayCategory(Category body) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(),body);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

}
