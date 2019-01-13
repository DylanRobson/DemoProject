package com.example.dylan.demoproject.View.ViewHolders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dylan.demoproject.Controller.BaseRecyclerController;
import com.example.dylan.demoproject.Model.API;
import com.example.dylan.demoproject.Model.APIService;
import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.View.BaseRecyclerViewFragment;

import java.util.EnumSet;

/**
 * FilterViewHolder contains the RadioGroup to filter the Adapter's items.
 */
public class FilterViewHolder extends RecyclerView.ViewHolder {
    protected BaseRecyclerViewFragment mParentFragment;
    protected RadioGroup mRadioGroup;

    private RadioButton mPostsButton;
    private RadioButton mCommentsButton;
    private RadioButton mAlbumsButton;
    private RadioButton mUsersButton;

    private TextView mFilterTextView;
    private ViewGroup mParentLayout;

    public FilterViewHolder(Context context, View view) {
        super(view);
        mFilterTextView = view.findViewById(R.id.filter_text_view);
        mRadioGroup = view.findViewById(R.id.filter_radio_group);

        mPostsButton = mRadioGroup.findViewById(R.id.filter_posts_radio_button);
        mCommentsButton = mRadioGroup.findViewById(R.id.filter_comments_radio_button);
        mAlbumsButton = mRadioGroup.findViewById(R.id.filter_albums_radio_button);
        mUsersButton = mRadioGroup.findViewById(R.id.filter_users_radio_button);
        setAllButtonsVisibility(View.GONE);

        FragmentActivity parentActivity = (FragmentActivity) context;
        mParentFragment = (BaseRecyclerViewFragment) parentActivity.getSupportFragmentManager().findFragmentById(R.id.base_recycler_view_fragment);
        mRadioGroup.check(mParentFragment.getBaseRecyclerController().getFilterCheckedId());

        mParentLayout = (ViewGroup) view;
    }

    public void setOptions(@Nullable EnumSet<FilterOptions> options, @Nullable Object detailItem) {

        setButtonsVisibilityForOptions(options);

        mFilterTextView.setText("Filter By");

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final BaseRecyclerController baseRecyclerController = mParentFragment.getBaseRecyclerController();
                baseRecyclerController.setFilterCheckedId(checkedId);
                final APIService apiService = API.getInstance();

                switch (checkedId) {
                    case R.id.filter_posts_radio_button:
                        baseRecyclerController.beginCall(apiService.listPosts());
                        break;
                    case R.id.filter_comments_radio_button:
                        baseRecyclerController.beginCall(apiService.listComments());
                        break;
                    case R.id.filter_albums_radio_button:
                        baseRecyclerController.beginCall(apiService.listAlbums());
                        break;
                    case R.id.filter_users_radio_button:
                        baseRecyclerController.beginCall(apiService.listUsers());
                        break;
                }
            }
        });
    }

    private void setButtonsVisibilityForOptions(@Nullable EnumSet<FilterOptions> options) {

        if (options == null) {
            options = EnumSet.of(FilterOptions.NONE);
        }

        if (options.contains(FilterOptions.NONE)) {
            // <editor-fold defaultstate="collapsed" desc="Hide FilterViewHolder when FilterOptions.NONE">
            /**
             * View.setVisibility(View.GONE) hides the FilterVH, but whitespace remains in RecyclerView.
             * Remove the RecyclerView whitespace by setting height 0.
             * @see: <a href="https://stackoverflow.com/a/46342024"/>
             */
            // </editor-fold>
            mParentLayout.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        } else if (options.contains(FilterOptions.ALL)) {
            setAllButtonsVisibility(View.VISIBLE);
        } else {

            if (options.contains(FilterOptions.POSTS)) {
                mPostsButton.setVisibility(View.VISIBLE);
            }

            if (options.contains(FilterOptions.COMMENTS)) {
                mCommentsButton.setVisibility(View.VISIBLE);
            }

            if (options.contains(FilterOptions.ALBUMS)) {
                mAlbumsButton.setVisibility(View.VISIBLE);
            }

            if (options.contains(FilterOptions.USERS)) {
                mUsersButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setAllButtonsVisibility(int visibility) {
        mPostsButton.setVisibility(visibility);
        mCommentsButton.setVisibility(visibility);
        mAlbumsButton.setVisibility(visibility);
        mUsersButton.setVisibility(visibility);
    }

}
