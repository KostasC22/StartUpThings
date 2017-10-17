package com.havistudio.android.startupthings;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
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

    public void onCreate() {
        super.onCreate();
        // Create the Realm instance
        realm = Realm.getDefaultInstance();
        mContext = this;

        RealmResults<StartUp> result = realm.where(StartUp.class).findAll();
        ListIterator<StartUp> LIResult = result.listIterator();
        Long currentDelay = 0L;
        while (LIResult.hasNext()) {
            final StartUp element = LIResult.next();
            Log.i(TAG, element.getId() + " " + element.getPackageName() + " " + element.getType());
            Log.i(TAG, element.toString());
            try {
                currentDelay = currentDelay + element.getDelay();
                if (element.getType().equals("URL")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String url = element.getData();
                            Intent intentURL = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            intentURL.setPackage(element.getPackageName());
                            //intentURL.setDataAndType(Uri.parse(url), element.getType());
                            intentURL.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentURL);
                        }
                    }, currentDelay);
                } else if (element.getType().equals("File")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Uri fileURI = FileProvider.getUriForFile(AutoStartUpService.this,
                                    BuildConfig.APPLICATION_ID + ".provider",
                                    new File(element.getData()));
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            String tempDataType = "application/"+element.getType();
                            Log.i(TAG, "tempDataType:"+tempDataType);
                            intent.setDataAndType(fileURI, "application/vnd.ms-powerpoint");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }, currentDelay);
                } else if (element.getType().equals("Application")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            openApp(mContext, element.getPackageName());
                        }
                    }, currentDelay);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open another app.
     * source: https://stackoverflow.com/questions/2780102/open-another-application-from-your-own-intent
     *
     * @param context     current Context, like Activity, App, or Service
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
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
