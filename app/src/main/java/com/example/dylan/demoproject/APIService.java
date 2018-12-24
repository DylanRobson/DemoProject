package com.example.dylan.demoproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("posts")
    Call<List<Post>> listPosts();
}
