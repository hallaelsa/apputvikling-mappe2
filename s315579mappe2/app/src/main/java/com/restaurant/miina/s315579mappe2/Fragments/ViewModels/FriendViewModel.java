package com.restaurant.miina.s315579mappe2.Fragments.ViewModels;

import android.app.Application;
import android.support.annotation.NonNull;

import com.restaurant.miina.s315579mappe2.DBHandler;
import com.restaurant.miina.s315579mappe2.Models.Friend;

import java.util.List;

public class FriendViewModel extends CustomViewModel {
    private List<Friend> friends;
    private Application application;

    public FriendViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @Override
    public List getList() {
        if (friends == null) {
            loadList();
        }
        return friends;
    }

    @Override
    void loadList() {
        DBHandler db = new DBHandler(application.getApplicationContext());
        friends = db.getAllFriends();
    }

    @Override
    public List getUpdatedList() {
        loadList();
        return friends;
    }
}
