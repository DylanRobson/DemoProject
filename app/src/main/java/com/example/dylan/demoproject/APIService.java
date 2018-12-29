package com.example.dylan.demoproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("posts")
    Call<List<Post>> listPosts();

    // @GET("users")
    // Call<List<User>> listUsers();

    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);
}
