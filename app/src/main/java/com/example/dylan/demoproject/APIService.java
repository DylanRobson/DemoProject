package com.example.dylan.demoproject;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Model.Comment;
import com.example.dylan.demoproject.Model.Photo;
import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.Model.User;

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

    // TODO: The API doesn't work correctly for this route, it lists ALL comments, unassociated to postId. Using query postId works properly.
//    @GET("posts/{postId}/comments")
//    Call<List<Comment>> listCommentsForPost(@Path("postId") int postId);

    @GET("albums/{albumId}/photos")
    Call<List<Photo>> listPhotosForAlbum(@Path("albumId") int albumId);

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
    // TODO: refactor to Call<List<User>> for BaseRecyclerViewFragment callback. ? - no bc this call is never made in recycler view.
    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);

    // TODO: getPhoto ? - no, use Picasso.
    // unless we're using getPhoto to read the Photo json metadata.
    // endregion

    //region POST requests...
    @POST("posts")
    Call<Post> createPost(@Body Post post);
    //endregion
}
