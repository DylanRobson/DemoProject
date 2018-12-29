package com.example.dylan.demoproject;

import android.content.Context;
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

    private Context mContext;
    private Post[] mPosts;

    public PostsAdapter(Post[] posts) {
        mPosts = posts;
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public PostViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // rename viewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view_holder, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView rv = (RecyclerView) parent;
                int itemPosition = rv.getChildLayoutPosition(v);
                Post post = mPosts[itemPosition];
                // TODO: After refresh button, we don't set adapter context, so this start activity fails -> NPE.
                ListPostsActivity.startDisplayPostActivity(mContext, post);

            }
        });
        return new PostViewHolder(view);
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

    public void setContext(Context context) {
        mContext = context;
    }
}
