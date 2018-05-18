package com.imdb.sa.Model;

import android.support.annotation.StringDef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {
    public static final String BASE_URL="http://api.themoviedb.org/3/";
    public static final String API_KEY="2b2fc7020dc3d25b0eb1a0a337c8daa3";
    public static final String lang="en-US";

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key")String apiKey);

    @GET("movie/{id}/similar")
    Call<MovieResponse> getSimilarMovies(@Path("id") int id, @Query("api_key") String apiKey
            , @Query("language") String lang);

    @GET("movie/{id}/videos")
    Call<VideosResponse> getVideos(@Path("id") int id, @Query("api_key") String apiKey
            , @Query("language") String lang);

    @GET("search/movie")
    Call<MovieResponse> searchMovies(@Query("api_key") String apiKey
            , @Query("language") String lang, @Query("query") String query, @Query("page") String page);
}
