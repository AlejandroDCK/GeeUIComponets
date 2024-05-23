package com.letianpai.robot.components.network.nets;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.letianpai.robot.components.locale.LocaleUtils;
import com.letianpai.robot.components.network.callback.AppQRCodeInfoCallback;
import com.letianpai.robot.components.network.callback.AppTitleTipCallback;
import com.letianpai.robot.components.network.callback.AppUploadConfigCallback;
import com.letianpai.robot.components.network.callback.apptips.AppTips;
import com.letianpai.robot.components.network.callback.qrcode.QRCodeTips;
import com.letianpai.robot.components.network.encryption.EncryptionUtils;
import com.letianpai.robot.components.network.system.SystemUtil;
import com.letianpai.robot.components.parser.appstatus.AppInfo;
import com.letianpai.robot.components.parser.uploadconfig.UploadDataConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.letianpai.robot.components.utils.GeeUILogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 *
 */
public class GeeUiNetManager {


    /**
     * 获取股票信息
     *
     * @param context
     * @param callback
     */
    public static void getDeviceInfo(Context context, boolean isChinese, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String auth = EncryptionUtils.getHardCodeSign(ts);
        GeeUINetworkUtil.get11(context, isChinese, auth, ts, GeeUINetworkConsts.GET_SN_BY_MAC, callback);
    }

    /**
     * 获取日历列表
     *
     * @param context
     * @param callback
     */
    public static void getCalendarList(Context context, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.CALENDAR_LIST,callback);
        get(context, GeeUINetworkConsts.CALENDAR_LIST, callback);
    }

//    /**
//     * 获取倒计时列表
//     *
//     * @param context
//     * @param callback
//     */
//    public static void getCountDownList(Context context, Callback callback) {
////        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
//        get(context, GeeUINetworkConsts.COUNTDOWN_LIST, callback);
//    }

    /**
     * 获取倒计时列表
     *
     * @param context
     * @param callback
     */
    public static void getCountDownList(Context context, boolean isChinese, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.COUNTDOWN_LIST, callback);
    }

    /**
     * 获取
     *
     * @param context
     * @param callback
     */
    public static void getSessionToken(Context context, boolean isChinese, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.GET_SESSION_TOKEN, callback);
    }

    /**
     * 获取
     *
     * @param context
     * @param callback
     */
    public static void getOTAInfo(Context context, boolean isChinese, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.GET_LAST_PACKAGE, callback);
    }

    /**
     * 获取冥想列表
     *
     * @param context
     * @param callback
     */
    public static void getMeditationConfig(Context context, boolean isChinese, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.GET_MEDITATION_CONFIG, callback);
    }

    /**
     * 获取设置渠道的LOGO
     * @param context
     * @param callback
     */
    public static void getDeviceChannelLogo(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_DEVICE_CHANNELLOGO, callback);
    }

