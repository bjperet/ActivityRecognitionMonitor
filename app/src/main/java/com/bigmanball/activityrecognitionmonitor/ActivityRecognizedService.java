package com.bigmanball.activityrecognitionmonitor;


import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianperet on 3/13/17.
 */

public class ActivityRecognizedService extends IntentService {

    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities( result.getProbableActivities() );
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        int type = 0;
        int confidence = 0;
        for(int i = 0; i < probableActivities.size(); i++){
            if(probableActivities.get(i).getConfidence() > confidence){
                type = probableActivities.get(i).getType();
                confidence = probableActivities.get(i).getConfidence();
            }
        }

        ModelActivity currentActivity = new ModelActivity(type, confidence);
        sendInfo(currentActivity);
    }

    public void sendInfo(ModelActivity activity){
        Intent intent = new Intent("Activity_Recognition");
        intent.putExtra("motion", activity);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
