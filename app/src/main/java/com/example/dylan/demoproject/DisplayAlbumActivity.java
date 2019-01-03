package com.example.dylan.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

public class DisplayAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_album);
        setTitle("DisplayAlbumActivity");

        Intent intent = getIntent();
        final int userId = intent.getIntExtra(getString(R.string.EXTRA_USER_ID), -1);
        final int albumId = intent.getIntExtra(getString(R.string.EXTRA_ALBUM_ID), -1);
        final String albumTitle = intent.getStringExtra(getString(R.string.EXTRA_ALBUM_TITLE));

        BaseRecyclerViewFragment baseRecyclerViewFragment = (BaseRecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        // Show Photos in the Album by default.
        baseRecyclerViewFragment.updateListView(APIController.getApiInstance().listPhotosForAlbum(albumId));
        baseRecyclerViewFragment.setInfoViewContent(new Album(userId, albumId, albumTitle));
    }

}
