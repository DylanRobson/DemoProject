package com.example.dylan.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.Model.User;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

import java.util.EnumSet;

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

        mBaseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        // Show Posts by user by default. This relies on the assumption that the Posts radio button is default selection.
        mBaseRecyclerViewFragment.updateListView(APIController.getApiInstance().listPostsForUser(userId));
        EnumSet<FilterOptions> filterOptions = EnumSet.of(FilterOptions.POSTS, FilterOptions.COMMENTS, FilterOptions.ALBUMS);
        mBaseRecyclerViewFragment.setFilterOptions(filterOptions);
    }

    // TODO: or instead of Callback to fetch User for userId, should just pass in intent extras for user...
    // TODO: impl. Callback<User> in this, for cleaner style.
    private void updateUserView(int userId) {
        final Call<User> callUser = APIController.getApiInstance().getUser(userId);

        // posts.execute for sync.
        // OR posts.enqueue for async.

        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    mBaseRecyclerViewFragment.setInfoViewContent(user);
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
