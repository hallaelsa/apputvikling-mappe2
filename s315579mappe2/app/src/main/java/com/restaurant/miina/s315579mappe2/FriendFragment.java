package com.restaurant.miina.s315579mappe2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.FriendAdapter;
import java.util.List;

public class FriendFragment extends CustomFragment {
    private final int REQUEST_CODE = 10001;

    @Override
    CustomAdapter setAdapter() {
        List<Friend> listFriends = model.getList();
        CustomAdapter adapter = new FriendAdapter(getActivity(), listFriends, flag);
        return adapter;
    }

    @Override
    CustomViewModel getViewModel() {
        return ViewModelProviders.of(this).get(FriendViewModel.class);
    }

    @Override
    Intent getCustomIntent() {
        return new Intent(getActivity(), FriendInput.class);
    }

    @Override
    int getRequestCode() {
        return REQUEST_CODE;
    }

    @Override
    void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    int getLayout() {
        return R.layout.custom_fragment;
    }

}
