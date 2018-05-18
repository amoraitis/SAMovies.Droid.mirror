package com.imdb.sa.Model;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SAMoviesAPIService {
    public static final String BASE_URL = "http://samoviesapi20180427042140.azurewebsites.net/api/";

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);

    @GET("users/login")
    Call<User> getUserByUserName(@Query("username") String username, @Query("password") String password);

    @GET("users/exist/{username}")
    Call<User> userExist(@Path("username") String username);

    @Headers("Content-Type: application/json")
    @POST("users")
    Call<User> createUser(@Body String user);

    @Headers("Content-Type: application/json")
    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body String user);

    @DELETE("users/{id)")
    Call<User> deleteUser(@Path("id") int id);

    /**
     * Movie consumer
     */
    @GET("movies/sorted")
    Call<Movie> getSortedMovies();

    @GET("movies/{id}")
    Call<Movie> getMovie(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @PUT("movies/{id}")
    Call<Movie> updateMovie(@Path("id") int id, @Body String Movie);

    @Headers("Content-Type: application/json")
    @POST("movies")
    Call<Movie> createMovie(@Body String Movie);

    @DELETE("movies/{id}")
    Call<Movie> deleteMovie(@Path("id") int id);
}
