package com.imdb.sa.Model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("userId")
    private int userId;
    @SerializedName("userFullname")
    private String userFullname;
    @SerializedName("content")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
