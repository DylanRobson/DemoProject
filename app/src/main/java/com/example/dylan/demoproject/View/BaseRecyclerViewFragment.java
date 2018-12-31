package com.example.dylan.demoproject.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Model.Album;
import com.example.dylan.demoproject.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseRecyclerViewFragment<E> extends Fragment implements Callback<List<E>> {

    private RecyclerView mBaseRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // container passed to onCreateView is parent viewgroup from activity's layout.
        // TODO: docs say inflate, is my way correct?
        //View debugMe = inflater.inflate(R.layout.fragment_base_list, container, false);

        // TODO: can we replace getActivity with "this"? getActivity is Fragment.
        mBaseRecyclerView = new RecyclerView(getActivity());
        mBaseRecyclerView.setVerticalScrollBarEnabled(true);
        //mPostsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mBaseRecyclerView.setLayoutManager(linearLayoutManager);

        // Add row line dividers
        mBaseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        // TODO: BaseAdapter, and setAdapter. - should base class have updateView method?...

        return mBaseRecyclerView;
    }

    public void updateListView(Call<List<E>> apiCall) {
        // Will set mBaseRecyclerView's adapter in onResponse.
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

            if (objArr[0] instanceof Post) {
                updateAdapter = new PostRecyclerViewAdapter(objArr);
            } else if (objArr[0] instanceof Album) {
                // TODO:
                updateAdapter = new AlbumRecyclerViewAdapter(objArr);
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
