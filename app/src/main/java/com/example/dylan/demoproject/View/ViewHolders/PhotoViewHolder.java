package com.example.dylan.demoproject.View.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import com.example.dylan.demoproject.R;

public class PhotoViewHolder extends BaseViewHolder {
    public ImageView mThumbnailView;
    public PhotoViewHolder(View view) {
        super(view);
        mThumbnailView = view.findViewById(R.id.thumbnail_view);
    }

}
