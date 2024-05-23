package com.letianpai.robot.components.baselog;

import android.os.Build;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.flattener.Flattener;
import com.elvishew.xlog.flattener.Flattener2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyFlattener implements Flattener, Flattener2 {
    @Override
    public CharSequence flatten(int logLevel, String tag, String message) {
        return flatten(System.currentTimeMillis(), logLevel, tag, message);
    }

    @Override
    public CharSequence flatten(long timeMillis, int logLevel, String tag, String message) {
        return (getCurrDDate()
                + '|' + LogLevel.getLevelName(logLevel)
                + '|' + tag
                + '|' + message);
    }

    public String getCurrDDate() {
        if (Build.VERSION.SDK_INT >= 24) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        } else {
            Calendar tms = Calendar.getInstance();
            return tms.get(Calendar.YEAR) + "-" + tms.get(Calendar.MONTH) + "-" + tms.get(Calendar.DAY_OF_MONTH) + " " + tms.get(Calendar.HOUR_OF_DAY) + ":" + tms.get(Calendar.MINUTE) + ":" + tms.get(Calendar.SECOND) + "." + tms.get(Calendar.MILLISECOND);
        }
    }
}
