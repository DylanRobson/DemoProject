package com.example.dylan.demoproject.View.ViewHolders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;

import com.example.dylan.demoproject.Model.User;

public class UserSelectionDetailViewHolder extends SelectionDetailViewHolder {
    public UserSelectionDetailViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    public void setDetail(@Nullable Object selectionDetail) {

        if (selectionDetail != null) {
            final User infoUser = (User) selectionDetail;
            mHeaderTextView.setText(mHeaderTextView.getText() + "(User)\n");
            mBodyTextView.setLinksClickable(true);
            mBodyTextView.setMovementMethod(LinkMovementMethod.getInstance());
            mBodyTextView.setText(Html.fromHtml(infoUser.getHtmlString()));
        }

        super.setDetail(selectionDetail);
    }

}
