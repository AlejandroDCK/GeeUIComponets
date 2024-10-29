/**
 *
 */
package com.letianpai.robot.components.storage

import com.letianpai.robot.components.locale.LocaleUtils.isChinese
import com.letianpai.robot.components.charging.ChargingUpdateCallback.Companion.instance
import com.letianpai.robot.components.network.encryption.EncryptionUtils
import com.letianpai.robot.components.network.nets.GeeUINetworkUtil
import com.letianpai.robot.components.network.nets.GeeUINetworkConsts
import com.letianpai.robot.components.network.nets.GeeUiNetManager
import com.letianpai.robot.components.network.system.SystemUtil
import com.letianpai.robot.components.network.nets.GeeUINetConsts
import com.letianpai.robot.components.network.callback.AppTitleTipCallback.AppTitleTipUpdateListener
import kotlin.Throws
import com.letianpai.robot.components.network.callback.apptips.AppTips
import com.google.gson.Gson
import com.letianpai.robot.components.network.callback.AppTitleTipCallback
import com.letianpai.robot.components.network.callback.AppUploadConfigCallback.AppUploadConfigUpdateListener
import com.letianpai.robot.components.parser.uploadconfig.UploadDataConfig
import com.letianpai.robot.components.network.callback.AppUploadConfigCallback
import com.letianpai.robot.components.network.callback.AppQRCodeInfoCallback.AppQRCodeInfoUpdateListener
import com.letianpai.robot.components.network.callback.qrcode.QRCodeTips
import com.letianpai.robot.components.network.callback.AppQRCodeInfoCallback
import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.HttpUrl
import okhttp3.RequestBody
import okhttp3.MultipartBody
import com.letianpai.robot.components.charging.ChargingUpdateCallback
import com.letianpai.robot.components.charging.ChargingUpdateCallback.ChargingUpdateListener
import com.letianpai.robot.components.network.nets.GeeUIStatusUploader
import com.letianpai.robot.components.charging.BatteryReceiver
import android.content.IntentFilter
import android.content.Intent
import com.letianpai.robot.components.utils.VolumeManager
import com.letianpai.robot.components.utils.SDCardUtil
import com.letianpai.robot.components.storage.RobotSubConfigManager
import com.letianpai.robot.components.parser.base.BaseMessageInfo
import android.net.wifi.WifiManager
import android.net.wifi.WifiInfo
import android.bluetooth.BluetoothAdapter
import com.letianpai.robot.components.network.nets.WIFIConnectionManager
import android.text.TextUtils
import android.net.wifi.WifiConfiguration
import android.net.wifi.SupplicantState
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.NetworkInfo.DetailedState
import com.letianpai.robot.components.parser.logo.LogoInfo
import com.letianpai.robot.components.network.nets.RobotRemoteConsts
import com.letianpai.robot.components.network.lexnet.callback.DeviceChannelLogoCallBack
import com.letianpai.robot.components.network.nets.GeeUINetResponseManager
import com.letianpai.robot.components.network.lexnet.parser.SessionTokenData
import com.letianpai.robot.components.network.lexnet.callback.DeviceChannelLogoCallBack.DeviceChannelLogoUpdateListener
import com.letianpai.robot.components.network.lexnet.callback.SessionTokenUpdateCallback.SessionTokenUpdateListener
import com.letianpai.robot.components.network.lexnet.callback.SessionTokenUpdateCallback
import com.letianpai.robot.components.network.lexnet.parser.LexSessionToken
import com.letianpai.robot.components.network.lexnet.LexNetworkManager
import android.util.DisplayMetrics
import com.letianpai.robot.components.network.callback.qrcode.QRCodeInfo
import com.letianpai.robot.components.network.callback.qrcode.QRCodeData
import com.letianpai.robot.components.network.callback.apptips.AppTipsInfo
import com.letianpai.robot.components.network.callback.apptips.AppData
import com.letianpai.robot.components.network.encryption.Sha256Utils
import android.widget.LinearLayout
import android.widget.TextView
import com.letianpai.robot.components.R
import com.letianpai.robot.components.nodata.NoDataView
import android.widget.RelativeLayout
import com.letianpai.robot.components.nodata.NoDataQRView
import com.letianpai.robot.components.utils.QRCodeUtil
import com.letianpai.robot.components.nodata.NoDataQRImageView
import com.letianpai.robot.components.parser.logo.LogoData
import com.letianpai.robot.components.parser.tips.TipsData
import com.letianpai.robot.components.parser.tips.ConfigData
import com.letianpai.robot.components.parser.tips.TipsName
import com.letianpai.robot.components.parser.remind.ReminderData
import com.letianpai.robot.components.parser.imagebg.ImageBgData
import com.letianpai.robot.components.parser.recharge.ReChargeData
import com.letianpai.robot.components.parser.filetoken.PhotoInfoData
import com.letianpai.robot.components.parser.filetoken.CloudFileTokenData
import com.letianpai.robot.components.parser.uploadconfig.UploadDataInfo
import com.letianpai.robot.components.parser.uploadconfig.UploadData
import android.content.SharedPreferences
import com.letianpai.robot.components.storage.base.RobotSharedPreference
import com.letianpai.robot.components.storage.RobotSubConfigConst
import com.letianpai.robot.components.storage.RobotSubSharedPreference
import com.google.gson.reflect.TypeToken
import android.annotation.SuppressLint
import android.app.Activity
import android.view.ViewGroup
import android.view.WindowManager
import android.view.Gravity
import com.google.zxing.WriterException
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.BarcodeFormat
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.StatFs
import android.content.res.AssetManager
import com.elvishew.xlog.XLog
import com.letianpai.robot.components.utils.GeeUILogUtils
import com.elvishew.xlog.LogConfiguration
import com.letianpai.robot.components.baselog.MyBorderFormatter
import com.elvishew.xlog.printer.AndroidPrinter
import com.elvishew.xlog.printer.file.FilePrinter
import com.letianpai.robot.components.baselog.MyFileNameGenerator
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy2
import com.elvishew.xlog.printer.file.backup.BackupStrategy2
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy
import com.letianpai.robot.components.baselog.MyFlattener
import android.os.CountDownTimer
import android.media.AudioManager
import com.letianpai.robot.components.parser.imagebg.ImageBgInfo
import com.letianpai.robot.components.view.ImageBgView
import com.squareup.picasso.Picasso
import com.letianpai.robot.components.view.ImageBgsView
import android.graphics.BitmapShader
import android.graphics.Shader
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import android.os.Looper
import com.letianpai.robot.components.parser.tips.Tips
import com.letianpai.robot.components.view.BottomStatusBar
import kotlin.jvm.JvmOverloads

