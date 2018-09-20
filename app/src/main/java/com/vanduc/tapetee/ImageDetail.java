package com.vanduc.tapetee;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.vanduc.tapetee.Control.SaveImage;
import com.vanduc.tapetee.Database.DatabaseHelper;
import com.vanduc.tapetee.Model.Favorite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageDetail extends AppCompatActivity implements View.OnClickListener {
    ImageView imgDetail;
    private FloatingActionMenu floatMenu;
    private FloatingActionButton btnLike;
    private FloatingActionButton btnShare;
    private FloatingActionButton btnSave;
    private FloatingActionButton btnSetWallpaper;
    private DatabaseHelper databaseHelper;
    private String url;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        layout=findViewById(R.id.layoutDetail);
        databaseHelper=new DatabaseHelper(this);
        floatMenu = (FloatingActionMenu)findViewById(R.id.floatMenu);
        btnLike = (FloatingActionButton)findViewById(R.id.btnLike);
        btnShare = (FloatingActionButton)findViewById(R.id.btnShare);
        btnSave = (FloatingActionButton)findViewById(R.id.btnSave);
        btnSetWallpaper = (FloatingActionButton)findViewById(R.id.btnSetWallpaper);
        imgDetail=findViewById(R.id.imgDetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Image Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        url=getIntent().getStringExtra("url");
        Log.e( "onViewCreated: ", url);
        Glide.with(this).load(url)
                .apply(new RequestOptions().placeholder(R.drawable.ic_image_black))
                .into(imgDetail);
        btnLike.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnSetWallpaper.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLike:
                addImage();
                floatMenu.close(true);
                break;
            case R.id.btnShare:
                shareImage(url,this);
                floatMenu.close(true);
                break;
            case R.id.btnSave:
                Toast.makeText(ImageDetail.this, "Started download image !", Toast.LENGTH_SHORT).show();
                SaveImage saveImage=new SaveImage(ImageDetail.this,url,getResources().getString(R.string.app_name));
                saveImage.downloadImage();
                floatMenu.close(true);
                break;
            case R.id.btnSetWallpaper:
                Glide.
                        with(ImageDetail.this)
                        .asBitmap()
                        .load(url)
                        .apply(new RequestOptions().placeholder(R.drawable.ic_image_black).error(R.drawable.ic_image_black))
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                try {
                                    WallpaperManager.getInstance(getApplicationContext()).setBitmap(resource);
                                    Snackbar.make(layout,"Wallpaper was set !",Snackbar.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                floatMenu.close(true);
                break;
        }
    }

    private void addImage() {
        Favorite favorite=new Favorite(url,"0","0");
        if(databaseHelper.addImage(favorite)){
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }

    }

    private void shareImage(String url, final Context context) {
        Glide.with(context).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Uri uri=getLocalBitmapUri(resource,ImageDetail.this);
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, uri);
                context.startActivity(Intent.createChooser(i, "Share Image"));
            }
        });
    }
    private Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        try {
            File file =  new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

}
