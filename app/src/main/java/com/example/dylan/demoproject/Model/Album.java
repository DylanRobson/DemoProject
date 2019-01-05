package com.example.dylan.demoproject.Model;

import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("userId")
    private int mUserId;

    @SerializedName("id")
    private int mAlbumId;

    @SerializedName("title")
    private String mTitle;

    public Album(int userId, int albumId, String title) {
        mUserId = userId;
        mAlbumId = albumId;
        mTitle = title;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("User ID: " + mUserId + '\n');
        sBuilder.append("Album ID: " + mAlbumId + '\n');
        sBuilder.append("Album Title: " + mTitle + '\n');

        return sBuilder.toString();
    }

    public int getUserId() {
        return mUserId;
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public String getTitle() {
        return mTitle;
    }
}
