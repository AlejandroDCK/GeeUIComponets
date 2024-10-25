package com.letianpai.robot.components.utils

import android.os.CountDownTimer

class SpeechCmdUtil {
    fun initCountDownTimer() {
        val timer: CountDownTimer = object : CountDownTimer((6 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
            }
        }
    }


    companion object {
        var SPEECH_OPEN_TYPE: String = "open_type"
        var SPEECH_CMD_STATUS_OPEN: String = "speech_cmd_open"
        var SPEECH_CMD_STATUS_CLOSE: String = "speech_cmd_close"
    }
}
