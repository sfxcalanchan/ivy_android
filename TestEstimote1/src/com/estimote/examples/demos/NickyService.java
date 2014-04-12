package com.estimote.examples.demos;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

//繼承android.app.Service
public class NickyService extends Service {
    private Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handler.postDelayed(showTime, 1000);
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(showTime);
        super.onDestroy();
    }
    
    private Runnable showTime = new Runnable() {
        public void run() {
            //log目前時間
            Log.d(ACTIVITY_SERVICE, "fuck off");
        	System.out.println("new Date():" + new Date().toString());
            handler.postDelayed(this, 1000);

            
            //showNotification();
        }
    };
    
    /*private void showNotification() {
//    	 CharSequence text = "ABc";
//    	 Notification notification = new Notification(R.drawable.ic_launcher, text,System.currentTimeMillis());
//    	 PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//    	                new Intent(this, MainActivity.class), 0);
//    	notification.setLatestEventInfo(this, "thisav",
//    	      text, contentIntent);
//    	nm.notify(R.string.service_started, notification);
    	
    	Intent resultIntent = new Intent(this, AllDemosActivity.class);
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    	// Adds the back stack
    	stackBuilder.addParentStack(AllDemosActivity.class);
    	// Adds the Intent to the top of the stack
    	stackBuilder.addNextIntent(resultIntent);
    	// Gets a PendingIntent containing the entire back stack
    	PendingIntent resultPendingIntent =
    	        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    	
    	NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    	builder.setContentIntent(resultPendingIntent);
    	    	    	
    	NotificationManager mNotificationManager =    			
    	    (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
    	mNotificationManager.notify(1, builder.build());    	
    	Log.i("showNotification", "showNotification");
    }*/
}
