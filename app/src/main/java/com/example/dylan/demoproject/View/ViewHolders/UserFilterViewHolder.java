package com.example.dylan.demoproject.View.ViewHolders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;

import com.example.dylan.demoproject.Controller.BaseRecyclerController;
import com.example.dylan.demoproject.Model.API;
import com.example.dylan.demoproject.Model.APIService;
import com.example.dylan.demoproject.Model.FilterOptions;
import com.example.dylan.demoproject.Model.User;
import com.example.dylan.demoproject.R;

import java.util.EnumSet;

public class UserFilterViewHolder extends FilterViewHolder {
    public UserFilterViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    public void setOptions(@Nullable EnumSet<FilterOptions> options, @Nullable Object detailItem) {
        super.setOptions(options, detailItem);

        if (detailItem != null) {
            final User detailUser = (User) detailItem;
            final int userId = detailUser.getUserId();
            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    final BaseRecyclerController baseRecyclerController = mParentFragment.getBaseRecyclerController();
                    baseRecyclerController.setFilterCheckedId(checkedId);
                    final APIService apiService = API.getInstance();

                    switch (checkedId) {
                        case R.id.filter_posts_radio_button:
                            baseRecyclerController.beginCall(apiService.listPostsForUser(userId));
                            break;
                        case R.id.filter_comments_radio_button:
                            baseRecyclerController.beginCall(apiService.listCommentsForUser(userId));
                            break;
                        case R.id.filter_albums_radio_button:
                            baseRecyclerController.beginCall(apiService.listAlbumsForUser(userId));
                            break;
                    }
                }
            });
        }
    }

}
