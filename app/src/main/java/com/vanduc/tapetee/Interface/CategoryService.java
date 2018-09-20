package com.vanduc.tapetee.Interface;

import com.vanduc.tapetee.Model.about.AboutUs;
import com.vanduc.tapetee.Model.category.Category;
import com.vanduc.tapetee.Model.gif.Gif;
import com.vanduc.tapetee.Model.latest.Latest;
import com.vanduc.tapetee.Model.listcategory.ListCategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryService {
    @GET("api.php?latest")
    Call<Latest> getLatest();
    @GET("api.php?gif_list")
    Call<Gif> getGif();
    @GET("api.php?cat_list")
    Call<Category> getCategory();
    @GET("api.php?")
    Call<ListCategory> getListCategory(@Query("cat_id") String cat_id);
    @GET("api.php")
    Call<AboutUs> getAboutUs();
}
