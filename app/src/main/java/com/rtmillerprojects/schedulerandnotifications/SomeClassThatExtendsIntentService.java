package com.rtmillerprojects.schedulerandnotifications;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Ryan on 11/2/2016.
 */
public class SomeClassThatExtendsIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SomeClassThatExtendsIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //New thread
    }
}
