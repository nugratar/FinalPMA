package com.example.finalp.Data.Local.Models;

import com.example.finalp.Const;
import com.example.finalp.ImageSize;

import io.realm.RealmObject;

public class FavoriteMovie extends RealmObject{
    private int id;
    private String title;
    private String posterPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath(ImageSize size) {
        return Const.BASE_IMAGE_URL + size.getValue() + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
