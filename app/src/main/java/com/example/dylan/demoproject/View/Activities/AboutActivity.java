package com.example.dylan.demoproject.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.dylan.demoproject.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("AboutActivity");

        TextView aboutTextView = findViewById(R.id.about_text_view);

        aboutTextView.setLinksClickable(true);
        aboutTextView.setMovementMethod(LinkMovementMethod.getInstance());
        String aboutHtmlString = getString(R.string.about_html);
        aboutTextView.setText(Html.fromHtml(aboutHtmlString));

    }
}
