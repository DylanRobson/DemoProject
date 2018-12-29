package com.example.dylan.demoproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** TODO: RENAME PostRecyclerViewFragment */
public class RecyclerViewFragment extends Fragment
{

    private RecyclerView mPostsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mPostsRecyclerView = getActivity().findViewById(R.id.posts_recycler_view);
        //mPostsRecyclerView = findViewById(R.id.posts_recycler_view);
        mPostsRecyclerView = new RecyclerView(getActivity());
        mPostsRecyclerView.setVerticalScrollBarEnabled(true);
//        mPostsRecyclerView.setMinimumHeight(0);
        //mPostsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mPostsRecyclerView.setLayoutManager(linearLayoutManager);

        // Add line dividers.
        mPostsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        // TODO: temp adapter for internet outtage.
        Post[] postArr = new Post[10];
        for (int i = 0; i < 10; i++) {
            postArr[i] = new Post();
        }
        PostsAdapter postsAdapter = new PostsAdapter(postArr);
        postsAdapter.setContext(getActivity());
        mPostsRecyclerView.setAdapter(postsAdapter);

        updatePostsView();

        return mPostsRecyclerView;
    }

    /**
     * Makes HTTP Get request to the Posts endpoint using Retrofit/GSON.<br>
     * Then updates the RecyclerView with the deserialized Posts.
     */
    public void updatePostsView() {
        final Context context = getActivity();
        final Call<List<Post>> callPosts = APIController.getApiInstance().listPosts();

        // posts.execute for sync.
        // OR posts.enqueue for async.

        callPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    Post[] postArr = response.body().toArray(new Post[0]);
                    PostsAdapter postsAdapter = new PostsAdapter(postArr);
                    postsAdapter.setContext(context);
                    mPostsRecyclerView.setAdapter(postsAdapter);
                } else {
                    // TODO:
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // TODO:
            }
        });
    }
}
