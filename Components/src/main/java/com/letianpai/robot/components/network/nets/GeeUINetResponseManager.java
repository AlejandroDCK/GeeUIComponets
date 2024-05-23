package com.letianpai.robot.components.network.nets;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.letianpai.robot.components.network.lexnet.callback.DeviceChannelLogoCallBack;
import com.letianpai.robot.components.network.system.SystemUtil;
import com.letianpai.robot.components.parser.logo.LogoInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liujunbin
 */
public class GeeUINetResponseManager {

    private static GeeUINetResponseManager instance;
    private Context mContext;
    private Gson gson;

    private LogoInfo logoInfo;
    private GeeUINetResponseManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        gson = new Gson();
    }

    public static GeeUINetResponseManager getInstance(Context context) {
        synchronized (GeeUINetResponseManager.class) {
            if (instance == null) {
                instance = new GeeUINetResponseManager(context.getApplicationContext());
            }
            return instance;
        }

    }

    public void getRobotCustomInfo() {
        if (WIFIConnectionManager.getInstance(mContext).isNetworkAvailable(mContext)) {
            updateCustomInfo();
        }
    }

    public void dispatchTask(String cmd, Object data) {
        if (cmd == null) {
            return;
        }
        if (cmd.equals(RobotRemoteConsts.COMMAND_TYPE_UPDATE_COUNT_DOWN_CONFIG_DATA)) {
            updateCustomInfo();
        }
    }

    /**
     *
     */
    public void updateCustomInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getCustomInfo();
            }
        }).start();
    }

    public void getCustomInfo() {
        GeeUiNetManager.getCustomList(mContext, new Callback() {
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

                    Log.e("RemoteCmdResponser", "commandDistribute:command ========= 6 ======== info: "+ info);

                }
            }
        });
    }
    public void updateDeviceBindStatus() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isDeviceBind();
            }
        }).start();
    }

    public void isDeviceBind() {
        GeeUiNetManager.isDeviceBind(mContext, new Callback() {
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

                    Log.e("RemoteCmdResponser", "commandDistribute:command ========= 6 ======== info: "+ info);

                }
            }
        });
    }

    public LogoInfo getLogoInfo() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getDeviceChannelLogo(SystemUtil.isInChinese());
                }
            }).start();

        return logoInfo;
    }

    private void getDeviceChannelLogo(boolean isChinese) {
        GeeUiNetManager.getDeviceChannelLogo(mContext, isChinese,new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response != null && response.body() != null) {
                    LogoInfo generalInfo = null;
                    String info = response.body().string();

                    if (info != null) {
                        try{
                            generalInfo = new Gson().fromJson(info, LogoInfo.class);
                            if (generalInfo != null) {
                               DeviceChannelLogoCallBack.getInstance().setDeviceChannelLogo(generalInfo);
                            } else {
                                Log.e("letianpai_1234", "getDeviceChannelLogo is null: ");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }


}
