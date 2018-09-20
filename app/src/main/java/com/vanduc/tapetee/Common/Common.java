package com.vanduc.tapetee.Common;

import com.vanduc.tapetee.Interface.CategoryService;
import com.vanduc.tapetee.Remote.RetrofitClient;

public class Common {
    public static final String BASE_URL="http://tapetee.com/";
    public static CategoryService getCategoryService(){
        return RetrofitClient.getClient(BASE_URL).create(CategoryService.class);
    }
}
