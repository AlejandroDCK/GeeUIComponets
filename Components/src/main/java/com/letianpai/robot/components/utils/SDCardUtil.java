package com.letianpai.robot.components.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @author liujunbin
 */
public class SDCardUtil {

//    public static long getAvailableSpaceOnSDCard() {
//        String state = Environment.getExternalStorageState();
//
//        if (Environment.MEDIA_MOUNTED.equals(state)) {
//            // 获取SD卡目录
//            File sdCard = Environment.getExternalStorageDirectory();
//
//            // 使用StatFs获取文件系统的状态信息
//            StatFs statFs = new StatFs(sdCard.getPath());
//
//            // 获取每个块的大小
//            long blockSize = statFs.getBlockSizeLong();
//
//            // 获取可用块的数量
//            long availableBlocks = statFs.getAvailableBlocksLong();
//
//            // 计算可用存储空间
//            long availableSpace = availableBlocks * blockSize;
//
//            return availableSpace;
//        } else {
//            // SD卡不可用
//            return -1;
//        }
//    }

    public static String formatFileSize(long sizeInBytes) {
        if (sizeInBytes <= 0) {
            return "0 B";
        }

        final String[] units = {"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(sizeInBytes) / Math.log10(1024));
        return String.format("%.1f %s", sizeInBytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }

    public static String getAvailableSpaceOnSDCard() {
        long availableSpace = getAvailableSpaceInBytes();
        return formatFileSize(availableSpace);
    }

    public static long getAvailableSpaceInBytes() {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdCard = Environment.getExternalStorageDirectory();
            StatFs statFs = new StatFs(sdCard.getPath());
            long blockSize = statFs.getBlockSizeLong();
            long availableBlocks = statFs.getAvailableBlocksLong();
            return availableBlocks * blockSize;
        } else {
            return -1;
        }
    }
}
