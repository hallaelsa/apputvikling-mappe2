package com.restaurant.miina.s315579mappe2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Activities.MainActivity;

public class NotificationService extends Service {
    public static final String CHANNEL_ID = "s315579 Restaurant notification channel";
    int notificationId = 1;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO should get notification text from db.. which restaurant, what date and time etc. with whoom.
        Toast.makeText(this, "NOTIFICATION!", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);


        Toast.makeText(getApplicationContext(), "Notfication service!", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(this.getResources().getString(R.string.notificationTitle))
                .setContentText(this.getResources().getString(R.string.notificationText))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, mBuilder.build());

        return super.onStartCommand(intent, flags, startId);
    }


}
