package com.restaurant.miina.s315579mappe2;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Activities.MainActivity;
import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.Models.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotificationService extends Service {
    SharedPreferences prefs;
    String date;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Her må jeg sjekke om det er noen bestillinger i dag.
        // TODO Her skal jeg sende notification til brukeren, og sende SMS til venner.
        // TODO Må hente ut standard meldingstekst og alle venner som skal være med.
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        DBHandler db = new DBHandler(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = sdf.format(new Date());
        date = currentDate;
        Log.d("date", date);

        List<Order> orders = db.getAllOrders(date);
        for (Order order: orders ) {
            sendNotification(order);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification(Order order) {
        String friends = "";
        sendSmsToFriends(order);
        for(Friend friend : order.getFriends()) {
            friends += friend.getName()+"\n";
        }

        String notificationTitle = getResources().getString(R.string.notificationTitle);
        String notificationTextBase = getResources().getString(R.string.notificationText);
        String notificationText = notificationTextBase+" width:\n"+friends;

        Log.d("NotificationService","i sendNotification()");
        Toast.makeText(getApplicationContext(), "I MinService", Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, i, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(notificationTitle)
                .setContentText(friends.equals("") ? notificationTextBase : notificationText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .build();
        notification.flags|= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);

    }

    private void sendSmsToFriends(Order order) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String smsMessageBase = prefs.getString("setSMSmessage", getResources().getString(R.string.notificationText));

        String message = smsMessageBase+"\n At: "+order.getRestaurant().getName()
                + ", " +order.getRestaurant().getAddress() + "\n " + order.getDate()+", "+order.getTime();

        int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if (MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED &&
                MY_PHONE_STATE_PERMISSION ==
                        PackageManager.PERMISSION_GRANTED) {

            Log.d("NotificationService", "inside if");
            SmsManager smsMan = SmsManager.getDefault();
            for(Friend friend : order.getFriends()) {
                Log.d("NotificationService", "sending SMS to "+friend.getName());
                smsMan.sendTextMessage(friend.getPhone(), null, message, null, null);
            }
        }
        else{
            checkPermission(this);
        }

    }
    // https://stackoverflow.com/questions/33867088/request-location-permissions-from-a-service-android-m/34203974
    public static boolean checkPermission(final Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
    }

}
