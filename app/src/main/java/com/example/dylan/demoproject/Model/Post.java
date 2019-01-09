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
        sBuilder.append("User ID: " + mUserId + '\n');
        sBuilder.append(getDetailString());

        return sBuilder.toString();
    }

    /**
     * Same as toString except excludes mUserId, so that SelectionDetailViewHolder can display User ID in separate TextView.
     */
    public String getDetailString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Post ID: " + mPostId + '\n');
        sBuilder.append("Post Title: " + mTitle + '\n');
        sBuilder.append("Post Body: " + mBody + '\n');

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

}