/**
 *
 * @author liujunbin
 */
interface RobotSubConfigConst {
    companion object {
        const val KEY_UPDATE_ROBOT_TIME: String = "update_robot_time"
        const val KEY_UPLOAD_FREQUENCY: String = "upload_frequency"
        const val KEY_UPLOAD_FREQUENCY_INTERNAL_TIME: String = "upload_frequency_internal_time"
        const val KEY_UPLOAD_DATA_TIME: String = "upload_data_time"
        const val KEY_SPEECH_COMMAND_SWITCH: String = "speech_command_switch"
        const val KEY_SAVE_OPEN_TIME: String = "open_main_view_time"
        const val KEY_SAVE_PACKAGE_LIST: String = "save_package_list"
        const val KEY_SAVE_APP_LIST: String = "save_app_list"
        const val VALUE_DEFAULT_APP_LIST: String = "{\n" +
                "\t\"code\": 0,\n" +
                "\t\"data\": [{\n" +
                "\t\t\"id\": 1527,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"宠物模式\",\n" +
                "\t\t\"app_name_en\": \"Pet Mode\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_pet.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.geeui.face\",\n" +
                "\t\t\"app_sign\": \"pet_mode\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202402161\",\n" +
                "\t\t\"upgrade_desc\": \"找人增加延迟,防止崩溃\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1594,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"我的音乐\",\n" +
                "\t\t\"app_name_en\": \"My Music\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_my_music.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.geeui.music\",\n" +
                "\t\t\"app_sign\": \"\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.2403262\",\n" +
                "\t\t\"upgrade_desc\": \"列表空提示\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1593,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"下载器\",\n" +
                "\t\t\"app_name_en\": \"Downloader\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/logo_downloader.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.downloader\",\n" +
                "\t\t\"app_sign\": \"downloader\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.0.24040101\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1592,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"控制\",\n" +
                "\t\t\"app_name_en\": \"Control\",\n" +
                "\t\t\"app_icon\": \"\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.desktop\",\n" +
                "\t\t\"app_sign\": \"menu\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.0.24032901\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 1\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1590,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"GeeUIWifiConnector\",\n" +
                "\t\t\"app_name_en\": \"GeeUIWifiConnector\",\n" +
                "\t\t\"app_icon\": \"\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.wificonnet\",\n" +
                "\t\t\"app_sign\": \"wificonnector\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.0.24022603\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 1\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1589,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"设置\",\n" +
                "\t\t\"app_name_en\": \"Setting\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_geeui_settings.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.robot.geeui.setting\",\n" +
                "\t\t\"app_sign\": \"setting\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202404011\",\n" +
                "\t\t\"upgrade_desc\": \"海外增加泰语切换功能\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1588,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"GeeUI Task\",\n" +
                "\t\t\"app_name_en\": \"GeeUI Task\",\n" +
                "\t\t\"app_icon\": \"\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.taskservice\",\n" +
                "\t\t\"app_sign\": \"taskservice\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24031203\",\n" +
                "\t\t\"upgrade_desc\": \"增加头部点击启动控制功能\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 1\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1587,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"AI 小游戏\",\n" +
                "\t\t\"app_name_en\": \"AI Game\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_ai_game.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.geeui.fistpalmgame\",\n" +
                "\t\t\"app_sign\": \"com.geeui.fistpalmgame\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202402081\",\n" +
                "\t\t\"upgrade_desc\": \"替换h0115\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1586,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"应用商店\",\n" +
                "\t\t\"app_name_en\": \"\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_app_store.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.appstore\",\n" +
                "\t\t\"app_sign\": \"appstore\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24033105\",\n" +
                "\t\t\"upgrade_desc\": \"修复应用商店加载异常的问题，版本显示不正确的问题\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 1\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1582,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"录音机\",\n" +
                "\t\t\"app_name_en\": \"Recorder\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_voice_recorder.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.geeui.voicememo\",\n" +
                "\t\t\"app_sign\": \"com.geeui.voicememo\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202311191\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1581,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"冥想\",\n" +
                "\t\t\"app_name_en\": \"Meditation\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_meditation.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.meditation\",\n" +
                "\t\t\"app_sign\": \"meditation\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24013001\",\n" +
                "\t\t\"upgrade_desc\": \"回滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1580,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"电子相册\",\n" +
                "\t\t\"app_name_en\": \"Album\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_album.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.album\",\n" +
                "\t\t\"app_sign\": \"album\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24013001\",\n" +
                "\t\t\"upgrade_desc\": \"会滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1579,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"视频播放\",\n" +
                "\t\t\"app_name_en\": \"Video player\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_video_player.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"视频播放器\",\n" +
                "\t\t\"app_package_name\": \"com.geeui.videoplayer\",\n" +
                "\t\t\"app_sign\": \"videoplayer\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202312191\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1577,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"番茄钟\",\n" +
                "\t\t\"app_name_en\": \"Pomodoro\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_pomodoro.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.pomodoro\",\n" +
                "\t\t\"app_sign\": \"pomodoro\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24013001\",\n" +
                "\t\t\"upgrade_desc\": \"回滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1576,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"Voice Assistant\",\n" +
                "\t\t\"app_name_en\": \"Voice Assistant\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/app-1dc18392d359149f/一键唤醒.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"海外lex\",\n" +
                "\t\t\"app_package_name\": \"com.geeui.lex\",\n" +
                "\t\t\"app_sign\": \"lex\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202404011\",\n" +
                "\t\t\"upgrade_desc\": \"增加泰语音色\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1567,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"MCU服务\",\n" +
                "\t\t\"app_name_en\": \"MCU Service\",\n" +
                "\t\t\"app_icon\": \"\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.mcuservice\",\n" +
                "\t\t\"app_sign\": \"mcuservice\",\n" +
                "\t\t\"sort_val\": 0,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.23092001\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 1\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1575,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"表情展示\",\n" +
                "\t\t\"app_name_en\": \"Face\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_expression.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.expression\",\n" +
                "\t\t\"app_sign\": \"face\",\n" +
                "\t\t\"sort_val\": 1,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.0.24030601\",\n" +
                "\t\t\"upgrade_desc\": \"去掉表情展示中的文字展示\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1583,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"闹钟与倒计时\",\n" +
                "\t\t\"app_name_en\": \"Alarm and Countdown\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_alarm.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.alarmnotice\",\n" +
                "\t\t\"app_sign\": \"alarm\",\n" +
                "\t\t\"sort_val\": 2,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.232403061\",\n" +
                "\t\t\"upgrade_desc\": \"修复关闭闹钟不正常的问题\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1525,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"天气\",\n" +
                "\t\t\"app_name_en\": \"Weather\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_weather.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.geeuiweather\",\n" +
                "\t\t\"app_sign\": \"weather\",\n" +
                "\t\t\"sort_val\": 2,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202402025\",\n" +
                "\t\t\"upgrade_desc\": \"增加华氏，摄氏温度展示\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1529,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"时间\",\n" +
                "\t\t\"app_name_en\": \"Time\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_time.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.time\",\n" +
                "\t\t\"app_sign\": \"time\",\n" +
                "\t\t\"sort_val\": 2,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24020218\",\n" +
                "\t\t\"upgrade_desc\": \"修复第二套表盘显示错误的bug\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1561,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"视频与遥控\",\n" +
                "\t\t\"app_name_en\": \"Video and Control\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_remote_view.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.rhj.aduioandvideo\",\n" +
                "\t\t\"app_sign\": \"video\",\n" +
                "\t\t\"sort_val\": 3,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202312011\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1531,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"纪念日\",\n" +
                "\t\t\"app_name_en\": \"Anniversary\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_commemoration.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.rhj.commemoration\",\n" +
                "\t\t\"app_sign\": \"commemoration_day\",\n" +
                "\t\t\"sort_val\": 3,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.2401301\",\n" +
                "\t\t\"upgrade_desc\": \"回滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1533,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"文字跑马灯\",\n" +
                "\t\t\"app_name_en\": \"Marquee\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_race_text.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.racelight\",\n" +
                "\t\t\"app_sign\": \"content_lamp\",\n" +
                "\t\t\"sort_val\": 4,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202401301\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1570,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"健康辅助提醒\",\n" +
                "\t\t\"app_name_en\": \"Health Reminder\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_health_notification.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.reminder\",\n" +
                "\t\t\"app_sign\": \"reminder\",\n" +
                "\t\t\"sort_val\": 5,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.230920025\",\n" +
                "\t\t\"upgrade_desc\": \"\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1535,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"一句话热点\",\n" +
                "\t\t\"app_name_en\": \"News\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_news.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.news\",\n" +
                "\t\t\"app_sign\": \"hot_news\",\n" +
                "\t\t\"sort_val\": 5,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202401301\",\n" +
                "\t\t\"upgrade_desc\": \"会滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1530,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"待办事项\",\n" +
                "\t\t\"app_name_en\": \"Todo\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_todo.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.eventcountdown\",\n" +
                "\t\t\"app_sign\": \"todo\",\n" +
                "\t\t\"sort_val\": 5,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24013001\",\n" +
                "\t\t\"upgrade_desc\": \"会滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1528,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"自定义屏幕\",\n" +
                "\t\t\"app_name_en\": \"Display\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_custom.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.geeuicustom\",\n" +
                "\t\t\"app_sign\": \"Display\",\n" +
                "\t\t\"sort_val\": 5,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24013001\",\n" +
                "\t\t\"upgrade_desc\": \"回滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1564,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"猜拳\",\n" +
                "\t\t\"app_name_en\": \"Finger Guessing\",\n" +
                "\t\t\"app_icon\": \"\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.ltp.ident\",\n" +
                "\t\t\"app_sign\": \"ident\",\n" +
                "\t\t\"sort_val\": 6,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202402201\",\n" +
                "\t\t\"upgrade_desc\": \"优化崩溃问题\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 1,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 1\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1538,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"消息通知、手机来电提醒\",\n" +
                "\t\t\"app_name_en\": \"Notification\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_message.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 1,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.message\",\n" +
                "\t\t\"app_sign\": \"notify\",\n" +
                "\t\t\"sort_val\": 6,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202401301\",\n" +
                "\t\t\"upgrade_desc\": \"回滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1565,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"能量站的辅助自动回充Beta\",\n" +
                "\t\t\"app_name_en\": \"Rux Home's Assisted Automatic Recharging Beta\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_charging_home.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"top.keepempty\",\n" +
                "\t\t\"app_sign\": \"autocharging\",\n" +
                "\t\t\"sort_val\": 6,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.202402222\",\n" +
                "\t\t\"upgrade_desc\": \"1、优化桩两侧上桩逻辑，2、去掉只有设备激活才会出发悬崖,由红外对桩开始触发发悬崖不停止，改为只有后退才触发悬崖不停止，3、优化找不到桩时，转身大于10次则开始前后左右行动找桩\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1574,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"频谱灯\",\n" +
                "\t\t\"app_name_en\": \"Wave\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_spectrum.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.spectrum\",\n" +
                "\t\t\"app_sign\": \"Wave\",\n" +
                "\t\t\"sort_val\": 6,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24022701\",\n" +
                "\t\t\"upgrade_desc\": \"增加海外版本兼容展示\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1536,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"一分钟行情\",\n" +
                "\t\t\"app_name_en\": \"Stock\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_stock.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"一分钟行情介绍\\n很好用的\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.geeuistock\",\n" +
                "\t\t\"app_sign\": \"stock\",\n" +
                "\t\t\"sort_val\": 7,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24013001\",\n" +
                "\t\t\"upgrade_desc\": \"勿动，测试用版本\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}, {\n" +
                "\t\t\"id\": 1534,\n" +
                "\t\t\"user_id\": 0,\n" +
                "\t\t\"app_name\": \"粉丝显示器\",\n" +
                "\t\t\"app_name_en\": \"Fans\",\n" +
                "\t\t\"app_icon\": \"https://d4owc89rbj03p.cloudfront.net/appStore/icon/icon_m_fans.png\",\n" +
                "\t\t\"app_type\": 0,\n" +
                "\t\t\"is_system\": 0,\n" +
                "\t\t\"notes\": \"\",\n" +
                "\t\t\"app_package_name\": \"com.letianpai.robot.fans\",\n" +
                "\t\t\"app_sign\": \"fans_show\",\n" +
                "\t\t\"sort_val\": 7,\n" +
                "\t\t\"app_version\": \"\",\n" +
                "\t\t\"upgrade_version\": \"1.1.24013001\",\n" +
                "\t\t\"upgrade_desc\": \"会滚代码，去掉回到后台杀死app逻辑\",\n" +
                "\t\t\"open_type\": 0,\n" +
                "\t\t\"is_restart\": 0,\n" +
                "\t\t\"is_show_battery\": 0,\n" +
                "\t\t\"is_show_text\": 0,\n" +
                "\t\t\"is_show_report\": 0,\n" +
                "\t\t\"is_auto_install\": 0,\n" +
                "\t\t\"open_content\": \"\",\n" +
                "\t\t\"status\": 0,\n" +
                "\t\t\"is_hide\": 0\n" +
                "\t}],\n" +
                "\t\"msg\": \"success\"\n" +
                "}"
    }
}


