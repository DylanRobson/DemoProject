package com.example.dylan.demoproject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

public class ListPostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ListPostsActivity");

        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_list_fragment);
        // Show all Posts by default
        baseRecyclerViewFragment.updateListView(APIController.getApiInstance().listPosts());

        configureToolbar();
        configureCreatePostFloatingButton();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        // seems after onCreate, also calls onResume, so updating listView twice.
//        // TODO: fix copypasta. this functionality should also be in BaseRVFrag with a RefreshFloatingActionButton.
//        // TODO: actually onResume seems useless, because the API doesn't actually modify the posts set, even though it accepts the POST request.
//        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_list_fragment);
//        // Show all Posts by default
//        baseRecyclerViewFragment.updateListView(APIController.getApiInstance().listPosts());
//    }

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

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Currently serves as refresh/fetch button
     * TODO: make create new Post button (HTTP POST request).
     */
    private void configureCreatePostFloatingButton() {
        final Context context = this;
        FloatingActionButton postFab = findViewById(R.id.create_post_floating_button);
        postFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                StartActivityUtils.startCreatePostActivity(context);
            }
        });
    }
}
