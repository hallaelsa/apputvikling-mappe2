package com.restaurant.miina.s315579mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.restaurant.miina.s315579mappe2.Orders.Add_Order_Activity;

// TODO! Se om det er en bedre å oppdatere fragmentet på. Kanskje via updatemetoden.
public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabPlus;
    FloatingActionButton fabFriend;
    FloatingActionButton fabOrder;
    FloatingActionButton fabRestaurant;
    FloatingActionButton fabMinus;
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

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        fabPlus = (FloatingActionButton)findViewById(R.id.fabPlus);
        fabFriend = (FloatingActionButton)findViewById(R.id.fabFriend);
        fabOrder = (FloatingActionButton)findViewById(R.id.fabOrder);
        fabRestaurant = (FloatingActionButton)findViewById(R.id.fabRestaurant);
        fabMinus = (FloatingActionButton)findViewById(R.id.fabMinus);

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
                break;
            case R.id.friedsmenu:
                fragment = new FriendFragment();
                fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
                break;
            case R.id.ordermenu:
                Intent i3 = new Intent(this, Add_Order_Activity.class);
                startActivity(i3);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }


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
        fabRestaurant.animate().translationY(0);
        fabOrder.animate().translationY(0);
        fabFriend.animate().translationY(0);
        fabPlus.show();
        fabMinus.hide();
    }

    public void showFabs(View view) {
        fabRestaurant.animate().translationY(-getResources().getDimension(R.dimen.high));
        fabFriend.animate().translationY(-getResources().getDimension(R.dimen.middle));
        fabOrder.animate().translationY(-getResources().getDimension(R.dimen.low));
        fabPlus.hide();
        fabMinus.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (requestCode) {
            case FRIEND_REQUEST_CODE:
                fragment = new FriendFragment();
                fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
                break;
            case RESTAURANT_REQUEST_CODE:
                fragment = new RestaurantFragment();
                fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
                break;
        }
    }
}
