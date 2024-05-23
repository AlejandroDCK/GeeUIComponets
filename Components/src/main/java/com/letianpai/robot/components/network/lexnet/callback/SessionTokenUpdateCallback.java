package com.letianpai.robot.components.network.lexnet.callback;


import com.letianpai.robot.components.network.lexnet.parser.SessionTokenData;

/**
 * Created by liujunbin
 */

public class SessionTokenUpdateCallback {

    private SessionTokenUpdateListener mSessionTokenUpdateListener;

    private static class RobotCommandWordsCallbackHolder {
        private static SessionTokenUpdateCallback instance = new SessionTokenUpdateCallback();
    }

    private SessionTokenUpdateCallback() {

    }

    public static SessionTokenUpdateCallback getInstance() {
        return RobotCommandWordsCallbackHolder.instance;
    }

    public interface SessionTokenUpdateListener {
        void updateSessionTokenInfo(SessionTokenData sessionTokenData);
    }

    public void setSessionTokenUpdateListener(SessionTokenUpdateListener listener) {
        this.mSessionTokenUpdateListener = listener;
    }

    public void updateSessionToken(SessionTokenData sessionTokenData) {
        if (mSessionTokenUpdateListener != null) {
            mSessionTokenUpdateListener.updateSessionTokenInfo(sessionTokenData);
        }
    }



}
