package com.restaurant.miina.s315579mappe2.Activities;

import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Fragments.CustomFragment;
import com.restaurant.miina.s315579mappe2.DBHandler;
import com.restaurant.miina.s315579mappe2.Fragments.FriendFragment;
import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.Models.Order;
import com.restaurant.miina.s315579mappe2.R;
import com.restaurant.miina.s315579mappe2.Models.Restaurant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    TextView selectedDate;
    ActionBar actionbar;
    List<Friend> friendList;
    List<Restaurant> restaurantList;
    String date;
    String[] restaurantNames;
    long[] restaurantIDs;
    long chosenRestaurantID;
    DBHandler db;
    DatePickerDialog datePickerDialog;
    CustomFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        db = new DBHandler(this);
        friendList = db.getAllFriends();
        restaurantList = db.getAllRestaurants();
        selectedDate = findViewById(R.id.selectedDate);
        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(R.string.OrderHeader);

        setArrays();

        Spinner restaurantSpinner = (Spinner) findViewById(R.id.restaurantSpinner);
        restaurantSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> restaurantAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, restaurantNames);
        restaurantAdapter.setDropDownViewResource(R.layout.dropdown_item);
        restaurantSpinner.setAdapter(restaurantAdapter);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new FriendFragment();
        fragment.setFlag(FriendFragment.SIMPLIFIED_CHECK_LIST_FLAG);
        fragmentTransaction.replace(R.id.selectFriendsFrameLayout, fragment).commit();

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                this, CreateOrderActivity.this,calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),  calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        (findViewById(R.id.selectedDate))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerDialog.show();
                    }
                });

    }

    private void setArrays() {
        restaurantIDs = new long[restaurantList.size()];
        restaurantNames = new String[restaurantList.size()];

        for(int i = 0 ; i < restaurantList.size(); i++) {
            restaurantNames[i] = restaurantList.get(i).getName();
            restaurantIDs[i] = restaurantList.get(i).get_ID();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Spinner","You chose "+restaurantNames[position]);
        chosenRestaurantID = restaurantIDs[position];
        Toast.makeText(getApplicationContext(), "You chose "+restaurantNames[position], Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        chosenRestaurantID = restaurantIDs[0];
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month +=1;
        date = dayOfMonth+"."+month+"."+year;
        selectedDate.setText(date);
    }

    public void addOrder(View view) {
        if(date.isEmpty()) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = db.getRestaurant(chosenRestaurantID);
        Order order = new Order(restaurant, date);

        if(fragment.getCheckedIds().size() > 0) {
            List<Friend> friends = new ArrayList<>();
            List<Long> chosenFriendIDs = fragment.getCheckedIds();
            Log.d("FriendIDs", String.valueOf(chosenFriendIDs));

            for(long friendID : chosenFriendIDs) {
                Log.d("ID:",
                        String.valueOf(friendID));
                friends.add(db.getFriend(friendID));
            }

            order.setFriends(friends);
        }

        db.createOrder(order);
    }
}