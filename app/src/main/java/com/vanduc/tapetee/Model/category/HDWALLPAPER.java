
package com.vanduc.tapetee.Model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HDWALLPAPER {

    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_image_thumb")
    @Expose
    private String categoryImageThumb;
    @SerializedName("total_wallpaper")
    @Expose
    private String totalWallpaper;

    public HDWALLPAPER(String categoryName, String categoryImage, String totalWallpaper,String cid) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.totalWallpaper = totalWallpaper;
        this.cid=cid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageThumb() {
        return categoryImageThumb;
    }

    public void setCategoryImageThumb(String categoryImageThumb) {
        this.categoryImageThumb = categoryImageThumb;
    }

    public String getTotalWallpaper() {
        return totalWallpaper;
    }

    public void setTotalWallpaper(String totalWallpaper) {
        this.totalWallpaper = totalWallpaper;
    }

}
