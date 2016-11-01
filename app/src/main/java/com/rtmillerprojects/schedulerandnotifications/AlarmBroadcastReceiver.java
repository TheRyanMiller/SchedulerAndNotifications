package com.rtmillerprojects.schedulerandnotifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Ryan on 10/30/2016.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        int i = extras.getInt("num"); //empty
        long longDate = extras.getLong("longdate");

        Date date=new Date(longDate);
        SimpleDateFormat df2 = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateText = df2.format(date);

        SharedPrefHelper.putStringPreference("update "+i+": ",dateText);

        Date now = new Date();
        long lDate = now.getTime();

        Toast.makeText(context, "Alarm "+i+"  "+dateText, Toast.LENGTH_LONG).show();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Intent newIntent = new Intent(context, AlarmBroadcastReceiver.class);
        newIntent.putExtra("num", ++i);
        newIntent.putExtra("longdate", lDate);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,  newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000, pendingIntent);
    }
}
