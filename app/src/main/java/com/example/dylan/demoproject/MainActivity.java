package com.example.dylan.demoproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mPostsRecyclerView;
    private APIService mAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureToolbar();
        configurePostsRecyclerView();
        configureFloatingActionButton();
        // TODO: String Resource for baseUrl.
        configureAPI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Adds the List of Posts to textView, with each Post separated by new lines.
     * */
    private void updatePostsView(List<Post> posts) {
        for (Post post : posts) {
            //mTextView.append(post.toString());
        }
    }

    /**
     * Configure Retrofit and GSON deserialization.
     * */
    private void configureAPI() {
        // TODO: Fix hardcode base URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPI = retrofit.create(APIService.class);
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void configurePostsRecyclerView() {
        mPostsRecyclerView = findViewById(R.id.posts_recycler_view);
        //mPostsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mPostsRecyclerView.setLayoutManager(linearLayoutManager);

        // TODO: Fix empty arr, call listPosts in onCreate.
        PostsAdapter postsAdapter = new PostsAdapter(new Post[] { new Post() });
        mPostsRecyclerView.setAdapter(postsAdapter);

        // Add line dividers.
        mPostsRecyclerView.addItemDecoration(new DividerItemDecoration(mPostsRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        // TODO:
        //mPostsRecyclerView.setOnClickListener(); // to displayPost
    }

    /**
     * Currently serves as refresh/fetch button
     */
    private void configureFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // TODO: Call listItems and populate textView.
                final Call<List<Post>> posts = mAPI.listPosts();

                // posts.execute for sync.
                // OR posts.enqueue for async.

                //Response<List<Post>> responsePosts = posts.execute();
                posts.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (response.isSuccessful()) {
                            List<Post> postsList = response.body();
                            //updatePostsView(postsList);
                            PostsAdapter postsAdapter = new PostsAdapter(postsList.toArray(new Post[0]));
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
        });
    }

    /**
     * Starts DisplayPostActivity.
     */
    private void displayPost() {

    }

}
