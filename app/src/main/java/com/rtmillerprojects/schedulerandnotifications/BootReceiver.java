package com.rtmillerprojects.schedulerandnotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Ryan on 10/31/2016.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"BOOTED!!!!!!!",Toast.LENGTH_LONG).show();
    }
}
