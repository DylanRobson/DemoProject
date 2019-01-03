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

    public Post(int userId, int postId, String title, String body) {
        mUserId = userId;
        mPostId = postId;
        mTitle = title;
        mBody = body;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("mUserId: " + mUserId + '\n');
        sBuilder.append(getInfoString());

        return sBuilder.toString();
    }

    /**
     * Same as toString except excludes mUserId, so that InfoViewHolder can display User ID in separate TextView.
     */
    public String getInfoString() {
        StringBuilder sBuilder = new StringBuilder();
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
