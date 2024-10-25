package com.letianpai.robot.components.utils

import android.os.Environment
import android.os.StatFs
import kotlin.math.log10
import kotlin.math.pow

/**
 * @author liujunbin
 */
object SDCardUtil {
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
    fun formatFileSize(sizeInBytes: Long): String {
        if (sizeInBytes <= 0) {
            return "0 B"
        }

        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (log10(sizeInBytes.toDouble()) / log10(1024.0)).toInt()
        return String.format(
            "%.1f %s",
            sizeInBytes / 1024.0.pow(digitGroups.toDouble()),
            units[digitGroups]
        )
    }

    val availableSpaceOnSDCard: String
        get() {
            val availableSpace = availableSpaceInBytes
            return formatFileSize(availableSpace)
        }

    val availableSpaceInBytes: Long
        get() {
            val state = Environment.getExternalStorageState()

            if (Environment.MEDIA_MOUNTED == state) {
                val sdCard = Environment.getExternalStorageDirectory()
                val statFs = StatFs(sdCard.path)
                val blockSize = statFs.blockSizeLong
                val availableBlocks = statFs.availableBlocksLong
                return availableBlocks * blockSize
            } else {
                return -1
            }
        }
}
