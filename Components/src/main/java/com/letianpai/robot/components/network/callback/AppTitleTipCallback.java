package com.letianpai.robot.components.network.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujunbin
 */

public class AppTitleTipCallback {
    private List<AppTitleTipUpdateListener> mAppTitleTipUpdateListener = new ArrayList();

    private static class AppTitleTipCallbackHolder {
        private static AppTitleTipCallback instance = new AppTitleTipCallback();
    }

    private AppTitleTipCallback() {

    }

    public static AppTitleTipCallback getInstance() {
        return AppTitleTipCallbackHolder.instance;
    }

    public interface AppTitleTipUpdateListener {
        void onAppTitleTipUpdateReceived(String tipName,String tipNameEn,String iconPath);
    }

    public void registerTimerKeeperUpdateListener(AppTitleTipUpdateListener listener) {
        if (mAppTitleTipUpdateListener != null) {
            mAppTitleTipUpdateListener.add(listener);
        }
    }

    public void unregisterTimerKeeperUpdateListener(AppTitleTipUpdateListener listener) {
        if (mAppTitleTipUpdateListener != null) {
            mAppTitleTipUpdateListener.remove(listener);
        }
    }

    public void setAppTitleTip(String tipName,String tipsNameEn,String iconPath) {
        for (int i = 0; i < mAppTitleTipUpdateListener.size(); i++) {
            if (mAppTitleTipUpdateListener.get(i) != null) {
                mAppTitleTipUpdateListener.get(i).onAppTitleTipUpdateReceived(tipName,tipsNameEn,iconPath);
            }
        }
    }

}
