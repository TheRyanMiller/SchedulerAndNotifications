package com.rtmillerprojects.schedulerandnotifications;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static int kJobId = 0;
    private static final int REQUEST_CODE = 1;
    Date date = new Date();

    long timeOfInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void jobMethod(View v){
        //Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show();

        /*
        ComponentName mServiceComponent = new ComponentName(this, MyJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(kJobId++, mServiceComponent);
        builder.setMinimumLatency(0); // wait at least
        builder.setOverrideDeadline(1 * 1000); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        builder.setRequiresCharging(false); // we don't care if the device is charging or not
        JobScheduler jobScheduler = (JobScheduler) getApplication().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getService(this, REQUEST_CODE, intent, 0);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, timeOfInterval, alarmIntent);
*/

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent broadcast_intent = new Intent(this, AlarmBroadcastReceiver.class);
        broadcast_intent.putExtra("test", "ValueReceived");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,  broadcast_intent, 0);

        //alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 2*1000, pendingIntent);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000, pendingIntent);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void cancelAllJobs(View v) {
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancelAll();
    }
}
