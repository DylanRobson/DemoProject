package com.example.dylan.demoproject.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.View.ViewHolders.BaseViewHolder;
import com.example.dylan.demoproject.View.ViewHolders.FilterViewHolder;
import com.example.dylan.demoproject.View.ViewHolders.SelectionDetailViewHolder;

import java.util.EnumSet;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int SELECTION_DETAIL_HOLDER_VIEW_TYPE = 0;
    protected static final int FILTER_HOLDER_VIEW_TYPE = 1;
    protected static final int BASE_HOLDER_VIEW_TYPE = 2;

    private Object[] mAdapterItems;

    public BaseRecyclerViewAdapter(Object[] objects) {
        mAdapterItems = objects;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return SELECTION_DETAIL_HOLDER_VIEW_TYPE;
            case 1:
                return FILTER_HOLDER_VIEW_TYPE;
            default:
                return BASE_HOLDER_VIEW_TYPE;
        }
    }

    /**
     * Create new ViewHolders (invoked by the layout manager)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final Context context = parent.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);

        View layout;
        switch (viewType) {
            case SELECTION_DETAIL_HOLDER_VIEW_TYPE:
                layout = layoutInflater.inflate(R.layout.selection_detail_view_holder, parent, false);
                return new SelectionDetailViewHolder(context, layout);
            case FILTER_HOLDER_VIEW_TYPE:
                layout = layoutInflater.inflate(R.layout.filter_view_holder, parent, false);
                return new FilterViewHolder(context, layout);
            default:
                layout = layoutInflater.inflate(R.layout.base_view_holder, parent, false);
                return new BaseViewHolder(layout);
        }
    }

    /**
     * Replace the contents of a ViewHolder (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)  {

        // TODO: mAdapterItems[SELECTION_DETAIL_HOLDER_VIEW_TYPE] and mAdapterItems[FILTER_HOLDER_VIEW_TYPE]
        // TODO: Instead of mAdapterItems[position] and mAdapterItems[position - 1] ?
        switch (viewHolder.getItemViewType()) {
            case SELECTION_DETAIL_HOLDER_VIEW_TYPE:
                // Setup SelectionDetailViewHolder
                SelectionDetailViewHolder selectionDetailViewHolder = (SelectionDetailViewHolder) viewHolder;
                selectionDetailViewHolder.setDetail(mAdapterItems[position]);
                break;
            case FILTER_HOLDER_VIEW_TYPE:
                // Setup FilterViewHolder
                FilterViewHolder filterViewHolder = (FilterViewHolder) viewHolder;
                EnumSet<FilterOptions> filterOptions = (EnumSet<FilterOptions>) mAdapterItems[position];
                Object selectionDetail = mAdapterItems[position - 1];
                filterViewHolder.setOptions(filterOptions, selectionDetail);
                break;
            case BASE_HOLDER_VIEW_TYPE:
                // Setup remaining BaseViewHolders
                BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
                baseViewHolder.mTextView.setText(mAdapterItems[position].toString());
                break;
        }
    }

    /**
     * Return the size of the member data set (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return mAdapterItems.length;
    }

    public Object[] getItems() {
        return mAdapterItems;
    }

}
