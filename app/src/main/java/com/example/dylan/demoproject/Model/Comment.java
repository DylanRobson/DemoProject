package com.example.dylan.demoproject.Model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("postId")
    private int mPostId;

    @SerializedName("id")
    private int mUserId;

    @SerializedName("name")
    private String mName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("body")
    private String mBody;

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("mPostId: " + mPostId + '\n');
        sBuilder.append("mUserId: " + mUserId + '\n');
        sBuilder.append("mName: " + mName + '\n');
        sBuilder.append("mEmail: " + mEmail + '\n');
        sBuilder.append("mBody: " + mBody + '\n');

        return sBuilder.toString();
    }
}
