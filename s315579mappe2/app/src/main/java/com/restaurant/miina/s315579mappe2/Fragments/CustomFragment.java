package com.restaurant.miina.s315579mappe2.Fragments;

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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Adapters.CustomAdapter;
import com.restaurant.miina.s315579mappe2.Adapters.FriendAdapter;
import com.restaurant.miina.s315579mappe2.Fragments.ViewModels.CustomViewModel;
import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

// TODO! set target via bundle istedenfor egen metode!
public abstract class CustomFragment extends Fragment {
    ListView lv;
    CustomAdapter adapter;
    CustomViewModel model;
    List<Long> checkedIDs;
    List<Friend> friendsForUpdate;
    boolean friendsForUpdateIsSet = false;
    int flag = DEFAULT_LIST_FLAG;
    View rootView;

    private CustomViewModel mViewModel;
    public abstract CustomAdapter setAdapter();
    public abstract CustomViewModel getViewModel();
    public abstract Intent getCustomIntent();
    public abstract int getRequestCode();
    public abstract void setFlag(int flag);
    public abstract int getLayout();
    public static final int SIMPLIFIED_CHECK_LIST_FLAG = 1;
    public static final int DEFAULT_LIST_FLAG = 0;

    public void updateView() {
        adapter.refresh(model.getUpdatedList());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        model = getViewModel();
        adapter = setAdapter();
        if(friendsForUpdateIsSet)
            ((FriendAdapter)adapter).setFriendsChecked(friendsForUpdate);
        checkedIDs = new ArrayList<>();
        rootView = inflater.inflate(getLayout(), container, false);
        lv = rootView.findViewById(R.id.list_view);
        lv.setAdapter(adapter);

        if(friendsForUpdateIsSet) {
            setFriendsForUpdate();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(flag == DEFAULT_LIST_FLAG) {
                    Intent i = getCustomIntent();
                    i.putExtra("OPTIONS","UPDATE");
                    i.putExtra("ID", id);
                    startActivityForResult(i, getRequestCode());
                } else if(flag == SIMPLIFIED_CHECK_LIST_FLAG) {
                    CheckBox cb = (CheckBox)view.findViewById(R.id.checkbox);
                    if(!cb.isChecked()) {
                        cb.setChecked(true);
                        checkedIDs.add(id);

                    } else {
                        cb.setChecked(false);
                        checkedIDs.remove(id);
                    }
                }

            }
        });

        return rootView;
    }

    private void setFriendsForUpdate() {
        for(int i = 0; i < friendsForUpdate.size(); i++) {
            checkedIDs.add(friendsForUpdate.get(i).get_ID());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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

    public List<Long> getCheckedIds() {
        return checkedIDs;
    }

    public void setAlpha(float num) {
        if(lv != null)
            lv.setAlpha(num);
    }




}
