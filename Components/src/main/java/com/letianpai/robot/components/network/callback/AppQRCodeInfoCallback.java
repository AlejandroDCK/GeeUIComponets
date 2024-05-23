package com.letianpai.robot.components.network.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujunbin
 */

public class AppQRCodeInfoCallback {
    private List<AppQRCodeInfoUpdateListener> mAppQRCodeInfoUpdateListener = new ArrayList();

    private static class AppQRCodeInfoCallbackHolder {
        private static AppQRCodeInfoCallback instance = new AppQRCodeInfoCallback();
    }

    private AppQRCodeInfoCallback() {

    }

    public static AppQRCodeInfoCallback getInstance() {
        return AppQRCodeInfoCallbackHolder.instance;
    }

    public interface AppQRCodeInfoUpdateListener {
        void onAppQRCodeInfoUpdateReceived(String qrcodeString,boolean isShowQrcode);
    }

    public void registerAppQRCodeInfoUpdateListener(AppQRCodeInfoUpdateListener listener) {
        if (mAppQRCodeInfoUpdateListener != null) {
            mAppQRCodeInfoUpdateListener.add(listener);
        }
    }

    public void unregisterAppQRCodeInfoUpdateListener(AppQRCodeInfoUpdateListener listener) {
        if (mAppQRCodeInfoUpdateListener != null) {
            mAppQRCodeInfoUpdateListener.remove(listener);
        }
    }

    public void setQrCodeInfo(String qrcodeString,boolean isShowQrcode) {
        for (int i = 0; i < mAppQRCodeInfoUpdateListener.size(); i++) {
            if (mAppQRCodeInfoUpdateListener.get(i) != null) {
                mAppQRCodeInfoUpdateListener.get(i).onAppQRCodeInfoUpdateReceived(qrcodeString,isShowQrcode);
            }
        }
    }

}
