package com.restaurant.miina.s315579mappe2.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.restaurant.miina.s315579mappe2.Activities.RestaurantInputActivity;
import com.restaurant.miina.s315579mappe2.Adapters.CustomAdapter;
import com.restaurant.miina.s315579mappe2.Adapters.RestaurantAdapter;
import com.restaurant.miina.s315579mappe2.Fragments.ViewModels.CustomViewModel;
import com.restaurant.miina.s315579mappe2.Fragments.ViewModels.RestaurantViewModel;
import com.restaurant.miina.s315579mappe2.R;
import com.restaurant.miina.s315579mappe2.Models.Restaurant;

import java.util.List;

public class RestaurantFragment extends CustomFragment {
    private final int REQUEST_CODE = 20001;

    @Override
    public CustomAdapter setAdapter() {
        List<Restaurant> listRestaurants = model.getList();
        CustomAdapter adapter = new RestaurantAdapter(getActivity(), listRestaurants, flag);
        return adapter;
    }

    @Override
    public CustomViewModel getViewModel() {
        return ViewModelProviders.of(this).get(RestaurantViewModel.class);
    }

    @Override
    public Intent getCustomIntent() {
        return new Intent(getActivity(), RestaurantInputActivity.class);
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
