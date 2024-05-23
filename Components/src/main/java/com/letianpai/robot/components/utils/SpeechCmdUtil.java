package com.letianpai.robot.components.utils;

import android.os.CountDownTimer;

public class SpeechCmdUtil {

    public static String SPEECH_OPEN_TYPE = "open_type";
    public static String SPEECH_CMD_STATUS_OPEN = "speech_cmd_open";
    public static String SPEECH_CMD_STATUS_CLOSE = "speech_cmd_close";

    public void initCountDownTimer(){
        CountDownTimer timer = new CountDownTimer(6 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
    }


}
