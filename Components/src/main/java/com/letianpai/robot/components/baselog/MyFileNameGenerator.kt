package com.letianpai.robot.components.baselog

import android.annotation.SuppressLint
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.printer.file.naming.FileNameGenerator
import java.text.SimpleDateFormat
import java.util.Date

class MyFileNameGenerator : FileNameGenerator {
    override fun isFileNameChangeable(): Boolean {
        return true
    }

    override fun generateFileName(logLevel: Int, timestamp: Long): String {
        @SuppressLint("SimpleDateFormat") val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return LogLevel.getLevelName(logLevel) + "-" + "myLog" + "-" + sdf.format(Date(timestamp)) + ".log"
    }
}
