package com.example.dylan.demoproject.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.View.ViewHolders.PostSelectionDetailViewHolder;

public class CommentRecyclerViewAdapter extends BaseRecyclerViewAdapter {
    public CommentRecyclerViewAdapter(Object[] objects) {
        super(objects);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final Context context = parent.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);

        View layout;
        switch (viewType) {
            case SELECTION_DETAIL_HOLDER_VIEW_TYPE:
                layout = layoutInflater.inflate(R.layout.info_view_holder, parent, false);
                return new PostSelectionDetailViewHolder(context, layout);
            case FILTER_HOLDER_VIEW_TYPE:
                return super.onCreateViewHolder(parent, viewType);
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
    }
}
