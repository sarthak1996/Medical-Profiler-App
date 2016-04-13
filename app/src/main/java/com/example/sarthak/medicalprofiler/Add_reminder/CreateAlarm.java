package com.example.sarthak.medicalprofiler.Add_reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Sarthak on 1/31/2016.
 */
public class CreateAlarm {
    private Intent intent;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Context ctx;
    private PickedTimeDate pickedTimeDate;

    public CreateAlarm(Context ctx, PickedTimeDate pickedTimeDate) {
        this.ctx = ctx;
        this.pickedTimeDate = pickedTimeDate;
    }

    public void createAlarmToNotify() {
        intent = new Intent(ctx, GenerateNotification.class);
        intent.putExtra("title", pickedTimeDate.getTitle());
        intent.putExtra("description", pickedTimeDate.getDescription() + "");
        intent.putExtra("time", "" + pickedTimeDate.getDate() + "/" + pickedTimeDate.getMonth() + ":" + pickedTimeDate.getHour() + ":" + pickedTimeDate.getMinute());
        alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        Log.d("h", "" + calendar.getTimeInMillis());

        calendar.set(Calendar.YEAR, pickedTimeDate.getYear());
        calendar.set(Calendar.MONTH, pickedTimeDate.getMonth() - 1);
        calendar.set(Calendar.DATE, pickedTimeDate.getDate());
        calendar.set(Calendar.HOUR_OF_DAY, pickedTimeDate.getHour());
        calendar.set(Calendar.MINUTE, pickedTimeDate.getMinute());
        calendar.set(Calendar.SECOND,0);
        if (pickedTimeDate.getTitle() == null || pickedTimeDate.getTitle().equals("") || pickedTimeDate.getTitle().isEmpty()) {

        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

}
