package com.restaurant.miina.s315579mappe2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.LinkedHashMap;
import java.util.List;

public class CreateOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    List<Friend> friendList;
    List<Restaurant> restaurantList;
    String[] friendNames;
    String[] restaurantNames;
    long[] friendIDs;
    long[] restaurantIDs;
    long[] chosenFriendIDs;
    long chosenRestaurantID;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        db = new DBHandler(this);
        friendList = db.getAllFriends();
        restaurantList = db.getAllRestaurants();
        friendNames = new String[friendList.size()];
        friendIDs = new long[friendList.size()];
        restaurantIDs = new long[restaurantList.size()];
        restaurantNames = new String[restaurantList.size()];
        setNames();

        Spinner restaurantSpinner = (Spinner) findViewById(R.id.restaurantSpinner);
        restaurantSpinner.setOnItemSelectedListener(this);
        //Spinner friendSpinner = findViewById(R.id.friendSpinner);

        ArrayAdapter<CharSequence> restaurantAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, restaurantNames);
        restaurantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurantSpinner.setAdapter(restaurantAdapter);

    }

    private void setNames() {
        for(int i = 0 ; i < friendList.size(); i++) {
            friendNames[i] = friendList.get(i).getName();
            friendIDs[i] = friendList.get(i).get_ID();
        }
        for(int i = 0 ; i < restaurantList.size(); i++) {
            restaurantNames[i] = restaurantList.get(i).getName();
            restaurantIDs[i] = restaurantList.get(i).get_ID();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Spinner","You chose "+restaurantNames[position]);
        Toast.makeText(getApplicationContext(), "You chose "+restaurantNames[position], Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }

}
