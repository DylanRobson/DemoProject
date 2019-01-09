package com.example.dylan.demoproject.View.ViewHolders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Utils.StartActivityUtils;

public class AlbumSelectionDetailViewHolder extends SelectionDetailViewHolder {
    public AlbumSelectionDetailViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    public void setDetail(@Nullable Object selectionDetail) {

        final Album infoAlbum = (Album) selectionDetail;
        mHeaderTextView.setText(mHeaderTextView.getText() + "(Album)\n");
        mLinkTextView.setText("User ID: " + infoAlbum.getUserId() + "\t\t(View Profile)");
        mLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityUtils.startUserActivity(mContext, infoAlbum.getUserId());
            }
        });
        mBodyTextView.setText(infoAlbum.getDetailString());

        super.setDetail(selectionDetail);
    }

}
