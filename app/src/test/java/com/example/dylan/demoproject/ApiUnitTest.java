package com.example.dylan.demoproject;

import com.example.dylan.demoproject.Model.API;
import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Model.Comment;
import com.example.dylan.demoproject.Model.Photo;
import com.example.dylan.demoproject.Model.Post;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApiUnitTest {

    @Test
    public void testListPosts_CountIs100() throws IOException {
        Call<List<Post>> callListPost = API.getInstance().listPosts();

        List<Post> listPost = callListPost.execute().body();
        assertEquals(100, listPost.size());
    }

    @Test
    public void testListCommentsForPost_CommentPostId_MatchesPostId() throws IOException {
        final int postId = 1;
        Call<List<Comment>> callListComment = API.getInstance().listCommentsForPost(postId);

        List<Comment> listComment = callListComment.execute().body();
        assertEquals(5, listComment.size());

        for (Comment comment : listComment) {
            assertEquals(postId, comment.getPostId());
        }
    }

    //region Test list requests for queried User id...
    @Test
    public void testListAlbumsForUser_AlbumUserId_MatchesUserId() throws IOException {
        final int userId = 1;
        Call<List<Album>> callListAlbum = API.getInstance().listAlbumsForUser(userId);

        List<Album> listAlbum = callListAlbum.execute().body();
        assertEquals(10, listAlbum.size());

        for (Album album : listAlbum) {
            assertEquals(userId, album.getUserId());
        }
    }

    @Test
    public void testListPostsForUser_PostUserId_MatchesUserId() throws IOException {
        final int userId = 1;

        Call<List<Post>> callListPost = API.getInstance().listPostsForUser(userId);

        List<Post> listPost = callListPost.execute().body();
        assertEquals(10, listPost.size());

        for (Post post : listPost) {
            assertEquals(userId, post.getUserId());
        }
    }

    // TODO: public void testListCommentsForUser_CommentUserId_MatchesUserId()
    //endregion

    @Test
    public void testListPhotosForAlbum_PhotoAlbumId_MatchesAlbumId() throws IOException {
        final int albumId = 1;
        Call<List<Photo>> callListPhoto = API.getInstance().listPhotosForAlbum(albumId);

        List<Photo> listPhoto = callListPhoto.execute().body();
        assertEquals(50, listPhoto.size());
        for (Photo photo : listPhoto) {
            assertEquals(albumId, photo.getAlbumId());
        }
    }
}