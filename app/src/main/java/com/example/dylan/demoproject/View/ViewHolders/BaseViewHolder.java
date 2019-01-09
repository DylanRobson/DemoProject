package com.example.dylan.demoproject.View.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dylan.demoproject.R;

/**
 * BaseViewHolder contains a single TextView for a row within the RecyclerView.
 * The single TextView will display the toString of the contained Object.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public BaseViewHolder(View view) {
        super(view);
        mTextView = view.findViewById(R.id.base_text_view);
    }
}
