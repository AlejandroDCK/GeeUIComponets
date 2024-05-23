package com.letianpai.robot.components.expressionres;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.letianpai.robot.components.expressionres.parser.ExpressionFileData;
import com.letianpai.robot.components.expressionres.parser.ExpressionFileInfo;
import com.letianpai.robot.components.storage.RobotSubConfigConst;
import com.letianpai.robot.components.storage.RobotSubSharedPreference;
import com.letianpai.robot.components.utils.AssetsUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 表情资源配置
 * @author liujunbin
 */
public class ExpressionResConfigCreateManager implements RobotSubConfigConst {

    private static ExpressionResConfigCreateManager mExpressionResConfigCreateManager;
    private Context mContext;
    private String ASSETS_PATH = "video";
    private String ExpressionFile = "/storage/emulated/0/Android/ExpressionConfig.txt";
    private Gson gson;

    private ExpressionResConfigCreateManager(Context context) {
        this.mContext = context;
        gson = new Gson();
    }

    public static ExpressionResConfigCreateManager getInstance(Context context) {
        if (mExpressionResConfigCreateManager == null) {
            mExpressionResConfigCreateManager = new ExpressionResConfigCreateManager(context);
        }
        return mExpressionResConfigCreateManager;

    }

    public void createExpressionFile() {
        Log.e("expression_list", "createExpressionFile ==== 0========: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("expression_list", "createExpressionFile ==== 1========: ");
                long updateTime = System.currentTimeMillis()/1000;
                String[] faceList = AssetsUtils.getAllAssetsList(mContext,ASSETS_PATH);
                if (faceList != null){
                    Log.e("expression_list", "createExpressionFile ==== 2========: faceList.length: "+ faceList.length);
                }
                String version = "23122701";
                Log.e("expression_list", "createExpressionFile ==== 3 ========: ");
                ExpressionFileInfo[] fileInfoList = new ExpressionFileInfo[faceList.length];
                if (fileInfoList != null){
                    Log.e("expression_list", "createExpressionFile ==== 4========: fileInfoList.length: "+ fileInfoList.length);
                }
                Log.e("expression_list", "createExpressionFile ==== 5========: ");

                for (int i = 0; i< fileInfoList.length; i++){
                    String fileTag = faceList[i].replace(".mp4","");
                    ExpressionFileInfo fileInfo = new ExpressionFileInfo();
                    fileInfo.setFile_tag(fileTag);
                    fileInfo.setFile_name(fileTag);
                    fileInfo.setFile_url(faceList[i]);
                    fileInfo.setUpdate_time(updateTime);
                    fileInfoList[i] = fileInfo;

                }

                ExpressionFileData data = new ExpressionFileData();
                data.setFile_list(fileInfoList);
                data.setVersion(version);
                if (data != null){
                    String jsonString = gson.toJson(data);
                    if (!TextUtils.isEmpty(jsonString)){
                        writeInfoToFile(jsonString,ExpressionFile);
                    }
                }
            }
        }).start();

    }

    /**
     *
     * @param content   要写入文件的字符串
     * @param filePath  目标文件的路径
     */
    private void writeInfoToFile(String content,String filePath) {
        File file = new File(filePath);

        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content);
            bufferedWriter.close();
            System.out.println("字符串已成功写入文件。");
        } catch (IOException e) {
            System.out.println("写入文件时发生错误：" + e.getMessage());
        }

    }


}