//    /**
//     * 获取粉丝信息
//     *
//     * @param context
//     * @param callback
//     */
//    public static void getFansInfoList(Context context, Callback callback) {
////        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.FANS_INFO_LIST,callback);
//        get(context, GeeUINetworkConsts.FANS_INFO_LIST, callback);
//    }
//

    /**
     * 获取粉丝信息
     *
     * @param context
     * @param callback
     */
    public static void getFansInfoList(Context context, boolean isChinese, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.FANS_INFO_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.FANS_INFO_LIST, callback);
    }


    /**
     * 获取通用信息列表
     *
     * @param context
     * @param callback
     */
    public static void getGeneralInfoList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GENERAL_INFO, callback);
    }


    /**
     * 获取通用信息列表
     *
     * @param context
     * @param callback
     */
    public static void getCustomWatchConfig(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.CUSTOM_WATCH_CONFIG, callback);
    }

    /**
     * 获取通用信息列表
     *
     * @param context
     * @param callback
     */
    public static void getCloudFileToken(Context context, boolean isChinese, Callback callback) {
        getWithModelPath(context, isChinese, GeeUINetworkConsts.CLOUD_FILE_TOKEN, callback);
    }

    /**
     * 获取天气信息
     *
     * @param context
     * @param callback
     */
    public static void getWeatherInfo(Context context, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.WEATHER_INFO,callback);
        get(context, GeeUINetworkConsts.WEATHER_INFO, callback);
    }

    /**
     * 获取天气信息
     *
     * @param context
     * @param callback
     */
    public static void getWeatherInfo(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.WEATHER_INFO, callback);
    }

    /**
     * 获取天气信息
     *
     * @param context
     * @param callback
     */
    public static void getTomatoList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_TOMATO_LIST, callback);
    }

    /**
     * 获取闹钟列表
     *
     * @param context
     * @param callback
     */
    public static void getClockList(Context context, Callback callback) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.CLOCK_LIST,callback);
        get(context, GeeUINetworkConsts.CLOCK_LIST, callback);
    }

    /**
     * 获取闹钟列表
     *
     * @param context
     * @param callback
     */
    public static void getClockList(Context context, boolean isChinese, Callback callback) {
        getWithPageSize(context, isChinese, GeeUINetworkConsts.CLOCK_LIST, 50, callback);
    }

    /**
     * 获取股票信息
     *
     * @param context
     * @param callback
     */
    public static void getStock(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.STOCK_INFO, callback);
    }
    /**
     * 获取设备绑定信息
     *
     * @param context
     * @param callback
     */
    public static void isDeviceBind(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.IS_DEVICE_BIND, callback);
    }

    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    public static void isDeviceBind1(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.IS_DEVICE_BIND, callback);
    }

    /**
     * 获取信息
     *
     * @param context
     * @param callback
     */
    public static void getCountryByIp(Context context, boolean isChinese, Callback callback) {
//        get(context, isChinese, GeeUINetworkConsts.GET_REGION_BY_DEVICE_IP, callback);
        get(context, GeeUINetworkConsts.GET_REGION_BY_DEVICE_IP, callback);
    }
    /**
     * 获取信息
     *
     * @param context
     * @param callback
     */
    public static void getCountryByIp(Context context,Callback callback) {
        get(context, GeeUINetworkConsts.GET_REGION_BY_DEVICE_IP, callback);
    }


    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    public static void getCustomList(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.CUSTOM_LIST, callback);
    }

    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    public static void getCustomList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.CUSTOM_LIST, callback);
    }

    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    public static void getCustomPhotoList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.CUSTOM_PHOTO_LIST, callback);
    }

    /**
     * 获取跑马灯信息
     *
     * @param context
     * @param callback
     */
    public static void getLampCustomInfo(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.LAMP_CUSTOM_INFO, callback);
    }

    /**
     * 获取跑马灯信息
     *
     * @param context
     * @param callback
     */
    public static void getLampCustomInfo(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.LAMP_CUSTOM_INFO, callback);
    }

    /**
     * 获取纪念日列表
     *
     * @param context
     * @param callback
     */
    public static void getCommemorationList(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.COMMEMORATION_LIST, callback);
    }

    /**
     * 获取纪念日列表
     *
     * @param context
     * @param callback
     */
    public static void getCommemorationList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.COMMEMORATION_LIST, callback);
    }

    /**
     * 获取新闻列表
     *
     * @param context
     * @param callback
     */
    public static void getNewsList(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.NEWS_LIST, callback);
    }

    /**
     * 获取新闻列表
     *
     * @param context
     * @param callback
     */
    public static void getNewsList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.NEWS_LIST, callback);
    }
