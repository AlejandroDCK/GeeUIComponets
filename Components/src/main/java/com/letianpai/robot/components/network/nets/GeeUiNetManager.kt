package com.letianpai.robot.components.network.nets

import android.content.Context
import android.os.Build
import android.util.Log
import com.google.gson.Gson
import com.letianpai.robot.components.locale.LocaleUtils.isChinese
import com.letianpai.robot.components.network.callback.AppQRCodeInfoCallback
import com.letianpai.robot.components.network.callback.AppTitleTipCallback
import com.letianpai.robot.components.network.callback.AppTitleTipCallback.AppTitleTipUpdateListener
import com.letianpai.robot.components.network.callback.AppUploadConfigCallback
import com.letianpai.robot.components.network.callback.AppUploadConfigCallback.AppUploadConfigUpdateListener
import com.letianpai.robot.components.network.callback.apptips.AppTips
import com.letianpai.robot.components.network.callback.qrcode.QRCodeTips
import com.letianpai.robot.components.network.encryption.EncryptionUtils
import com.letianpai.robot.components.network.system.SystemUtil
import com.letianpai.robot.components.parser.appstatus.AppInfo
import com.letianpai.robot.components.parser.uploadconfig.UploadDataConfig
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 *
 */
object GeeUiNetManager {
    /**
     * 获取股票信息
     *
     * @param context
     * @param callback
     */
    fun getDeviceInfo(context: Context?, isChinese: Boolean, callback: Callback?) {
        val ts: String = EncryptionUtils.ts
        val auth: String = EncryptionUtils.getHardCodeSign(ts)
        GeeUINetworkUtil.get11(
            context,
            isChinese,
            auth,
            ts,
            GeeUINetworkConsts.GET_SN_BY_MAC,
            callback
        )
    }

