package com.example.dylan.demoproject;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public PostViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.post_text_view);
        }
    }

    private Post[] mPosts;

    public PostsAdapter(Post[] posts) {
        mPosts = posts;
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view_holder, parent, false);
        PostViewHolder postHolder = new PostViewHolder(view);

        return postHolder;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(PostViewHolder postViewHolder, int position) {
        TextView textView = postViewHolder.mTextView;
        textView.setText(mPosts[position].toString());
    }

    /**
     * Return the size of dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return mPosts.length;
    }

}
