package com.example.finalp;

import java.util.Locale;

public class Const {
    public static final String API_KEY = "6344ab6e0bf02635571bb18be66acfb3";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    public static final String MOVIE_ID = "MOVIE_ID";

    public static String getLang() {
        switch (Locale.getDefault().toString()) {
            case "in_ID":
                return "id-ID";
            default:
                return "en-US";
        }
    }
}
