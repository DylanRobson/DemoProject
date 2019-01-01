package com.example.dylan.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

public class DisplayAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_album);
        setTitle("DisplayAlbumActivity");

        Intent intent = getIntent();
        final int albumId = intent.getIntExtra(getString(R.string.EXTRA_ALBUM_ID), -1);

        // TODO: listPhotosForAlbum(albumId);
        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        // Show Photos in the Album by default.
        baseRecyclerViewFragment.updateListView(APIController.getApiInstance().listPhotosForAlbum(albumId));
    }
}
