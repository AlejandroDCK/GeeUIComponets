package com.letianpai.robot.components.network.lexnet;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.letianpai.robot.components.network.lexnet.callback.SessionTokenUpdateCallback;
import com.letianpai.robot.components.network.lexnet.parser.LexSessionToken;
import com.letianpai.robot.components.network.lexnet.parser.SessionTokenData;
import com.letianpai.robot.components.network.nets.GeeUiNetManager;
import com.letianpai.robot.components.network.nets.WIFIConnectionManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liujunbin
 */
public class LexNetworkManager {

    private static LexNetworkManager instance;
    private Context mContext;
    private Gson gson;
    private SessionTokenData sessionTokenData;

    private LexNetworkManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        gson = new Gson();
    }

    public static LexNetworkManager getInstance(Context context) {
        synchronized (LexNetworkManager.class) {
            if (instance == null) {
                instance = new LexNetworkManager(context.getApplicationContext());
            }
            return instance;
        }

    }

    /**
     *
     */
    public void getRobotSessionToken() {
        if (!WIFIConnectionManager.getInstance(mContext).isNetworkAvailable(mContext)) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                getSessionToken();
            }
        }).start();
    }

    private void getSessionToken() {
        GeeUiNetManager.getSessionToken(mContext, false, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response != null && response.body() != null) {

                    String info = "";
                    if (response != null && response.body() != null) {
                        info = response.body().string();
                    }
                    LexSessionToken lexSessionToken;
                    try {
                        if (info != null) {
                            lexSessionToken = new Gson().fromJson(info, LexSessionToken.class);
                            if (lexSessionToken != null && lexSessionToken.getData() != null) {
                                sessionTokenData = lexSessionToken.getData();
                                SessionTokenUpdateCallback.getInstance().updateSessionToken(sessionTokenData);
                            }

                        }
                        Log.e("letianpai_RemoteCmdResponser", "commandDistribute:command ========= 6 ======== info: " + info);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }

    public void getSessionTokenData() {
        long currentTime = (System.currentTimeMillis()) / 1000;
        if (sessionTokenData != null && (sessionTokenData.getExpire_time() > currentTime)) {
            SessionTokenUpdateCallback.getInstance().updateSessionToken(sessionTokenData);
        } else {
            getRobotSessionToken();
        }
    }

    /**
     * 注册回调信息
     */
    private void initSessionCallback() {
        SessionTokenUpdateCallback.getInstance().setSessionTokenUpdateListener(new SessionTokenUpdateCallback.SessionTokenUpdateListener() {
            @Override
            public void updateSessionTokenInfo(SessionTokenData sessionTokenData) {

                //TODO 做你想做的事
            }
        });
    }

}
