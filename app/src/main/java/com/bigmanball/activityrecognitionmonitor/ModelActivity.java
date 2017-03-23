package com.bigmanball.activityrecognitionmonitor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by brianperet on 3/13/17.
 */

public class ModelActivity implements Serializable {

    private int mActivity;
    private int mConfidence;
    private Date mDate;

    public ModelActivity(int activity, int confidence){
        mActivity = activity;
        mConfidence = confidence;
        mDate = new Date();
    }

    public int getActivity() {
        return mActivity;
    }

    public int getConfidence() {
        return mConfidence;
    }

    public Date getDate() {
        return mDate;
    }
}
