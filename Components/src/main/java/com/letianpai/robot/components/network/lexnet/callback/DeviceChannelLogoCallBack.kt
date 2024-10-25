package com.letianpai.robot.components.network.lexnet.callback

import com.letianpai.robot.components.parser.logo.LogoInfo

class DeviceChannelLogoCallBack private constructor() {
    private var mDeviceChannelLogoUpdateListener: DeviceChannelLogoUpdateListener? = null


    private object DeviceChannelLogoCallBackHolder {
        val instance: DeviceChannelLogoCallBack = DeviceChannelLogoCallBack()
    }

    interface DeviceChannelLogoUpdateListener {
        fun onLogoInfoUpdate(logoInfo: LogoInfo?)
    }

    fun getmDeviceChannelLogoUpdateListener(): DeviceChannelLogoUpdateListener? {
        return mDeviceChannelLogoUpdateListener
    }

    fun setDeviceChannelLogoUpdateListener(mDeviceChannelLogoUpdateListener: DeviceChannelLogoUpdateListener?) {
        this.mDeviceChannelLogoUpdateListener = mDeviceChannelLogoUpdateListener
    }


    fun setDeviceChannelLogo(logoInfo: LogoInfo?) {
        if (mDeviceChannelLogoUpdateListener != null) {
            mDeviceChannelLogoUpdateListener!!.onLogoInfoUpdate(logoInfo)
        }
    }

    companion object {
        val instance: DeviceChannelLogoCallBack
            get() = DeviceChannelLogoCallBackHolder.instance
    }
}
