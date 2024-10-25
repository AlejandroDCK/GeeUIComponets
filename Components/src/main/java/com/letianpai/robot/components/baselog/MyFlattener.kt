package com.letianpai.robot.components.baselog

import android.os.Build
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.flattener.Flattener
import com.elvishew.xlog.flattener.Flattener2
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MyFlattener() : Flattener, Flattener2 {
    override fun flatten(logLevel: Int, tag: String, message: String): CharSequence {
        return flatten(System.currentTimeMillis(), logLevel, tag, message)
    }

    override fun flatten(
        timeMillis: Long,
        logLevel: Int,
        tag: String,
        message: String
    ): CharSequence {
        return (((currDDate
                + '|' + LogLevel.getLevelName(logLevel)
                + '|' + tag
                + '|' + message)))
    }

    val currDDate: String
        get() {
            if (Build.VERSION.SDK_INT >= 24) {
                return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
            } else {
                val tms: Calendar = Calendar.getInstance()
                return tms.get(Calendar.YEAR)
                    .toString() + "-" + tms.get(Calendar.MONTH) + "-" + tms.get(
                    Calendar.DAY_OF_MONTH
                ) + " " + tms.get(Calendar.HOUR_OF_DAY) + ":" + tms.get(
                    Calendar.MINUTE
                ) + ":" + tms.get(Calendar.SECOND) + "." + tms.get(Calendar.MILLISECOND)
            }
        }
}
