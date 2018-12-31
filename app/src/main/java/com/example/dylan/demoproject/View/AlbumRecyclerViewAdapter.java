package com.example.dylan.demoproject.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.StartActivityUtils;

public class AlbumRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    public static class AlbumViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder {
        public AlbumViewHolder(View view) {
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

    public AlbumRecyclerViewAdapter(Object[] objects) {//Post[] posts) {
        super(objects);
    }

    /**
     * Create new ViewHolders (invoked by the layout manager)
     */
    @Override
    public AlbumRecyclerViewAdapter.AlbumViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_view_holder, parent, false);
        final Context context = parent.getContext();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView rv = (RecyclerView) parent;
                int itemPosition = rv.getChildLayoutPosition(v);
                Album album = (Album) AlbumRecyclerViewAdapter.super.getObjects()[itemPosition];
                int albumId = album.getAlbumId();
                StartActivityUtils.startDisplayAlbumActivity(context, albumId);
            }
        });

        return new AlbumRecyclerViewAdapter.AlbumViewHolder(layout);
    }
}
