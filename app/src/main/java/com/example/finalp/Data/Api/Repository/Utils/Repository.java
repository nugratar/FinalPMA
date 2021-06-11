package com.example.finalp.Data.Api.Repository.Utils;

import com.example.finalp.Data.Api.Repository.Callbacks.OnCastCallback;
import com.example.finalp.Data.Api.Repository.Callbacks.OnMovieListCallback;
import com.example.finalp.Data.Api.Repository.Callbacks.OnSearchCallback;
import com.example.finalp.Data.Api.Service;

public abstract class Repository<T> {
    protected Service service;

    protected abstract void getModel(String queryType, int page, final OnMovieListCallback callback);
    protected abstract void getModelDetail(int id, final OnMovieListCallback callback);
    protected abstract void search(String query, int page, final OnSearchCallback callback);
    protected abstract void getCasts(int id, final OnCastCallback callback);
}