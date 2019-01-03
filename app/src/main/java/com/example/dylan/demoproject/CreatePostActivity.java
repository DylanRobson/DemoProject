package com.example.dylan.demoproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dylan.demoproject.Model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity implements Callback<Post> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        setTitle("CreatePostActivity");

        // TODO: configureCreatePostButton();
        Button createPostButton = findViewById(R.id.create_post_button);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: input validation
                EditText userIdEditText = findViewById(R.id.user_id_edit_text);
                EditText postTitleEditText = findViewById(R.id.post_title_edit_text);
                EditText postBodyEditText = findViewById(R.id.post_body_edit_text);

                final String userIdText = userIdEditText.getText().toString();
                final String postTitleText = postTitleEditText.getText().toString();
                final String postBodyText = postBodyEditText.getText().toString();

                try {
                    final int userIdInt = Integer.parseInt(userIdText);

                    Post post = new Post(userIdInt, -1, postTitleText, postBodyText);

                    Call<Post> apiCall = APIController.getApiInstance().createPost(post);
                    apiCall.enqueue(CreatePostActivity.this);
                } catch (NumberFormatException nfe) {
                    // TODO
                }
            }
        });
    }

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<Post> call, Response<Post> response) {
        if (response.isSuccessful()) {
            Post post = response.body();
            StartActivityUtils.startDisplayPostActivity(this, post);
            finish();
        } else {
            // TODO:
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<Post> call, Throwable t) {
        // TODO:
    }
}
