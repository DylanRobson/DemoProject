package com.example.dylan.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DisplayPhotoActivity extends AppCompatActivity {

    // TODO: Share/download image features.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo);
        setTitle("DisplayPhotoActivity");

        ImageView imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String photoUrl = intent.getStringExtra(getString(R.string.EXTRA_PHOTO_URL));
        Picasso.get().load(photoUrl).into(imageView);
    }
}