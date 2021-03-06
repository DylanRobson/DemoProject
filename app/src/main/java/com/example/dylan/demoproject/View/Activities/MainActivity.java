package com.example.dylan.demoproject.View.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dylan.demoproject.Model.API;
import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.Utils.StartActivityUtils;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

import java.util.EnumSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MainActivity");

        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        EnumSet<FilterOptions> filterOptions = EnumSet.of(FilterOptions.POSTS, FilterOptions.COMMENTS, FilterOptions.ALBUMS, FilterOptions.USERS);
        baseRecyclerViewFragment.getBaseRecyclerController().setFilterOptionsSet(filterOptions);
        // Show all Posts by default
        baseRecyclerViewFragment.getBaseRecyclerController().beginCall(API.getInstance().listPosts());

        configureToolbar();
        configureCreatePostFloatingButton();
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
        if (id == R.id.action_about) {
            StartActivityUtils.startAboutActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void configureCreatePostFloatingButton() {
        final Context context = this;
        FloatingActionButton postFab = findViewById(R.id.create_post_floating_button);
        postFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivityUtils.startCreatePostActivity(context);
            }
        });
    }
}
