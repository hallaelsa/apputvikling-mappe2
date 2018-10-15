package com.restaurant.miina.s315579mappe2.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Adapters.RestaurantAdapter;
import com.restaurant.miina.s315579mappe2.Fragments.CustomFragment;
import com.restaurant.miina.s315579mappe2.DBHandler;
import com.restaurant.miina.s315579mappe2.Fragments.FriendFragment;
import com.restaurant.miina.s315579mappe2.Fragments.RestaurantFragment;
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
    Button deleteBtn;
    Button addBtn;
    Spinner restaurantSpinner;
    List<Friend> friendList;
    List<Restaurant> restaurantList;
    String date;
    long chosenRestaurantID;
    DBHandler db;
    DatePickerDialog datePickerDialog;
    CustomFragment fragment;
    boolean isUpdate = false;
    long updateID;
    Order orderForUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        if(getIntent()!=null && getIntent().getExtras()!=null) {
            isUpdate = getIntent().getStringExtra("OPTIONS").equals("UPDATE");
            updateID = getIntent().getLongExtra("ID", 0);
        }

        db = new DBHandler(this);
        friendList = db.getAllFriends();
        restaurantList = db.getAllRestaurants();
        selectedDate = findViewById(R.id.selectedDate);
        deleteBtn = findViewById(R.id.deleteBtn);
        addBtn = findViewById(R.id.addBtn);

        if(!isUpdate) {
            deleteBtn.setVisibility(View.GONE);
        }

        fixToolbar();
        setRestaurantSpinner();
        setFriendFragment();
        setCalendar();

    }

    private void setValues(long id) {
        orderForUpdate = db.getOrder(id);
        Restaurant res = orderForUpdate.getRestaurant();
        date = orderForUpdate.getDate();
        selectedDate.setText(date);
        List<Friend> friends = orderForUpdate.getFriends();
        if(friends.size() > 0) {
            ((FriendFragment)fragment).setSelectedFriends(friends);
        }

    }

    private void fixToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        if(isUpdate) {
            actionbar.setTitle(R.string.OrderUpdateHeader);
        } else {
            actionbar.setTitle(R.string.OrderHeader);
        }

    }

    private void setFriendFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new FriendFragment();
        fragment.setFlag(FriendFragment.SIMPLIFIED_CHECK_LIST_FLAG);
        fragmentTransaction.replace(R.id.selectFriendsFrameLayout, fragment).commit();

        if(isUpdate) {
            setValues(updateID);
        }
    }

    private void setRestaurantSpinner() {
        restaurantSpinner = (Spinner) findViewById(R.id.restaurantSpinner);
        restaurantSpinner.setOnItemSelectedListener(this);

        RestaurantAdapter restaurantAdapter1 = new RestaurantAdapter(this, restaurantList, RestaurantFragment.DEFAULT_LIST_FLAG);
        restaurantSpinner.setAdapter(restaurantAdapter1);
    }

    private void setCalendar() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                this, CreateOrderActivity.this, calendar.get(Calendar.YEAR),
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        chosenRestaurantID = restaurantList.get(position).get_ID();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        chosenRestaurantID = restaurantList.get(0).get_ID();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month +=1;
        date = dayOfMonth+"."+month+"."+year;
        selectedDate.setText(date);
    }

    public void addOrder(View view) {
        if(date == null) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = db.getRestaurant(chosenRestaurantID);
        Order order;

        if(isUpdate) {
            order = orderForUpdate;
        } else {
            order = new Order(restaurant, date);
        }

        if(fragment.getCheckedIds().size() > 0) {
            List<Friend> friends = new ArrayList<>();
            List<Long> chosenFriendIDs = fragment.getCheckedIds();

            for(long friendID : chosenFriendIDs) {
                Log.d("CreateOrderActivity ", "checkedID: "+String.valueOf(friendID));
                friends.add(db.getFriend(friendID));
            }

            order.setFriends(friends);
        }

        if(isUpdate) {
            if(updateID == 999999999) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                return;
            }
            db.updateOrder(orderForUpdate);
        } else {
            db.createOrder(order);
        }

        setResult(RESULT_OK);
        finish();

    }

    public void deleteOrder(View view) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        builder.setTitle(this.getResources().getString(R.string.deleteDialogTitle))
                .setMessage(this.getResources().getString(R.string.deleteDialogText))
                .setPositiveButton(this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteOrder(orderForUpdate.get_ID());
                        setResult(RESULT_OK);
                        finish();
                    }
                })
                .setNegativeButton(this.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .setCancelable(false)
                .show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                if(isUpdate) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    finish();
                }
                break;
        }
        return true;
    }
}
