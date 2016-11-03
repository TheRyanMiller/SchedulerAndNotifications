package com.rtmillerprojects.schedulerandnotifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ryan on 10/27/2016.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    NotificationManager nm;
    int mNotificationId = 001;

    private UpdateAppsAsyncTask updateTask = new UpdateAppsAsyncTask();

    @Override
    public boolean onStartJob(JobParameters params) {
        nm =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, SomeClassThatExtendsIntentService.class);
            //In SomeClass... onHandleIntent do background calls

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("My first notification")
                        .setContentText("Hello World!");

        Intent resultIntent = new Intent(this, MainActivity.class);

// Sets an ID for the notification
        mNotificationId++;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );


        // Note: this is preformed on the main thread.
        updateTask.execute(params);
        if(SharedPrefHelper.getBoolean("switch",false)){
            Toast.makeText(this, "SWITCH IS ACTIVATED", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "SWITCH IS DE-ACTIVATED", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    // Stopping jobs if our job requires change.

    @Override
    public boolean onStopJob(JobParameters params) {
        // Note: return true to reschedule this job.
        Toast.makeText(this, "STOP JOB TOAST", Toast.LENGTH_SHORT).show();
        boolean shouldReschedule = updateTask.stopJob(params);


        return shouldReschedule;
        //return true;
    }


    private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {

        @Override
        protected JobParameters[] doInBackground(JobParameters... params) {
            // Do updating and stopping logical here.
            Log.d("log","JOB RUN");
            if(SharedPrefHelper.getBoolean("switch",false)){
            }
            return params;
        }

        @Override
        protected void onPostExecute(JobParameters[] result) {
            for (JobParameters params : result) {
                if (!hasJobBeenStopped(params)) {
                    jobFinished(params, false);
                }
            }
        }

        private boolean hasJobBeenStopped(JobParameters params) {
            // Logic for checking stop.
            return false;
        }

        public boolean stopJob(JobParameters params) {
            // Logic for stopping a job. return true if job should be rescheduled.
            return false;
        }

    }

}
