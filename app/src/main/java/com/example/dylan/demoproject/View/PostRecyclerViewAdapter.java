package com.example.dylan.demoproject.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.StartActivityUtils;

public class PostRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    public PostRecyclerViewAdapter(Object[] objects) {//Post[] posts) {
        super(objects);
    }

    /**
     * Create new ViewHolders (invoked by the layout manager)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        switch (viewType) {
            case SELECTION_DETAIL_HOLDER_VIEW_TYPE:
                return super.onCreateViewHolder(parent, viewType);
            case FILTER_HOLDER_VIEW_TYPE:
                return super.onCreateViewHolder(parent, viewType);
            default:
                final View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_view_holder, parent, false);
                final Context context = parent.getContext();

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecyclerView rv = (RecyclerView) parent;
                        int itemPosition = rv.getChildLayoutPosition(v);
                        Post post = (Post) PostRecyclerViewAdapter.super.getItems()[itemPosition];
                        StartActivityUtils.startDisplayPostActivity(context, post);
                    }
                });

                return new BaseViewHolder(layout);
        }
    }

}
