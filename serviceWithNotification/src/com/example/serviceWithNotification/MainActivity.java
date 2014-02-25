package com.example.serviceWithNotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    public static boolean isService = false;

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
