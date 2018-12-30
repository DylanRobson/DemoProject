package com.example.dylan.demoproject;

import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    //region List requests...
    @GET("posts")
    Call<List<Post>> listPosts();


    // @GET("users")
    // Call<List<User>> listUsers();

    // TODO: /posts/{postId}/comments
//    @GET("posts/{postId}/comments")
//    Call<List<Comment>> listCommentsForPost(@Path("postId") int postId);

    //region List requests for queried User id...
    // TODO: /posts?userId=1
    @GET("posts")
    Call<List<Post>> listPostsForUser(@Query("userId") int userId);

    // TODO: /comments?userId=1
//    @GET("comments")
//    Call<List<Comment>> listCommentsForUser(@Query("userId") int userId) {}

    // TODO: /albums?userId=1
    // @GET("albums")
    // getAlbumsForUser() {}

    //endregion
    //endregion

    //region Single requests...
    // TODO: refactor to Call<List<User>> for BaseRecyclerViewFragment callback. ? - no bc this call is never made in recycler view.
    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);
    //endregion
}
