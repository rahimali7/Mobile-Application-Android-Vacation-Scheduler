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

public class MyReceiver2 extends BroadcastReceiver {
    String channel_id = "test1";
    String channel_id2 = "test2";
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Toast.makeText(context, intent.getStringExtra("vacation Time"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context,channel_id);
        Notification n=new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("vacation Time"))
                .setContentTitle("Vacation Date").build();
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++,n);


        /*Toast.makeText(context, intent.getStringExtra("vacation end"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context,channel_id2);
        Notification notification=new NotificationCompat.Builder(context,channel_id2)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("vacation end"))
                .setContentTitle("Vacation End").build();
        NotificationManager notificationManager2=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager2.notify(notificationID++,notification);*/


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
