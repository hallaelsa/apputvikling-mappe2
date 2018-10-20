package com.restaurant.miina.s315579mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;

public class PeriodicalService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("PeriodicalService","i onStartCommand()");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int hour;
        int minute;
        if(prefs == null) {
            hour = 12;
            minute = 0;
        } else {
            hour = Integer.parseInt(prefs.getString("setHour", "12" ));
            minute = Integer.parseInt(prefs.getString("setMinute", "0" ));
        }

        Log.d("PeriodicalService","hour: "+String.valueOf(hour));
        Log.d("PeriodicalService","minute: "+String.valueOf(minute));

        java.util.Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);

        Intent i = new Intent(this, NotificationService.class);
        PendingIntent pIntent = PendingIntent.getService(this, 0, i, 0);

        AlarmManager alarm =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pIntent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), 60*1000, pIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
