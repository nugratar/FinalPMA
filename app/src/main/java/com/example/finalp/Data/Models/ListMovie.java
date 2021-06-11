package com.example.finalp.Data.Models;

import com.example.finalp.Const;
import com.example.finalp.ImageSize;
import com.google.gson.annotations.SerializedName;

public class ListMovie {
    private final int id;
    private final String title;
    @SerializedName("poster_path")
    private final String posterPath;

    public ListMovie(int id, String title, String posterPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getPosterPath(ImageSize imageSize) {
        return Const.BASE_IMAGE_URL + imageSize.getValue() + posterPath;
    }
}
