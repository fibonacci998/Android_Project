package com.example.startup;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Context context = getApplicationContext();
        CountDownTimer countDownTimer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    FileOutputStream fos = context.openFileOutput("data.txt", Context.MODE_APPEND);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write("data: "+millisUntilFinished);
                    osw.write("\n");
                    osw.close();
                    fos.close();
                }catch (Exception e){
                    Log.d("onTick: ",e.toString());
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                String NOTIFICATION_CHANNEL_ID = "101";

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification",
                            NotificationManager.IMPORTANCE_HIGH);

                    //Configure Notification Channel
                    notificationChannel.setDescription("Game Notifications");
                    notificationChannel.enableLights(true);
                    notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    notificationChannel.enableVibration(true);

                    notificationManager.createNotificationChannel(notificationChannel);
                }

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("test")
                        .setAutoCancel(true)
                        .setContentText("baams nust")
                        .setContentIntent(pendingIntent)
                        .setWhen(System.currentTimeMillis())
                        .setPriority(Notification.PRIORITY_MAX);


                notificationManager.notify(1, notificationBuilder.build());

            }
        };
        countDownTimer.start();

        return super.onStartCommand(intent, flags, startId);
    }
}
