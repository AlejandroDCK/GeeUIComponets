package com.letianpai.robot.components.appinfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class AppListManager {
    private ArrayList<String> packageList = new ArrayList<>();

    public static void getInstalledAppsList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(0);

        for (ApplicationInfo appInfo : installedApps) {
            // 过滤掉系统应用
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = appInfo.loadLabel(packageManager).toString();
                String packageName = appInfo.packageName;
                Log.d("InstalledApp", "App Name: " + appName + ", Package Name: " + packageName);
            }
        }
    }

    public static List<String> getInstalledAppsList2(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(0);
        List<String> mList = new ArrayList<>();
        for (ApplicationInfo appInfo : installedApps) {
            String appName = appInfo.loadLabel(packageManager).toString();
            String packageName = appInfo.packageName;
            Log.d("InstalledApp", "App Name: " + appName + ", Package Name: " + packageName);
            mList.add(packageName);
        }
        return mList;
    }

    private static final int PERMISSION_REQUEST_CODE = 123;

    private void checkPermissionsAndFetchAppsList(Activity context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.QUERY_ALL_PACKAGES)
                == PackageManager.PERMISSION_GRANTED) {
            // 已经获得权限，可以获取应用列表
            getInstalledAppsList(context);
        } else {
            // 未获得权限，请求用户授权
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.QUERY_ALL_PACKAGES},
                    PERMISSION_REQUEST_CODE);
        }
    }

    private static ArrayList<String> initPackageList() {
        ArrayList<String> packageList = new ArrayList<>();
        packageList.add(PackageConsts.TAKEPHOTO_PACKAGE_NAME);
        packageList.add(PackageConsts.ROBOT_PACKAGE_NAME);
        packageList.add(PackageConsts.LAUNCHER_PACKAGE_NAME);
        packageList.add(PackageConsts.AUTO_APP_PACKAGE_NAME);
        packageList.add(PackageConsts.LEX_CLASS_PACKAGE);
        packageList.add(PackageConsts.SPEECH_PACKAGE_NAME);
        packageList.add(PackageConsts.ALARM_PACKAGE_NAME);
        packageList.add(PackageConsts.STOCK_PACKAGE_NAME);
        packageList.add(PackageConsts.WEATHER_PACKAGE_NAME);
        packageList.add(PackageConsts.PACKAGE_NAME_COUNT_DOWN);
        packageList.add(PackageConsts.PACKAGE_NAME_COMMEMORATION);
        packageList.add(PackageConsts.PACKAGE_NAME_WORDS);
        packageList.add(PackageConsts.PACKAGE_NAME_NEWS);
        packageList.add(PackageConsts.PACKAGE_NAME_MESSAGE);
        packageList.add(PackageConsts.PACKAGE_NAME_FANS);
        packageList.add(PackageConsts.PACKAGE_NAME_IDENT);
        packageList.add(PackageConsts.PACKAGE_NAME_CUSTOM);
        packageList.add(PackageConsts.PACKAGE_NAME_VIDEO_CALL);
        packageList.add(PackageConsts.PACKAGE_NAME_LAMP);
        packageList.add(PackageConsts.PACKAGE_NAME_REMINDER);
        packageList.add(PackageConsts.PACKAGE_APP_NAME_REMINDER);
        packageList.add(PackageConsts.PACKAGE_APP_NAME_SPECTRUM);
        packageList.add(PackageConsts.PACKAGE_APP_NAME_POMO);
        packageList.add(PackageConsts.PACKAGE_APP_NAME_OTA);
        packageList.add(PackageConsts.PACKAGE_APP_NAME_MEDITATION);
        packageList.add(PackageConsts.PACKAGE_NAME_TIME);
        packageList.add(PackageConsts.PACKAGE_NAME_EXPRESSION);
        packageList.add(PackageConsts.PACKAGE_NAME_ALBUM);
        packageList.add(PackageConsts.PACKAGE_NAME_WIFI_CONNECTOR);
        packageList.add(PackageConsts.PACKAGE_NAME_GEEUI_SETTINGS);
        packageList.add(PackageConsts.PACKAGE_NAME_APP_STORE);
        packageList.add(PackageConsts.PACKAGE_NAME_FIST_PALM_GAME);
        packageList.add(PackageConsts.PACKAGE_NAME_VOICE_MEMO);
        packageList.add(PackageConsts.PACKAGE_NAME_MCU_SERVICE);
        packageList.add(PackageConsts.PACKAGE_NAME_APHORISMS);
        packageList.add(PackageConsts.PACKAGE_NAME_GEEUI_RESOURCE);
        packageList.add(PackageConsts.PACKAGE_NAME_GEEUI_VIDEO_PLAYER);
        packageList.add(PackageConsts.PACKAGE_NAME_GEEUI_INSTALLER);
        return packageList;
    }

    public static boolean isInThePackageList(String packageName) {
        ArrayList<String> packageList = initPackageList();
        if (packageList.contains(packageName)) {
            return true;
        } else {
            return false;
        }
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // 用户授予了权限，获取应用列表
//                getInstalledAppsList();
//            } else {
//                // 用户拒绝了权限请求，可以根据需要进行适当的处理
//            }
//        }
//    }

}
