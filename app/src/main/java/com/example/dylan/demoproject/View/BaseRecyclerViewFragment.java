package com.example.dylan.demoproject.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.demoproject.Controller.AdapterChangedListener;
import com.example.dylan.demoproject.Controller.BaseRecyclerController;
import com.example.dylan.demoproject.Controller.RetrofitErrorListener;
import com.example.dylan.demoproject.R;

import java.util.List;

import retrofit2.Call;

public class BaseRecyclerViewFragment extends Fragment implements AdapterChangedListener, RetrofitErrorListener {

    private RecyclerView mBaseRecyclerView;
    private BaseRecyclerController mBaseRecyclerController;
    private FloatingActionButton mRefreshFloatingButton;
    // TODO: ... to HTTP POST based on current Adapter selection.
    // private FloatingActionButton mCreateFloatingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.base_recycler_view, container, false);

        mBaseRecyclerController = new BaseRecyclerController();
        mBaseRecyclerController.setAdapterChangedListener(this);
        mBaseRecyclerController.setRetrofitErrorListener(this);


        mRefreshFloatingButton = layout.findViewById(R.id.refresh_floating_button);
        mRefreshFloatingButton.hide();
        mRefreshFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseRecyclerController.beginRefreshCall();
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

    @Override
    public void onAdapterChanged(BaseRecyclerViewAdapter baseRecyclerViewAdapter) {
        // Reset Adapter. "Scrolls" to top of the RecyclerView.
        mBaseRecyclerView.setAdapter(baseRecyclerViewAdapter);
    }

    @Override
    public void onRetrofitError(String errorMessage) {
        // Show Snackbar with errorMessage.
        Snackbar.make(this.getView(), errorMessage, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        // TODO: Log.
    }

    public BaseRecyclerController getBaseRecyclerController() {
        return mBaseRecyclerController;
    }

}
