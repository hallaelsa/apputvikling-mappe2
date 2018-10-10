package com.restaurant.miina.s315579mappe2.Fragments.ViewModels;

import android.app.Application;
import android.support.annotation.NonNull;

import com.restaurant.miina.s315579mappe2.DBHandler;
import com.restaurant.miina.s315579mappe2.Models.Order;

import java.util.List;

public class OrderViewModel extends CustomViewModel{
    List<Order> orders;
    Application application;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public List<Order> getList() {
        if(orders == null)
            loadList();

        return orders;
    }

    void loadList() {
        DBHandler db = new DBHandler(application.getApplicationContext());
        orders = db.getAllOrders();
    }

    public List getUpdatedList() {
        loadList();
        return orders;
    }
}
