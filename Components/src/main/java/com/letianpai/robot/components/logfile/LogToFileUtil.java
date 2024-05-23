package com.letianpai.robot.components.logfile;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.util.Log;

import com.letianpai.robot.components.charging.BatteryReceiver;
import com.letianpai.robot.components.charging.ChargingUpdateCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class LogToFileUtil {

    private static final String TAG = "LogToFile";
    private static final String LOG_FILE_NAME = "ChargingLog.txt";
    private File logFile;
    private FileWriter fileWriter = null;
    private int index;


    private static LogToFileUtil instance;
    private Context mContext;

    private LogToFileUtil(Context context) {
        this.mContext = context;
        init();
    }
//
//    public void writeMessage(String log) {
//        Message message = new Message();
//        message.obj = log;
//        message.what = WRITE_MESSAGE;
//        handler.sendMessage(message);
//    }


    //电池监听
    private void setBatteryListener() {
        BatteryReceiver batteryReceiver = new BatteryReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        mContext.registerReceiver(batteryReceiver, intentFilter);
    }


    private void init() {
        logFile = new File(mContext.getApplicationContext().getFilesDir(), LOG_FILE_NAME);
        Log.d(TAG, "logFile.getAbsoluteFile(): "+ logFile.getAbsoluteFile());
        try {
            fileWriter = new FileWriter(logFile, true); // true表示追加写入，false表示覆盖写入
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBatteryListener();
    }

    public static LogToFileUtil getInstance(Context context) {
        synchronized (LogToFileUtil.class) {
            if (instance == null) {
                instance = new LogToFileUtil(context.getApplicationContext());
            }
            return instance;
        }

    }

    private void writeLogToFile(String logMessage) {
        if (fileWriter == null ){
            init();
            return;
        }
            index += 1;
        try {
            fileWriter.append(index+ "_"+ logMessage);
            fileWriter.append("\n");
            fileWriter.flush();
            Log.d(TAG, "Log written to file successfully.");
        } catch (IOException e) {
            Log.e(TAG, "Error writing log to file.", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing file writer.", e);
                }
            }
        }
    }



    public static void writeLogToFile(Context context,String logMessage) {
        File logFile = new File(context.getApplicationContext().getFilesDir(), LOG_FILE_NAME);
        File logFile1 = new File(context.getApplicationContext().getExternalFilesDir(1+""), LOG_FILE_NAME);
        File logFile2 = new File("/storage/emulated/0/Android/", LOG_FILE_NAME);
//        Log.d(TAG, "logFile_0:.getAbsoluteFile(): "+ logFile.getAbsoluteFile());
//        Log.d(TAG, "logFile_1:.getAbsoluteFile(): "+ logFile1.getAbsoluteFile());
//        Log.d(TAG, "logFile_2:.getAbsoluteFile(): "+ logFile2.getAbsoluteFile());
        FileWriter fileWriter = null;
        FileWriter fileWriter1 = null;
        FileWriter fileWriter2 = null;
        try {
            fileWriter = new FileWriter(logFile, true); // true表示追加写入，false表示覆盖写入
            fileWriter.append(logMessage);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter1 = new FileWriter(logFile1, true); // true表示追加写入，false表示覆盖写入
            fileWriter1.append(logMessage);
            fileWriter1.append("\n");
            fileWriter1.flush();
            fileWriter2 = new FileWriter(logFile2, true); // true表示追加写入，false表示覆盖写入
            fileWriter2.append(logMessage);
            fileWriter2.append("\n");
            fileWriter2.flush();
//            Log.d(TAG, "Log written to file successfully.");
        } catch (IOException e) {
            Log.e(TAG, "Error writing log to file.", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                    fileWriter1.close();
                    fileWriter2.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing file writer.", e);
                }
            }
        }
    }

    public void writeLogToFile() {
        File logFile2 = new File("/storage/emulated/0/Android/", LOG_FILE_NAME);
        String log = TimeUtil.getCorrectFullTime() +"__" +ChargingUpdateCallback.getInstance().getBattery()+ "%";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(logFile2, true); // true表示追加写入，false表示覆盖写入
            fileWriter.append(log);
            fileWriter.append("\n");
            fileWriter.flush();
        } catch (IOException e) {
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing file writer.", e);
                }
            }
        }
    }
    public void writeLogToFile(Context context) {
        File logFile = new File(mContext.getApplicationContext().getFilesDir(), LOG_FILE_NAME);
        File logFile1 = new File(mContext.getApplicationContext().getExternalFilesDir(1+""), LOG_FILE_NAME);
        File logFile2 = new File("/storage/emulated/0/Android/", LOG_FILE_NAME);
        String log = TimeUtil.getCorrectFullTime() +"__" +ChargingUpdateCallback.getInstance().getBattery()+ "%";
//        Log.d(TAG, "logFile_0:.getAbsoluteFile(): "+ logFile.getAbsoluteFile());
//        Log.d(TAG, "logFile_1:.getAbsoluteFile(): "+ logFile1.getAbsoluteFile());
//        Log.d(TAG, "logFile_2:.getAbsoluteFile(): "+ logFile2.getAbsoluteFile());
        FileWriter fileWriter = null;
        FileWriter fileWriter1 = null;
        FileWriter fileWriter2 = null;
        try {
            fileWriter = new FileWriter(logFile, true); // true表示追加写入，false表示覆盖写入
            fileWriter.append(log);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter1 = new FileWriter(logFile1, true); // true表示追加写入，false表示覆盖写入
            fileWriter1.append(log);
            fileWriter1.append("\n");
            fileWriter1.flush();
            fileWriter2 = new FileWriter(logFile2, true); // true表示追加写入，false表示覆盖写入
            fileWriter2.append(log);
            fileWriter2.append("\n");
            fileWriter2.flush();
            Log.d(TAG, "Log written to file successfully.");
        } catch (IOException e) {
            Log.e(TAG, "Error writing log to file.", e);
        } finally {
            if (fileWriter != null) {
                try {
//                    fileWriter.close();
//                    fileWriter1.close();
                    fileWriter2.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing file writer.", e);
                }
            }
        }
    }
}