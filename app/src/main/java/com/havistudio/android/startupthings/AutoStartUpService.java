package com.havistudio.android.startupthings;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kostas on 01/08/2017.
 */

public class AutoStartUpService extends Service {

    private static final String TAG = "AutoStartUpService";

    private Realm realm;
    private Context mContext;

    public void onCreate(){
        super.onCreate();
        // Create the Realm instance
        realm = Realm.getDefaultInstance();
        mContext = this;

        RealmResults<StartUp> result = realm.where(StartUp.class).findAll();
        ListIterator<StartUp> LIResult = result.listIterator();
        while (LIResult.hasNext()) {
            final StartUp element = LIResult.next();
            Log.i(TAG,element.getId() + " " + element.getPackageName() + " " + element.getType());
            if(element.getCategory().equals("URL")){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String url = element.getData();
                        Intent intentVLC = new Intent(Intent.ACTION_VIEW);
                        intentVLC.setPackage(element.getPackageName());
                        intentVLC.setDataAndType(Uri.parse(url), element.getType());
                        intentVLC.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentVLC);
                    }
                }, element.getDelay());
            } else if(element.getCategory().equals("File")){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        File myFile = new File(element.getData());
                        Uri uri = Uri.fromFile(myFile);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri, element.getType());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, element.getDelay());
            } else if(element.getCategory().equals("Application")){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openApp(mContext,element.getPackageName());
                    }
                }, element.getDelay());
            }
        }
    }

    /** Open another app.
     *  source: https://stackoverflow.com/questions/2780102/open-another-application-from-your-own-intent
     * @param context current Context, like Activity, App, or Service
     * @param packageName the full package name of the app to open
     * @return true if likely successful, false if unsuccessful
     */
    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                return false;
                //throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
