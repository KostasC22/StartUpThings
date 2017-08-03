package com.havistudio.android.startupthings;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import io.realm.Realm;

/**
 * Created by kostas on 01/08/2017.
 */

public class AutoStartUpService extends Service {

    private static final String TAG = "AutoStartUpService";

    public void onCreate(){
        super.onCreate();
        Realm.init(this);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
