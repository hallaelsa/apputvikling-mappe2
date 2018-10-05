package com.restaurant.miina.s315579mappe2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

public class CustomViewModel extends AndroidViewModel {
    private List<Restaurant> restaurants;
    private List<Friend> friends;
    private Application application;

    public CustomViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public List<Restaurant> getRestaurants() {
        if (restaurants == null) {
            loadRestaurants();
        }
        return restaurants;
    }

    public List<Friend> getFriends() {
        if (friends == null) {
            loadFriends();
        }
        return friends;
    }

    private void loadRestaurants() {
        DBHandler db = new DBHandler(application.getApplicationContext());
        restaurants = db.getAllRestaurants();
    }

    private void loadFriends() {
        DBHandler db = new DBHandler(application.getApplicationContext());
        friends = db.getAllFriends();
    }

    public List<Restaurant> getUpdatedRestaurants() {
        loadRestaurants();
        return restaurants;
    }

    public List<Friend> getUpdatedFriends() {
        loadFriends();
        return friends;
    }
}
