package com.restaurant.miina.s315579mappe2.Models;

public class Friend {
    long _ID;
    String name;
    String phone;
    String address;

    public Friend() {

    }

    public Friend(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Friend(long _ID, String name, String phone, String address) {
        this._ID = _ID;
        this.name = name;
        this.phone = phone;
        this.address = address;
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

}
