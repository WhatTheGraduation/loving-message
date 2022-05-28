package com.example.cnu_graduation_project;
/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Bundle;
import android.util.Log;


/**
 * Demos enabling/disabling Activity Recognition transitions, e.g., starting or stopping a walk,
 * run, drive, etc.).
 */

/**
 * 사용자 페이지
 */
public class ClientActivity extends DrivingRecognitionActivity {

    String TAG = "ClientActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Create");
        /**
         * 운전 상태 인식시에
         */
        if(TaskTag.ACTIVITY_TAG&&TaskTag.WINDOW_ON) {
        } else {
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(TaskTag.ACTIVITY_TAG&&!TaskTag.WINDOW_ON) {
        }
    }
}
