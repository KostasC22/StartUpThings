package com.havistudio.android.startupthings;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by kostas on 04/08/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}