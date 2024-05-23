package com.letianpai.robot.components.network.lexnet.callback;

import com.letianpai.robot.components.parser.logo.LogoInfo;

public class DeviceChannelLogoCallBack {
    private DeviceChannelLogoUpdateListener mDeviceChannelLogoUpdateListener;


    private DeviceChannelLogoCallBack() {

    }
    private static class DeviceChannelLogoCallBackHolder {
        private static DeviceChannelLogoCallBack instance = new DeviceChannelLogoCallBack();
    }

    public interface DeviceChannelLogoUpdateListener {
        void onLogoInfoUpdate(LogoInfo logoInfo);

    }
    public static DeviceChannelLogoCallBack getInstance() {
        return DeviceChannelLogoCallBack.DeviceChannelLogoCallBackHolder.instance;
    }

    public DeviceChannelLogoUpdateListener getmDeviceChannelLogoUpdateListener() {
        return mDeviceChannelLogoUpdateListener;
    }

    public void setDeviceChannelLogoUpdateListener(DeviceChannelLogoUpdateListener mDeviceChannelLogoUpdateListener) {
        this.mDeviceChannelLogoUpdateListener = mDeviceChannelLogoUpdateListener;
    }


    public void setDeviceChannelLogo(LogoInfo logoInfo) {
        if (mDeviceChannelLogoUpdateListener != null) {
            mDeviceChannelLogoUpdateListener.onLogoInfoUpdate(logoInfo);
        }
    }
}
