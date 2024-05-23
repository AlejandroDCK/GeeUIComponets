package com.letianpai.robot.components.utils;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;


/**
 * 睡眠模式管理器
 *
 * @author liujunbin
 */
public class VolumeManager {

    private static VolumeManager instance;
    private Context mContext;
    private AudioManager audioManager;
    private String vTag = "volume1111";

    private VolumeManager(Context context) {
        this.mContext = context;
        init(context);
    }

    public static VolumeManager getInstance(Context context) {
        synchronized (VolumeManager.class) {
            if (instance == null) {
                instance = new VolumeManager(context.getApplicationContext());
            }
            return instance;
        }

    }

    ;

    private void init(Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //TODO 屏蔽消息提醒
//        test();

    }

    private void test() {
        int currentSystem = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        int currentAccessibility = audioManager.getStreamVolume(AudioManager.STREAM_ACCESSIBILITY);
        int currentAlarm = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        int currentRing = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        int currentMusic = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int currentVoiceCall = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        int currentDTMF = audioManager.getStreamVolume(AudioManager.STREAM_DTMF);
        int currentNotification = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

//        LogUtils.logi(vTag, "currentSystem: " + currentSystem);
//        LogUtils.logi(vTag, "currentAccessibility: " + currentAccessibility);
//        LogUtils.logi(vTag, "currentAlarm: " + currentAlarm);
//        LogUtils.logi(vTag, "currentRing: " + currentRing);
//        LogUtils.logi(vTag, "currentMusic: " + currentMusic);
//        LogUtils.logi(vTag, "currentVoiceCall: " + currentVoiceCall);
//        LogUtils.logi(vTag, "currentDTMF: " + currentDTMF);
//        LogUtils.logi(vTag, "currentNotification: " + currentNotification);

//        2023-03-31 17:39:41.591  9423-9423  volume                  com.renhejia.robot.launcher          E  currentSystem: 5
//        2023-03-31 17:39:41.591  9423-9423  volume                  com.renhejia.robot.launcher          E  currentAccessibility: 13
//        2023-03-31 17:39:41.591  9423-9423  volume                  com.renhejia.robot.launcher          E  currentAlarm: 6
//        2023-03-31 17:39:41.592  9423-9423  volume                  com.renhejia.robot.launcher          E  currentRing: 5
//        2023-03-31 17:39:41.592  9423-9423  volume                  com.renhejia.robot.launcher          E  currentMusic: 13
//        2023-03-31 17:39:41.592  9423-9423  volume                  com.renhejia.robot.launcher          E  currentVoiceCall: 3
//        2023-03-31 17:39:41.592  9423-9423  volume                  com.renhejia.robot.launcher          E  currentDTMF: 13
//        2023-03-31 17:39:41.592  9423-9423  volume                  com.renhejia.robot.launcher          E  currentNotification: 5

        audioManager.setStreamVolume(AudioManager.STREAM_ACCESSIBILITY, 5, 0);
//        LogUtils.logi(vTag, "currentSystem1: " + currentSystem);
//        LogUtils.logi(vTag, "currentAccessibility1: " + currentAccessibility);
//        LogUtils.logi(vTag, "currentAlarm1: " + currentAlarm);
//        LogUtils.logi(vTag, "currentRing1: " + currentRing);
//        LogUtils.logi(vTag, "currentMusic1: " + currentMusic);
//        LogUtils.logi(vTag, "currentVoiceCall1: " + currentVoiceCall);
//        LogUtils.logi(vTag, "currentDTMF: " + currentDTMF);
//        LogUtils.logi(vTag, "currentNotification: " + currentNotification);

    }

    public int getCurrentVolume() {
        int currentAccessibility = audioManager.getStreamVolume(AudioManager.STREAM_ACCESSIBILITY);
        return currentAccessibility;

    }

    public void setRobotVolume(int volume) {
        if (volume > 15) {
            volume = 15;
        } else if (volume < 0) {
            volume = 0;
        }

    }

    public void volumeDown() {
        int volume = getCurrentVolume();
        Log.e("letianpai_volume", "volumeDown: " + volume);
        setRobotVolume(volume - 2);
    }

    public void volumeUp() {
        int volume = getCurrentVolume();
        Log.e("letianpai_volume", "volumeUp: " + volume);
        setRobotVolume(volume + 2);
    }

    public void setRobotVolumeTo20() {
//        setRobotVolume(20);
        setRobotVolume(20);
    }

    public void volumeMax() {
        setRobotVolume(15);
    }

    public void volumeMin() {
        setRobotVolume(5);
    }


}
