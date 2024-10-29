package com.letianpai.robot.components.expression

import android.content.Context
import android.net.Uri
import android.text.TextUtils

class ExpressionCenter private constructor(context: Context) {
    private var mContext: Context? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        this.mContext = context
    }

    fun getExpressionPath(name: String) {
        Thread(Runnable {
            val contentResolver = mContext!!.contentResolver
            val uri = Uri.parse("content://com.letianpai.robot.resources.provider/expression")
            val cursor = contentResolver.query(
                uri, arrayOf("fileName", "filePath", "fileTag", "defaultPath"), "fileName", arrayOf(
                    "$name.mp4"
                ), null
            )
            var fileName: String? = ""
            var filePath: String? = ""
            var fileTag: String? = ""
            var defaultPath: String? = ""

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    fileName = cursor.getString(cursor.getColumnIndexOrThrow("fileName"))
                    filePath = cursor.getString(cursor.getColumnIndexOrThrow("filePath"))
                    fileTag = cursor.getString(cursor.getColumnIndexOrThrow("fileTag"))
                    defaultPath = cursor.getString(cursor.getColumnIndexOrThrow("defaultPath"))
                }
                cursor.close()
            }
            if (!TextUtils.isEmpty(filePath)) {
                ExpressionPathCallback.instance.updateExpressionPath(filePath)
            } else if (TextUtils.isEmpty(filePath) && !TextUtils.isEmpty(defaultPath)) {
                ExpressionPathCallback.instance.updateExpressionPath(defaultPath)
            } else {
                //切换下一个表情
                ExpressionPathCallback.instance.expressionFileNoExit(name)
            }
        }).start()
    }


    companion object {
        private var instance: ExpressionCenter? = null
        fun getInstance(context: Context): ExpressionCenter? {
            synchronized(ExpressionCenter::class.java) {
                if (instance == null) {
                    instance = ExpressionCenter(context.getApplicationContext())
                }
                return instance
            }
        }
    }
}
