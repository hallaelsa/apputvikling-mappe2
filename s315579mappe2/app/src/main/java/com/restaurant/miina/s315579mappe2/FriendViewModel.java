package com.restaurant.miina.s315579mappe2;

import android.app.Application;
import android.support.annotation.NonNull;

import com.restaurant.miina.s315579mappe2.Friends.Friend;

import java.util.List;

public class FriendViewModel extends CustomViewModel {
    private List<Friend> friends;
    private Application application;

    public FriendViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @Override
    List getList() {
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
    List getUpdatedList() {
        loadList();
        return friends;
    }
}
