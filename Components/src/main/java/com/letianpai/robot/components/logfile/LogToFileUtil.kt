package com.letianpai.robot.components.logfile

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.letianpai.robot.components.charging.BatteryReceiver
import com.letianpai.robot.components.charging.ChargingUpdateCallback
import java.io.File
import java.io.FileWriter
import java.io.IOException

class LogToFileUtil private constructor(private val mContext: Context) {
    private var logFile: File? = null
    private var fileWriter: FileWriter? = null
    private var index = 0


    init {
        init()
    }


    //
    //    public void writeMessage(String log) {
    //        Message message = new Message();
    //        message.obj = log;
    //        message.what = WRITE_MESSAGE;
    //        handler.sendMessage(message);
    //    }
    //电池监听
    private fun setBatteryListener() {
        val batteryReceiver = BatteryReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        mContext.registerReceiver(batteryReceiver, intentFilter)
    }


    private fun init() {
        logFile = File(mContext.applicationContext.filesDir, LOG_FILE_NAME)
        Log.d(TAG, "logFile.getAbsoluteFile(): " + logFile!!.absoluteFile)
        try {
            fileWriter = FileWriter(logFile, true) // true表示追加写入，false表示覆盖写入
        } catch (e: IOException) {
            e.printStackTrace()
        }
        setBatteryListener()
    }

    private fun writeLogToFile(logMessage: String) {
        if (fileWriter == null) {
            init()
            return
        }
        index += 1
        try {
            fileWriter!!.append(index.toString() + "_" + logMessage)
            fileWriter!!.append("\n")
            fileWriter!!.flush()
            Log.d(TAG, "Log written to file successfully.")
        } catch (e: IOException) {
            Log.e(TAG, "Error writing log to file.", e)
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter!!.close()
                } catch (e: IOException) {
                    Log.e(TAG, "Error closing file writer.", e)
                }
            }
        }
    }


    fun writeLogToFile() {
        val logFile2 = File("/storage/emulated/0/Android/", LOG_FILE_NAME)
        val log =
            TimeUtil.correctFullTime + "__" + ChargingUpdateCallback.Companion.instance
                .battery + "%"
        var fileWriter: FileWriter? = null
        try {
            fileWriter = FileWriter(logFile2, true) // true表示追加写入，false表示覆盖写入
            fileWriter.append(log)
            fileWriter.append("\n")
            fileWriter.flush()
        } catch (e: IOException) {
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close()
                } catch (e: IOException) {
                    Log.e(TAG, "Error closing file writer.", e)
                }
            }
        }
    }

    fun writeLogToFile(context: Context?) {
        val logFile = File(mContext.applicationContext.filesDir, LOG_FILE_NAME)
        val logFile1 =
            File(mContext.applicationContext.getExternalFilesDir(1.toString() + ""), LOG_FILE_NAME)
        val logFile2 = File("/storage/emulated/0/Android/", LOG_FILE_NAME)
        val log =
            TimeUtil.correctFullTime + "__" + ChargingUpdateCallback.Companion.instance
                .battery + "%"
        //        Log.d(TAG, "logFile_0:.getAbsoluteFile(): "+ logFile.getAbsoluteFile());
//        Log.d(TAG, "logFile_1:.getAbsoluteFile(): "+ logFile1.getAbsoluteFile());
//        Log.d(TAG, "logFile_2:.getAbsoluteFile(): "+ logFile2.getAbsoluteFile());
        var fileWriter: FileWriter? = null
        var fileWriter1: FileWriter? = null
        var fileWriter2: FileWriter? = null
        try {
            fileWriter = FileWriter(logFile, true) // true表示追加写入，false表示覆盖写入
            fileWriter.append(log)
            fileWriter.append("\n")
            fileWriter.flush()
            fileWriter1 = FileWriter(logFile1, true) // true表示追加写入，false表示覆盖写入
            fileWriter1.append(log)
            fileWriter1.append("\n")
            fileWriter1.flush()
            fileWriter2 = FileWriter(logFile2, true) // true表示追加写入，false表示覆盖写入
            fileWriter2.append(log)
            fileWriter2.append("\n")
            fileWriter2.flush()
            Log.d(TAG, "Log written to file successfully.")
        } catch (e: IOException) {
            Log.e(TAG, "Error writing log to file.", e)
        } finally {
            if (fileWriter != null) {
                try {
//                    fileWriter.close();
//                    fileWriter1.close();
                    fileWriter2!!.close()
                } catch (e: IOException) {
                    Log.e(TAG, "Error closing file writer.", e)
                }
            }
        }
    }

    companion object {
        private const val TAG = "LogToFile"
        private const val LOG_FILE_NAME = "ChargingLog.txt"
        private var instance: LogToFileUtil? = null
        fun getInstance(context: Context): LogToFileUtil? {
            synchronized(LogToFileUtil::class.java) {
                if (instance == null) {
                    instance = LogToFileUtil(context.applicationContext)
                }
                return instance
            }
        }

        fun writeLogToFile(context: Context, logMessage: String?) {
            val logFile = File(context.applicationContext.filesDir, LOG_FILE_NAME)
            val logFile1 = File(
                context.applicationContext.getExternalFilesDir(1.toString() + ""),
                LOG_FILE_NAME
            )
            val logFile2 = File("/storage/emulated/0/Android/", LOG_FILE_NAME)
            //        Log.d(TAG, "logFile_0:.getAbsoluteFile(): "+ logFile.getAbsoluteFile());
//        Log.d(TAG, "logFile_1:.getAbsoluteFile(): "+ logFile1.getAbsoluteFile());
//        Log.d(TAG, "logFile_2:.getAbsoluteFile(): "+ logFile2.getAbsoluteFile());
            var fileWriter: FileWriter? = null
            var fileWriter1: FileWriter? = null
            var fileWriter2: FileWriter? = null
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
                fileWriter2.append(logMessage)
                fileWriter2.append("\n")
                fileWriter2.flush()
                //            Log.d(TAG, "Log written to file successfully.");
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
}