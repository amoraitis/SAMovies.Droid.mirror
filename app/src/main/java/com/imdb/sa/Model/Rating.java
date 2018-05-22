package com.imdb.sa.Model;

import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("userId")
    private int userId;
    @SerializedName("userFullname")
    private String userFullname;
    @SerializedName("rate")
    private int rate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
