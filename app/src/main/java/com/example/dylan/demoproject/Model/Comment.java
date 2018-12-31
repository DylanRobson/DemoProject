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

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("mPostId: " + mPostId + '\n');
        sBuilder.append("mCommentId: " + mCommentId + '\n');
        sBuilder.append("mName: " + mName + '\n');
        sBuilder.append("mEmail: " + mEmail + '\n');
        sBuilder.append("mBody: " + mBody + '\n');

        return sBuilder.toString();
    }
}
