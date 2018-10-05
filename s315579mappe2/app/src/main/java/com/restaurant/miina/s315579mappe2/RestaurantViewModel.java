package com.restaurant.miina.s315579mappe2;

import android.app.Application;
import android.support.annotation.NonNull;

import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

public class RestaurantViewModel extends CustomViewModel {
    List<Restaurant> restaurants;
    Application application;

    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @Override
    List getList() {
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
    List getUpdatedList() {
        loadList();
        return restaurants;
    }
}
