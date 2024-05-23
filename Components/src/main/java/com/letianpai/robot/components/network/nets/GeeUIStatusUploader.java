package com.letianpai.robot.components.network.nets;

import static android.content.Context.WIFI_SERVICE;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import android.widget.ExpandableListAdapter;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.letianpai.robot.components.charging.BatteryReceiver;
import com.letianpai.robot.components.charging.ChargingUpdateCallback;
import com.letianpai.robot.components.network.callback.AppUploadConfigCallback;
import com.letianpai.robot.components.network.system.SystemUtil;
import com.letianpai.robot.components.parser.base.BaseMessageInfo;
import com.letianpai.robot.components.storage.RobotSubConfigManager;
import com.letianpai.robot.components.utils.GeeUILogUtils;
import com.letianpai.robot.components.utils.SDCardUtil;
import com.letianpai.robot.components.utils.VolumeManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author
 */
public class GeeUIStatusUploader {
    public static final int HIGH_TEMP = 90;
    private static int currentPercent;
    private boolean changingStatus;
    private static final long UPDATE_INTERNAL_TIME = 60 * 60 * 1000;

    private static GeeUIStatusUploader instance;
    private Context mContext;

    public static GeeUIStatusUploader getInstance(Context context) {
        synchronized (GeeUIStatusUploader.class) {
            if (instance == null) {
                instance = new GeeUIStatusUploader(context.getApplicationContext());
            }
            return instance;
        }

    }

    private GeeUIStatusUploader(Context context) {
        this.mContext = context;
        init(context);
    }


    private void init(Context context) {
        setBatteryListener();
        ChargingUpdateCallback.getInstance().registerChargingStatusUpdateListener(new ChargingUpdateCallback.ChargingUpdateListener() {
            @Override
            public void onChargingUpdateReceived(boolean changingStatus, int percent) {
                currentPercent = percent;
            }

            @Override
            public void onChargingUpdateReceived(boolean changingStatus, int percent, int chargePlug) {

            }
        });
        //TODO 屏蔽消息提醒

    }

    //电池监听
    private void setBatteryListener() {
        BatteryReceiver batteryReceiver = new BatteryReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        mContext.registerReceiver(batteryReceiver, intentFilter);
    }


    /**
     * 获取当前wifi名字
     *
     * @param context
     * @return
     */
    public static String getConnectWifiSsid(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getSSID();
    }

