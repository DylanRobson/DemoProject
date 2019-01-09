package com.example.dylan.demoproject.Model;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("albumId")
    private int mAlbumId;

    @SerializedName("id")
    private int mPhotoId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("thumbnailUrl")
    private String mThumbnailUrl;

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Album ID: " + mAlbumId + '\n');
        sBuilder.append("Photo ID: " + mPhotoId + '\n');
        sBuilder.append("Photo Title: " + mTitle + '\n');
        sBuilder.append("URL: " + mUrl + '\n');
        sBuilder.append("Thumbnail URL: " + mThumbnailUrl + '\n');

        return sBuilder.toString();
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }
}
