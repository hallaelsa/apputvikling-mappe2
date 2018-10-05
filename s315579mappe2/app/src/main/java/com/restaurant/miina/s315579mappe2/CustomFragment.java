package com.restaurant.miina.s315579mappe2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

import static android.app.Activity.RESULT_OK;

// TODO! set target via bundle istedenfor egen metode!
public class CustomFragment extends Fragment {
    ListView lv;
    CustomAdapter adapter;
    CustomViewModel model;
    String target;
    final String resTarget = "RESTAURANT";

    private CustomViewModel mViewModel;

    public static CustomFragment newInstance() {
        return new CustomFragment();
    }

    public void updateView() {
        if(target.equals(resTarget)) {
            adapter.refreshRestaurant(model.getUpdatedRestaurants());
        } else {
            adapter.refreshFriends(model.getUpdatedFriends());
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(CustomViewModel.class);
        if(target.equals(resTarget)) {
            List<Restaurant> listRestaurants = model.getRestaurants();
            adapter = new CustomAdapter(getActivity(), listRestaurants, null);
        } else {
            List<Friend> listFriends = model.getFriends();
            adapter = new CustomAdapter(getActivity(), null, listFriends);
        }


        View rootView = inflater.inflate(R.layout.custom_fragment, container, false);

        lv = (ListView)rootView.findViewById(R.id.list_view);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(getActivity(), ItemInputActivity.class);
                i.putExtra("OPTIONS","UPDATE");
                i.putExtra("TARGET", target);
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
        mViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        // TODO: Use the ViewModel
    }

    public void setTarget(String target) {
        this.target = target;
    }


}
