package com.example.vacationscheduler.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.vacationscheduler.R;

public class MyReceiver extends BroadcastReceiver {
    String channel_id = "test";
    String channel_id2 = "test2";
    static int notificationID;
    static int notificationID2;

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context,channel_id);
        Notification n=new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("Excursion Date").build();
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++,n);



        Toast.makeText(context, intent.getStringExtra("key2"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context,channel_id);
        Notification notification=new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("key2"))
                .setContentTitle("Vacation Start Date").build();
        NotificationManager notificationManager2=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++,notification);
    }
    private void createNotificationChannel(Context context, String CHANNEL_ID){
        CharSequence name="mychannelname";
        String description="mychanneldescription";
        int importance= NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel= new NotificationChannel(CHANNEL_ID,name,importance);
        channel.setDescription(description);
        NotificationManager notificationManager=context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
