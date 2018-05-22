package com.imdb.sa.Model;

import com.google.gson.annotations.SerializedName;

public class ExternalIds {
    public static final String IMDB_BASEURL = "https://www.imdb.com/title/";
    @SerializedName("imdb_id")
    private String imdb_id;

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    @Override
    public String toString(){
        return IMDB_BASEURL+this.getImdb_id();
    }
}
