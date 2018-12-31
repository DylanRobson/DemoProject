package com.example.dylan.demoproject.Model;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("userId")
    private int mUserId;

    @SerializedName("id")
    private int mPostId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("body")
    private String mBody;

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("mUserId: " + mUserId + '\n');
        sBuilder.append("mPostId: " + mPostId + '\n');
        sBuilder.append("mTitle: " + mTitle + '\n');
        sBuilder.append("mBody: " + mBody + '\n');

        return sBuilder.toString();
    }

    public int getUserId() {
        return mUserId;
    }

    public int getPostId() {
        return mPostId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setBody(String body) {
        mBody = body;
    }
}
