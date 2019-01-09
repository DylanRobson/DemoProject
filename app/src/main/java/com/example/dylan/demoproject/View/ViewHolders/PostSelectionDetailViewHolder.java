package com.example.dylan.demoproject.View.ViewHolders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.Utils.StartActivityUtils;

public class PostSelectionDetailViewHolder extends SelectionDetailViewHolder {
    public PostSelectionDetailViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    public void setDetail(@Nullable Object selectionDetail) {

        if (selectionDetail != null) {
            final Post infoPost = (Post) selectionDetail;
            mHeaderTextView.setText(mHeaderTextView.getText() + "(Post)\n");
            mLinkTextView.setText("User ID: " + infoPost.getUserId() + "\t\t(View Profile)");
            mLinkTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartActivityUtils.startUserActivity(mContext, infoPost.getUserId());
                }
            });
            mBodyTextView.setText(infoPost.getDetailString());
        }

        super.setDetail(selectionDetail);
    }

}
