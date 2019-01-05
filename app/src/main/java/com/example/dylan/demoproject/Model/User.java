package com.example.dylan.demoproject.Model;

import com.example.dylan.demoproject.UriUtils;
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

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("User ID: " + mUserId + '\n');
        sBuilder.append("Full Name: " + mName + '\n');
        sBuilder.append("User Name: " + mUsername + '\n');
        sBuilder.append("User Email: " + mEmail + '\n');
        sBuilder.append("Phone Number: " + mPhoneNumber + '\n');
        sBuilder.append("Website: " + mWebsite + '\n');

        return sBuilder.toString();
    }

    public String getHtmlString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("<p>User ID: " + mUserId + "<br>");
        sBuilder.append("Full Name: " + mName + "<br>");
        sBuilder.append("User Name: " + mUsername + "<br><br>");

        sBuilder.append("User Email: <a href=\"" + UriUtils.getMailUri(mEmail) + "\">" + mEmail + "</a><br><br>");
        sBuilder.append("Phone Number: <a href=\"" + UriUtils.getTelephoneUri(mPhoneNumber) + "\">" + mPhoneNumber + "</a><br><br>");
        sBuilder.append("Website: <a href=\"" + UriUtils.getWebsiteUrl(mWebsite) + "\">" + mWebsite + "</a></p>");

        return sBuilder.toString();
    }

    public int getUserId() {
        return mUserId;
    }
}
