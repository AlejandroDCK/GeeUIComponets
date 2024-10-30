package com.letianpai.robot.components.network.callback

/**
 * Created by liujunbin
 */
class AppUploadConfigCallback private constructor() {
    private val mAppUploadConfigUpdateListener: MutableList<AppUploadConfigUpdateListener?>? =
        ArrayList()

    private object AppUploadConfigCallbackHolder {
        val instance: AppUploadConfigCallback = AppUploadConfigCallback()
    }

    interface AppUploadConfigUpdateListener {
        fun onAppUploadConfigUpdate(uploadFrequency: Int)
    }

    fun registerAppUploadConfigUpdateListener(listener: AppUploadConfigUpdateListener?) {
        mAppUploadConfigUpdateListener?.add(listener)
    }

    fun unregisterAppUploadConfigUpdateListener(listener: AppUploadConfigUpdateListener?) {
        mAppUploadConfigUpdateListener?.remove(listener)
    }

    fun setAppUploadConfig(uploadFrequency: Int) {
        for (i in mAppUploadConfigUpdateListener!!.indices) {
            if (mAppUploadConfigUpdateListener[i] != null) {
                mAppUploadConfigUpdateListener[i]!!.onAppUploadConfigUpdate(uploadFrequency)
            }
        }
    }

    companion object {
        val instance: AppUploadConfigCallback = AppUploadConfigCallbackHolder.instance
    }
}
