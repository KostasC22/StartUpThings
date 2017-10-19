package com.havistudio.android.startupthings;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddingActivityFragment extends Fragment {

    public static final String TAG = AddingActivityFragment.class.getName();

    private Realm realm;
    private AppSpinAdapter adapter;
    @BindView(R.id.label_field)
    EditText labelField;
    @BindView(R.id.apps_spinner)
    Spinner appsSpinner;
    @BindView(R.id.type_spinner)
    Spinner typeSpinner;
    @BindView(R.id.data_field)
    EditText dataField;
    @BindView(R.id.delay_field)
    EditText delayField;

    public AddingActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootContainer = inflater.inflate(R.layout.fragment_adding, container, false);
        ButterKnife.bind(this, rootContainer);

        final PackageManager pm = this.getActivity().getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<MyApp> allApps = new ArrayList<MyApp>();

        for (ApplicationInfo packageInfo : packages) {
            String localPackageName = packageInfo.packageName;
            String localAppName = packageInfo.loadLabel(this.getActivity().getPackageManager()).toString();
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
        adapter = new AppSpinAdapter(this.getActivity(), android.R.layout.simple_spinner_item, myApps);
        appsSpinner.setAdapter(adapter);

        // Create the Realm instance
        realm = Realm.getDefaultInstance();

        return rootContainer;
    }

    @OnClick(R.id.addingbutton)
    public void AddingStartUp(Button button) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a Startup
                Log.i(TAG,"adding");
                Log.i(TAG,"adding"+appsSpinner.getSelectedItem().toString());
                Log.i(TAG,"adding type"+typeSpinner.getSelectedItem());
                Log.i(TAG,"adding label"+labelField.getText().toString());
                StartUp startUpTemp = realm.createObject(StartUp.class, UUID.randomUUID().toString());
                startUpTemp.setLabel(labelField.getText().toString());
                startUpTemp.setFileName("");
                startUpTemp.setPackageName(appsSpinner.getSelectedItem().toString() + "");
                startUpTemp.setType(typeSpinner.getSelectedItem() + "");
                startUpTemp.setData(dataField.getText().toString());
                Long tempDelay = 1000L;
                try{
                    tempDelay = Long.parseLong(delayField.getText().toString());
                } catch (Exception e){
                    e.printStackTrace();
                }
                startUpTemp.setDelay(tempDelay);
            }
        });

    }
}
