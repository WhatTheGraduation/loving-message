package com.example.cnu_graduation_project.Lock;

import static com.example.cnu_graduation_project.TaskTag.ACTIVITY_TAG;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.cnu_graduation_project.ClientActivity;
import com.example.cnu_graduation_project.R;
import com.example.cnu_graduation_project.Resource.LoveMessage;
import com.example.cnu_graduation_project.Service.ForegroundService;

/**
 * 잠금화면 페이지
 *
 *
 * 여기에서
 * case 1 구현 필요할 듯?
 * 잠금화면 자체를 다루는 엑티비티
 *
 *
 * 상속 과정
 *
 *  DrivingRecongnitionActivity --> FeedbackActivity --> ClientActivity --> LockActivity
 *
 */
public class LockActivity extends ClientActivity {
    static String TAG ="LockActivity";
    private Button closeBtn;
    private TextView messageTextView;

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE= 2323;


    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        Log.d(TAG,"Start "+ TAG);
        /**
         * 백그라운드 권한 부여
         */

        /**
         * ACTIBITY_TAG ==> activity recognition 에서 인식을 하면 true 로 변경
         * 현재는 true로 고정
         */
        if(ACTIVITY_TAG){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                    !Settings.canDrawOverlays(this)) {
                RequestPermission();
            }
            setContentView(R.layout.lock);
            /**
             * 잠금화면보다 높은 순위의 액티비티로 설정하고
             * 잠금화면을 지우는 태그
             */
            setShowWhenLocked(true);
            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            km.requestDismissKeyguard(this,null);


            closeBtn = findViewById(R.id.close);

            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent serviceIntent = new Intent(LockActivity.this, ForegroundService.class);
                    serviceIntent.setAction("startForeground");

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        ContextCompat.startForegroundService(LockActivity.this, serviceIntent);
                        finish();
                    } else {
                        startService(serviceIntent);
                    }
                }
            });
            messageTextView = findViewById(R.id.warn_message);
            messageTextView.setText(LoveMessage.getMessage());
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStart() {
        super.onStart();

        messageTextView.setText(LoveMessage.getMessage());
    }


    /**
     * 백그라운드 환경에서 앱이 실행될 수 있게 권한을 주는 함수
     *
     */
    private void RequestPermission() {
        // Check if Android M or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Show alert dialog to the user saying a separate permission is needed
            // Launch the settings activity if the user prefers
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getApplicationContext().getPackageName()));
            startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     *
     * 정확히 뭔지 모름 그냥 권한 부여 관련 함수
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(getApplicationContext())) {
//                    PermissionDenied();
                    Log.d(TAG,"권환 없음");
                } else {
                    // Permission Granted-System will work
                }
            }
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}