    /**
     * 获取日历列表
     *
     * @param context
     * @param callback
     */
    fun getCalendarList(context: Context?, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.CALENDAR_LIST,callback);
        get(context, GeeUINetworkConsts.CALENDAR_LIST, callback)
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
    fun getCountDownList(context: Context?, isChinese: Boolean, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.COUNTDOWN_LIST, callback)
    }

    /**
     * 获取
     *
     * @param context
     * @param callback
     */
    fun getSessionToken(context: Context?, isChinese: Boolean, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.GET_SESSION_TOKEN, callback)
    }

    /**
     * 获取
     *
     * @param context
     * @param callback
     */
    fun getOTAInfo(context: Context?, isChinese: Boolean, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.GET_LAST_PACKAGE, callback)
    }

    /**
     * 获取冥想列表
     *
     * @param context
     * @param callback
     */
    fun getMeditationConfig(context: Context?, isChinese: Boolean, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.COUNTDOWN_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.GET_MEDITATION_CONFIG, callback)
    }

    /**
     * 获取设置渠道的LOGO
     * @param context
     * @param callback
     */
    fun getDeviceChannelLogo(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_DEVICE_CHANNELLOGO, callback)
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
    fun getFansInfoList(context: Context?, isChinese: Boolean, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.FANS_INFO_LIST,callback);
        get(context, isChinese, GeeUINetworkConsts.FANS_INFO_LIST, callback)
    }


    /**
     * 获取通用信息列表
     *
     * @param context
     * @param callback
     */
    fun getGeneralInfoList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GENERAL_INFO, callback)
    }


    /**
     * 获取通用信息列表
     *
     * @param context
     * @param callback
     */
    fun getCustomWatchConfig(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.CUSTOM_WATCH_CONFIG, callback)
    }

    /**
     * 获取通用信息列表
     *
     * @param context
     * @param callback
     */
    fun getCloudFileToken(context: Context?, isChinese: Boolean, callback: Callback?) {
        getWithModelPath(context, isChinese, GeeUINetworkConsts.CLOUD_FILE_TOKEN, callback)
    }

    /**
     * 获取天气信息
     *
     * @param context
     * @param callback
     */
    fun getWeatherInfo(context: Context?, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.WEATHER_INFO,callback);
        get(context, GeeUINetworkConsts.WEATHER_INFO, callback)
    }

    /**
     * 获取天气信息
     *
     * @param context
     * @param callback
     */
    fun getWeatherInfo(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.WEATHER_INFO, callback)
    }

    /**
     * 获取天气信息
     *
     * @param context
     * @param callback
     */
    fun getTomatoList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_TOMATO_LIST, callback)
    }

    /**
     * 获取闹钟列表
     *
     * @param context
     * @param callback
     */
    fun getClockList(context: Context?, callback: Callback?) {
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.CLOCK_LIST,callback);
        get(context, GeeUINetworkConsts.CLOCK_LIST, callback)
    }

    /**
     * 获取闹钟列表
     *
     * @param context
     * @param callback
     */
    fun getClockList(context: Context?, isChinese: Boolean, callback: Callback?) {
        getWithPageSize(context, isChinese, GeeUINetworkConsts.CLOCK_LIST, 50, callback)
    }

    /**
     * 获取股票信息
     *
     * @param context
     * @param callback
     */
    fun getStock(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.STOCK_INFO, callback)
    }

    /**
     * 获取设备绑定信息
     *
     * @param context
     * @param callback
     */
    fun isDeviceBind(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.IS_DEVICE_BIND, callback)
    }

    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    fun isDeviceBind1(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.IS_DEVICE_BIND, callback)
    }

    /**
     * 获取信息
     *
     * @param context
     * @param callback
     */
    fun getCountryByIp(context: Context?, isChinese: Boolean, callback: Callback?) {
//        get(context, isChinese, GeeUINetworkConsts.GET_REGION_BY_DEVICE_IP, callback);
        get(context, GeeUINetworkConsts.GET_REGION_BY_DEVICE_IP, callback)
    }

    /**
     * 获取信息
     *
     * @param context
     * @param callback
     */
    fun getCountryByIp(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.GET_REGION_BY_DEVICE_IP, callback)
    }


    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    fun getCustomList(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.CUSTOM_LIST, callback)
    }

    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    fun getCustomList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.CUSTOM_LIST, callback)
    }

    /**
     * 获取自定义列表
     *
     * @param context
     * @param callback
     */
    fun getCustomPhotoList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.CUSTOM_PHOTO_LIST, callback)
    }

    /**
     * 获取跑马灯信息
     *
     * @param context
     * @param callback
     */
    fun getLampCustomInfo(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.LAMP_CUSTOM_INFO, callback)
    }

    /**
     * 获取跑马灯信息
     *
     * @param context
     * @param callback
     */
    fun getLampCustomInfo(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.LAMP_CUSTOM_INFO, callback)
    }

    /**
     * 获取纪念日列表
     *
     * @param context
     * @param callback
     */
    fun getCommemorationList(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.COMMEMORATION_LIST, callback)
    }

    /**
     * 获取纪念日列表
     *
     * @param context
     * @param callback
     */
    fun getCommemorationList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.COMMEMORATION_LIST, callback)
    }

    /**
     * 获取新闻列表
     *
     * @param context
     * @param callback
     */
    fun getNewsList(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.NEWS_LIST, callback)
    }

    /**
     * 获取新闻列表
     *
     * @param context
     * @param callback
     */
    fun getNewsList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.NEWS_LIST, callback)
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
    fun getAllConfig(context: Context?, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_ALL_CONFIG, callback)
    }

    /**
     * 获取APP配置
     *
     * @param context
     * @param callback
     */
    fun getUserAppsConfig(context: Context?, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_USER_APPS_CONFIG, callback)
    }

    /**
     * 获取自动切换的APP
     *
     * @param context
     * @param callback
     */
    fun getAppsShowConfig(context: Context?, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_APPS_SHOW_CONFIG, callback)
    }

    /**
     * 获取全部配置
     *
     * @param context
     * @param callback
     */
    fun getAllConfig(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_ALL_CONFIG, callback)
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    fun getAllAppList(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.GET_ALL_APP_LIST, callback)
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    fun getAllAppList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_ALL_APP_LIST, callback)
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    fun getRobotDisplayAppList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_APP_LIST, callback)
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    fun getReChargeConfig(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_RECHARGE_CONFIG, callback)
    }

    /**
     * 获取全部appList
     *
     * @param context
     * @param callback
     */
    fun getAppBgInfo(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_APP_BG_INFO, callback)
    }

    /**
     * 获取最新安装包
     *
     * @param context
     * @param callback
     */
    fun getLatestPackage(context: Context?, appId: Int, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_APP_ID] = appId.toString() + ""
        get1(context, hashMap, GeeUINetworkConsts.GET_LATEST_PACKAGE, callback)
    }

    /**
     * 获取设备端应用列表
     *
     * @param context
     * @param callback
     */
    fun getAppList(context: Context?, isChinese: Boolean, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_APP_LIST, callback)
    }

    /**
     * 获取最新安装包
     *
     * @param context
     * @param callback
     */
    fun getLatestPackage(context: Context?, isChinese: Boolean, appId: Int, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_APP_ID] = appId.toString() + ""
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_LATEST_PACKAGE, callback)
    }

    /**
     * 获取绑定码
     *
     * @param context
     * @param callback
     */
    fun getBindCode(context: Context?, isChinese: Boolean, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_BIND_CODE, callback)
    }

    /**
     * 获取新闻列表
     *
     * @param context
     * @param callback
     */
    fun moduleChange(context: Context?, modeName: String, callback: Callback?) {
        val modules = arrayOf(modeName)
        //TODO 增加获取
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["selected_module_tag_list"] = modules
        post(context, GeeUINetworkConsts.POST_MODULE_CHANGE, hashMap, callback)
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    fun updateAppStatus(context: Context?, appInfo: AppInfo, sn: String?, callback: Callback?) {
        val appInfos = arrayOf(appInfo)
        //TODO 增加获取
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["appInfo"] = appInfos
        hashMap["sn"] = sn as Any
        post(context, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback)
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    fun updateAppStatus(
        context: Context?,
        isChinese: Boolean,
        appInfo: AppInfo,
        sn: String?,
        callback: Callback?
    ) {
        val appInfos = arrayOf(appInfo)
        //TODO 增加获取
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["appInfo"] = appInfos
        hashMap["sn"] = sn!!
        post(context, isChinese, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback)
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    fun updateUserAppStatus(
        context: Context?,
        isChinese: Boolean,
        appId: Int,
        appName: String?,
        appPackageName: String?,
        callback: Callback?
    ) {
        //TODO 增加获取
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["app_id"] = appId
        hashMap["app_name"] = appName!!
        hashMap["app_package_name"] = appPackageName!!
        //        hashMap.put("sn", sn);
        post(context, isChinese, GeeUINetworkConsts.POST_UPLOAD_USER_APP_STATUS, hashMap, callback)
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    fun updateAppStatus(
        context: Context?,
        appInfo: ArrayList<AppInfo?>,
        sn: String?,
        callback: Callback?
    ) {
        val appInfos = arrayOfNulls<AppInfo>(appInfo.size)
        for (i in appInfo.indices) {
            appInfos[i] = appInfo[i]
        }

        //TODO 增加获取
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["appInfo"] = appInfos
        hashMap["sn"] = sn!!
        post(context, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback)
    }

    /**
     * 更新App状态
     *
     * @param context
     * @param callback
     */
    fun updateAppStatus(
        context: Context?,
        isChinese: Boolean,
        appInfo: ArrayList<AppInfo?>,
        sn: String?,
        callback: Callback?
    ) {
        val appInfos = arrayOfNulls<AppInfo>(appInfo.size)
        for (i in appInfo.indices) {
            appInfos[i] = appInfo[i]
        }

        //TODO 增加获取
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["appInfo"] = appInfos
        hashMap["sn"] = sn!!
        post(context, isChinese, GeeUINetworkConsts.POST_UPLOAD_APP_STATUS, hashMap, callback)
    }

    /**
     * 重置机器人状态
     *
     * @param callback
     */
    fun robotReset(context: Context?, callback: Callback?) {
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["reset_status"] = 0
        post(context, GeeUINetworkConsts.POST_RESET_STATUS, hashMap, callback)
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    fun uploadStatus(context: Context?, hashMap: HashMap<String, Any>?, callback: Callback?) {
        post(context, GeeUINetworkConsts.UPLOAD_STATUS, hashMap, callback)
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    fun uploadStatus(
        context: Context?,
        isChinese: Boolean,
        hashMap: HashMap<String, Any>?,
        callback: Callback?
    ) {
        post(context, isChinese, GeeUINetworkConsts.UPLOAD_STATUS, hashMap, callback)
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    fun bindRobot(
        context: Context?,
        isChinese: Boolean,
        hashMap: HashMap<String, Any>?,
        callback: Callback?
    ) {
        post(context, isChinese, GeeUINetworkConsts.POST_MANAGE_ADD, hashMap, callback)
    }

    /**
     * 更新机器人状态
     *
     * @param context
     * @param callback
     */
    fun uploadBatteryStatus(
        context: Context?,
        isChinese: Boolean,
        hashMap: HashMap<String, Any>?,
        callback: Callback?
    ) {
        post(context, isChinese, GeeUINetworkConsts.UPLOAD_BATTERY_STATUS, hashMap, callback)
    }

    private fun uploadBatteryStatus(context: Context, chargingStatus: Int, callback: Callback) {
        val hashMap: HashMap<String, Any> = HashMap()
        val sn = SystemUtil.ltpSn
        hashMap["sn"] = sn
        hashMap["automatic_battery"] = chargingStatus

        uploadBatteryStatus(context, SystemUtil.isInChinese, hashMap, callback)
    }

    fun uploadExitAutoCharging(context: Context, callback: Callback) {
        uploadBatteryStatus(context, 0, callback)
    }

    fun uploadEnterAutoCharging(context: Context, callback: Callback) {
        uploadBatteryStatus(context, 1, callback)
    }


    /**
     * 上传lexlog
     *
     * @param context
     * @param callback
     */
    fun uploadLexLog(context: Context?, isChinese: Boolean, data: String?, callback: Callback?) {
        val sn = SystemUtil.ltpSn
        //TODO 增加获取
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["sn"] = sn
        hashMap["data"] = data!!
        post(context, isChinese, GeeUINetworkConsts.UPLOAD_LEX_LOG, hashMap, callback)
    }

    //    /**
    //     * 获取唤醒词列表
    //     * @param context
    //     * @param callback
    //     */
    //    public static void getTipsList(Context context, Callback callback){
    //
    //        HashMap<String,String> hashMap = new HashMap<>();
    //        String sn = SystemUtil.ltpSn;
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
    fun getTipsList(context: Context?, isChinese: Boolean, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = GeeUINetConsts.HASH_MAP_CONFIG_KEY_VALUE
        //        GeeUINetworkUtil.get(context,GeeUINetworkConsts.GET_COMMON_CONFIG,hashMap,callback);
//        GeeUINetworkUtil.get1(context, GeeUINetworkConsts.GET_COMMON_CONFIG,callback);
        get1(context, isChinese, ts, GeeUINetworkConsts.GET_COMMON_CONFIG, callback)
    }

    /**
     * 获取唤醒词列表
     *
     * @param context
     * @param callback
     */
    fun getCommonList(context: Context?, isChinese: Boolean, key: String?, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = key
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, callback)
    }

    /**
     * 获取表情文案列表
     *
     * @param context
     * @param callback
     */
    fun getExpressionList(context: Context?, isChinese: Boolean, callback: Callback?) {
        getCommonList(
            context,
            isChinese,
            GeeUINetConsts.HASH_MAP_CONFIG_EXPRESSION_CONTENT_VALUE,
            callback
        )
    }

    /**
     * 获取格言列表
     *
     * @param context
     * @param callback
     */
    fun getAphorismsList(context: Context?, isChinese: Boolean, callback: Callback?) {
        getCommonList(context, isChinese, GeeUINetConsts.HASH_MAP_CONFIG_APHORISMS_VALUE, callback)
    }


    /**
     * 获取机器人的上传状态配置
     *
     * @param context
     * @param callback
     */
    fun getUploadStatusConfig(context: Context?, isChinese: Boolean, callback: Callback?) {
        getCommonList(
            context,
            isChinese,
            GeeUINetConsts.HASH_MAP_ROBOT_UPLOAD_STATUS_CONFIG,
            callback
        )
    }


    /**
     * 获取Tips 列表
     *
     * @param context
     * @param callback
     */
    fun getAppTipsList(context: Context?, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = GeeUINetConsts.HASH_MAP_CONFIG_APP_TIPS_VALUE
        get1(context, ts, GeeUINetworkConsts.GET_COMMON_CONFIG, callback)
    }

    fun getMenuConfig(context: Context?, isChinese: Boolean, callback: Callback?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = GeeUINetConsts.HASH_MAP_CONFIG_ROBOT_MENU_LIST
        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, callback)
    }

    /**
     * 获取Tips 列表
     *
     * @param context
     */
    fun getAppTipsList(context: Context, appTitleTipUpdateListener: AppTitleTipUpdateListener?) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = GeeUINetConsts.HASH_MAP_CONFIG_APP_TIPS_VALUE

        get1(context, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.body != null) {
                    var info = ""
                    if (response?.body != null) {
                        info = response.body!!.string()
                    }

                    //                    Log.e("letianpai_tips", "getAppTipsList ======== info: " + info);
//                    Log.e("letianpai_tips", "getAppTipsList =========: " + info);

                    val appTips: AppTips?
                    try {
                        if (info != null) {
                            appTips = Gson().fromJson(info, AppTips::class.java)
                            if (appTips != null && appTips.data!!.config_data.isNotEmpty()) {
                                AppTitleTipCallback.instance
                                    .registerTimerKeeperUpdateListener(appTitleTipUpdateListener)
                                for (i in appTips.data!!.config_data.indices) {
                                    if (appTips.data!!.config_data[i].packageName != null && appTips.data!!.config_data[i].packageName == context.packageName) {
                                        AppTitleTipCallback.instance.setAppTitleTip(
                                            appTips.data!!.config_data[i].tip_content,
                                            appTips.data!!.config_data[i].tip_content_en,
                                            appTips.data!!.config_data[i].tip_icon
                                        )
                                    }
                                }
                                AppTitleTipCallback.instance
                                    .unregisterTimerKeeperUpdateListener(appTitleTipUpdateListener)
                            }
                        }
                    } catch (e: Exception) {
                    }
                }
            }
        })
    }

    /**
     * 获取Tips 列表
     *
     * @param context
     */
    fun getAppTipsList(
        context: Context,
        isChinese: Boolean,
        appTitleTipUpdateListener: AppTitleTipUpdateListener?
    ) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = GeeUINetConsts.HASH_MAP_CONFIG_APP_TIPS_VALUE

        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response?.body != null) {
                    var info = ""
                    if (response?.body != null) {
                        info = response.body!!.string()
                    }

                    //                    Log.e("letianpai_tips", "getAppTipsList ======== info: " + info);
//                    Log.e("letianpai_tips", "getAppTipsList =========: " + info);

                    val appTips: AppTips?
                    try {
                        if (info != null) {
                            appTips = Gson().fromJson(info, AppTips::class.java)
                            if (appTips != null && appTips.data!! != null && appTips.data!!.config_data != null && appTips.data!!.config_data.size > 0) {
                                AppTitleTipCallback.instance
                                    .registerTimerKeeperUpdateListener(appTitleTipUpdateListener)
                                for (i in appTips.data!!.config_data.indices) {
                                    if (appTips.data!!.config_data[i] != null && appTips.data!!.config_data[i].packageName != null && appTips.data!!.config_data[i].packageName == context.packageName) {
                                        AppTitleTipCallback.instance.setAppTitleTip(
                                            appTips.data!!.config_data[i].tip_content,
                                            appTips.data!!.config_data[i].tip_content_en,
                                            appTips.data!!.config_data[i].tip_icon
                                        )
                                    }
                                }
                                AppTitleTipCallback.instance
                                    .unregisterTimerKeeperUpdateListener(appTitleTipUpdateListener)
                            }
                        }
                    } catch (e: Exception) {
                    }
                }
            }
        })
    }

    /**
     * 获取Tips 列表
     *
     * @param context
     */
    fun getUploadDataConfig(
        context: Context,
        isChinese: Boolean,
        appUploadConfigUpdateListener: AppUploadConfigUpdateListener?
    ) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] =
            GeeUINetConsts.HASH_MAP_ROBOT_UPLOAD_STATUS_CONFIG

        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response?.body != null) {
                    var info = ""
                    if (response?.body != null) {
                        info = response.body!!.string()
                    }


                    val uploadDataConfig: UploadDataConfig?
                    if (info != null) {
                        try {
                            uploadDataConfig = Gson().fromJson(info, UploadDataConfig::class.java)
                            if (uploadDataConfig != null && uploadDataConfig.data!!.config_data.isNotEmpty()) {
                                AppUploadConfigCallback.instance
                                    .registerAppUploadConfigUpdateListener(
                                        appUploadConfigUpdateListener
                                    )
                                for (i in uploadDataConfig.data!!.config_data.indices) {
                                    if (uploadDataConfig.data!!.config_data[i].packageName != null && uploadDataConfig.data!!.config_data[i].packageName == context.packageName) {
                                        AppUploadConfigCallback.instance
                                            .setAppUploadConfig(
                                                uploadDataConfig.data!!.config_data[i].upload_frequency
                                            )
                                    }
                                }
                                AppUploadConfigCallback.instance
                                    .unregisterAppUploadConfigUpdateListener(
                                        appUploadConfigUpdateListener
                                    )
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        })
    }

    /**
     * 获取二维码信息
     *
     * @param context
     */
    fun getAppQrcodeInfo(
        context: Context,
        isChinese: Boolean,
        appQRCodeInfoUpdateListener: (String, Boolean) -> Int
    ) {
        val hashMap = HashMap<String?, String?>()
        val sn = SystemUtil.ltpSn
        val ts: String = EncryptionUtils.ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] =
            GeeUINetConsts.HASH_MAP_CONFIG_QRCODE_INFO_VALUE

        get1(context, isChinese, hashMap, GeeUINetworkConsts.GET_COMMON_CONFIG, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }


            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.body != null) {
                    var info = ""
                    if (response?.body != null) {
                        info = response.body!!.string()
                    }

                    //                    GeeUILogUtils.logi("letianpai_qrcode", "getAppQrcodeInfo ======== info: " + info);
                    val qrCodeTips: QRCodeTips?

                    try {
                        if (info != null) {
                            qrCodeTips = Gson().fromJson(info, QRCodeTips::class.java)
                            if (qrCodeTips?.data != null && qrCodeTips.data!!.config_data.isNotEmpty()) {
                                val updateListener = object : AppQRCodeInfoCallback.AppQRCodeInfoUpdateListener {
                                    override fun onAppQRCodeInfoUpdateReceived(
                                        qrcodeString: String,
                                        isShowQrcode: Boolean
                                    ) {
                                        appQRCodeInfoUpdateListener(qrcodeString, isShowQrcode)
                                    }
                                }
                                AppQRCodeInfoCallback.instance.registerAppQRCodeInfoUpdateListener(updateListener)

                                //                                GeeUILogUtils.logi("letianpai_qrcode", "qrCodeTips.getData().getConfig_data().length: " + qrCodeTips.getData().getConfig_data().length);
                                for (i in qrCodeTips.data!!.config_data.indices) {
//                                    GeeUILogUtils.logi("letianpai_qrcode", "getAppQrcodeInfo =========2: ");
                                    if (qrCodeTips.data!!.config_data[i].package_name != null && qrCodeTips.data!!.config_data[i].package_name == context.packageName) {
//                                        GeeUILogUtils.logi("letianpai_qrcode", "getAppQrcodeInfo =========3: ");
                                        AppQRCodeInfoCallback.instance.setQrCodeInfo(
                                            qrCodeTips.data!!.config_data[i].toString(),
                                            qrCodeTips.data!!.config_data[i].isShow_qrcode
                                        )
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
//                        GeeUILogUtils.logi(Log.getStackTraceString(e));
                        e.printStackTrace()
                    }
                }
            }
        })
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun get1(context: Context?, uri: String?, callback: Callback?) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        //        String hardCode = "YMcQMMZc49ZM0M";
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        GeeUINetworkUtil.get11(context, auth, sn, ts, uri!!, callback)
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun get(context: Context?, uri: String?, callback: Callback?) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }

        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        GeeUINetworkUtil.get11(context, auth, sn, ts, uri!!, callback)
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun get(context: Context?, isChinese: Boolean, uri: String?, callback: Callback?) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }

        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        GeeUINetworkUtil.get(context!!, isChinese, auth, sn, ts, uri!!, callback)
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun getWithPageSize(
        context: Context?,
        isChinese: Boolean,
        uri: String?,
        pageSize: Int,
        callback: Callback?
    ) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }

        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        GeeUINetworkUtil.getWithPage(
            context,
            isChinese,
            auth,
            sn,
            ts,
            uri!!,
            pageSize,
            callback
        )
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun getWithModelPath(context: Context?, isChinese: Boolean, uri: String?, callback: Callback?) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }

        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)
        val modelPath = "tmp"

        GeeUINetworkUtil.get(context, isChinese, auth, sn, ts, modelPath, uri!!, callback)
    }


    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun get1(context: Context?, ts: String?, uri: String?, callback: Callback?) {
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        //        String hardCode = "YMcQMMZc49ZM0M";
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        val hashMap: MutableMap<String?, String?> = HashMap()
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = GeeUINetConsts.HASH_MAP_CONFIG_KEY_VALUE

        GeeUINetworkUtil.get11(context, auth, hashMap, uri!!, callback)
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun get1(
        context: Context?,
        isChinese: Boolean,
        ts: String?,
        uri: String?,
        callback: Callback?
    ) {
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        //        String hardCode = "YMcQMMZc49ZM0M";
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        val hashMap: MutableMap<String?, String?> = HashMap()
        hashMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        hashMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn
        hashMap[GeeUINetConsts.HASH_MAP_KEY_CONFIG] = GeeUINetConsts.HASH_MAP_CONFIG_KEY_VALUE

        GeeUINetworkUtil.get11(context, isChinese, auth, hashMap, uri!!, callback)
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun get1(context: Context?, hashMap: Map<String?, String?>, uri: String?, callback: Callback?) {
        var sn: String? = null
        val ts = hashMap[GeeUINetConsts.HASH_MAP_KEY_TS]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        GeeUINetworkUtil.get11(context, auth, hashMap, uri!!, callback)
    }

    /**
     * @param context
     * @param uri
     * @param callback
     */
    fun get1(
        context: Context?,
        isChinese: Boolean,
        hashMap: Map<String?, String?>,
        uri: String,
        callback: Callback?
    ) {
        Log.d("-----", "url++$uri")
        var sn: String? = null
        val ts = hashMap[GeeUINetConsts.HASH_MAP_KEY_TS]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        GeeUINetworkUtil.get11(context, isChinese, auth, hashMap, uri!!, callback)
    }

    /**
     * 重置机器人状态
     *
     * @param callback
     */
    fun post1(context: Context?, uri: String?, bodyMap: HashMap<String, Any>?, callback: Callback?) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        //        String hardCode = "YMcQMMZc49ZM0M";
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        //        GeeUILogUtils.logi("letianpai_encode", "ts_1: " + ts);
//        GeeUILogUtils.logi("letianpai_encode", "sn_1: " + sn);
//        GeeUILogUtils.logi("letianpai_encode", "hardCode_2: " + hardCode);
//        GeeUILogUtils.logi("letianpai_encode", "auth_1: " + auth);
        val queryMap: MutableMap<String?, String?> = HashMap()
        queryMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        queryMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn

        GeeUINetworkUtil.post3(bodyMap!!, auth, queryMap, uri!!, callback)
    }

    /**
     * post
     *
     * @param callback
     */
    fun post(context: Context?, uri: String?, bodyMap: HashMap<String, Any>?, callback: Callback?) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        val queryMap: MutableMap<String?, String?> = HashMap()
        queryMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        queryMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn

        GeeUINetworkUtil.post3(bodyMap!!, auth, queryMap, uri!!, callback)
    }

    /**
     * post
     *
     * @param callback
     */
    fun post(
        context: Context?,
        isChinese: Boolean,
        uri: String?,
        bodyMap: HashMap<String, Any>?,
        callback: Callback?
    ) {
        val ts: String = EncryptionUtils.ts
        var sn: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial()
        }
        val hardCode = SystemUtil.hardCode
        val auth: String = EncryptionUtils.getRobotSign(sn!!, hardCode!!, ts)

        val queryMap: MutableMap<String?, String?> = HashMap()
        queryMap[GeeUINetConsts.HASH_MAP_KEY_TS] = ts
        queryMap[GeeUINetConsts.HASH_MAP_KEY_SN] = sn

        GeeUINetworkUtil.post3(bodyMap!!, isChinese, auth, queryMap, uri!!, callback)
    }


    /**
     * 获取提醒列表
     *
     * @param context
     * @param callback
     */
    fun getRemindList(context: Context?, callback: Callback?) {
        get(context, GeeUINetworkConsts.GET_USER_REMIND_LIST, callback)
    }

    /**
     * 获取提醒列表
     *
     * @param context
     * @param callback
     */
    fun getRemindList(context: Context?, isChinese: Boolean, callback: Callback?) {
        get(context, isChinese, GeeUINetworkConsts.GET_USER_REMIND_LIST, callback)
    } //    /**
    //     * 获取股票信息
    //     * @param context
    //     * @param callback
    //     */
    //    public static void getStock(Context context, Callback callback){
    //        String ts = EncryptionUtils.ts;
    //        String sn = null;
    //        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
    //            sn = Build.getSerial();
    //        }
    //        String hardCode = SystemUtil.hardCode;
    ////        String hardCode = "YMcQMMZc49ZM0M";
    //        String auth = EncryptionUtils.getRobotSign(sn,hardCode!!,ts);
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
    //        String ts = EncryptionUtils.ts;
    //        String sn = null;
    //        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
    //            sn = Build.getSerial();
    //        }
    //        String hardCode = SystemUtil.hardCode;
    ////        String hardCode = "YMcQMMZc49ZM0M";
    //        String auth = EncryptionUtils.getRobotSign(sn,hardCode!!,ts);
    //
    //        Log.e("letianpai_encode","ts_1: "+ts);
    //        Log.e("letianpai_encode","sn_1: "+sn);
    //        Log.e("letianpai_encode","hardCode_2: "+hardCode);
    //        Log.e("letianpai_encode","auth_1: "+auth);
    //        GeeUINetworkUtil.get11(context , auth, sn,ts, GeeUINetworkConsts.CUSTOM_LIST,callback);
    //    }
}
