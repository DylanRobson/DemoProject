package com.example.dylan.demoproject;

import android.content.Context;
import android.content.Intent;

import com.example.dylan.demoproject.Model.Post;

public class StartActivityUtils {

    /**
     * Starts DisplayPostActivity.
     */
    public static void startDisplayPostActivity(Context context, Post post) {
        Intent intent = new Intent(context, DisplayPostActivity.class);

        intent.putExtra(context.getString(R.string.EXTRA_USER_ID), post.getUserId());
        intent.putExtra(context.getString(R.string.EXTRA_POST_ID), post.getPostId());
        intent.putExtra(context.getString(R.string.EXTRA_POST_TITLE), post.getTitle());
        intent.putExtra(context.getString(R.string.EXTRA_POST_BODY), post.getBody());

        context.startActivity(intent);
    }

    /**
     * Starts DisplayUserActivity.
     */
    public static void startDisplayUserActivity(Context context, int userId) {
        Intent intent = new Intent(context, DisplayUserActivity.class);

        intent.putExtra(context.getString(R.string.EXTRA_USER_ID), userId);

        context.startActivity(intent);
    }

    // TODO: startDisplayAlbumActivity

    // TODO: startDisplayPhotoActivity
}
