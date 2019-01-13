package com.example.dylan.demoproject.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    //region List GET requests...
    @GET("posts")
    Call<List<Post>> listPosts();

    @GET("comments")
    Call<List<Comment>> listComments();

    @GET("users")
    Call<List<User>> listUsers();

    @GET("albums")
    Call<List<Album>> listAlbums();

    //region List GET requests for queried Album id...
    @GET("photos")
    Call<List<Photo>> listPhotosForAlbum(@Query("albumId") int albumId);
    //endregion

    //region List GET requests for queried Post id...
    @GET("comments")
    Call<List<Comment>> listCommentsForPost(@Query("postId") int postId);
    //endregion

    //region List GET requests for queried User id...
    // /posts?userId=1
    @GET("posts")
    Call<List<Post>> listPostsForUser(@Query("userId") int userId);

    // /comments?userId=1
    @GET("comments")
    Call<List<Comment>> listCommentsForUser(@Query("userId") int userId);

    // /albums?userId=1
    @GET("albums")
    Call<List<Album>> listAlbumsForUser(@Query("userId") int userId);

    //endregion
    //endregion

    //region Single GET requests...
    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);
    // endregion

    //region POST requests...
    @POST("posts")
    Call<Post> createPost(@Body Post post);
    //endregion
}
