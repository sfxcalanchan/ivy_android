package com.example.serviceWithNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by developer on 24/2/14.
 */
public class BackgroundService extends Service {

    private NotificationManager mNM;
    Bundle b;
    Intent notificationIntent;

    private final IBinder mBinder = new LocalBinder();
    private String newtext;

    public class LocalBinder extends Binder
    {
        BackgroundService getService()
        {
            return BackgroundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        newtext = "BackGroundApp Service Running";

        Notification notification = new Notification(R.drawable.ic_launcher, newtext,System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(BackgroundService.this, 0, new Intent(BackgroundService.this,MainActivity.class), 0);
        notification.setLatestEventInfo(BackgroundService.this,"BackgroundAppExample", newtext, contentIntent);
        mNM.notify(R.string.local_service_started, notification);
        notificationIntent = new Intent(this, MainActivity.class);
        showNotification();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public void onDestroy() {
        mNM.cancel(R.string.local_service_started);
        stopSelf();
    }

    private void showNotification() {
        CharSequence text = getText(R.string.local_service_started);

        Notification notification = new Notification(R.drawable.ic_launcher, text, System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,new Intent(this, MainActivity.class), 0);
        notification.setLatestEventInfo(this, "MainActivity",newtext, contentIntent);
        notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        mNM.notify(R.string.local_service_started, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
