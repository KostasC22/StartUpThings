package com.havistudio.android.startupthings;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class AddingActivity extends AppCompatActivity {

    public static final String TAG = AddingActivity.class.getName();

//    private Realm realm;
//    private AppSpinAdapter adapter;
//    @BindView(R.id.apps_spinner)
//    Spinner appsSpinner;
//    @BindView(R.id.type_spinner)
//    Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        //ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        final PackageManager pm = getPackageManager();
//        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
//        List<MyApp> allApps = new ArrayList<MyApp>();
//
//        for (ApplicationInfo packageInfo : packages) {
//            String localPackageName = packageInfo.packageName;
//            String localAppName = packageInfo.loadLabel(getPackageManager()).toString();
//            Log.d(TAG, "Installed package :" + localPackageName);
//            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
//            Log.d(TAG, "AppName:" + localAppName);
//            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
//            MyApp lmyapp = new MyApp();
//            lmyapp.setPackageName(localPackageName);
//            lmyapp.setAppName(localAppName);
//            allApps.add(lmyapp);
//        }
//
//        MyApp[] myApps = allApps.toArray(new MyApp[allApps.size()]);
//        adapter = new AppSpinAdapter(this, android.R.layout.simple_spinner_item, myApps);
//        appsSpinner.setAdapter(adapter);
//
//        // Create the Realm instance
//        realm = Realm.getDefaultInstance();
    }

//    @OnClick(R.id.addingbutton)
//    public void AddingStartUp(Button button) {
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                // Add a Startup
//                Log.i(TAG,"adding");
//                StartUp startUpTemp = realm.createObject(StartUp.class, UUID.randomUUID().toString());
//                startUpTemp.setFileName("");
//                startUpTemp.setPackageName(appsSpinner.getSelectedItem() + "");
//                startUpTemp.setType(typeSpinner.getSelectedItem() + "");
//            }
//        });
//
//    }
}
