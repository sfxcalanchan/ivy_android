package com.example.serviceWithNotification;


import java.util.List;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.utils.L;




public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    public static boolean isService = false;
    private BeaconManager beaconManager;
    private LeDeviceListAdapter adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button startserviceButton = (Button) findViewById(R.id.button1);
        startserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,BackgroundService.class));
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                isService = true;
            }
        });
        
        
        Log.d(ACTIVITY_SERVICE, "fuck off");
     // Configure BeaconManager.
        adapter = new LeDeviceListAdapter(this);
        beaconManager = new BeaconManager(this);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
          @Override
          public void onBeaconsDiscovered(com.estimote.sdk.Region region, final List<Beacon> beacons) {
            // Note that results are not delivered on UI thread.
            runOnUiThread(new Runnable() {
              @Override
              public void run() {
                //getActionBar().setSubtitle("Found beacons: " + beacons.size());
                //adapter.replaceWith(beacons);
            	  Log.d(ACTIVITY_SERVICE, "fuck off123");
              }
            });
          }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        
        
        stopService(new Intent(MainActivity.this,
                BackgroundService.class));
        if(isService)
        {
            TextView tv = (TextView) findViewById(R.id.textView1);
            tv.setText("Service Resumed");
            isService = false;
        }
    }


    //    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(android.R.menu.main, menu);
//        return true;
//    }
}
