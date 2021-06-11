package com.example.finalp.Data.Api;

import com.example.finalp.Data.Models.CastResponse;
import com.example.finalp.Data.Models.ListMovieResponse;
import com.example.finalp.Data.Models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/{query_type}")
    Call<ListMovieResponse> getResults(
            @Path("query_type") String queryType,
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Call<Movie> getDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("append_to_response") String appendToResponse
    );

    @GET("search/movie")
    Call<ListMovieResponse> search(
            @Query("query") String query,
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/{id}/credits")
    Call<CastResponse> getCasts(
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
