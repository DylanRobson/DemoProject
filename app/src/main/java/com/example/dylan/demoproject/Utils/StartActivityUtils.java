package com.example.dylan.demoproject.Utils;

import android.content.Context;
import android.content.Intent;

import com.example.dylan.demoproject.View.Activities.AlbumActivity;
import com.example.dylan.demoproject.View.Activities.CreatePostActivity;
import com.example.dylan.demoproject.View.Activities.PhotoActivity;
import com.example.dylan.demoproject.View.Activities.PostActivity;
import com.example.dylan.demoproject.View.Activities.UserActivity;
import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.View.Activities.AboutActivity;

public class StartActivityUtils {

    /**
     * Starts PostActivity.
     */
    public static void startPostActivity(Context context, Post post) {
        Intent intent = new Intent(context, PostActivity.class);

        intent.putExtra(context.getString(R.string.EXTRA_USER_ID), post.getUserId());
        intent.putExtra(context.getString(R.string.EXTRA_POST_ID), post.getPostId());
        intent.putExtra(context.getString(R.string.EXTRA_POST_TITLE), post.getTitle());
        intent.putExtra(context.getString(R.string.EXTRA_POST_BODY), post.getBody());

        context.startActivity(intent);
    }

    /**
     * Starts UserActivity.
     */
    public static void startUserActivity(Context context, int userId) {
        Intent intent = new Intent(context, UserActivity.class);

        intent.putExtra(context.getString(R.string.EXTRA_USER_ID), userId);

        context.startActivity(intent);
    }

    /**
     * Starts AlbumActivity.
     */
    public static void startAlbumActivity(Context context, Album album) {
        Intent intent = new Intent(context, AlbumActivity.class);

        intent.putExtra(context.getString(R.string.EXTRA_USER_ID), album.getUserId());
        intent.putExtra(context.getString(R.string.EXTRA_ALBUM_ID), album.getAlbumId());
        intent.putExtra(context.getString(R.string.EXTRA_ALBUM_TITLE), album.getTitle());

        context.startActivity(intent);
    }

    /**
     * Starts PhotoActivity.
     */
    public static void startPhotoActivity(Context context, String photoUrl) {
        Intent intent = new Intent(context, PhotoActivity.class);

        intent.putExtra(context.getString(R.string.EXTRA_PHOTO_URL), photoUrl);

        context.startActivity(intent);
    }

    /**
     * Starts CreatePostActivity.
     */
    public static void startCreatePostActivity(Context context) {
        Intent intent = new Intent(context, CreatePostActivity.class);

        context.startActivity(intent);
    }

    /**
     * Starts AboutActivity.
     */
    public static void startAboutActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);

        context.startActivity(intent);
    }

}
