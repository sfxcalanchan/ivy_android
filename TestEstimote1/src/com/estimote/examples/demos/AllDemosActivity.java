package com.estimote.examples.demos;

import com.estimote.examples.demos.R;
//import com.example.test2.MainActivity;
import com.estimote.examples.demos.NickyService;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Shows all available demos.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class AllDemosActivity extends Activity {
	private Button startButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.all_demos);
    
    startButton = (Button) findViewById(R.id.distance_demo_button);
    startButton.setOnClickListener(startClickListener);
    
    //R.id.distance_demo_button.setOnClickListener(startClickListener);
    /*findViewById(R.id.distance_demo_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Intent intent = new Intent(AllDemosActivity.this, ListBeaconsActivity.class);
        //intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, DistanceBeaconActivity.class.getName());
        //startActivity(intent);
    	  Intent intent = new Intent(AllDemosActivity.this, NickyService.class);
          
        startService(intent);
      }
    });*/
    
    
    
    findViewById(R.id.notify_demo_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(AllDemosActivity.this, ListBeaconsActivity.class);
        intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, NotifyDemoActivity.class.getName());
        startActivity(intent);
      }
    });
  }
  
  private Button.OnClickListener startClickListener = new Button.OnClickListener() {
      public void onClick(View arg0) {
          //啟動服務
          Intent intent = new Intent(AllDemosActivity.this, ListBeaconsActivity.class);
          startService(intent);
      }
  };
}