    /**
     * 获取蓝牙地址
     *
     * @return
     */
    public static String getBtAddressByReflection() {

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Field field = null;
        try {
            field = BluetoothAdapter.class.getDeclaredField("mService");
            field.setAccessible(true);
            Object bluetoothManagerService = field.get(bluetoothAdapter);
            if (bluetoothManagerService == null) {
                return null;
            }
            Method method = bluetoothManagerService.getClass().getMethod("getAddress");
            if (method != null) {
                Object obj = method.invoke(bluetoothManagerService);
                if (obj != null) {
                    return obj.toString();
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static float getCpuThermal() {

        List<String> result = new ArrayList<>();
        BufferedReader br = null;
        float temp = 0;

        try {
            File dir = new File("/sys/class/thermal/");

            File[] files = dir.listFiles(file -> {
                if (Pattern.matches("thermal_zone[0-9]+", file.getName())) {
                    return true;
                }
                return false;
            });
            final int SIZE = files.length;
            String line;
            for (int i = 0; i < SIZE; i++) {
                br = new BufferedReader(new FileReader("/sys/class/thermal/thermal_zone" + i + "/temp"));
                line = br.readLine();
                if (line != null) {
                    long temperature = Long.parseLong(line);
                    if (temperature < 0) {
                        temp = -1f;
                        return temp;
                    } else {
                        temp = (float) (temperature / 1000.0);
                        return temp;
                    }

                }
                break;
            }
            return temp;
        } catch (FileNotFoundException e) {
            result.add(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return temp;
    }

    public static boolean isInHighTemperature() {
        float temp = getCpuThermal();
//        GeeUILogUtils.logi("letianpai_uploader: ", "letianpai_temp: " + temp);
        if (temp > HIGH_TEMP) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 更新机器人状态
     *
     * @param callback
     */
    private void uploadStatus(Callback callback) {
        if (currentPercent == 0) {
            return;
        }
        String sn = SystemUtil.getLtpSn();
        String mac = SystemUtil.getWlanMacAddress();
        String ip = SystemUtil.getIp(mContext);
        String mcu = SystemUtil.getMcu();
        String wifiSsid = getConnectWifiSsid(mContext);
        String btAddress = getBtAddressByReflection();
        int battery = ChargingUpdateCallback.getInstance().getBattery();
        int volume = VolumeManager.getInstance(mContext).getCurrentVolume();
        boolean isInHighTemperature = isInHighTemperature();
        long space = SDCardUtil.getAvailableSpaceInBytes();
        float temp = getCpuThermal();
        long uptime = (SystemClock.elapsedRealtime()) / 1000;

//        Log.e("letianpai_uploader","sn: "+ sn);
//        Log.e("letianpai_uploader","mac: "+ mac);
//        Log.e("letianpai_uploader","ip: "+ ip);
//        Log.e("letianpai_uploader","mcu: "+ mcu);
//        Log.e("letianpai_uploader","wifiSsid: "+ wifiSsid);
//        Log.e("letianpai_uploader","btAddress: "+ btAddress);
//        Log.e("letianpai_uploader","battery: "+ battery);
//        Log.e("letianpai_uploader","volume: "+ volume);
//        Log.e("letianpai_uploader","isInHighTemperature: "+ isInHighTemperature);
//        Log.e("letianpai_uploader","currentPercent: "+ currentPercent);
//        Log.e("letianpai_uploader","space: "+ space);
//        Log.e("letianpai_uploader","temp: "+ temp);
//        Log.e("letianpai_uploader","uptime: "+ uptime);

        //TODO 增加获取
        HashMap<String ,Object> hashMap = new HashMap();
        if (battery > 0) {
            hashMap.put("battery_percent", battery);
        }
        hashMap.put("ble", btAddress);
//        hashMap.put("humidity", 0);
        hashMap.put("mac", mac);
        hashMap.put("mcu_version", mcu);
        hashMap.put("ip", ip);
        hashMap.put("rom_version", Build.DISPLAY);
        hashMap.put("sn", sn);
        hashMap.put("sound_volume", volume);
        hashMap.put("system_version", Build.DISPLAY);
        hashMap.put("temperature", temp);

        if (isInHighTemperature) {
            hashMap.put("hot_status", 1);
        } else {
            hashMap.put("hot_status", 0);
        }
//        hashMap.put("update", 0);
        hashMap.put("wifi_name", wifiSsid);
        if (ChargingUpdateCallback.getInstance().isCharging()) {
            hashMap.put("charge_status", 1);
        } else {
            hashMap.put("charge_status", 2);
        }
        hashMap.put("space", space);
        hashMap.put("uptime", uptime);
        GeeUiNetManager.uploadStatus(mContext, SystemUtil.isInChinese(), hashMap, callback);
    }


    private void checkUploadFrequency() {
        long uploadFrequencyInternalTime = RobotSubConfigManager.getInstance(mContext).getUploadFrequencyInternalTime();
        long currentTime = System.currentTimeMillis();
        boolean isChinese = SystemUtil.isInChinese();
//        GeeUILogUtils.logi("uploadFrequency", "uploadFrequencyInternalTime: " + uploadFrequencyInternalTime);
//        GeeUILogUtils.logi("uploadFrequency", "currentTime: " + currentTime);
        if ((currentTime - uploadFrequencyInternalTime) > UPDATE_INTERNAL_TIME) {
//            GeeUILogUtils.logi("uploadFrequency", "currentTime === 1 ===: ");
            checkRobotUploadFrequency();
//            GeeUiNetManager.getUploadDataConfig(mContext, isChinese, new AppUploadConfigCallback.AppUploadConfigUpdateListener() {
//                @Override
//                public void onAppUploadConfigUpdate(int uploadFrequency) {
//                    Log.e("letianpai_uploadFrequency", "currentTime === 2 ===: ");
//                    Log.e("letianpai_uploadFrequency", "uploadFrequency: " + uploadFrequency);
//                    if (uploadFrequency > 0) {
//                        Log.e("letianpai_uploadFrequency", "currentTime === 3 ===: ");
//                        RobotSubConfigManager.getInstance(mContext).setUploadFrequency(uploadFrequency);
//                        RobotSubConfigManager.getInstance(mContext).setUploadFrequencyInternalTime(System.currentTimeMillis());
//                        RobotSubConfigManager.getInstance(mContext).commit();
//
//                        Log.e("letianpai_uploadFrequency", "currentTime === 4 ===: ");
//                    }
//                }
//            });
        }

    }

    private void checkRobotUploadFrequency() {
        GeeUiNetManager.getUploadDataConfig(mContext, SystemUtil.isInChinese(), new AppUploadConfigCallback.AppUploadConfigUpdateListener() {
            @Override
            public void onAppUploadConfigUpdate(int uploadFrequency) {
                if (uploadFrequency > 0) {
                    RobotSubConfigManager.getInstance(mContext).setUploadFrequency(uploadFrequency);
                    RobotSubConfigManager.getInstance(mContext).setUploadFrequencyInternalTime(System.currentTimeMillis());
                    RobotSubConfigManager.getInstance(mContext).commit();

                }
            }
        });

    }

    public void uploadRobotStatus() {
        if (mContext != null) {
            checkUploadFrequency();
            uploadRobotData();
        }

    }

    public void syncRobotStatus() {
        if (mContext != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkRobotUploadFrequency();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    uploadRobotStatusData();
                }
            }).start();

        }

    }

    private void uploadRobotData() {
        long internalTime = RobotSubConfigManager.getInstance(mContext).getUploadFrequencyInternalTime();
        long uploadFrequency = RobotSubConfigManager.getInstance(mContext).getUploadFrequency();
        long uploadDataTime = RobotSubConfigManager.getInstance(mContext).getUploadDataTime();

        long current = System.currentTimeMillis();
//        GeeUILogUtils.logi("uploadFrequency", "internalTime: " + internalTime);
//        GeeUILogUtils.logi("uploadFrequency", "uploadFrequency: " + uploadFrequency);
//        GeeUILogUtils.logi("uploadFrequency", "uploadDataTime: " + uploadDataTime);
//        GeeUILogUtils.logi("uploadFrequency", "current: " + current);
//        GeeUILogUtils.logi("uploadFrequency", "current - uploadDataTime: " + (current - uploadDataTime));
//        GeeUILogUtils.logi("uploadFrequency", "current - uploadDataTime - uploadFrequency * 1000 * 60 :" + (current - uploadDataTime - uploadFrequency * 1000 * 60));
        if (internalTime == 0 || uploadFrequency == 0 || uploadDataTime == 0 || ((current - uploadDataTime) > uploadFrequency * 1000 * 60)) {
            //TODO 直接上传数据
            uploadRobotStatusData();
        }

    }

    public void uploadRobotStatusData() {
        GeeUIStatusUploader.getInstance(mContext).uploadStatus(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response != null && response.body() != null) {
                    String info = "";
                    if (response != null && response.body() != null) {
                        info = response.body().string();
                    }
                    BaseMessageInfo baseMessageInfo;
                    if (info != null) {
                        try{
//                            GeeUILogUtils.logi("uploadFrequency", "currentTime === info === 1 === " + info);
                            baseMessageInfo = new Gson().fromJson(info, BaseMessageInfo.class);
                            if (baseMessageInfo != null && baseMessageInfo.getMsg() != null) {
                                if (baseMessageInfo.getCode() == 0) {
                                    RobotSubConfigManager.getInstance(mContext).setUploadDataTime(System.currentTimeMillis());
                                    RobotSubConfigManager.getInstance(mContext).commit();
                                }
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
