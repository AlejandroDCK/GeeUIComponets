package com.letianpai.robot.components.callback.remind

import com.letianpai.robot.components.parser.remind.ReminderInfo


/**
 * Created by liujunbin
 */
class ReminderInfoUpdateCallback private constructor() {
    private var mReminderInfoListener: ReminderInfoListener? = null

    private object ReminderInfoUpdateCallbackHolder {
        val instance: ReminderInfoUpdateCallback = ReminderInfoUpdateCallback()
    }

    interface ReminderInfoListener {
        fun updateReminderInfo(mStockInfo: ReminderInfo?)
    }

    fun seReminderInfoUpdateListener(listener: ReminderInfoListener?) {
        this.mReminderInfoListener = listener
    }

    fun updateReminderInfo(reminderInfo: ReminderInfo?) {
        if (mReminderInfoListener != null) {
            mReminderInfoListener!!.updateReminderInfo(reminderInfo)
        }
    }


    companion object {
        val instance: ReminderInfoUpdateCallback
            get() {
                return ReminderInfoUpdateCallbackHolder.instance
            }
    }
}
