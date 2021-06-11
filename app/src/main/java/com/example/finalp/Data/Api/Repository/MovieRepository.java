package com.example.finalp.Data.Api.Repository;

import androidx.annotation.NonNull;

import com.example.finalp.Const;
import com.example.finalp.Data.Api.Repository.Callbacks.OnCastCallback;
import com.example.finalp.Data.Api.Repository.Callbacks.OnMovieListCallback;
import com.example.finalp.Data.Api.Repository.Callbacks.OnSearchCallback;
import com.example.finalp.Data.Api.Repository.Utils.Repository;
import com.example.finalp.Data.Api.Service;
import com.example.finalp.Data.Models.CastResponse;
import com.example.finalp.Data.Models.ListMovieResponse;
import com.example.finalp.Data.Models.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository extends Repository<Movie>{
    private static MovieRepository repository;
    private final Service service;

    private MovieRepository(Service service) {
        this.service = service;
    }

    public static MovieRepository getRepository() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            repository = new MovieRepository(retrofit.create(Service.class));
        }
        return repository;
    }

    @Override
    public void getModel(String queryType, int page, final OnMovieListCallback callback) {
        service.getResults(queryType, Const.API_KEY, page).enqueue(new Callback<ListMovieResponse>() {
            @Override
            public void onResponse(Call<ListMovieResponse> call, Response<ListMovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            callback.onSuccess(response.body().getResults(), response.body().getPage(), response.message());
                        } else {
                            callback.onFailure("response.body().getResults() is null");
                        }
                    } else {
                        callback.onFailure("response.body() is null");
                    }
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ListMovieResponse> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void getModelDetail(int id, OnMovieListCallback callback) {
        service.getDetails(id, Const.API_KEY, Const.getLang()).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body(), response.message());
                    } else {
                        callback.onFailure("response.body() is null");
                    }
                } else {
                    callback.onFailure(response.message() + ", Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void search(String query, int page, OnSearchCallback callback) {
        service.search(query, Const.API_KEY, page).enqueue(new Callback<ListMovieResponse>() {
            @Override
            public void onResponse(Call<ListMovieResponse> call, Response<ListMovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            callback.onSuccess(response.body().getResults(), response.body().getPage(), response.message());
                        } else {
                            callback.onFailure("No results");
                        }
                    } else {
                        callback.onFailure("response.body() is null");
                    }
                } else {
                    callback.onFailure(response.message() + ", Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListMovieResponse> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void getCasts(int id, OnCastCallback callback) {
        service.getCasts(id, Const.API_KEY, Const.getLang()).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body() != null) {
                            callback.onSuccess(response.body().getCastList(), response.message());
                        } else {
                            callback.onFailure("No Casts");
                        }
                    } else {
                        callback.onFailure("response.body() is null");
                    }
                } else {
                    callback.onFailure(response.message() + " : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
