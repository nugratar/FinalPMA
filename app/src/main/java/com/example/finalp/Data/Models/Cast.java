package com.example.finalp.Data.Models;

import com.example.finalp.Const;
import com.example.finalp.ImageSize;
import com.google.gson.annotations.SerializedName;

public class Cast {
    @SerializedName("name")
    private String name;

    @SerializedName("character")
    private String character;

    @SerializedName("profile_path")
    private String profilePath;

    public String getName() {
        return name;
    }

    public String getCharacter() {
        return character;
    }

    public String getProfilePath(ImageSize size) {
        return Const.BASE_IMAGE_URL + size.getValue() + profilePath;
    }
}
