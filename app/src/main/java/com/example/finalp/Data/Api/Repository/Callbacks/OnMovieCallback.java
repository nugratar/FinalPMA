package com.example.finalp.Data.Api.Repository.Callbacks;

import com.example.finalp.Data.Models.Movie;

import java.util.List;

public interface OnMovieCallback {
        void onFailure(String msg);

    void onSuccess(List<Movie> results, int page, String message);
}
