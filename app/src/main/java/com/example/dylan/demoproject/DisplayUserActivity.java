package com.example.dylan.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayUserActivity extends AppCompatActivity {

    //RecyclerViewFragment listPostsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);
        setTitle("DisplayUserActivity");

        RadioGroup filterUserContentRadioGroup = findViewById(R.id.filter_user_content_radio_group);
        filterUserContentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO: Change the RecyclerViewFragment being shown: either Posts, Comments, or Albums RecyclerViewFragment.
                switch (checkedId) {
                    case R.id.filter_posts_radio_button:
                        // TODO:
                        break;
                    case R.id.filter_comments_radio_button:
                        break;
                    case R.id.filter_albums_radio_button:
                        break;
                    default:
                        break;
                }
            }
        });

        Intent intent = getIntent();
        final int userId = intent.getIntExtra(getString(R.string.EXTRA_USER_ID), -1);

        updateUserView(userId);
    }

    // TODO: impl. Callback<User> in this, for cleaner style.
    private void updateUserView(int userId) {
        final TextView userTextView = findViewById(R.id.user_text_view);

        final Call<User> callUser = APIController.getApiInstance().getUser(userId);

        // posts.execute for sync.
        // OR posts.enqueue for async.

        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    userTextView.setText(user.toString());
                } else {
                    // TODO:
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // TODO:
            }
        });
    }
}
