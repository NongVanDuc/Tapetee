package com.vanduc.tapetee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vanduc.tapetee.Adapter.FavoriteAdapter;
import com.vanduc.tapetee.Database.DatabaseHelper;
import com.vanduc.tapetee.Model.Favorite;

import java.util.ArrayList;
import java.util.List;

public class ACFavorite extends AppCompatActivity {
    private RecyclerView recyclerview;
    private DatabaseHelper databaseHelper;
    private List<Favorite> favoriteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        databaseHelper=new DatabaseHelper(this);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Favorite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        disPlayData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void disPlayData() {
        favoriteList=new ArrayList<>();
        favoriteList=databaseHelper.getAllImage();
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(glm);
        FavoriteAdapter  favoriteAdapter=new FavoriteAdapter(this,favoriteList,databaseHelper);
        recyclerview.setAdapter(favoriteAdapter);
        favoriteAdapter.notifyDataSetChanged();
    }
}