//
//    /**
//     * 获取全部配置
//     *
//     * @param context
//     * @param callback
//     */
//    public static void getAllConfig(Context context, Callback callback) {
//        get(context, GeeUINetworkConsts.GET_ALL_CONFIG, callback);
//    }

    /**
     * 获取全部配置
     *
     * @param context
     * @param callback
     */
    public static void getAllConfig(Context context, Callback callback) {
        get(context, LocaleUtils.isChinese(), GeeUINetworkConsts.GET_ALL_CONFIG, callback);
    }

    /**
     * 获取APP配置
     *
     * @param context
     * @param callback
     */
    public static void getUserAppsConfig(Context context, Callback callback) {
        get(context, LocaleUtils.isChinese(), GeeUINetworkConsts.GET_USER_APPS_CONFIG, callback);
    }

    /**
     * 获取自动切换的APP
     *
     * @param context
     * @param callback
     */
    public static void getAppsShowConfig(Context context, Callback callback) {
        get(context, LocaleUtils.isChinese(), GeeUINetworkConsts.GET_APPS_SHOW_CONFIG, callback);
    }

    /**
     * 获取全部配置
     *
     * @param context
     * @param callback
     */
    public static void getAllConfig(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_ALL_CONFIG, callback);
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    public static void getAllAppList(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.GET_ALL_APP_LIST, callback);
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    public static void getAllAppList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_ALL_APP_LIST, callback);
    }
    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    public static void getRobotDisplayAppList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_APP_LIST, callback);
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    public static void getReChargeConfig(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_RECHARGE_CONFIG, callback);
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    public static void getAppBgInfo(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_APP_BG_INFO, callback);
    }

    /**
     * 获取最新安装包
     *
     * @param context
     * @param callback
     */
    public static void getLatestPackage(Context context, int appId, Callback callback) {
        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_APP_ID, appId + "");
        get1(context, hashMap, GeeUINetworkConsts.GET_LATEST_PACKAGE, callback);
    }

    /**
     * 获取设备端应用列表
     *
     * @param context
     * @param callback
     */
    public static void getAppList(Context context, boolean isChinese, Callback callback) {
        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_APP_LIST, callback);
    }

    /**
     * 获取最新安装包
     *
     * @param context
     * @param callback
     */
    public static void getLatestPackage(Context context, boolean isChinese, int appId, Callback callback) {
        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_APP_ID, appId + "");
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_LATEST_PACKAGE, callback);
    }

    /**
     * 获取绑定码
     *
     * @param context
     * @param callback
     */
    public static void getBindCode(Context context, boolean isChinese, Callback callback) {
        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_BIND_CODE, callback);
    }

    /**
     * 获取新闻列表
     *
     * @param context
     * @param callback
     */
    public static void moduleChange(Context context, String modeName, Callback callback) {
        String[] modules = new String[]{modeName};
        //TODO 增加获取
        HashMap hashMap = new HashMap();
        hashMap.put("selected_module_tag_list", modules);
        post(context, GeeUINetworkConsts.POST_MODULE_CHANGE, hashMap, callback);
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    public static void updateAppStatus(Context context, AppInfo appInfo, String sn, Callback callback) {
        AppInfo[] appInfos = new AppInfo[]{appInfo};
        //TODO 增加获取
        HashMap hashMap = new HashMap();
        hashMap.put("appInfo", appInfos);
        hashMap.put("sn", sn);
        post(context, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback);
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    public static void updateAppStatus(Context context, boolean isChinese, AppInfo appInfo, String sn, Callback callback) {
        AppInfo[] appInfos = new AppInfo[]{appInfo};
        //TODO 增加获取
        HashMap hashMap = new HashMap();
        hashMap.put("appInfo", appInfos);
        hashMap.put("sn", sn);
        post(context, isChinese, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback);
    }
    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    public static void updateUserAppStatus(Context context, boolean isChinese, int appId, String appName,  String appPackageName,Callback callback) {
        //TODO 增加获取
        HashMap hashMap = new HashMap();
        hashMap.put("app_id", appId);
        hashMap.put("app_name", appName);
        hashMap.put("app_package_name", appPackageName);
//        hashMap.put("sn", sn);
        post(context, isChinese, GeeUINetworkConsts.POST_UPLOAD_USER_APP_STATUS, hashMap, callback);
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    public static void updateAppStatus(Context context, ArrayList<AppInfo> appInfo, String sn, Callback callback) {
        AppInfo[] appInfos = new AppInfo[appInfo.size()];
        for (int i = 0; i < appInfo.size(); i++) {
            appInfos[i] = appInfo.get(i);
        }

        //TODO 增加获取
        HashMap hashMap = new HashMap();
        hashMap.put("appInfo", appInfos);
        hashMap.put("sn", sn);
        post(context, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback);
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    public static void updateAppStatus(Context context, boolean isChinese, ArrayList<AppInfo> appInfo, String sn, Callback callback) {
        AppInfo[] appInfos = new AppInfo[appInfo.size()];
        for (int i = 0; i < appInfo.size(); i++) {
            appInfos[i] = appInfo.get(i);
        }

        //TODO 增加获取
        HashMap hashMap = new HashMap();
        hashMap.put("appInfo", appInfos);
        hashMap.put("sn", sn);
        post(context, isChinese, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback);
    }

    /**
     * 重置机器人状态
     *
     * @param callback
     */
    public static void robotReset(Context context, Callback callback) {
        HashMap hashMap = new HashMap();
        hashMap.put("reset_status", 0);
        post(context, GeeUINetworkConsts.POST_RESET_STATUS, hashMap, callback);
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    public static void uploadStatus(Context context, HashMap hashMap, Callback callback) {
        post(context, GeeUINetworkConsts.UPLOAD_STATUS, hashMap, callback);
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    public static void uploadStatus(Context context, boolean isChinese, HashMap hashMap, Callback callback) {
        post(context, isChinese, GeeUINetworkConsts.UPLOAD_STATUS, hashMap, callback);
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    public static void bindRobot(Context context, boolean isChinese, HashMap hashMap, Callback callback) {
        post(context, isChinese, GeeUINetworkConsts.POST_MANAGE_ADD, hashMap, callback);
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    public static void uploadBatteryStatus(Context context, boolean isChinese, HashMap hashMap, Callback callback) {
        post(context, isChinese, GeeUINetworkConsts.UPLOAD_BATTERY_STATUS, hashMap, callback);
    }

    private static void uploadBatteryStatus(Context context, int chargingStatus, Callback callback) {
        HashMap hashMap = new HashMap();
        String sn = SystemUtil.getLtpSn();
        hashMap.put("sn", sn);
        hashMap.put("automatic_battery", chargingStatus);

        GeeUiNetManager.uploadBatteryStatus(context, SystemUtil.isInChinese(), hashMap, callback);
    }

    public static void uploadExitAutoCharging(Context context, Callback callback) {
        uploadBatteryStatus(context, 0, callback);
    }

    public static void uploadEnterAutoCharging(Context context, Callback callback) {
        uploadBatteryStatus(context, 1, callback);
    }


    /**
     * 上传lexlog
     *
     * @param context
     * @param callback
     */
    public static void uploadLexLog(Context context, boolean isChinese, String data, Callback callback) {
        String sn = SystemUtil.getLtpSn();
        //TODO 增加获取
        HashMap hashMap = new HashMap();
        hashMap.put("sn", sn);
        hashMap.put("data", data);
        post(context, isChinese, GeeUINetworkConsts.UPLOAD_LEX_LOG, hashMap, callback);
    }

//    /**
//     * 获取唤醒词列表
//     * @param context
//     * @param callback
//     */
//    public static void getTipsList(Context context, Callback callback){
//
//        HashMap<String,String> hashMap = new HashMap<>();
//        String sn = SystemUtil.getLtpSn();
//        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN,sn);
//        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN,sn);
//        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG,GeeUINetConsts.HASH_MAP_CONFIG_KEY_VALUE);
//        GeeUINetworkUtil.get(context,GeeUINetworkConsts.GET_COMMON_CONFIG,hashMap,callback);
////        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.GET_COMMON_CONFIG,callback);
//    }

    /**
     * 获取唤醒词列表
     *
     * @param context
     * @param callback
     */
    public static void getTipsList(Context context, boolean isChinese, Callback callback) {

        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_KEY_VALUE);
//        GeeUINetworkUtil.get(context,GeeUINetworkConsts.GET_COMMON_CONFIG,hashMap,callback);
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.GET_COMMON_CONFIG,callback);
        get1(context, isChinese, ts, GeeUINetworkConsts.GET_COMMON_CONFIG, callback);
    }

    /**
     * 获取唤醒词列表
     *
     * @param context
     * @param callback
     */
    public static void getCommonList(Context context, boolean isChinese, String key, Callback callback) {

        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, key);
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, callback);
    }

    /**
     * 获取表情文案列表
     *
     * @param context
     * @param callback
     */
    public static void getExpressionList(Context context, boolean isChinese, Callback callback) {
        getCommonList(context, isChinese, GeeUINetConsts.HASH_MAP_CONFIG_EXPRESSION_CONTENT_VALUE, callback);
    }

    /**
     * 获取格言列表
     *
     * @param context
     * @param callback
     */
    public static void getAphorismsList(Context context, boolean isChinese, Callback callback) {
        getCommonList(context, isChinese, GeeUINetConsts.HASH_MAP_CONFIG_APHORISMS_VALUE, callback);
    }


    /**
     * 获取机器人的上传状态配置
     *
     * @param context
     * @param callback
     */
    public static void getUploadStatusConfig(Context context, boolean isChinese, Callback callback) {
        getCommonList(context, isChinese, GeeUINetConsts.HASH_MAP_ROBOT_UPLOAD_STATUS_CONFIG, callback);
    }


    /**
     * 获取Tips 列表
     *
     * @param context
     * @param callback
     */
    public static void getAppTipsList(Context context, Callback callback) {

        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_APP_TIPS_VALUE);
        get1(context, ts, GeeUINetworkConsts.GET_COMMON_CONFIG, callback);
    }

    public static void getMenuConfig(Context context, boolean isChinese,Callback callback) {
        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_ROBOT_MENU_LIST);
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, callback);
    }

    /**
     * 获取Tips 列表
     *
     * @param context
     */
    public static void getAppTipsList(Context context, AppTitleTipCallback.AppTitleTipUpdateListener appTitleTipUpdateListener) {

        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_APP_TIPS_VALUE);

        get1(context, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response != null && response.body() != null) {

                    String info = "";
                    if (response != null && response.body() != null) {
                        info = response.body().string();
                    }

//                    Log.e("letianpai_tips", "getAppTipsList ======== info: " + info);
//                    Log.e("letianpai_tips", "getAppTipsList =========: " + info);
                    ;
                    AppTips appTips;
                    try {
                        if (info != null) {
                            appTips = new Gson().fromJson(info, AppTips.class);
                            if (appTips != null && appTips.getData() != null && appTips.getData().getConfig_data() != null && appTips.getData().getConfig_data().length > 0) {
                                AppTitleTipCallback.getInstance().registerTimerKeeperUpdateListener(appTitleTipUpdateListener);
                                for (int i = 0; i < appTips.getData().getConfig_data().length; i++) {
                                    if (appTips.getData().getConfig_data()[i] != null && appTips.getData().getConfig_data()[i].getPackageName() != null && appTips.getData().getConfig_data()[i].getPackageName().equals(context.getPackageName())) {
                                        AppTitleTipCallback.getInstance().setAppTitleTip(appTips.getData().getConfig_data()[i].getTip_content(), appTips.getData().getConfig_data()[i].getTip_content_en(), appTips.getData().getConfig_data()[i].getTip_icon());
                                    }
                                }
                                AppTitleTipCallback.getInstance().unregisterTimerKeeperUpdateListener(appTitleTipUpdateListener);
                            }

                        }
                    } catch (Exception e) {

                    }


                }

            }
        });
    }

    /**
     * 获取Tips 列表
     *
     * @param context
     */
    public static void getAppTipsList(Context context, boolean isChinese, AppTitleTipCallback.AppTitleTipUpdateListener appTitleTipUpdateListener) {

        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_APP_TIPS_VALUE);

        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response != null && response.body() != null) {

                    String info = "";
                    if (response != null && response.body() != null) {
                        info = response.body().string();
                    }

//                    Log.e("letianpai_tips", "getAppTipsList ======== info: " + info);
//                    Log.e("letianpai_tips", "getAppTipsList =========: " + info);
                    ;
                    AppTips appTips;
                    try {
                        if (info != null) {
                            appTips = new Gson().fromJson(info, AppTips.class);
                            if (appTips != null && appTips.getData() != null && appTips.getData().getConfig_data() != null && appTips.getData().getConfig_data().length > 0) {
                                AppTitleTipCallback.getInstance().registerTimerKeeperUpdateListener(appTitleTipUpdateListener);
                                for (int i = 0; i < appTips.getData().getConfig_data().length; i++) {
                                    if (appTips.getData().getConfig_data()[i] != null && appTips.getData().getConfig_data()[i].getPackageName() != null && appTips.getData().getConfig_data()[i].getPackageName().equals(context.getPackageName())) {
                                        AppTitleTipCallback.getInstance().setAppTitleTip(appTips.getData().getConfig_data()[i].getTip_content(), appTips.getData().getConfig_data()[i].getTip_content_en(), appTips.getData().getConfig_data()[i].getTip_icon());
                                    }
                                }
                                AppTitleTipCallback.getInstance().unregisterTimerKeeperUpdateListener(appTitleTipUpdateListener);
                            }

                        }
                    } catch (Exception e) {

                    }


                }

            }
        });
    }

    /**
     * 获取Tips 列表
     *
     * @param context
     */
    public static void getUploadDataConfig(Context context, boolean isChinese, AppUploadConfigCallback.AppUploadConfigUpdateListener appUploadConfigUpdateListener) {

        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_ROBOT_UPLOAD_STATUS_CONFIG);

        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response != null && response.body() != null) {

                    String info = "";
                    if (response != null && response.body() != null) {
                        info = response.body().string();
                    }


                    UploadDataConfig uploadDataConfig;
                    if (info != null) {
                        try {

                            uploadDataConfig = new Gson().fromJson(info, UploadDataConfig.class);
                            if (uploadDataConfig != null && uploadDataConfig.getData() != null && uploadDataConfig.getData().getConfig_data() != null && uploadDataConfig.getData().getConfig_data().length > 0) {
                                AppUploadConfigCallback.getInstance().registerAppUploadConfigUpdateListener(appUploadConfigUpdateListener);
                                for (int i = 0; i < uploadDataConfig.getData().getConfig_data().length; i++) {
                                    if (uploadDataConfig.getData().getConfig_data()[i] != null && uploadDataConfig.getData().getConfig_data()[i].getPackageName() != null && uploadDataConfig.getData().getConfig_data()[i].getPackageName().equals(context.getPackageName())) {
                                        AppUploadConfigCallback.getInstance().setAppUploadConfig(uploadDataConfig.getData().getConfig_data()[i].getUpload_frequency());
                                    }
                                }
                                AppUploadConfigCallback.getInstance().unregisterAppUploadConfigUpdateListener(appUploadConfigUpdateListener);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * 获取二维码信息
     *
     * @param context
     */
    public static void getAppQrcodeInfo(Context context, boolean isChinese, AppQRCodeInfoCallback.AppQRCodeInfoUpdateListener appQRCodeInfoUpdateListener) {

        HashMap<String, String> hashMap = new HashMap<>();
        String sn = SystemUtil.getLtpSn();
        String ts = EncryptionUtils.getTs();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_QRCODE_INFO_VALUE);

        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response != null && response.body() != null) {

                    String info = "";
                    if (response != null && response.body() != null) {
                        info = response.body().string();
                    }

//                    GeeUILogUtils.logi("letianpai_qrcode", "getAppQrcodeInfo ======== info: " + info);
                    QRCodeTips qrCodeTips;

                    try {
                        if (info != null) {
                            qrCodeTips = new Gson().fromJson(info, QRCodeTips.class);
                            if (qrCodeTips != null && qrCodeTips.getData() != null && qrCodeTips.getData().getConfig_data() != null && qrCodeTips.getData().getConfig_data().length > 0) {
                                AppQRCodeInfoCallback.getInstance().registerAppQRCodeInfoUpdateListener(appQRCodeInfoUpdateListener);
//                                GeeUILogUtils.logi("letianpai_qrcode", "qrCodeTips.getData().getConfig_data().length: " + qrCodeTips.getData().getConfig_data().length);
                                for (int i = 0; i < qrCodeTips.getData().getConfig_data().length; i++) {
//                                    GeeUILogUtils.logi("letianpai_qrcode", "getAppQrcodeInfo =========2: ");
                                    if (qrCodeTips.getData().getConfig_data()[i] != null && qrCodeTips.getData().getConfig_data()[i].getPackage_name() != null && qrCodeTips.getData().getConfig_data()[i].getPackage_name().equals(context.getPackageName())) {
//                                        GeeUILogUtils.logi("letianpai_qrcode", "getAppQrcodeInfo =========3: ");
                                        AppQRCodeInfoCallback.getInstance().setQrCodeInfo(qrCodeTips.getData().getConfig_data()[i].toString(), qrCodeTips.getData().getConfig_data()[i].isShow_qrcode());

                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
//                        GeeUILogUtils.logi(Log.getStackTraceString(e));
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void get1(Context context, String uri, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
//        String hardCode = "YMcQMMZc49ZM0M";
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        GeeUINetworkUtil.get11(context, auth, sn, ts, uri, callback);
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void get(Context context, String uri, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }

        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        GeeUINetworkUtil.get11(context, auth, sn, ts, uri, callback);
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void get(Context context, boolean isChinese, String uri, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }

        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        GeeUINetworkUtil.get(context, isChinese, auth, sn, ts, uri, callback);
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void getWithPageSize(Context context, boolean isChinese, String uri, int pageSize, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }

        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        GeeUINetworkUtil.getWithPage(context, isChinese, auth, sn, ts, uri, pageSize, callback);
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void getWithModelPath(Context context, boolean isChinese, String uri, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }

        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);
        String modelPath = "tmp";

        GeeUINetworkUtil.get(context, isChinese, auth, sn, ts, modelPath, uri, callback);
    }


    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void get1(Context context, String ts, String uri, Callback callback) {

        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
//        String hardCode = "YMcQMMZc49ZM0M";
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_KEY_VALUE);

        GeeUINetworkUtil.get11(context, auth, hashMap, uri, callback);
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void get1(Context context, boolean isChinese, String ts, String uri, Callback callback) {

        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
//        String hardCode = "YMcQMMZc49ZM0M";
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);
        hashMap.put(GeeUINetConsts.HASH_MAP_KEY_CONFIG, GeeUINetConsts.HASH_MAP_CONFIG_KEY_VALUE);

        GeeUINetworkUtil.get11(context, isChinese, auth, hashMap, uri, callback);
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void get1(Context context, Map<String, String> hashMap, String uri, Callback callback) {

        String sn = null;
        String ts = hashMap.get(GeeUINetConsts.HASH_MAP_KEY_TS);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        GeeUINetworkUtil.get11(context, auth, hashMap, uri, callback);
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    public static void get1(Context context, boolean isChinese, Map<String, String> hashMap, String uri, Callback callback) {
        Log.d("-----", "url++"+uri);
        String sn = null;
        String ts = hashMap.get(GeeUINetConsts.HASH_MAP_KEY_TS);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        GeeUINetworkUtil.get11(context, isChinese, auth, hashMap, uri, callback);
    }

    /**
     * 重置机器人状态
     *
     * @param callback
     */
    public static void post1(Context context, String uri, HashMap bodyMap, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
//        String hardCode = "YMcQMMZc49ZM0M";
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

//        GeeUILogUtils.logi("letianpai_encode", "ts_1: " + ts);
//        GeeUILogUtils.logi("letianpai_encode", "sn_1: " + sn);
//        GeeUILogUtils.logi("letianpai_encode", "hardCode_2: " + hardCode);
//        GeeUILogUtils.logi("letianpai_encode", "auth_1: " + auth);

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        queryMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);

        GeeUINetworkUtil.post3(bodyMap, auth, queryMap, uri, callback);

    }

    /**
     * post
     *
     * @param callback
     */
    public static void post(Context context, String uri, HashMap bodyMap, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        queryMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);

        GeeUINetworkUtil.post3(bodyMap, auth, queryMap, uri, callback);

    }

    /**
     * post
     *
     * @param callback
     */
    public static void post(Context context, boolean isChinese, String uri, HashMap bodyMap, Callback callback) {
        String ts = EncryptionUtils.getTs();
        String sn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        }
        String hardCode = SystemUtil.getHardCode();
        String auth = EncryptionUtils.getRobotSign(sn, hardCode, ts);

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(GeeUINetConsts.HASH_MAP_KEY_TS, ts);
        queryMap.put(GeeUINetConsts.HASH_MAP_KEY_SN, sn);

        GeeUINetworkUtil.post3(bodyMap, isChinese, auth, queryMap, uri, callback);

    }


    /**
     * 获取提醒列表
     *
     * @param context
     * @param callback
     */
    public static void getRemindList(Context context, Callback callback) {
        get(context, GeeUINetworkConsts.GET_USER_REMIND_LIST, callback);
    }

    /**
     * 获取提醒列表
     *
     * @param context
     * @param callback
     */
    public static void getRemindList(Context context, boolean isChinese, Callback callback) {
        get(context, isChinese, GeeUINetworkConsts.GET_USER_REMIND_LIST, callback);
    }


//    /**
//     * 获取股票信息
//     * @param context
//     * @param callback
//     */
//    public static void getStock(Context context, Callback callback){
//        String ts = EncryptionUtils.getTs();
//        String sn = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            sn = Build.getSerial();
//        }
//        String hardCode = SystemUtil.getHardCode();
////        String hardCode = "YMcQMMZc49ZM0M";
//        String auth = EncryptionUtils.getRobotSign(sn,hardCode,ts);
//
//        Log.e("letianpai_encode","ts_1: "+ts);
//        Log.e("letianpai_encode","sn_1: "+sn);
//        Log.e("letianpai_encode","hardCode_2: "+hardCode);
//        Log.e("letianpai_encode","auth_1: "+auth);
//        GeeUINetworkUtil.get11(context , auth, sn,ts, GeeUINetworkConsts.STOCK_INFO,callback);
//    }

//    /**
//     * 获取自定义列表
//     * @param context
//     * @param callback
//     */
//    public static void getCustomList(Context context, Callback callback){
//        String ts = EncryptionUtils.getTs();
//        String sn = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            sn = Build.getSerial();
//        }
//        String hardCode = SystemUtil.getHardCode();
////        String hardCode = "YMcQMMZc49ZM0M";
//        String auth = EncryptionUtils.getRobotSign(sn,hardCode,ts);
//
//        Log.e("letianpai_encode","ts_1: "+ts);
//        Log.e("letianpai_encode","sn_1: "+sn);
//        Log.e("letianpai_encode","hardCode_2: "+hardCode);
//        Log.e("letianpai_encode","auth_1: "+auth);
//        GeeUINetworkUtil.get11(context , auth, sn,ts, GeeUINetworkConsts.CUSTOM_LIST,callback);
//    }


}
