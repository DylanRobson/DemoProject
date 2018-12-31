package com.example.dylan.demoproject.Model;

import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("userId")
    private int mUserId;

    @SerializedName("id")
    private int mAlbumId;

    @SerializedName("title")
    private String mTitle;

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("mUserId: " + mUserId + '\n');
        sBuilder.append("mAlbumId: " + mAlbumId + '\n');
        sBuilder.append("mTitle: " + mTitle + '\n');

        return sBuilder.toString();
    }
}
