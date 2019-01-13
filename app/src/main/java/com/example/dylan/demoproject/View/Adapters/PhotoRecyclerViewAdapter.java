package com.example.dylan.demoproject.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Model.Photo;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.Utils.StartActivityUtils;
import com.example.dylan.demoproject.View.ViewHolders.AlbumSelectionDetailViewHolder;
import com.example.dylan.demoproject.View.ViewHolders.PhotoViewHolder;
import com.squareup.picasso.Picasso;

public class PhotoRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    public PhotoRecyclerViewAdapter(Object[] objects) {//Post[] posts) {
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
                return new AlbumSelectionDetailViewHolder(context, layout);
            case FILTER_HOLDER_VIEW_TYPE:
                return super.onCreateViewHolder(parent, viewType);
            default:
                layout = layoutInflater.inflate(R.layout.photo_view_holder, parent, false);

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecyclerView rv = (RecyclerView) parent;
                        int itemPosition = rv.getChildLayoutPosition(v);
                        Photo photo = (Photo) PhotoRecyclerViewAdapter.super.getItems()[itemPosition];
                        String photoUrl = photo.getUrl();
                        StartActivityUtils.startPhotoActivity(context, photoUrl);
                    }
                });

                return new PhotoViewHolder(layout);
        }
    }

    /**
     * Replace the contents of a ViewHolder (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        boolean infoOrFilterViewType = viewHolder.getItemViewType() == SELECTION_DETAIL_HOLDER_VIEW_TYPE || viewHolder.getItemViewType() == FILTER_HOLDER_VIEW_TYPE;
        if (!infoOrFilterViewType) {
            // Setup remaining PhotoViewHolders
            Photo photo = (Photo) getItems()[position];
            String thumbnailUrl = photo.getThumbnailUrl();
            PhotoViewHolder photoViewHolder = (PhotoViewHolder) viewHolder;
            Picasso.get()
                    .load(thumbnailUrl)
                    .resize(100, 100)
                    .centerCrop()
                    .placeholder(R.drawable.thumbnail_placeholder)
                    .into(photoViewHolder.mThumbnailView);
        }
    }

}
