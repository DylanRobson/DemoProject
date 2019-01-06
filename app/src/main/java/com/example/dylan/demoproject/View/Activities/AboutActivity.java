package com.example.dylan.demoproject.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dylan.demoproject.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("AboutActivity");

        // TODO: Attributions/Github links, license.
    }
}
