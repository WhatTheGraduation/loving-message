package com.example.cnu_graduation_project.Lock;

import static com.example.cnu_graduation_project.TaskTag.WINDOW_ON;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.cnu_graduation_project.Lock.LockActivity;

import java.util.TimerTask;
import java.util.concurrent.locks.Lock;

public class LockReceiver extends BroadcastReceiver {
    String TAG="LockReceiver";
    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d(TAG, "Screen Off");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d(TAG, "Screen On");
            /**
             * 화면 종료 인식시 리시버 호출
             */
            Intent screenIntent = new Intent(context, LockActivity.class);
            screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(screenIntent);
        }
    }

}
