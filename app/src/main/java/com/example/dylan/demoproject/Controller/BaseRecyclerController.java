package com.example.dylan.demoproject.Controller;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Model.Comment;
import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.Model.Photo;
import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.Model.User;
import com.example.dylan.demoproject.R;
import com.example.dylan.demoproject.View.Adapters.AlbumRecyclerViewAdapter;
import com.example.dylan.demoproject.View.Adapters.BaseRecyclerViewAdapter;
import com.example.dylan.demoproject.View.Adapters.CommentRecyclerViewAdapter;
import com.example.dylan.demoproject.View.Adapters.PhotoRecyclerViewAdapter;
import com.example.dylan.demoproject.View.Adapters.PostRecyclerViewAdapter;
import com.example.dylan.demoproject.View.Adapters.UserRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * BaseRecyclerController handles making Retrofit Calls, and handles Retrofit Callbacks.
 * It configures the Adapter Items and the Adapter type to be used by the RecyclerView.
 * In addition to the API response items, header Adapter Items are added for: SelectionDetail Object and FilterOptions Set.
 * @see #addAdapterHeaderItems(List).
 *
 * BaseRecyclerController acts as the Sender for {@link AdapterChangedListener#onAdapterChanged(BaseRecyclerViewAdapter)} and {@link RetrofitErrorListener#onRetrofitError(String)}.
 */
public class BaseRecyclerController<E> implements Callback<List<E>> {

    /**
     * Stored so can refresh (HTTP GET) for refreshButton onClick
     */
    private Call<List<E>> mLastApiCall;
    /**
     * TODO: Doc.
     */
    private Object mSelectionDetail;
    /**
     * TODO: Doc.
     */
    private EnumSet<FilterOptions> mFilterOptionsSet;
    /**
     * Stored so can recheck the correct RadioButton after onAdapterChanged causes FilterViewHolder to be redrawn.
     */
    private int mFilterCheckedId;

    private AdapterChangedListener mAdapterChangedListener;
    private RetrofitErrorListener mRetrofitErrorListener;

    public BaseRecyclerController() {
        setFilterCheckedId(R.id.filter_posts_radio_button);
    }

    public void beginCall(Call<List<E>> apiCall) {
        mLastApiCall = apiCall;
        // Will trigger onAdapterChanged in onResponse.
        apiCall.enqueue(this);
    }

    public void beginRefreshCall() {
        // Have to clone Retrofit Call, otherwise get IllegalStateException: already executed.
        beginCall(mLastApiCall.clone());
    }

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<List<E>> call, Response<List<E>> response) {

        if (response.isSuccessful()) {
            // Trigger onAdapterChanged event.
            if (mAdapterChangedListener != null) {
                final Object[] adapterItems = addAdapterHeaderItems(new ArrayList<Object>(response.body()));
                // Offset = 1 for SelectionDetail header Item xor FilterOptions header Item.
                // Offset = 2 for SelectionDetail header Item and FilterOptions header Item.
                final int headerItemsOffset = adapterItems.length - response.body().size();

                final BaseRecyclerViewAdapter updateAdapter = selectAdapter(adapterItems, headerItemsOffset);
                mAdapterChangedListener.onAdapterChanged(updateAdapter);
            }
        } else {
            // Trigger onRetrofitError event.
            if (mRetrofitErrorListener != null) {
                String errorMessage = "Response Unsuccessful. HTTP Error Code: " + response.code();
                mRetrofitErrorListener.onRetrofitError(errorMessage);
            }
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<List<E>> call, Throwable t) {

        // Trigger onRetrofitError event.
        if (mRetrofitErrorListener != null) {
            String errorMessage = "Response Failure. Message: " + t.getLocalizedMessage();
            mRetrofitErrorListener.onRetrofitError(errorMessage);
        }
    }

    /**
     * Add Adapter header Items: SelectionDetailObject and FilterOptionsSet.
     * For {@link com.example.dylan.demoproject.View.ViewHolders.SelectionDetailViewHolder#setDetail(Object)}
     * And {@link com.example.dylan.demoproject.View.ViewHolders.FilterViewHolder#setOptions(EnumSet, Object)}
     */
    private Object[] addAdapterHeaderItems(List<Object> responseItems) {

        responseItems.add(0, mSelectionDetail);
        responseItems.add(1, mFilterOptionsSet);

        Object[] adapterItems = responseItems.toArray();
        return adapterItems;
    }

    private BaseRecyclerViewAdapter selectAdapter(Object[] adapterItems, int headerItemsOffset) {

        if (adapterItems.length == headerItemsOffset) {
            return new BaseRecyclerViewAdapter(adapterItems);
        }
        Object baseItem = adapterItems[headerItemsOffset];
        if (baseItem instanceof Post) {
            return new PostRecyclerViewAdapter(adapterItems);
        } else if (baseItem instanceof Album) {
            return new AlbumRecyclerViewAdapter(adapterItems);
        } else if (baseItem instanceof Photo) {
            return new PhotoRecyclerViewAdapter(adapterItems);
        } else if (baseItem instanceof User) {
           return new UserRecyclerViewAdapter(adapterItems);
        } else if (baseItem instanceof Comment) {
            return new CommentRecyclerViewAdapter(adapterItems);
        }

        return new BaseRecyclerViewAdapter(adapterItems);
    }



    public void setRetrofitErrorListener(RetrofitErrorListener retrofitErrorListener) {
        mRetrofitErrorListener = retrofitErrorListener;
    }

    public void setAdapterChangedListener(AdapterChangedListener adapterChangedListener) {
        mAdapterChangedListener = adapterChangedListener;
    }

    public void setSelectionDetail(Object selectionDetail) {
        mSelectionDetail = selectionDetail;
    }

    public void setFilterOptionsSet(EnumSet<FilterOptions> optionsSet) {
        mFilterOptionsSet = optionsSet;
    }

    public void setFilterCheckedId(int checkedId) {
        mFilterCheckedId = checkedId;
    }

    public int getFilterCheckedId() {
        return mFilterCheckedId;
    }

}
