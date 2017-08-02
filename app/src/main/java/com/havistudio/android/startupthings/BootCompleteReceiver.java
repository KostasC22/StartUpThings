package com.havistudio.android.startupthings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kostas on 01/08/2017.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    public static String TAG = "ReceivedBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received A Broadcast");

        if(intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)){
            Intent serviceIntent = new Intent(context, AutoStartUpService.class);
            context.startService(serviceIntent);
        }

    }
}
