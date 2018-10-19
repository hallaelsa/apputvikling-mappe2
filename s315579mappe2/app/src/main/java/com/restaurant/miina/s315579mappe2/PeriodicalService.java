package com.restaurant.miina.s315579mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;

public class PeriodicalService extends JobService {


//    @Override
////    public int onStartCommand(Intent intent, int flags, int startId) {
////        java.util.Calendar cal = Calendar.getInstance();
////        Intent i = new Intent(this, NotificationService.class);
////        PendingIntent pintent = PendingIntent.getService(this, 0, i, 0);
////        // http://www.vogella.com/tutorials/AndroidTaskScheduling/article.html
////
////        AlarmManager alarm =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
////        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000, pintent);
////        return super.onStartCommand(intent, flags, startId);
////    }

    @Override
    public boolean onStartJob(JobParameters params) {
        java.util.Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, NotificationService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, i, 0);

        Intent service = new Intent(getApplicationContext(), NotificationService.class);
        getApplicationContext().startService(service);
        Util.scheduleJob(getApplicationContext()); // reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
