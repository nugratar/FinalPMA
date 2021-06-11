package com.example.finalp.Data.Api.Repository.Callbacks;

import com.example.finalp.Data.Models.ListMovie;

import java.util.List;

public interface OnMovieListCallback {
    void onSuccess(List<ListMovie> movieList, int page, String message);
    void onFailure(String message);
}
