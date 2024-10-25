package com.letianpai.robot.components.utils

import android.content.Context
import com.elvishew.xlog.XLog
import com.elvishew.xlog.LogConfiguration
import com.letianpai.robot.components.baselog.MyBorderFormatter
import com.elvishew.xlog.printer.AndroidPrinter
import com.elvishew.xlog.printer.file.FilePrinter
import com.letianpai.robot.components.baselog.MyFileNameGenerator
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy2
import com.elvishew.xlog.printer.file.backup.BackupStrategy2
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy
import com.letianpai.robot.components.baselog.MyFlattener
import java.io.File


object GeeUILogUtils {
    fun logi(tag: String?, message: String?) {
        XLog.i("TAG:%s>>>--%s", tag, message)
    }

    fun logi(message: String?) {
        logi("default", message)
    }

    fun logd(message: String?) {
        logd("default", message)
    }

    fun logd(tag: String?, message: String?) {
        XLog.d("TAG:%s>>>--%s", tag, message)
    }

    fun loge(tag: String?, message: String?) {
        XLog.e("TAG:%s>>>--%s", tag, message)
    }

    fun loge(message: String?) {
        loge("default", message)
    }

    private const val showLength = 1000

    fun initXlog2(tag: String, context: Context) {
        val config = LogConfiguration.Builder()
            .tag(tag)
            .enableBorder()
            .borderFormatter(MyBorderFormatter())
            .build()
        val androidPrinter =
            AndroidPrinter(true) // Printer that print the log using android.util.Log 使用android.util.Log打印日志的打印机
        val filePrinter = FilePrinter.Builder(
            File(
                "sdcard/letianpai/.log",
                context.packageName
            ).path
        ) // Specify the directory path of log file(s) 指定日志文件的目录路径
            .fileNameGenerator(MyFileNameGenerator()) //自定义文件名称 默认值:ChangelessFileNameGenerator(“日志”)
            .backupStrategy(
                FileSizeBackupStrategy2(
                    (3 * 1024 * 1024).toLong(),
                    BackupStrategy2.NO_LIMIT
                )
            ) //单个日志文件的大小默认:FileSizeBackupStrategy(1024 * 1024)
            // .cleanStrategy(new FileLastModifiedCleanStrategy(  30L * 24L * 60L * 60L * 1000L))  //日志文件存活时间，单位毫秒
            .cleanStrategy(FileLastModifiedCleanStrategy(3L * 24L * 60L * 60L * 1000L)) //日志文件存活时间，单位毫秒
            .flattener(MyFlattener()) //自定义flattener，控制打印格式
            .build()

        XLog.init(config, androidPrinter, filePrinter)
    }

    fun showLargeLog(tag: String?, logContent: String) {
        if (logContent.length > showLength) {
            val show = logContent.substring(0, showLength)
            logd(tag, show)
            /*剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果*/
            if ((logContent.length - showLength) > showLength) {
                val partLog = logContent.substring(showLength, logContent.length)
                showLargeLog(tag, partLog)
            } else {
                val printLog = logContent.substring(showLength, logContent.length)
                logd(tag, printLog)
            }
        } else {
            logd(tag, logContent)
        }
    }
}
