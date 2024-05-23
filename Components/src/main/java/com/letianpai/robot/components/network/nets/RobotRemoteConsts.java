package com.letianpai.robot.components.network.nets;

public class RobotRemoteConsts {


    /**
     * OTA升级
     */
    public final static String COMMAND_TYPE_OTA = "otaUpgrade";

    /**
     * 更新wifi配置
     */
    public final static String COMMAND_TYPE_UPDATE_WIFI_CONFIG = "updateWifiConfig";

    /**
     * 更新ble配置
     */
    public final static String COMMAND_TYPE_UPDATE_BLE_CONFIG = "updateBleConfig";

    /**
     * 更新显示模式配置
     */
    public final static String COMMAND_TYPE_UPDATE_SHOW_MODE_CONFIG = "updateShowModeConfig";

    /**
     * 更新睡眠模式配置
     */
    public final static String COMMAND_TYPE_UPDATE_SLEEP_MODE_CONFIG = "updateSleepModeConfig";

    /**
     * 更新睡眠模式配置
     */
    public final static String COMMAND_TYPE_UPDATE_AWAKE_CONFIG = "updateAwakeConfig";

    /**
     * 更新通用配置
     */
    public final static String COMMAND_TYPE_UPDATE_GENERAL_CONFIG = "updateGeneralConfig";

    /**
     * 更新通用配置
     */
    public final static String COMMAND_UPDATE_DEVICE_FANS = "updateDeviceFans";

    /**
     * 更新日期配置
     */
    public final static String COMMAND_TYPE_UPDATE_DATE_CONFIG = "updateDateConfig";

    /**
     * 更新日历配置
     */
    public final static String COMMAND_TYPE_UPDATE_CALENDAR_CONFIG = "updateCalendarConfig";

    /**
     * 更新粉丝配置
     */
    public final static String COMMAND_TYPE_UPDATE_FANS_CONFIG = "updateFansConfig";

    /**
     * 更新倒计时配置
     */
    public final static String COMMAND_TYPE_UPDATE_COUNT_DOWN_CONFIG = "updateCountDownConfig";

    /**
     * 更新倒计时配置
     */
    public final static String COMMAND_TYPE_UPDATE_COUNT_DOWN_CONFIG_DATA = "updateEventData";

    /**
     * 更新自定义屏幕内容
     */
    public final static String COMMAND_TYPE_UPDATE_CUSTOM_CONTENT_DATA = "updateCustomContentData";

    /**
     * 更新电子相册
     */
    public final static String COMMAND_TYPE_UPDATE_CUSTOM_PHOTO_DATA = "updateCustomPhotoData";

    /**
     * 更新跑马灯数据
     */
    public final static String COMMAND_TYPE_UPDATE_LAMP_CONTENT_DATA = "updateLampContentData";

    /**
     * 更新显示模式
     */
    public final static String COMMAND_TYPE_APP_DISPLAY_SWITCH_CONFIG = "updateDisplaySwitchConfig";

    /**
     * 更新显示模式 --天气页面
     */
    public final static String COMMAND_TYPE_UPDATE_WEATHER_CONFIG = "updateWeatherConfig";

    /**
     * 闹钟信息
     */
    public final static String COMMAND_TYPE_UPDATE_CLOCK_DATA = "updateClockData";
    /**
     * 悬崖开始
     */
    public final static String COMMAND_TYPE_CONTROL_PRECIPICE_START_DATA = "controlStartPrecipice";
    /**
     * 悬崖结束
     */
    public final static String COMMAND_TYPE_CONTROL_PRECIPICE_STOP_DATA = "controlStopPrecipice";
    /**
     * 防跌落，往后退
     */
    public final static String COMMAND_TYPE_CONTROL_FALL_BACKEND = "fallBackend";
    /**
     * 防跌落，往前走
     */
    public final static String COMMAND_TYPE_CONTROL_FALL_FORWARD = "fallForward";

    /**
     * 选择图片
     */
    public final static String COMMAND_TYPE_CONTROL_SEND_PIC = "controlSendPic";

