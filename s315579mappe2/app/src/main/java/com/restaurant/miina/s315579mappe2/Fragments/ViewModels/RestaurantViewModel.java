package com.restaurant.miina.s315579mappe2.Fragments.ViewModels;

import android.app.Application;
import android.support.annotation.NonNull;

import com.restaurant.miina.s315579mappe2.DBHandler;
import com.restaurant.miina.s315579mappe2.Models.Restaurant;

import java.util.List;

public class RestaurantViewModel extends CustomViewModel {
    List<Restaurant> restaurants;
    Application application;

    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @Override
    public List getList() {
        if(restaurants == null) {
            loadList();
        }
        return restaurants;
    }

    @Override
    void loadList() {
        DBHandler db = new DBHandler(application.getApplicationContext());
        restaurants = db.getAllRestaurants();
    }

    @Override
    public List getUpdatedList() {
        loadList();
        return restaurants;
    }
}
