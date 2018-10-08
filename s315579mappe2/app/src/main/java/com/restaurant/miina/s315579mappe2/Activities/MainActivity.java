package com.restaurant.miina.s315579mappe2.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.restaurant.miina.s315579mappe2.Fragments.CustomFragment;
import com.restaurant.miina.s315579mappe2.Fragments.FriendFragment;
import com.restaurant.miina.s315579mappe2.R;
import com.restaurant.miina.s315579mappe2.Fragments.RestaurantFragment;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabPlus;
    FloatingActionButton fabFriend;
    FloatingActionButton fabOrder;
    FloatingActionButton fabRestaurant;
    FloatingActionButton fabMinus;
    FrameLayout frameLayout;
    TextView fabOrderLabel;
    TextView fabRestaurantLabel;
    TextView fabFriendLabel;
    SharedPreferences sharedPreferences;
    ActionBar actionbar;
    CustomFragment fragment;
    final int FRIEND_REQUEST_CODE = 10002;
    final int RESTAURANT_REQUEST_CODE = 20002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionbar = getSupportActionBar();
        fabPlus = (FloatingActionButton)findViewById(R.id.fabPlus);
        fabFriend = (FloatingActionButton)findViewById(R.id.fabFriend);
        fabOrder = (FloatingActionButton)findViewById(R.id.fabOrder);
        fabRestaurant = (FloatingActionButton)findViewById(R.id.fabRestaurant);
        fabMinus = (FloatingActionButton)findViewById(R.id.fabMinus);
        fabFriendLabel = findViewById(R.id.fabFriendLabel);
        fabRestaurantLabel = findViewById(R.id.fabRestaurantLabel);
        fabOrderLabel = findViewById(R.id.fabOrderLabel);
        frameLayout = findViewById(R.id.frameLayout);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case R.id.resmenu:
                fragment = new RestaurantFragment();
                fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
                actionbar.setTitle(R.string.resMenuText);
                actionbar.setDisplayHomeAsUpEnabled(true);
                break;
            case R.id.friedsmenu:
                fragment = new FriendFragment();
                fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
                actionbar.setTitle(R.string.friendsMenuText);
                actionbar.setDisplayHomeAsUpEnabled(true);

                break;
            case R.id.ordermenu:
                Intent intent = new Intent(this, Test.class);
                startActivity(intent);
                break;
            case R.id.settingsmenu:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;

            case android.R.id.home:
                fragmentManager.beginTransaction().remove(fragment).commit();
                actionbar.setTitle(R.string.app_name);
                actionbar.setDisplayHomeAsUpEnabled(false);
            default:
                return super.onOptionsItemSelected(item);
        }

        hideFabs();
        return true;
    }

    public void addRestaurant(View view) {
        Intent i = new Intent(this, RestaurantInput.class);
        i.putExtra("OPTIONS","ADD");
        startActivityForResult(i, RESTAURANT_REQUEST_CODE);
        hideFabs(view);
    }

    public void addOrder(View view) {
        Intent i = new Intent(this, CreateOrderActivity.class);
        startActivity(i);
        hideFabs(view);
    }

    public void addFriend(View view) {
        Intent i = new Intent(this, FriendInput.class);
        i.putExtra("OPTIONS","ADD");
        startActivityForResult(i, FRIEND_REQUEST_CODE);
        hideFabs(view);
    }

    public void hideFabs(View view) {
        hideFabs();
    }

    public void hideFabs() {
        fabRestaurant.animate().translationY(0);
        fabOrder.animate().translationY(0);
        fabFriend.animate().translationY(0);
        fabRestaurantLabel.animate().translationY(0);
        fabOrderLabel.animate().translationY(0);
        fabFriendLabel.animate().translationY(0);
        fabRestaurantLabel.setVisibility(View.GONE);
        fabOrderLabel.setVisibility(View.GONE);
        fabFriendLabel.setVisibility(View.GONE);
        fabPlus.show();
        fabMinus.hide();

        if(fragment != null)
            fragment.setAlpha(1f);

    }

    public void showFabs(View view) {
        showFabs();

    }

    public void showFabs() {
        fabRestaurant.animate().translationY(-getResources().getDimension(R.dimen.high));
        fabFriend.animate().translationY(-getResources().getDimension(R.dimen.middle));
        fabOrder.animate().translationY(-getResources().getDimension(R.dimen.low));
        fabRestaurantLabel.setVisibility(View.VISIBLE);
        fabOrderLabel.setVisibility(View.VISIBLE);
        fabFriendLabel.setVisibility(View.VISIBLE);
        fabRestaurantLabel.animate().translationY(-getResources().getDimension(R.dimen.high));
        fabFriendLabel.animate().translationY(-getResources().getDimension(R.dimen.middle));
        fabOrderLabel.animate().translationY(-getResources().getDimension(R.dimen.low));
        fabPlus.hide();
        fabMinus.show();

        if(fragment != null)
            fragment.setAlpha(0.25f);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFabs();
        Log.d("resultcode",String.valueOf(resultCode));
        Log.d("requestcode",String.valueOf(requestCode));
        switch (requestCode) {
            case FRIEND_REQUEST_CODE:
                if(resultCode == RESULT_OK) {
                    fragment = new FriendFragment();
                    fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
                    actionbar.setTitle(R.string.friendsMenuText);
                    actionbar.setDisplayHomeAsUpEnabled(true);
                }
                break;
            case RESTAURANT_REQUEST_CODE:
                if(resultCode == RESULT_OK) {
                    fragment = new RestaurantFragment();
                    fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
                    actionbar.setTitle(R.string.resMenuText);
                    actionbar.setDisplayHomeAsUpEnabled(true);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.getBoolean("SMScheckbox", false);
        Log.d("preferences", String.valueOf(sharedPreferences.getBoolean("SMScheckbox", false)));
    }
}
