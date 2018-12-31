package com.example.dylan.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dylan.demoproject.Model.User;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayUserActivity extends AppCompatActivity {

    BaseRecyclerViewFragment mBaseRecyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);
        setTitle("DisplayUserActivity");

        Intent intent = getIntent();
        final int userId = intent.getIntExtra(getString(R.string.EXTRA_USER_ID), -1);
        updateUserView(userId);

        mBaseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_list_fragment);
        // Show Posts by user by default. This relies on the assumption that the Posts radio button is default selection.
        mBaseRecyclerViewFragment.updateListView(APIController.getApiInstance().listPostsForUser(userId));


        RadioGroup filterUserContentRadioGroup = findViewById(R.id.filter_user_content_radio_group);
        filterUserContentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO: Change the RecyclerViewFragment being shown: either Posts, Comments, or Albums.
                switch (checkedId) {
                    case R.id.filter_posts_radio_button:
                        mBaseRecyclerViewFragment.updateListView(APIController.getApiInstance().listPostsForUser(userId));
                        break;
                    case R.id.filter_comments_radio_button:
                        mBaseRecyclerViewFragment.updateListView(APIController.getApiInstance().listCommentsForUser(userId));
                        break;
                    case R.id.filter_albums_radio_button:
                        mBaseRecyclerViewFragment.updateListView(APIController.getApiInstance().listAlbumsForUser(userId));
                        break;
                }
            }
        });

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
