package com.restaurant.miina.s315579mappe2.Models;

import android.util.Log;

import java.util.List;

public class Order {
    long _ID;
    String date; // Date funker ikke i SQLite: https://stackoverflow.com/questions/16739836/how-to-add-date-in-sqlite-database
    String time;
    Restaurant restaurant;
    List<Friend> friends;  // obs. valgfri!

    public Order() {

    }

    public Order(long _ID, Restaurant restaurant, String date, String time) {
    this._ID = _ID;
    this.restaurant = restaurant;
    this.date = date;
    this.time = time;
    }

    public Order(Restaurant restaurant, String date, String time) {
        this.restaurant = restaurant;
        this.date = date;
        this.time = time;
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

    public String getTime() {
        return time;
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

}
