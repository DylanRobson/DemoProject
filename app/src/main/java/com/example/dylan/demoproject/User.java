package com.example.dylan.demoproject;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int mUserId;

    @SerializedName("name")
    private String mName;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("email")
    private String mEmail;

    // TODO: object deserialization
    //private Address mAddress;

    @SerializedName("phone")
    private String mPhoneNumber;

    @SerializedName("website")
    private String mWebsite;

    // TODO: object deserialization
    // private Company mCompany;

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("mUserId: " + mUserId + '\n');
        sBuilder.append("mName: " + mName + '\n');
        sBuilder.append("mUsername: " + mUsername + '\n');
        sBuilder.append("mEmail: " + mEmail + '\n');
        sBuilder.append("mPhoneNumber: " + mPhoneNumber + '\n');
        sBuilder.append("mWebsite: " + mWebsite + '\n');

        return sBuilder.toString();
    }
}
