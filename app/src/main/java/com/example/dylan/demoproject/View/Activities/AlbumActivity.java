package com.example.dylan.demoproject.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dylan.demoproject.Controller.BaseRecyclerController;
import com.example.dylan.demoproject.Model.API;
import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        setTitle("AlbumActivity");

        Intent intent = getIntent();
        final int userId = intent.getIntExtra(getString(R.string.EXTRA_USER_ID), -1);
        final int albumId = intent.getIntExtra(getString(R.string.EXTRA_ALBUM_ID), -1);
        final String albumTitle = intent.getStringExtra(getString(R.string.EXTRA_ALBUM_TITLE));

        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        BaseRecyclerController baseRecyclerController = baseRecyclerViewFragment.getBaseRecyclerController();

        baseRecyclerController.setSelectionDetail(new Album(userId, albumId, albumTitle));
        // Show Photos in the Album by default.
        baseRecyclerController.beginCall(API.getInstance().listPhotosForAlbum(albumId));
    }

}
