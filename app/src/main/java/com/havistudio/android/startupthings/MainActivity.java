package com.havistudio.android.startupthings;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;

    @BindView(R.id.floating_action_button)
    FloatingActionButton addingFB;
    @BindView(R.id.listView)
    ListView listView;

    private Realm realm;
    private AppSpinAdapter adapter;
    private MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Create the Realm instance
        realm = Realm.getDefaultInstance();

        RealmResults<StartUp> result2 = realm.where(StartUp.class).findAll();
        ListIterator<StartUp> LIResult2 = result2.listIterator();
        Log.i(TAG, "result2 size:"+result2.size());


        //basicCRUD(realm);

        RealmResults<StartUp> result = realm.where(StartUp.class).findAll();
        ListIterator<StartUp> LIResult = result.listIterator();
        while (LIResult.hasNext()) {
            StartUp element = LIResult.next();
            //showStatus(element.getId() + " " + element.getPackageName() + " " + element.getType());
        }

        listAdapter = new MyListAdapter(MainActivity.this,result);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }





    @OnClick(R.id.floating_action_button)
    public void addingFButton(FloatingActionButton addingFB){
        Intent intent = new Intent(this, AddingActivity.class);
        startActivity(intent);
    }

}
