package com.restaurant.miina.s315579mappe2.Orders;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

public class Order {
    long _ID;
    String date; // Date funker ikke i SQLite: https://stackoverflow.com/questions/16739836/how-to-add-date-in-sqlite-database
    Restaurant restaurant;
    List<Friend> friends;  // obs. valgfri!

    public Order() {

    }

    public Order(long _ID, Restaurant restaurant, String date) {
    this._ID = _ID;
    this.restaurant = restaurant;
    this.date = date;
    }

    public Order(Restaurant restaurant, String date) {
        this.restaurant = restaurant;
        this.date = date;
    }

    public long get_ID() {
        return _ID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getDate() {
        return date;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

}
