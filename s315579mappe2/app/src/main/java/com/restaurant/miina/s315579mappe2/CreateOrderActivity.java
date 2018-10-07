package com.restaurant.miina.s315579mappe2;

import android.app.DatePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    TextView selectedDate;
    List<Friend> friendList;
    List<Restaurant> restaurantList;
    String[] friendNames;
    String[] restaurantNames;
    long[] friendIDs;
    long[] restaurantIDs;
    long[] chosenFriendIDs;
    long chosenRestaurantID;
    boolean[] checkSelectedFriends;
    DBHandler db;
    DatePickerDialog datePickerDialog;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        db = new DBHandler(this);
        friendList = db.getAllFriends();
        restaurantList = db.getAllRestaurants();
        selectedDate = findViewById(R.id.selectedDate);

        setArrays();

        Spinner restaurantSpinner = (Spinner) findViewById(R.id.restaurantSpinner);
        restaurantSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> restaurantAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, restaurantNames);
        restaurantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurantSpinner.setAdapter(restaurantAdapter);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CustomFragment fragment = new FriendFragment();
        fragment.setFlag(FriendFragment.SIMPLIFIED_CHECK_LIST_FLAG);
        fragmentTransaction.replace(R.id.selectFriendsFrameLayout, fragment).commit();
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(
                this, CreateOrderActivity.this,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),  calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        (findViewById(R.id.displayDatepicker))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerDialog.show();
                    }
                });

    }

    private void setArrays() {
        friendNames = new String[friendList.size()];
        friendIDs = new long[friendList.size()];
        restaurantIDs = new long[restaurantList.size()];
        restaurantNames = new String[restaurantList.size()];
        checkSelectedFriends = new boolean[friendList.size()];

        for(int i = 0 ; i < friendList.size(); i++) {
            friendNames[i] = friendList.get(i).getName();
            friendIDs[i] = friendList.get(i).get_ID();
        }
        for (int i = 0; i < checkSelectedFriends.length; i++) {
            checkSelectedFriends[i] = false;
        }
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
        chosenRestaurantID = restaurantIDs[0];        Toast.makeText(getApplicationContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month +=1;
        date = dayOfMonth+"."+month+"."+year;
        selectedDate.setText(getResources().getString(R.string.selectedDateBase)+date);
    }
}
