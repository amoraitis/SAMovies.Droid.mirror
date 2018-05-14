package com.imdb.sa.Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SAMoviesAPIService {
    @GET("users/")
    Call<User> getUser(@Query("id") int id);

    @POST("users/")
    Call<User> createUser(@Body() String user);
}
