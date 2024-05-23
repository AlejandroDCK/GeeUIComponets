package com.letianpai.robot.components.baselog;

import android.annotation.SuppressLint;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFileNameGenerator implements FileNameGenerator {
    @Override
    public boolean isFileNameChangeable() {
        return true;
    }

    @Override
    public String generateFileName(int logLevel, long timestamp) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return LogLevel.getLevelName(logLevel) + "-" + "myLog" + "-" + sdf.format(new Date(timestamp)) + ".log";
    }
}
