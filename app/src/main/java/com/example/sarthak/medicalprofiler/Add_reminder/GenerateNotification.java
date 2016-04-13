package com.example.sarthak.medicalprofiler.Add_reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.sarthak.medicalprofiler.MainActivity;
import com.example.sarthak.medicalprofiler.R;

import java.util.Calendar;

/**
 * Created by Sarthak on 1/31/2016.
 */
public class GenerateNotification extends Service {
    private Uri sound;
    private NotificationManager notificationManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Notification notification;
    private String title="";
    private String description="";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int r= super.onStartCommand(intent, flags, startId);
        if(intent!=null) {
            title = intent.getExtras().getString("title").toString();
            description = intent.getExtras().getString("description").toString() + intent.getExtras().getString("time").toString();
            if(title==null || title.trim().equals("") || title.isEmpty()){
                return 0;
            }
        }
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        intent = new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setSound(sound)
                .build();
        //build a unique id
        notificationManager.notify(1, notification);
        //.addAction to add button in notification
        return r;
    }
}
