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
        sBuilder.append("mAlbumId: " + mAlbumId + '\n');
        sBuilder.append("mPhotoId: " + mPhotoId + '\n');
        sBuilder.append("mTitle: " + mTitle + '\n');
        sBuilder.append("mUrl: " + mUrl + '\n');
        sBuilder.append("mThumbnailUrl: " + mThumbnailUrl + '\n');

        return sBuilder.toString();
    }

    public String getUrl() {
        return mUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }
}
