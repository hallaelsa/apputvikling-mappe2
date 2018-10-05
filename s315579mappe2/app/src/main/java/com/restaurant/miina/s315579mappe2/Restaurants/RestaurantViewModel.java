package com.restaurant.miina.s315579mappe2.Restaurants;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.restaurant.miina.s315579mappe2.DBHandler;
import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private List<Restaurant> restaurants;
    private Application application;

    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public List<Restaurant> getRestaurants() {
        if (restaurants == null) {
            loadRestaurants();
        }
        return restaurants;
    }

    private void loadRestaurants() {
        DBHandler db = new DBHandler(application.getApplicationContext());
        restaurants = db.getAllRestaurants();
    }

    public List<Restaurant> getUpdatedRestaurants() {
        loadRestaurants();
        return restaurants;
    }

}
