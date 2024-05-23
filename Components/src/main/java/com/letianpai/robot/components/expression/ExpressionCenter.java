package com.letianpai.robot.components.expression;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class ExpressionCenter {


    private static ExpressionCenter instance;
    private Context mContext;

    private ExpressionCenter(Context context) {
        init(context);
    }

    public static ExpressionCenter getInstance(Context context) {
        synchronized (ExpressionCenter.class) {
            if (instance == null) {
                instance = new ExpressionCenter(context.getApplicationContext());
            }
            return instance;
        }

    }

    private void init(Context context) {
        this.mContext = context;

    }

    public void getExpressionPath(String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentResolver contentResolver = mContext.getContentResolver();
                Uri uri = Uri.parse("content://com.letianpai.robot.resources.provider/expression");
                Cursor cursor = contentResolver.query(uri, new String[]{"fileName", "filePath", "fileTag", "defaultPath"}, "fileName", new String[]{name+".mp4"}, null);
                String fileName = "";
                String filePath = "";
                String fileTag = "";
                String defaultPath = "";

                if (cursor!=null){
                    while (cursor.moveToNext()){
                        fileName = cursor.getString(cursor.getColumnIndexOrThrow("fileName"));
                        filePath = cursor.getString(cursor.getColumnIndexOrThrow("filePath"));
                        fileTag = cursor.getString(cursor.getColumnIndexOrThrow("fileTag"));
                        defaultPath = cursor.getString(cursor.getColumnIndexOrThrow("defaultPath"));
                    }
                    cursor.close();
                }
                if (!TextUtils.isEmpty(filePath)){
                    ExpressionPathCallback.getInstance().updateExpressionPath(filePath);
                }else if (TextUtils.isEmpty(filePath) && !TextUtils.isEmpty(defaultPath)){
                    ExpressionPathCallback.getInstance().updateExpressionPath(defaultPath);
                }else{
                    //切换下一个表情
                    ExpressionPathCallback.getInstance().expressionFileNoExit(name);
                }
            }
        }).start();
    }




}
