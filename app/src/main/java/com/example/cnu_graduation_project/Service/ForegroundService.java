package com.example.cnu_graduation_project.Service;


import static android.app.Notification.PRIORITY_MIN;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.cnu_graduation_project.Lock.LockActivity;
import com.example.cnu_graduation_project.R;

/**
 * 어플리케이션의 지속적인 동작을 위한 서비스
 * 상단바 알림을 이용하여 서비스 유지
 */
public class ForegroundService extends Service {

    private static final int NOTIFI_ID = 2098;
    private static final String NOTIFI_CHANNEL_ID = "default";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if("startForeground".equals(intent.getAction())) {
            startForegroundService();

        }

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    private boolean startForegroundService() {

        Intent notificationIntent = new Intent(this, LockActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(NOTIFI_CHANNEL_ID, "백그라운드 실행 채널", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);

            Notification notification
                    = new NotificationCompat.Builder(this, NOTIFI_CHANNEL_ID)
                    .setOngoing(true)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("운전 중입니다")
                    .setContentText("운전 중입니다. 사용을 자제하세요!")
                    .setContentIntent(pendingIntent)
                    .setPriority(Notification.PRIORITY_MAX)
                    .build();

            startForeground(NOTIFI_ID, notification);
            return true;
        }
        return false;
    }
}
