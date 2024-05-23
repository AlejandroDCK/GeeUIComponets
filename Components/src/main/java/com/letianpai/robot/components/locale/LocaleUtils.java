package com.letianpai.robot.components.locale;

import android.util.Log;

import java.util.Locale;

public class LocaleUtils {

    private static String PRC_LAUNGUAGE = "zh";
    public static boolean isChinese(){
        String language = Locale.getDefault().getLanguage();
        if (language.equals(PRC_LAUNGUAGE)){
            return true;
        }else{
            return false;
        }
    }
}
