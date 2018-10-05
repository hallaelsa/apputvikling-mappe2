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
import android.widget.TextView;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

import static android.app.Activity.RESULT_OK;

// TODO! set target via bundle istedenfor egen metode!
public abstract class CustomFragment extends Fragment {
    ListView lv;
    TextView header;
    CustomAdapter adapter;
    CustomViewModel model;
    private CustomViewModel mViewModel;
    abstract CustomAdapter setAdapter();
    abstract CustomViewModel getViewModel();
    abstract Intent getCustomIntent();
    abstract int getRequestCode();
    abstract String getHeader();

    public void updateView() {
        adapter.refresh(model.getUpdatedList());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        model = getViewModel();
        adapter = setAdapter();
        View rootView = inflater.inflate(R.layout.custom_fragment, container, false);
        header = rootView.findViewById(R.id.listHeader);
        header.setText(getHeader());
        lv = rootView.findViewById(R.id.list_view);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = getCustomIntent();
                i.putExtra("OPTIONS","UPDATE");
                i.putExtra("ID", id);
                startActivityForResult(i, getRequestCode());
            }
        });

        return rootView;
    }


    // abstract method?
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("Requestcode", String.valueOf(requestCode));

        if(requestCode == getRequestCode() && resultCode == RESULT_OK) {
            updateView();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = getViewModel();
        // TODO: Use the ViewModel
    }


}
