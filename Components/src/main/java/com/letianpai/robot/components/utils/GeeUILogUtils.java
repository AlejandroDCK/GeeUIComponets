package com.letianpai.robot.components.utils;


import android.content.Context;
import androidx.annotation.NonNull;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.BackupStrategy2;
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy2;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.letianpai.robot.components.baselog.MyBorderFormatter;
import com.letianpai.robot.components.baselog.MyFileNameGenerator;
import com.letianpai.robot.components.baselog.MyFlattener;

import java.io.File;


public class GeeUILogUtils {
    public static void logi(String tag, String message) {
        XLog.i("TAG:%s>>>--%s",tag, message);
    }

    public static void logi(String message) {
        logi("default", message);
    }

    public static void logd(String message) {
        logd("default", message);
    }

    public static void logd(String tag, String message) {
        XLog.d("TAG:%s>>>--%s",tag, message);
    }

    public static void loge(String tag, String message) {
        XLog.e("TAG:%s>>>--%s",tag, message);
    }

    public static void loge(String message) {
        loge("default", message);
    }
    private static final int showLength = 1000;

    public static void initXlog2(@NonNull String tag, @NonNull Context context){
        LogConfiguration config = new LogConfiguration.Builder()
                .tag(tag)
                .enableBorder()
                .borderFormatter(new MyBorderFormatter())
                .build();
        AndroidPrinter androidPrinter = new AndroidPrinter(true);         // Printer that print the log using android.util.Log 使用android.util.Log打印日志的打印机
        FilePrinter filePrinter = new FilePrinter                      // Printer that print(save) the log to file 打印(保存)日志到文件的打印机
                .Builder(new File("sdcard/letianpai/.log", context.getPackageName()).getPath())// Specify the directory path of log file(s) 指定日志文件的目录路径
                .fileNameGenerator(new MyFileNameGenerator()) //自定义文件名称 默认值:ChangelessFileNameGenerator(“日志”)
                .backupStrategy(new FileSizeBackupStrategy2(3 * 1024 * 1024, BackupStrategy2.NO_LIMIT)) //单个日志文件的大小默认:FileSizeBackupStrategy(1024 * 1024)
                // .cleanStrategy(new FileLastModifiedCleanStrategy(  30L * 24L * 60L * 60L * 1000L))  //日志文件存活时间，单位毫秒
                .cleanStrategy(new FileLastModifiedCleanStrategy(  3L * 24L * 60L * 60L * 1000L))  //日志文件存活时间，单位毫秒
                .flattener(new MyFlattener()) //自定义flattener，控制打印格式
                .build();

        XLog.init(config,androidPrinter, filePrinter);
    }

    public static void showLargeLog(String tag, String logContent) {
        if (logContent.length() > showLength) {
            String show = logContent.substring(0, showLength);
            logd(tag, show);
            /*剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果*/
            if ((logContent.length() - showLength) > showLength) {
                String partLog = logContent.substring(showLength, logContent.length());
                showLargeLog(tag, partLog);
            } else {
                String printLog = logContent.substring(showLength, logContent.length());
                logd(tag, printLog);
            }
        } else {
            logd(tag, logContent);
        }
    }
}
