package com.example.cnu_graduation_project.Lock;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * 백그라운드 작업
 * 잠금화면을 실행하기 위한 서비스
 *
 * 시퀀스 다이어 그램에서 잠금화면에 해당
 *
 */
public class LockService extends Service {
    private LockReceiver mReceiver = null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mReceiver = new LockReceiver();
        /**
         * 스크린이 꺼짐을 감지하는 리시버 등록
         */
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mReceiver, filter);
    }

    /**
     * 스크린이 꺼짐을 감지하는 리시버 등록
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        if(intent != null){
            if(intent.getAction()==null){
                if(mReceiver==null){
                    mReceiver = new LockReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    filter.addAction(Intent.ACTION_SCREEN_ON);
                    registerReceiver(mReceiver, filter);
                }
            }
        }
        return START_REDELIVER_INTENT;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mReceiver != null){
            unregisterReceiver(mReceiver);
        }
    }
}

