package com.example.dylan.demoproject.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dylan.demoproject.Controller.BaseRecyclerController;
import com.example.dylan.demoproject.Model.API;
import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.Model.User;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

import java.util.EnumSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private BaseRecyclerController mBaseRecyclerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);
        setTitle("UserActivity");

        Intent intent = getIntent();
        final int userId = intent.getIntExtra(getString(R.string.EXTRA_USER_ID), -1);

        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        mBaseRecyclerController = baseRecyclerViewFragment.getBaseRecyclerController();

        configureRecyclerViewForUserSelectionDetail(userId);
    }

    // TODO: or instead of Callback to fetch User for userId, should just pass in intent extras for user...
    private void configureRecyclerViewForUserSelectionDetail(final int userId) {
        final Call<User> callUser = API.getInstance().getUser(userId);
        //final RetrofitController retrofitController = new RetrofitController<User>(mBaseRecyclerViewFragment.getView());

        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    mBaseRecyclerController.setSelectionDetail(user);

                    EnumSet<FilterOptions> filterOptions = EnumSet.of(FilterOptions.POSTS, FilterOptions.ALBUMS);
                    mBaseRecyclerController.setFilterOptionsSet(filterOptions);

                    // Don't invoke beginCall until SelectionDetail set, otherwise could show empty SelectionDetailViewHolder.
                    // Show Posts by user by default.
                    mBaseRecyclerController.beginCall(API.getInstance().listPostsForUser(userId));
                } else {
                    //retrofitController.handleUnsuccessfulResponse(call, response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //retrofitController.handleFailure(call, t);
            }
        });
    }

}
