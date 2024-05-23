package com.letianpai.robot.components.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

public class AssetsUtils {

    /**
     * @param assetsPath 如果读取assets根目录下的东西 传入""即可
     *                   如果需要读取assets下某个文件夹下的所有文件列表，传文件夹名称 即可
     *                   有多级文件夹，传入各级文件夹名称，使用"/"分隔即可
     * @return 所有文件名
     * @title: getAllAssetsList
     * @return: String[]
     */
    public static String[] getAllAssetsList(Context context, String assetsPath) {
        AssetManager manager = context.getAssets();
        String[] fileNames = null;
        try {
            fileNames = manager.list(assetsPath);
            for (String fileName : fileNames) {
                Log.e("expression_list", "expressionFileName: "+fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }



}
