package com.restaurant.miina.s315579mappe2.Models;

public class Restaurant {
    long _ID;
    String name;
    String phone;
    String address;
    String type;

    public Restaurant() {

    }

    public Restaurant(String name, String phone, String address, String type) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    public Restaurant(long _ID, String name, String phone, String address, String type) {
        this._ID = _ID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    public long get_ID() {
        return _ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(String type) {
        this.type = type;
    }
}
