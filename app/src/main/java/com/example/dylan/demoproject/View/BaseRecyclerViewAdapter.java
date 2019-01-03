package com.example.dylan.demoproject.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dylan.demoproject.APIController;
import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.Model.User;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.StartActivityUtils;

import java.util.EnumSet;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * BaseViewHolder contains single TextView for a row within the RecyclerView.
     * The single TextView will display the toString of the contained Object.
     */
    public static class  BaseViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public BaseViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.base_text_view);
        }
    }

    /** TODO: InfoViewHolder (metadata) */
    /**
     * InfoViewHolder contains multiple TextViews for a row within the RecyclerView.
     * InfoViewHolder will only be shown as the 0-index row when mInfoViewVisible = true.
     * The multiple TextViews display the necessary metadata about the item selected in the previous Activity.
     *
     * e.g. for the DisplayPostActivity, the InfoViewHolder will contain TextViews for:
     * userId, postId, postTitle, and postBody.
     * Then, from index 1 onwards DisplayPostActivity displays BaseViewHolders with Comments.
     */
    public static class InfoViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeaderTextView;
        private TextView mLinkTextView;
        private TextView mBodyTextView;
        private Context mContext;

        public InfoViewHolder(Context context, View view) {
            super(view);
            mHeaderTextView = view.findViewById(R.id.info_text_view);
            mLinkTextView = view.findViewById(R.id.info_link_text_view);
            mBodyTextView = view.findViewById(R.id.info_body_text_view);
            mContext = context;
        }

        public void setContent(@Nullable Object infoContent) {

            if (infoContent == null) {
                // <editor-fold defaultstate="collapsed" desc="Hide InfoVH when infoContent null">
                /**
                 * View.setVisibility(View.GONE) hides the InfoVH, but whitespace remains in RecyclerView.
                 * Remove the RecyclerView whitespace by setting height 0.
                 * @see: <a href="https://stackoverflow.com/a/46342024"/>
                 */
                // </editor-fold>
                View v = (View) mHeaderTextView.getParent();
                v.setVisibility(View.GONE);
                v.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }

            mHeaderTextView.setText("* ** *** InfoViewHolder *** ** *\n");
            if (infoContent instanceof Post) {
                final Post infoPost = (Post) infoContent;
                mHeaderTextView.setText(mHeaderTextView.getText() + "(Post)\n");
                mLinkTextView.setText("User ID: " + infoPost.getUserId() + "\t\t(Link) -->");
                mLinkTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartActivityUtils.startDisplayUserActivity(mContext, infoPost.getUserId());
                    }
                });
                mBodyTextView.setText(infoPost.getInfoString());
            } else if (infoContent instanceof Album) {
                final Album infoAlbum = (Album) infoContent;
                mHeaderTextView.setText(mHeaderTextView.getText() + "(Album)\n");
                mBodyTextView.setText(infoAlbum.toString());
            } else if (infoContent instanceof User) {
                final User infoUser = (User) infoContent;
                mHeaderTextView.setText(mHeaderTextView.getText() + "(User)\n");
                // nothing for linkText?
                mBodyTextView.setText(infoUser.toString());
            }
        }
    }

    /** TODO: FilterViewHolder (RadioGroup) */
    /**
     * FilterViewHolder contains the RadioGroup to filter the Adapter's items.
     */
    public static class FilterViewHolder extends RecyclerView.ViewHolder {
        private TextView mFilterTextView;
        private RadioGroup mRadioGroup;  // TODO: remove?

        private RadioButton mPostsButton;
        private RadioButton mCommentsButton;
        private RadioButton mAlbumsButton;
        private RadioButton mUsersButton;

        private BaseRecyclerViewFragment mParentFragment;

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
            mRadioGroup.check(mParentFragment.getFilterCheckedId());
        }

        // TODO: setOptions(FilterOptions options, @Nullable Object filterItem) ... so if filterItem instanceOf User, then onCheckChange can call listPostForUser(user.getId()).
        public void setOptions(@Nullable EnumSet<FilterOptions> options, @Nullable Object infoItem) {

            // <editor-fold defaultstate="collapsed" desc="Set Buttons Visibility specified by FilterOptions">
            if (options == null) {
                options = EnumSet.of(FilterOptions.NONE);
            }

            if (options.contains(FilterOptions.NONE)) {
                // TODO: Remove? - why hide Buttons if Parent hidden.
                setAllButtonsVisibility(View.GONE);
                // <editor-fold defaultstate="collapsed" desc="Hide FilterVH when FilterOptions.NONE">
                /**
                 * View.setVisibility(View.GONE) hides the FilterVH, but whitespace remains in RecyclerView.
                 * Remove the RecyclerView whitespace by setting height 0.
                 * @see: <a href="https://stackoverflow.com/a/46342024"/>
                 */
                // </editor-fold>
                View v = (View) mFilterTextView.getParent();
                v.setVisibility(View.GONE);
                v.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
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
            // </editor-fold>

            mFilterTextView.setText("* ** *** FilterViewHolder *** ** *\n" + options.toString());

            // <editor-fold defaultstate="collapsed" desc="Set RadioGroup.OnCheckedChangeListener">
            if (infoItem instanceof User) {
                final User infoUser = (User) infoItem;
                final int userId = infoUser.getUserId();
                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        mParentFragment.setFilterCheckedId(checkedId);
                        switch (checkedId) {
                            case R.id.filter_posts_radio_button:
                                mParentFragment.updateListView(APIController.getApiInstance().listPostsForUser(userId));
                                break;
                            case R.id.filter_comments_radio_button:
                                mParentFragment.updateListView(APIController.getApiInstance().listCommentsForUser(userId));
                                break;
                            case R.id.filter_albums_radio_button:
                                mParentFragment.updateListView(APIController.getApiInstance().listAlbumsForUser(userId));
                                break;
                        }
                    }
                });
            } else {
                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        mParentFragment.setFilterCheckedId(checkedId);
                        switch (checkedId) {
                            case R.id.filter_posts_radio_button:
                                mParentFragment.updateListView(APIController.getApiInstance().listPosts());
                                break;
                            case R.id.filter_comments_radio_button:
                                mParentFragment.updateListView(APIController.getApiInstance().listComments());
                                break;
                            case R.id.filter_albums_radio_button:
                                mParentFragment.updateListView(APIController.getApiInstance().listAlbums());
                                break;
                            case R.id.filter_users_radio_button:
                                mParentFragment.updateListView(APIController.getApiInstance().listUsers());
                        }
                    }
                });
            }
            // </editor-fold>
        }

        private void setAllButtonsVisibility(int visibility) {
            mPostsButton.setVisibility(visibility);
            mCommentsButton.setVisibility(visibility);
            mAlbumsButton.setVisibility(visibility);
            mUsersButton.setVisibility(visibility);
        }

    }


    protected static final int INFO_HOLDER_VIEW_TYPE = 0;
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
                return INFO_HOLDER_VIEW_TYPE;
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
            case INFO_HOLDER_VIEW_TYPE:
                layout = layoutInflater.inflate(R.layout.info_view_holder, parent, false);
                return new InfoViewHolder(context, layout);
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
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case INFO_HOLDER_VIEW_TYPE:
                // Setup InfoViewHolder
                InfoViewHolder infoViewHolder = (InfoViewHolder) viewHolder;
                infoViewHolder.setContent(mAdapterItems[position]);
                break;
            case FILTER_HOLDER_VIEW_TYPE:
                // Setup FilterViewHolder
                FilterViewHolder filterViewHolder = (FilterViewHolder) viewHolder;
                EnumSet<FilterOptions> filterOptions = (EnumSet<FilterOptions>) mAdapterItems[position];
                filterViewHolder.setOptions(filterOptions, mAdapterItems[position - 1]);
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
