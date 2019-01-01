package com.example.dylan.demoproject.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Model.Photo;
import com.example.dylan.demoproject.Model.Post;
import com.example.dylan.demoproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseRecyclerViewFragment<E> extends Fragment implements Callback<List<E>> {

    private RecyclerView mBaseRecyclerView;
    private FloatingActionButton mRefreshFloatingButton;

    /** Stored so can refetch for refreshButton onClick */
    private Call<List<E>> mLastApiCall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: BaseAdapter, and setAdapter. - should base class have updateView method?...

        View layout = inflater.inflate(R.layout.base_recycler_view, container, false);

        mRefreshFloatingButton = layout.findViewById(R.id.refresh_floating_button);
        mRefreshFloatingButton.hide();
        mRefreshFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Have to clone Retrofit Call, otherwise get IllegalStateException: already executed.
                updateListView(mLastApiCall.clone());
            }
        });

        mBaseRecyclerView = layout.findViewById(R.id.base_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mBaseRecyclerView.setLayoutManager(linearLayoutManager);
        //mPostsRecyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mBaseRecyclerView.addItemDecoration(dividerDecoration);

        mBaseRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean scrollUp = dy < 0;
                if (scrollUp) {
                    mRefreshFloatingButton.show();
                } else {
                    mRefreshFloatingButton.hide();
                }
            }
        });

        return layout;
    }

    public void updateListView(Call<List<E>> apiCall) {
        mLastApiCall = apiCall;
        // Will set mBaseRecyclerView's adapter in onResponse.
        // TODO: Resetting adapter scrolls to top?
        apiCall.enqueue(this);
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
            Object[] objArr = response.body().toArray();

            BaseRecyclerViewAdapter updateAdapter;
            // TODO: this condition is false from ListPostsActivity call to updateListView. Despite all items in objArr being Post...
            // TODO: mBaseRecyclerView.getAdapter().mObjects is Post[] too...
            // TODO: How can I downcast Object[] to Post[] if instanceof fails? ... objArr[0] instanceof Post is horribly hacky.
//            if (objArr instanceof Post[]) {
//                Post[] postArr = (Post[]) objArr;
//                updateAdapter = new PostRecyclerViewAdapter(objArr);
//
//            } else

            // TODO: fix hacky workaround, got indexBoundsExcep. after CreatePostActiv.createPostButton clicked..
            // try to recreate issue.
            if(objArr.length == 0) {
                return;
            }

            if (objArr[0] instanceof Post) {
                updateAdapter = new PostRecyclerViewAdapter(objArr);
            } else if (objArr[0] instanceof Album) {
                updateAdapter = new AlbumRecyclerViewAdapter(objArr);
            } else if (objArr[0] instanceof Photo) {
                updateAdapter = new PhotoRecyclerViewAdapter(objArr);
            } else {
                updateAdapter = new BaseRecyclerViewAdapter(objArr);
            }
            mBaseRecyclerView.setAdapter(updateAdapter);
        } else {
            // TODO:log/snackbar dialog for error code/status, look into: .code, .errorBody, .message
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
        // TODO:
    }

    public void SetFilterViewVisible() {
        // TODO: refactor radio group into here,
        // TODO: this flag could indicate whether the RecyclerView's position 0 ViewHolder is replaced with the custom radio group to handle recyclerview adapter changes.
    }
}
