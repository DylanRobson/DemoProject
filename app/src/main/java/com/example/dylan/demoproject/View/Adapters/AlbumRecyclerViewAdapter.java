package com.example.dylan.demoproject.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.Utils.StartActivityUtils;
import com.example.dylan.demoproject.View.ViewHolders.BaseViewHolder;
import com.example.dylan.demoproject.View.ViewHolders.UserFilterViewHolder;
import com.example.dylan.demoproject.View.ViewHolders.UserSelectionDetailViewHolder;

public class AlbumRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    public AlbumRecyclerViewAdapter(Object[] objects) {//Post[] posts) {
        super(objects);
    }

    /**
     * Create new ViewHolders (invoked by the layout manager)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final Context context = parent.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);

        View layout;
        switch (viewType) {
            case SELECTION_DETAIL_HOLDER_VIEW_TYPE:
                layout = layoutInflater.inflate(R.layout.selection_detail_view_holder, parent, false);
                return new UserSelectionDetailViewHolder(context, layout);
            case FILTER_HOLDER_VIEW_TYPE:
                layout = layoutInflater.inflate(R.layout.filter_view_holder, parent, false);
                return new UserFilterViewHolder(context, layout);
            default:
                layout = layoutInflater.inflate(R.layout.base_view_holder, parent, false);

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecyclerView rv = (RecyclerView) parent;
                        int itemPosition = rv.getChildLayoutPosition(v);
                        Album album = (Album) AlbumRecyclerViewAdapter.super.getItems()[itemPosition];
                        StartActivityUtils.startAlbumActivity(context, album);
                    }
                });

                return new BaseViewHolder(layout);
        }
    }

}
