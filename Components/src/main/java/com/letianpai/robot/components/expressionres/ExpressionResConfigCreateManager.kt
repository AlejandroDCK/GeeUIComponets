package com.letianpai.robot.components.expressionres

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.letianpai.robot.components.expressionres.parser.ExpressionFileData
import com.letianpai.robot.components.expressionres.parser.ExpressionFileInfo
import com.letianpai.robot.components.storage.RobotSubConfigConst
import com.letianpai.robot.components.utils.AssetsUtils
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * 表情资源配置
 * @author liujunbin
 */
class ExpressionResConfigCreateManager private constructor(private val mContext: Context) :
    RobotSubConfigConst {
    private val ASSETS_PATH = "video"
    private val ExpressionFile = "/storage/emulated/0/Android/ExpressionConfig.txt"
    private val gson = Gson()

    fun createExpressionFile() {
        Log.e("expression_list", "createExpressionFile ==== 0========: ")
        Thread {
            Log.e("expression_list", "createExpressionFile ==== 1========: ")
            val updateTime = System.currentTimeMillis() / 1000
            val faceList = AssetsUtils.getAllAssetsList(mContext, ASSETS_PATH)
            if (faceList != null) {
                Log.e(
                    "expression_list",
                    "createExpressionFile ==== 2========: faceList.length: " + faceList.size
                )
            }
            val version = "23122701"
            Log.e("expression_list", "createExpressionFile ==== 3 ========: ")
            val fileInfoList = arrayOfNulls<ExpressionFileInfo>(
                faceList!!.size
            )
            if (fileInfoList != null) {
                Log.e(
                    "expression_list",
                    "createExpressionFile ==== 4========: fileInfoList.length: " + fileInfoList.size
                )
            }
            Log.e("expression_list", "createExpressionFile ==== 5========: ")

            for (i in fileInfoList.indices) {
                val fileTag = faceList[i].replace(".mp4", "")
                val fileInfo = ExpressionFileInfo()
                fileInfo.file_tag = fileTag
                fileInfo.file_name = fileTag
                fileInfo.file_url = faceList[i]
                fileInfo.update_time = updateTime
                fileInfoList[i] = fileInfo
            }

            val data = ExpressionFileData()
            data.file_list = fileInfoList
            data.version = version
            if (data != null) {
                val jsonString = gson.toJson(data)
                if (!TextUtils.isEmpty(jsonString)) {
                    writeInfoToFile(jsonString, ExpressionFile)
                }
            }
        }.start()
    }

    /**
     *
     * @param content   要写入文件的字符串
     * @param filePath  目标文件的路径
     */
    private fun writeInfoToFile(content: String, filePath: String) {
        val file = File(filePath)

        try {
            val writer = FileWriter(filePath)
            val bufferedWriter = BufferedWriter(writer)
            bufferedWriter.write(content)
            bufferedWriter.close()
            println("字符串已成功写入文件。")
        } catch (e: IOException) {
            println("写入文件时发生错误：" + e.message)
        }
    }


    companion object {
        private var mExpressionResConfigCreateManager: ExpressionResConfigCreateManager? = null
        fun getInstance(context: Context): ExpressionResConfigCreateManager? {
            if (mExpressionResConfigCreateManager == null) {
                mExpressionResConfigCreateManager = ExpressionResConfigCreateManager(context)
            }
            return mExpressionResConfigCreateManager
        }
    }
}
