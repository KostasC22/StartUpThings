package com.havistudio.android.startupthings;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;
    @BindView(R.id.addingbutton)
    Button addingButton;
    @BindView(R.id.apps_spinner)
    Spinner appsSpinner;
    @BindView(R.id.type_spinner)
    Spinner typeSpinner;

    private Realm realm;
    private AppSpinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //appsSpinner;

        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<MyApp> allApps = new ArrayList<MyApp>();

        for (ApplicationInfo packageInfo : packages) {
            String localPackageName = packageInfo.packageName;
            String localAppName = packageInfo.loadLabel(getPackageManager()).toString();
            Log.d(TAG, "Installed package :" + localPackageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "AppName:" + localAppName);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
            MyApp lmyapp = new MyApp();
            lmyapp.setPackageName(localPackageName);
            lmyapp.setAppName(localAppName);
            allApps.add(lmyapp);
        }

        MyApp[] myApps = allApps.toArray(new MyApp[allApps.size()]);
        adapter = new AppSpinAdapter(this, android.R.layout.simple_spinner_item, myApps);
        appsSpinner.setAdapter(adapter);

        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();
        // Create the Realm instance
        realm = Realm.getDefaultInstance();

        basicCRUD(realm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }

    private void basicCRUD(Realm realm) {
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...");

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                StartUp startUpTemp = realm.createObject(StartUp.class);
                startUpTemp.setFileName("test1");
                startUpTemp.setPackageName("com.havistudio.test1");
                startUpTemp.setType("test1");
            }
        });

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                StartUp startUpTemp = realm.createObject(StartUp.class);
                startUpTemp.setFileName("test1");
                startUpTemp.setPackageName("com.havistudio.test1");
                startUpTemp.setType("test1");
            }
        });

        // Find the first person (no query conditions) and read a field
        final StartUp startUpTemp = realm.where(StartUp.class).findFirst();
        showStatus(startUpTemp.getFileName() + ":" + startUpTemp.getType());

        // Update person in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                startUpTemp.setFileName("1test1");
                startUpTemp.setPackageName("com.havistudio.1test1");
                showStatus(startUpTemp.getFileName() + " is now: " + startUpTemp.getPackageName());
            }
        });

        RealmResults<StartUp> result = realm.where(StartUp.class).findAll();
        ListIterator<StartUp> LIResult = result.listIterator();
        while (LIResult.hasNext()) {
            StartUp element = LIResult.next();
            showStatus(element.getId() + " " + element.getPackageName() + " " + element.getType());
        }


        // Delete all persons
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(StartUp.class);
            }
        });
    }

    private void basicQuery(Realm realm) {
        showStatus("\nPerforming basic Query operation...");
        showStatus("Number of persons: " + realm.where(StartUp.class).count());

        RealmResults<StartUp> results = realm.where(StartUp.class).equalTo("type", "test1").findAll();

        showStatus("Size of result set: " + results.size());
    }

    @OnClick(R.id.addingbutton)
    public void sayHi(Button button) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a Startup
                Log.i(TAG,"adding");
                StartUp startUpTemp = realm.createObject(StartUp.class);
                startUpTemp.setFileName("");
                startUpTemp.setPackageName(appsSpinner.getSelectedItem() + "");
                startUpTemp.setType(typeSpinner.getSelectedItem() + "");
            }
        });

    }
}
