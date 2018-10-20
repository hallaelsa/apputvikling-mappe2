package com.restaurant.miina.s315579mappe2;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.Models.Order;

public class SMSNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsnotification);

        long id = getIntent().getLongExtra("ORDER_ID", 0);
        DBHandler db = new DBHandler(this);
        Order order = db.getOrder(id);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String smsMessageBase = prefs.getString("setSMSmessage", getResources().getString(R.string.notificationText));

        String message = smsMessageBase+"\n At: "+order.getRestaurant().getName()
                + ", " +order.getRestaurant().getAddress() + "\n " + order.getDate();

        int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if (MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED &&
                MY_PHONE_STATE_PERMISSION ==
                        PackageManager.PERMISSION_GRANTED) {
            SmsManager smsMan = SmsManager.getDefault();
            for(Friend friend : order.getFriends()) {
                smsMan.sendTextMessage(friend.getPhone(), null, message, null, null);
                Log.d("SMS", "SMS sent to "+friend.getName());
            }
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_PHONE_STATE}, 0);
        }
    }
}
