package com.letianpai.robot.components.network.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujunbin
 */

public class AppUploadConfigCallback {
    private List<AppUploadConfigUpdateListener> mAppUploadConfigUpdateListener = new ArrayList();

    private static class AppUploadConfigCallbackHolder {
        private static AppUploadConfigCallback instance = new AppUploadConfigCallback();
    }

    private AppUploadConfigCallback() {

    }

    public static AppUploadConfigCallback getInstance() {
        return AppUploadConfigCallbackHolder.instance;
    }

    public interface AppUploadConfigUpdateListener {
        void onAppUploadConfigUpdate(int uploadFrequency);
    }

    public void registerAppUploadConfigUpdateListener(AppUploadConfigUpdateListener listener) {
        if (mAppUploadConfigUpdateListener != null) {
            mAppUploadConfigUpdateListener.add(listener);
        }
    }

    public void unregisterAppUploadConfigUpdateListener(AppUploadConfigUpdateListener listener) {
        if (mAppUploadConfigUpdateListener != null) {
            mAppUploadConfigUpdateListener.remove(listener);
        }
    }

    public void setAppUploadConfig(int uploadFrequency) {
        for (int i = 0; i < mAppUploadConfigUpdateListener.size(); i++) {
            if (mAppUploadConfigUpdateListener.get(i) != null) {
                mAppUploadConfigUpdateListener.get(i).onAppUploadConfigUpdate(uploadFrequency);
            }
        }
    }

}
