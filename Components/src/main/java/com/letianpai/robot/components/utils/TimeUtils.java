package com.letianpai.robot.components.utils;

import android.text.TextUtils;

import com.letianpai.robot.components.locale.LocaleUtils;
import com.letianpai.robot.components.network.system.SystemUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static String getFullTime() {
        return convertTimeFormat("yyyy年MM月dd日   E");
    }

    public static String getDay(long time) {
        return convertTimeFormat(time, "MM月dd日");
    }

    public static String getCountDownDay(long time) {
//        if (LocaleUtils.isChinese()) {
        if (SystemUtil.isInChinese()){
            return convertTimeFormat(time, "MM月dd日");
        } else {
            return convertTimeFormat(time, "yyyy/MM/dd");
        }

    }

    public static String getClockTime() {
        return convertTimeFormat("HH:mm");
    }

    public static String get12HourTime() {
        return convertTimeFormat("hh");
    }

    public static String get24HourTime() {
        return convertTimeFormat("HH");
    }

    public static String getMinTime() {
        return convertTimeFormat("mm");
    }

    public static int get24HourTime(long time) {
        return convertTimeFormatInt(time,"HH");
    }

    public static int getMinTime(long time) {
        return convertTimeFormatInt(time,"mm");
    }

    public static int getDayTime(long time) {
        return convertTimeFormatInt(time,"dd");
    }

    public static int getMonTime(long time) {
        return convertTimeFormatInt(time,"MM");
    }

    public static int getYearTime(long time) {
        return convertTimeFormatInt(time,"yyyy");
    }


    public static String convertTimeFormat(String strFormat) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(strFormat, Locale.getDefault());
        return format.format(date);
    }

    public static String convertTimeFormat(long time, String strFormat) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(strFormat, Locale.getDefault());
        return format.format(date);
    }

    public static int convertTimeFormatInt(long time, String strFormat) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(strFormat, Locale.getDefault());
        if (format != null && !TextUtils.isEmpty(format.format(date))){
            return Integer.valueOf(format.format(date));
        }
        return -1;
    }
}
