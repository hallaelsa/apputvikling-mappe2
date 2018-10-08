package com.restaurant.miina.s315579mappe2.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.restaurant.miina.s315579mappe2.Activities.FriendInput;
import com.restaurant.miina.s315579mappe2.Adapters.CustomAdapter;
import com.restaurant.miina.s315579mappe2.Adapters.FriendAdapter;
import com.restaurant.miina.s315579mappe2.Fragments.ViewModels.CustomViewModel;
import com.restaurant.miina.s315579mappe2.Fragments.ViewModels.FriendViewModel;
import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.R;

import java.util.List;

public class FriendFragment extends CustomFragment {
    private final int REQUEST_CODE = 10001;

    @Override
    public CustomAdapter setAdapter() {
        List<Friend> listFriends = model.getList();
        CustomAdapter adapter = new FriendAdapter(getActivity(), listFriends, flag);
        return adapter;
    }

    @Override
    public CustomViewModel getViewModel() {
        return ViewModelProviders.of(this).get(FriendViewModel.class);
    }

    @Override
    public Intent getCustomIntent() {
        return new Intent(getActivity(), FriendInput.class);
    }

    @Override
    public int getRequestCode() {
        return REQUEST_CODE;
    }

    @Override
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public int getLayout() {
        return R.layout.custom_fragment;
    }

}
