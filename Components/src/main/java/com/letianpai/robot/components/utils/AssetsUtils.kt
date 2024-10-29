package com.letianpai.robot.components.utils

import android.content.Context
import android.util.Log
import java.io.IOException

object AssetsUtils {
    /**
     * @param assetsPath If you want to read something in the assets root directory, pass in ‘’.
     * If you need to read the list of all files in a folder under assets, pass in the name of the folder.
     * If you have multiple levels of folders, pass in the names of each level, separated by ‘/’.
     * @return All file names
     * @title: getAllAssetsList
     * @return: String[]
     */
    fun getAllAssetsList(context: Context, assetsPath: String?): Array<String>? {
        val manager = context.assets
        var fileNames: Array<String>? = null
        try {
            fileNames = manager.list(assetsPath!!)
            for (fileName in fileNames!!) {
                Log.e("expression_list", "expressionFileName: $fileName")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return fileNames
    }
}
