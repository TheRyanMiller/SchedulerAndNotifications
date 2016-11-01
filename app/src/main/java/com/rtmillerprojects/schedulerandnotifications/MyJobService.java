package com.rtmillerprojects.schedulerandnotifications;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ryan on 10/27/2016.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private UpdateAppsAsyncTask updateTask = new UpdateAppsAsyncTask();

    @Override
    public boolean onStartJob(JobParameters params) {
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
