package com.example.dylan.demoproject.Model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("postId")
    private int mPostId;

    @SerializedName("id")
    private int mCommentId;

    @SerializedName("name")
    private String mName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("body")
    private String mBody;

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Post ID: " + mPostId + '\n');
        sBuilder.append("Comment ID: " + mCommentId + '\n');
        sBuilder.append("Comment Name: " + mName + '\n');
        sBuilder.append("Commenter Email: " + mEmail + '\n');
        sBuilder.append("Comment Body: " + mBody + '\n');

        return sBuilder.toString();
    }
}
