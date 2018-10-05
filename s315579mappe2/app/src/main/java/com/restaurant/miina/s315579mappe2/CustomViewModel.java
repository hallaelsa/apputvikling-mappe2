package com.restaurant.miina.s315579mappe2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

public abstract class CustomViewModel extends AndroidViewModel {
    abstract List getList();
    abstract void loadList();
    abstract List getUpdatedList();

    public CustomViewModel(@NonNull Application application) {
        super(application);
    }

}
