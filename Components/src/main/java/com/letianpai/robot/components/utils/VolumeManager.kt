package com.letianpai.robot.components.utils

import android.content.Context
import android.media.AudioManager
import android.util.Log

/**
 * 睡眠模式管理器
 *
 * @author liujunbin
 */
class VolumeManager private constructor(private val mContext: Context) {
    private var audioManager: AudioManager? = null
    private val vTag = "volume1111"

    init {
        init(mContext)
    }

    private fun init(context: Context) {
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        //TODO Block Message Alerts
//        test();
    }

    private fun test() {
        val currentSystem = audioManager!!.getStreamVolume(AudioManager.STREAM_SYSTEM)
        val currentAccessibility = audioManager!!.getStreamVolume(AudioManager.STREAM_ACCESSIBILITY)
        val currentAlarm = audioManager!!.getStreamVolume(AudioManager.STREAM_ALARM)
        val currentRing = audioManager!!.getStreamVolume(AudioManager.STREAM_RING)
        val currentMusic = audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)
        val currentVoiceCall = audioManager!!.getStreamVolume(AudioManager.STREAM_VOICE_CALL)
        val currentDTMF = audioManager!!.getStreamVolume(AudioManager.STREAM_DTMF)
        val currentNotification = audioManager!!.getStreamVolume(AudioManager.STREAM_NOTIFICATION)

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
        audioManager!!.setStreamVolume(AudioManager.STREAM_ACCESSIBILITY, 5, 0)

        //        LogUtils.logi(vTag, "currentSystem1: " + currentSystem);
//        LogUtils.logi(vTag, "currentAccessibility1: " + currentAccessibility);
//        LogUtils.logi(vTag, "currentAlarm1: " + currentAlarm);
//        LogUtils.logi(vTag, "currentRing1: " + currentRing);
//        LogUtils.logi(vTag, "currentMusic1: " + currentMusic);
//        LogUtils.logi(vTag, "currentVoiceCall1: " + currentVoiceCall);
//        LogUtils.logi(vTag, "currentDTMF: " + currentDTMF);
//        LogUtils.logi(vTag, "currentNotification: " + currentNotification);
    }

    val currentVolume: Int
        get() {
            val currentAccessibility =
                audioManager!!.getStreamVolume(AudioManager.STREAM_ACCESSIBILITY)
            return currentAccessibility
        }

    fun setRobotVolume(volume: Int) {
        var volume = volume
        if (volume > 15) {
            volume = 15
        } else if (volume < 0) {
            volume = 0
        }
    }

    fun volumeDown() {
        val volume = currentVolume
        Log.e("letianpai_volume", "volumeDown: $volume")
        setRobotVolume(volume - 2)
    }

    fun volumeUp() {
        val volume = currentVolume
        Log.e("letianpai_volume", "volumeUp: $volume")
        setRobotVolume(volume + 2)
    }

    fun setRobotVolumeTo20() {
//        setRobotVolume(20);
        setRobotVolume(20)
    }

    fun volumeMax() {
        setRobotVolume(15)
    }

    fun volumeMin() {
        setRobotVolume(5)
    }


    companion object {
        private var instance: VolumeManager? = null
        fun getInstance(context: Context): VolumeManager? {
            synchronized(VolumeManager::class.java) {
                if (instance == null) {
                    instance = VolumeManager(context.applicationContext)
                }
                return instance
            }
        }
    }
}
