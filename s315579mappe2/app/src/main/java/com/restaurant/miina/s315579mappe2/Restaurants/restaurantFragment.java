package com.restaurant.miina.s315579mappe2.Restaurants;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.restaurant.miina.s315579mappe2.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class restaurantFragment extends Fragment {
    ListView lv;
    RestaurantAdapter adapter;
    RestaurantViewModel model;

    private RestaurantViewModel mViewModel;

    public static restaurantFragment newInstance() {
        return new restaurantFragment();
    }

    public void updateView() {
        adapter.refresh(model.getUpdatedRestaurants());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        List<Restaurant> listRestaurants = model.getRestaurants();

        View rootView = inflater.inflate(R.layout.restaurant_fragment, container, false);
        adapter = new RestaurantAdapter(getActivity(), listRestaurants);
        lv = (ListView)rootView.findViewById(R.id.list_view);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(getActivity(), Add_Restaurant_Activity.class);
                i.putExtra("OPTIONS","UPDATE");
                i.putExtra("ID", id);
                startActivityForResult(i, 101);

            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == RESULT_OK) {

            Log.d("Fragment","101");

            updateView();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        // TODO: Use the ViewModel
    }

}
