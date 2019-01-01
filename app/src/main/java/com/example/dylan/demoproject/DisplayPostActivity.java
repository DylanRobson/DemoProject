package com.example.dylan.demoproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

public class DisplayPostActivity extends AppCompatActivity {

    private TextView mUserIdTextView;
    private TextView mPostIdTextView;
    private TextView mPostTitleTextView;
    private TextView mPostBodyTextView;
    // TODO: private Button mPostCommentButton; // To add new comment/get comments.

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

        // Configure TextViews
        mUserIdTextView = findViewById(R.id.user_id_text_view);
        mPostIdTextView = findViewById(R.id.post_id_text_view);
        mPostTitleTextView = findViewById(R.id.post_title_text_view);
        mPostBodyTextView = findViewById(R.id.post_body_text_view);

        mUserIdTextView.setText(String.valueOf(userId));
        mPostIdTextView.setText(String.valueOf(postId));
        mPostTitleTextView.setText(postTitle);
        mPostBodyTextView.setText(postBody);

        final Context context = this;
        mUserIdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityUtils.startDisplayUserActivity(context, userId);
            }
        });

        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        // Show Comments for the given Post
        baseRecyclerViewFragment.updateListView(APIController.getApiInstance().listCommentsForPost(postId));
    }

}
