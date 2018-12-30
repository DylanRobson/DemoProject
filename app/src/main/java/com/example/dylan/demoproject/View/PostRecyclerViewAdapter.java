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

    public static class PostViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder {
        public PostViewHolder(View view) {
            super(view);
        }

//        /**
//         * Called when a view has been clicked.
//         *
//         * @param v The view that was clicked.
//         */
//        @Override
//        public void onClick(View v) {
//            RecyclerView rv = (RecyclerView) v;
//        }
    }

    public PostRecyclerViewAdapter(Object[] objects) {//Post[] posts) {
        super(objects);
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public PostRecyclerViewAdapter.PostViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_view_holder, parent, false);
        final Context context = parent.getContext();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView rv = (RecyclerView) parent;
                int itemPosition = rv.getChildLayoutPosition(v);
                Post post = (Post) PostRecyclerViewAdapter.super.getObjects()[itemPosition];
                StartActivityUtils.startDisplayPostActivity(context, post);
            }
        });

        return new PostRecyclerViewAdapter.PostViewHolder(layout);
    }
}
