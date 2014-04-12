package com.estimote.examples.demos;

//import LeDeviceListAdapter;
import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.estimote.examples.demos.R;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.utils.L;

import java.util.Collections;
import java.util.List;

/**
 * Displays list of found beacons sorted by RSSI.
 * Starts new activity with selected beacon if activity was provided.
 *
 * @author wiktorgworek@google.com (Wiktor Gworek)
 */
public class ListBeaconsActivity extends Service {

  private static final String TAG = ListBeaconsActivity.class.getSimpleName();

  public static final String EXTRAS_TARGET_ACTIVITY = "extrasTargetActivity";
  public static final String EXTRAS_BEACON = "extrasBeacon";

  private static final int REQUEST_ENABLE_BT = 1234;
  //private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
  private static final String ESTIMOTE_PROXIMITY_UUID = "ABCDDCBA-B644-4520-8F0C-720EAF059935";
  private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);

  private BeaconManager beaconManager;
  private LeDeviceListAdapter adapter;

  //@Override
  //protected void onCreate(Bundle savedInstanceState) {
	  public void onCreate() {
    //super.onCreate(savedInstanceState);
    //setContentView(R.layout.main);
    //getActionBar().setDisplayHomeAsUpEnabled(true);

    // Configure device list.
    //adapter = new LeDeviceListAdapter(this);
    //ListView list = (ListView) findViewById(R.id.device_list);
    //list.setAdapter(adapter);
    //list.setOnItemClickListener(createOnItemClickListener());

    // Configure verbose debug logging.
    L.enableDebugLogging(true);

    // Configure BeaconManager.
    beaconManager = new BeaconManager(this);
    beaconManager.setRangingListener(new BeaconManager.RangingListener() {
      @Override
      public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
        // Note that results are not delivered on UI thread.
        //runOnUiThread(new Runnable() {
          //@Override
          //public void run() {
            //getActionBar().setSubtitle("Found beacons: " + beacons.size());
            //adapter.replaceWith(beacons);
          //}
        //});
      }
    });
  }

  /*@Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.scan_menu, menu);
    MenuItem refreshItem = menu.findItem(R.id.refresh);
    refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }*/

  @Override
  public void onDestroy() {
    beaconManager.disconnect();

    super.onDestroy();
  }

  /*@Override
  public void onStart(Intent intent, int startId) {
      //handler.postDelayed(showTime, 1000);
	  System.out.println("bbb");
      super.onStart(intent, startId);
  }*/
  @Override
  public void onStart(Intent intent, int startId) {
	  String a = "1234";
	  a="123";
	  
    super.onStart(intent, startId);
    //onCreate();
    //beaconManager = new BeaconManager(this);
    
    
    // Check if device supports Bluetooth Low Energy.
    if (!beaconManager.hasBluetooth()) {
      Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
      return;
    }

    // If Bluetooth is not enabled, let user enable it.
    if (!beaconManager.isBluetoothEnabled()) {
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    } else {
      connectToService();
    }
  }

  /*@Override
  public void onStop() {
    try {
      beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS_REGION);
    } catch (RemoteException e) {
      Log.d(TAG, "Error while stopping ranging", e);
    }

    super.onStop();
  }*/

  /*@Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_ENABLE_BT) {
      if (resultCode == Activity.RESULT_OK) {
        connectToService();
      } else {
        Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();
        getActionBar().setSubtitle("Bluetooth not enabled");
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }*/

  private void connectToService() {
    //getActionBar().setSubtitle("Scanning...");
    //adapter.replaceWith(Collections.<Beacon>emptyList());
    beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
      @Override
      public void onServiceReady() {
        try {
          beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
          Toast.makeText(ListBeaconsActivity.this, "Test 1", Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
          Toast.makeText(ListBeaconsActivity.this, "Cannot start ranging, something terrible happened",
              Toast.LENGTH_LONG).show();
          Log.e(TAG, "Cannot start ranging", e);
        }
      }
    });
  }

@Override
public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
}

  /*private AdapterView.OnItemClickListener createOnItemClickListener() {
    return new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (getIntent().getStringExtra(EXTRAS_TARGET_ACTIVITY) != null) {
          try {
            Class<?> clazz = Class.forName(getIntent().getStringExtra(EXTRAS_TARGET_ACTIVITY));
            Intent intent = new Intent(ListBeaconsActivity.this, clazz);
            intent.putExtra(EXTRAS_BEACON, adapter.getItem(position));
            startActivity(intent);
          } catch (ClassNotFoundException e) {
            Log.e(TAG, "Finding class by name failed", e);
          }
        }
      }
    };
  }*/

}
