package com.example.dylan.demoproject.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dylan.demoproject.R;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {

    /**
     * ViewHolder contains the views for each row within the RecyclerView.
     */
    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public BaseViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.base_text_view);
        }
    }

    private Object[] mObjects;
    // TODO:
    //private boolean mInfoViewVisible;
    //private boolean mFilterViewVisible;

    public BaseRecyclerViewAdapter() {
        this(new Object[0]);
    }

    public BaseRecyclerViewAdapter(Object[] objects) {
        mObjects = objects;
    }

    /**
     * Create new ViewHolders (invoked by the layout manager)
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_view_holder, parent, false);
        // TODO: We should create different ViewHolder based on index. We can get VH index here as shown in subclasses onCreateVH.
        // 0 = infoViewHolder.
        // 1 = filterViewHolder.
        // default = baseViewHolder.

        // TODO: generic viewHolder.onClickListener ? not possible? - bc in PostsAdapter, the onClick starts DisplayPostActivity.

        return new BaseViewHolder(layout);
    }

    /**
     * Replace the contents of a ViewHolder (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int position) {
        // TODO:
        switch (position) {
            case 0:
                // if (mInfoViewVisible) {}
                // Set infoView
                break;
            case 1:
                // if (mFilterViewVisible) {}
                // Set filterView
                break;
            default:
                // Set remaining as BaseViewHolders - just mTextView.setText
                break;
        }
        // TODO: if (position == 0) { baseViewHolder.mTextView.setVisible(false); baseViewHolder.mFilterRadioGroup.setVisible(true); }
        // TODO: Or separate RadioGroup ViewHolder class?... (Prob. better option, why have textView if unused.)

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
