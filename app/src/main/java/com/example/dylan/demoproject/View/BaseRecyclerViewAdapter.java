package com.example.dylan.demoproject.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dylan.demoproject.R;

// TODO: RENAME BaseRecyclerViewAdapter, android.widget.listView already exists.
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public BaseViewHolder(View view) {
            super(view); // TODO: Rfile.
            mTextView = view.findViewById(R.id.base_text_view);
        }
    }

    private Object[] mObjects;

    public BaseRecyclerViewAdapter() {
        this(new Object[0]);
    }

    public BaseRecyclerViewAdapter(Object[] objects) {
        mObjects = objects;
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_view_holder, parent, false);
        // TODO: generic viewHolder.onClickListener ? not possible? - bc in PostsAdapter, the onClick starts DisplayPostActivity.

        return new BaseViewHolder(layout);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.mTextView.setText(mObjects[position].toString());
    }

    /**
     * Return the size of the member data set (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return mObjects.length;
    }

    public Object[] getObjects() {
        return mObjects;
    }

}
