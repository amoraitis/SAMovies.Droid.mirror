package com.imdb.sa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.*;
import com.imdb.sa.imdb.R;

import com.imdb.sa.Model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.squareup.picasso.Picasso.*;

public class MoviesAdapter extends BaseAdapter {
    private final Context mContext;
    private List<Movie> movies;
    public static final String IMAGE_URL_BASE_PATH= "https://image.tmdb.org/t/p/w200//";

    public MoviesAdapter(Context mContext, List<Movie> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = movies.get(position);
        if(convertView ==null){
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.homesingleview, null);
        }

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        final TextView textView = (TextView) convertView.findViewById(R.id.title);
        String image_url = (IMAGE_URL_BASE_PATH+ movies.get(position).getPoster_path());
        Glide.with(convertView).load(image_url).into(imageView);
        textView.setText(String.valueOf(movie.getTitle()));
        return convertView;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
