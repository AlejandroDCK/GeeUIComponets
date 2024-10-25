package com.letianpai.robot.components.network.callback

/**
 * Created by liujunbin
 */
class AppQRCodeInfoCallback private constructor() {
    private val mAppQRCodeInfoUpdateListener: MutableList<AppQRCodeInfoUpdateListener?>? =
        ArrayList()

    private object AppQRCodeInfoCallbackHolder {
        val instance: AppQRCodeInfoCallback = AppQRCodeInfoCallback()
    }

    interface AppQRCodeInfoUpdateListener {
        fun onAppQRCodeInfoUpdateReceived(qrcodeString: String, isShowQrcode: Boolean)
    }

    fun registerAppQRCodeInfoUpdateListener(listener: AppQRCodeInfoUpdateListener?) {
        mAppQRCodeInfoUpdateListener?.add(listener)
    }

    fun unregisterAppQRCodeInfoUpdateListener(listener: AppQRCodeInfoUpdateListener?) {
        mAppQRCodeInfoUpdateListener?.remove(listener)
    }

    fun setQrCodeInfo(qrcodeString: String, isShowQrcode: Boolean) {
        for (i in mAppQRCodeInfoUpdateListener!!.indices) {
            if (mAppQRCodeInfoUpdateListener[i] != null) {
                mAppQRCodeInfoUpdateListener[i]!!
                    .onAppQRCodeInfoUpdateReceived(qrcodeString, isShowQrcode)
            }
        }
    }

    companion object {
        val instance: AppQRCodeInfoCallback
            get() = AppQRCodeInfoCallbackHolder.instance
    }
}
