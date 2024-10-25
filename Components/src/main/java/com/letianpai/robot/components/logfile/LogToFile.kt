package com.letianpai.robot.components.logfile

import android.content.Context
import android.util.Log
import com.letianpai.robot.components.charging.ChargingUpdateCallback
import java.io.File
import java.io.FileWriter
import java.io.IOException

object LogToFile {
    private const val TAG = "LogToFile"
    private const val LOG_FILE_NAME = "log111.txt"


    fun writeLogToFile(context: Context, logMessage: String) {
        val logFile = File(context.applicationContext.filesDir, LOG_FILE_NAME)
        val logFile1 =
            File(context.applicationContext.getExternalFilesDir(1.toString() + ""), LOG_FILE_NAME)
        val logFile2 = File("/storage/emulated/0/Android/", LOG_FILE_NAME)
        var fileWriter: FileWriter? = null
        var fileWriter1: FileWriter? = null
        var fileWriter2: FileWriter? = null
        val battery: Int = ChargingUpdateCallback.Companion.instance.battery
        try {
            fileWriter = FileWriter(logFile, true) // true表示追加写入，false表示覆盖写入
            fileWriter.append(logMessage)
            fileWriter.append("\n")
            fileWriter.flush()
            fileWriter1 = FileWriter(logFile1, true) // true表示追加写入，false表示覆盖写入
            fileWriter1.append(logMessage)
            fileWriter1.append("\n")
            fileWriter1.flush()
            fileWriter2 = FileWriter(logFile2, true) // true表示追加写入，false表示覆盖写入
            fileWriter2.append(logMessage + "_" + battery)
            fileWriter2.append("\n")
            fileWriter2.flush()
            Log.d(TAG, "Log written to file successfully.")
        } catch (e: IOException) {
            Log.e(TAG, "Error writing log to file.", e)
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close()
                    fileWriter1!!.close()
                    fileWriter2!!.close()
                } catch (e: IOException) {
                    Log.e(TAG, "Error closing file writer.", e)
                }
            }
        }
    }
}