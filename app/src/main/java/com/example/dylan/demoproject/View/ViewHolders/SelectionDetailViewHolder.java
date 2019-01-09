package com.example.dylan.demoproject.View.ViewHolders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dylan.demoproject.R;

/**
 * SelectionDetailViewHolder contains multiple TextViews for a row within the RecyclerView.
 * The multiple TextViews display the details (metadata) for the item selected in the previous Activity.
 *
 * e.g. for the PostActivity (where the user selected a Post Item in the previous Activity),
 * the SelectionDetailViewHolder will contain TextViews for Post's fields: userId, postId, postTitle, and postBody.
 * Then, from index 1 onwards PostActivity displays BaseViewHolders for the Post's Comments.
 */
public class SelectionDetailViewHolder extends RecyclerView.ViewHolder {
    // TODO: rename titleTextView?...
    protected TextView mHeaderTextView;
    // TODO: rename profileTextView?...avoid confusion with url link
    protected TextView mLinkTextView;
    protected TextView mBodyTextView;
    protected Context mContext;

    private ViewGroup mParentLayout;

    public SelectionDetailViewHolder(Context context, View view) {
        super(view);
        mHeaderTextView = view.findViewById(R.id.info_text_view);
        mLinkTextView = view.findViewById(R.id.info_link_text_view);
        mBodyTextView = view.findViewById(R.id.info_body_text_view);
        mContext = context;

        mParentLayout = (ViewGroup) view;
    }

    public void setDetail(@Nullable Object selectionDetail) {

        if (selectionDetail == null) {
            // <editor-fold defaultstate="collapsed" desc="Hide SelectionDetailViewHolder when selectionDetail null">
            /**
             * View.setVisibility(View.GONE) hides the SelectionDetailViewHolder, but whitespace remains in RecyclerView.
             * Remove the RecyclerView whitespace by setting the ViewHolder's parent layout height to 0.
             * @see: <a href="https://stackoverflow.com/a/46342024"/>
             */
            // </editor-fold>
            mParentLayout.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        }

        mHeaderTextView.setText("Selection Details\n");

        if (mLinkTextView.getText() == "") {
            // Hide empty link TextView
            mLinkTextView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }
    }

    // TODO: setUserProfileView_ForUserId(int userId)

}
