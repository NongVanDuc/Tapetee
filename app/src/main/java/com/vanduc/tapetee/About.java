package com.vanduc.tapetee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.vanduc.tapetee.Common.Common;
import com.vanduc.tapetee.Interface.CategoryService;
import com.vanduc.tapetee.Model.about.AboutUs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class About extends AppCompatActivity {
    private TextView tvAppName;
    private TextView tvtextVersion;
    private TextView tvVersion;
    private TextView tvAuthor;
    private TextView tvEmail;
    private TextView tvContact;
    private TextView tvDecription;
    private CategoryService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        // tollbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvtextVersion = (TextView) findViewById(R.id.tvtextVersion);
        tvVersion = (TextView) findViewById(R.id.tvVersion);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvContact = (TextView) findViewById(R.id.tvContact);
        tvDecription = (TextView) findViewById(R.id.tvDecription);
        service= Common.getCategoryService();
        service.getAboutUs().enqueue(new Callback<AboutUs>() {
            @Override
            public void onResponse(Call<AboutUs> call, Response<AboutUs> response) {
                displayInfo(response.body());
            }

            @Override
            public void onFailure(Call<AboutUs> call, Throwable t) {

            }
        });

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

    private void displayInfo(AboutUs body) {
        tvAppName.setText(body.getHDWALLPAPER().get(0).getAppName());
        tvVersion.setText(body.getHDWALLPAPER().get(0).getAppVersion());
        tvAuthor.setText(body.getHDWALLPAPER().get(0).getAppAuthor());
        tvEmail.setText(body.getHDWALLPAPER().get(0).getAppEmail());
        tvContact.setText(body.getHDWALLPAPER().get(0).getAppContact());
        tvDecription.setText(Html.fromHtml((body.getHDWALLPAPER().get(0).getAppDescription())));
    }
}
