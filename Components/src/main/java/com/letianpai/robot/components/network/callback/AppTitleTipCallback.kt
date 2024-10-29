package com.letianpai.robot.components.network.callback

/**
 * Created by liujunbin
 */
class AppTitleTipCallback private constructor() {
    private val mAppTitleTipUpdateListener: MutableList<AppTitleTipUpdateListener?>? =
        ArrayList()

    private object AppTitleTipCallbackHolder {
        val instance: AppTitleTipCallback = AppTitleTipCallback()
    }

    interface AppTitleTipUpdateListener {
        fun onAppTitleTipUpdateReceived(tipName: String?, tipNameEn: String?, iconPath: String?)
    }

    fun registerTimerKeeperUpdateListener(listener: AppTitleTipUpdateListener?) {
        mAppTitleTipUpdateListener?.add(listener)
    }

    fun unregisterTimerKeeperUpdateListener(listener: AppTitleTipUpdateListener?) {
        mAppTitleTipUpdateListener?.remove(listener)
    }

    fun setAppTitleTip(tipName: String?, tipsNameEn: String?, iconPath: String?) {
        for (i in mAppTitleTipUpdateListener!!.indices) {
            if (mAppTitleTipUpdateListener[i] != null) {
                mAppTitleTipUpdateListener[i]!!
                    .onAppTitleTipUpdateReceived(tipName, tipsNameEn, iconPath)
            }
        }
    }

    companion object {
        val instance: AppTitleTipCallback = AppTitleTipCallbackHolder.instance
    }
}
