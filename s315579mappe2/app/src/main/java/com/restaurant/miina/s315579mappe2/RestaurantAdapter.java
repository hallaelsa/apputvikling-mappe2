package com.restaurant.miina.s315579mappe2;

import android.content.Context;

import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

public class RestaurantAdapter extends CustomAdapter {
    List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List restaurants, int flag) {
        super(context, restaurants, flag);
        this.restaurants = restaurants;
    }

    @Override
    void refresh(List updatedRestaurants) {
        restaurants.clear();
        restaurants.addAll(updatedRestaurants);
        this.notifyDataSetChanged();
    }

    @Override
    void setText(ViewHolder holder, int position) {
        holder.header.setText(restaurants.get(position).getName());
        holder.address.setText(restaurants.get(position).getAddress());
        holder.phone.setText(restaurants.get(position).getPhone());
        holder.type.setText(restaurants.get(position).getType());
    }

    @Override
    int getLayout() {
        return R.layout.item_row;
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return restaurants.get(position).get_ID();
    }
}
