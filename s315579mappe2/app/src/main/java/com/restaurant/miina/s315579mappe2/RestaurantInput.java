package com.restaurant.miina.s315579mappe2;

import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

public class RestaurantInput extends ItemInputActivity {
    Restaurant restaurant;

    @Override
    void setTitle(boolean update) {
        if(update) {
            title.setText("Update Restaurant");
        } else {
            title.setText("Add Resaurant");
        }
    }

    @Override
    void setValues(long id) {
        Restaurant restaurant = db.getRestaurant(id);
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        phone.setText(restaurant.getPhone());
        type.setText(restaurant.getType());
    }

    @Override
    void addItem() {
        restaurant = new Restaurant(name.getText().toString(),phone.getText().toString(),address.getText().toString(), type.getText().toString());
        db.regRestaurant(restaurant);

    }

    @Override
    void updateItem(long id) {
        restaurant = new Restaurant(id, name.getText().toString(),phone.getText().toString(),address.getText().toString(), type.getText().toString());
        db.updateRestaurant(restaurant);
    }

    @Override
    void deleteItem(long id) {
        db.deleteRestaurant(id);
    }
}
