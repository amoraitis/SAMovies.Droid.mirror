package com.imdb.sa.imdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;

import com.imdb.sa.Model.MovieApiService;
import com.imdb.sa.Model.MovieResponse;
import com.imdb.sa.adapters.MoviesAdapter;

import com.imdb.sa.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    private static final String TAG = Home.class.getSimpleName();

    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;

    // insert your themoviedb.org API KEY here

    private final static String API_KEY = "2b2fc7020dc3d25b0eb1a0a337c8daa3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        connectAndGetApiData(this);


        //SaveSharedPreference.deleteUserName(getApplicationContext();
    }

    private void connectAndGetApiData(final Home home) {
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();

                GridView gridView = (GridView)findViewById(R.id.gridview);
                MoviesAdapter moviesAdapter = new MoviesAdapter(home, movies);
                gridView.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
