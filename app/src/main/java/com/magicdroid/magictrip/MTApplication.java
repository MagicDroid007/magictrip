package com.magicdroid.magictrip;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by sanidhya on 8/4/17.
 */

public class MTApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
