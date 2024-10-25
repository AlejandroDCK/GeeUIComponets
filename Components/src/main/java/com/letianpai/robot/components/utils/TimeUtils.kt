package com.letianpai.robot.components.utils

import com.letianpai.robot.components.network.system.SystemUtil
import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtils {
    val fullTime: String
        get() = convertTimeFormat("yyyy年MM月dd日   E")

    fun getDay(time: Long): String {
        return convertTimeFormat(time, "MM月dd日")
    }

    fun getCountDownDay(time: Long): String {
//        if (LocaleUtils.isChinese()) {
        return if (SystemUtil.isInChinese()) {
            convertTimeFormat(time, "MM月dd日")
        } else {
            convertTimeFormat(time, "yyyy/MM/dd")
        }
    }

    val clockTime: String
        get() = convertTimeFormat("HH:mm")

    fun get12HourTime(): String {
        return convertTimeFormat("hh")
    }

    fun get24HourTime(): String {
        return convertTimeFormat("HH")
    }

    val minTime: String
        get() = convertTimeFormat("mm")

    fun get24HourTime(time: Long): Int {
        return convertTimeFormatInt(time, "HH")
    }

    fun getMinTime(time: Long): Int {
        return convertTimeFormatInt(time, "mm")
    }

    fun getDayTime(time: Long): Int {
        return convertTimeFormatInt(time, "dd")
    }

    fun getMonTime(time: Long): Int {
        return convertTimeFormatInt(time, "MM")
    }

    fun getYearTime(time: Long): Int {
        return convertTimeFormatInt(time, "yyyy")
    }


    fun convertTimeFormat(strFormat: String?): String {
        val date = Date(System.currentTimeMillis())
        val format = SimpleDateFormat(strFormat, Locale.getDefault())
        return format.format(date)
    }

    fun convertTimeFormat(time: Long, strFormat: String?): String {
        val date = Date(time)
        val format = SimpleDateFormat(strFormat, Locale.getDefault())
        return format.format(date)
    }

    fun convertTimeFormatInt(time: Long, strFormat: String?): Int {
        val date = Date(time)
        val format = SimpleDateFormat(strFormat, Locale.getDefault())
        if (format != null && !TextUtils.isEmpty(format.format(date))) {
            return format.format(date).toInt()
        }
        return -1
    }
}
