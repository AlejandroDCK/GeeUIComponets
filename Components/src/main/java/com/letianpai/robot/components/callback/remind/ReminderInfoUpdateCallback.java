package com.letianpai.robot.components.callback.remind;


import android.util.Log;

import com.letianpai.robot.components.parser.remind.ReminderInfo;


/**
 * Created by liujunbin
 */

public class ReminderInfoUpdateCallback {

    private ReminderInfoListener mReminderInfoListener;

    private static class ReminderInfoUpdateCallbackHolder {
        private static ReminderInfoUpdateCallback instance = new ReminderInfoUpdateCallback();
    }

    private ReminderInfoUpdateCallback() {

    }

    public static ReminderInfoUpdateCallback getInstance() {
        return ReminderInfoUpdateCallbackHolder.instance;
    }

    public interface ReminderInfoListener {
        void updateReminderInfo(ReminderInfo mStockInfo);
    }

    public void seReminderInfoUpdateListener(ReminderInfoListener listener) {
        this.mReminderInfoListener = listener;
    }

    public void updateReminderInfo(ReminderInfo reminderInfo) {
        if (mReminderInfoListener != null) {
            mReminderInfoListener.updateReminderInfo(reminderInfo);
        }
    }


}
