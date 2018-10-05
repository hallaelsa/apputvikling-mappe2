package com.restaurant.miina.s315579mappe2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

public class RestaurantFragment extends CustomFragment {
    private final int REQUEST_CODE = 20001;

    @Override
    CustomAdapter setAdapter() {
        List<Restaurant> listRestaurants = model.getList();
        CustomAdapter adapter = new RestaurantAdapter(getActivity(), listRestaurants);
        return adapter;
    }

    @Override
    CustomViewModel getViewModel() {
        return ViewModelProviders.of(this).get(RestaurantViewModel.class);
    }

    @Override
    Intent getCustomIntent() {
        return new Intent(getActivity(), RestaurantInput.class);
    }

    @Override
    int getRequestCode() {
        return REQUEST_CODE;
    }

    @Override
    String getHeader() {
        return "My Restaurants";
    }
}
