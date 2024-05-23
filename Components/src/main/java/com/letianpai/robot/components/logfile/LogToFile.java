package com.letianpai.robot.components.logfile;

import android.content.Context;
import android.util.Log;

import com.letianpai.robot.components.charging.ChargingUpdateCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogToFile {
    private static final String TAG = "LogToFile";
    private static final String LOG_FILE_NAME = "log111.txt";


    public static void writeLogToFile(Context context,String logMessage) {
        File logFile = new File(context.getApplicationContext().getFilesDir(), LOG_FILE_NAME);
        File logFile1 = new File(context.getApplicationContext().getExternalFilesDir(1+""), LOG_FILE_NAME);
        File logFile2 = new File("/storage/emulated/0/Android/", LOG_FILE_NAME);
        FileWriter fileWriter = null;
        FileWriter fileWriter1 = null;
        FileWriter fileWriter2 = null;
        int battery = ChargingUpdateCallback.getInstance().getBattery();
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
            fileWriter2.append(logMessage +"_"+battery );
            fileWriter2.append("\n");
            fileWriter2.flush();
            Log.d(TAG, "Log written to file successfully.");
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
}