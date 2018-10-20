package com.restaurant.miina.s315579mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BROADCASTRECEIVER","i broadcast receiver");
        Intent i = new Intent(context, PeriodicalService.class);
        context.startService(i);
    }
}
