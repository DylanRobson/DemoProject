package com.example.dylan.demoproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

public class DisplayPostActivity extends AppCompatActivity {

    // TODO: private Button mPostCommentButton; // To POST new comment.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_post);
        setTitle("DisplayPostActivity");

        Intent intent = getIntent();
        final int userId = intent.getIntExtra(getString(R.string.EXTRA_USER_ID), -1);
        final int postId = intent.getIntExtra(getString(R.string.EXTRA_POST_ID), -1);
        final String postTitle = intent.getStringExtra(getString(R.string.EXTRA_POST_TITLE));
        final String postBody = intent.getStringExtra(getString(R.string.EXTRA_POST_BODY));

        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        // Show Comments for the given Post
        baseRecyclerViewFragment.updateListView(APIController.getApiInstance().listCommentsForPost(postId));
        baseRecyclerViewFragment.setInfoViewContent(new Post(userId, postId, postTitle, postBody));

    }

}
