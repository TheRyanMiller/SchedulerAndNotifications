package com.rtmillerprojects.schedulerandnotifications;

import android.app.Application;

import java.util.Date;

/**
 * Created by Ryan on 10/30/2016.
 */

public class SANApp extends Application {

    private static SANApp singleton;
    public static SANApp getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        singleton = this;

        SharedPrefHelper.initialize(this);
    }
}