    /**
     * 文字
     */
    public final static String COMMAND_TYPE_CONTROL_SEND_WORD = "controlSendWord";

    /**
     * 重置机器
     */
    public final static String COMMAND_TYPE_RESET_DEVICE_INFO = "resetDeviceInfo";

    public static String ROBOT_UPDATE_VERSION_IS_READY = "latest";

    /**
     * 机器人模式状态切换
     */
    public final static String COMMAND_TYPE_CHANGE_MODE = "changeMode";

    public final static String COMMAND_VALUE_CHANGE_MODE_TRANSFORM = "transform";
    public final static String COMMAND_VALUE_CHANGE_MODE_SHOW = "show";
    public final static String COMMAND_VALUE_CHANGE_MODE_SLEEP = "sleep";
    public final static String COMMAND_VALUE_CHANGE_MODE_AUTO = "auto";
    public final static String COMMAND_VALUE_CHANGE_MODE_DEMO = "demo";
    public final static String COMMAND_VALUE_CHANGE_MODE_RESET = "reset"; // 恢复
    public final static String COMMAND_VALUE_CHANGE_MODE_STATIC = "static"; // 静止模式
    public final static String COMMAND_VALUE_CHANGE_MODE_ROBOT = "robot"; // 机器人模式

    /**
     * 机器人模式状态切换
     */
    public final static String COMMAND_TYPE_ADD_FACE_FEATURE = "addFaceFeature";

    /**
     * 控制音量
     */
    public final static String COMMAND_TYPE_CONTROL_SOUND_VOLUME = "controlSoundVolume";

    /**
     * 演示模式显示切换
     */
    public final static String COMMAND_TYPE_CONTROL_DISPLAY_MODE = "controlDisplayMode";

    public final static String COMMAND_VALUE_CONTROL_DISPLAY_TIME = "time";
    public final static String COMMAND_VALUE_CONTROL_DISPLAY_WEATHER = "weather";
    public final static String COMMAND_VALUE_CONTROL_DISPLAY_COUNTDOWN = "countdown";
    public final static String COMMAND_VALUE_CONTROL_DISPLAY_FANS = "fans";
    public final static String COMMAND_VALUE_CONTROL_DISPLAY_SCHEDULE = "schedule";
    public final static String COMMAND_VALUE_CONTROL_DISPLAY_EMPTY = "empty";
    public final static String COMMAND_VALUE_CONTROL_DISPLAY_BLACK = "darkScreen";
    public final static String COMMAND_VALUE_CONTROL_DISPLAY_EXT_BLACK = "exitDarkScreen";

    public final static String LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_TIME = "display/time";
    public final static String LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_WEATHER = "display/weather";
    public final static String LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_COUNTDOWN = "display/countdown";
    public final static String LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_FANS = "display/fans";
    public final static String LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_SCHEDULE = "display/notice";
    public final static String LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_EMPTY = "display/empty";
    public final static String LOCAL_COMMAND_VALUE_IDENT_FACE_RESULT = "identFaceResult";

    /**
     * 演示模式自动动作
     */
    public final static String COMMAND_TYPE_CONTROL_AUTO_MODE = "controlAutoMode";
    public final static String COMMAND_VALUE_CONTROL_AUTO_MODE_FOLLOW = "follow";
    public final static String COMMAND_VALUE_CONTROL_AUTO_MODE_EXT_FOLLOW = "exitFollow";
    public final static String COMMAND_VALUE_CONTROL_AUTO_MODE_RANDOM = "random";

    /**
     * 应用中心安装更新
     */
    public static final String COMMAND_TYPE_UPDATE_DEVICE_APP_MODE = "updateDeviceAppMode";
    public static final String COMMAND_TYPE_UNINSTALL_DEVICE_APP = "uninstall";

    /**
     * 更新提醒，喝水，坐姿
     */
    public static final String COMMAND_TYPE_UPDATE_REMIND_INFO_DATA = "updateRemindInfoData";

}
