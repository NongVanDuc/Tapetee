package com.vanduc.tapetee.Model;

public class Favorite {
    private String id;
    private String link;
    private String countView;
    private String countLike;

    public Favorite() {
    }

    public Favorite(String link, String countView, String countLike) {
        this.link = link;
        this.countView = countView;
        this.countLike = countLike;
    }

    public Favorite(String id, String link, String countView, String countLike) {
        this.id = id;
        this.link = link;
        this.countView = countView;
        this.countLike = countLike;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCountView() {
        return countView;
    }

    public void setCountView(String countView) {
        this.countView = countView;
    }

    public String getCountLike() {
        return countLike;
    }

    public void setCountLike(String countLike) {
        this.countLike = countLike;
    }
}
