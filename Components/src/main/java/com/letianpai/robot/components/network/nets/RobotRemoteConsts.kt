package com.letianpai.robot.components.network.nets

object RobotRemoteConsts {
    /**
     * OTA升级
     */
    const val COMMAND_TYPE_OTA: String = "otaUpgrade"

    /**
     * 更新wifi配置
     */
    const val COMMAND_TYPE_UPDATE_WIFI_CONFIG: String = "updateWifiConfig"

    /**
     * 更新ble配置
     */
    const val COMMAND_TYPE_UPDATE_BLE_CONFIG: String = "updateBleConfig"

    /**
     * 更新显示模式配置
     */
    const val COMMAND_TYPE_UPDATE_SHOW_MODE_CONFIG: String = "updateShowModeConfig"

    /**
     * 更新睡眠模式配置
     */
    const val COMMAND_TYPE_UPDATE_SLEEP_MODE_CONFIG: String = "updateSleepModeConfig"

    /**
     * 更新睡眠模式配置
     */
    const val COMMAND_TYPE_UPDATE_AWAKE_CONFIG: String = "updateAwakeConfig"

    /**
     * 更新通用配置
     */
    const val COMMAND_TYPE_UPDATE_GENERAL_CONFIG: String = "updateGeneralConfig"

    /**
     * 更新通用配置
     */
    const val COMMAND_UPDATE_DEVICE_FANS: String = "updateDeviceFans"

    /**
     * 更新日期配置
     */
    const val COMMAND_TYPE_UPDATE_DATE_CONFIG: String = "updateDateConfig"

    /**
     * 更新日历配置
     */
    const val COMMAND_TYPE_UPDATE_CALENDAR_CONFIG: String = "updateCalendarConfig"

    /**
     * 更新粉丝配置
     */
    const val COMMAND_TYPE_UPDATE_FANS_CONFIG: String = "updateFansConfig"

    /**
     * 更新倒计时配置
     */
    const val COMMAND_TYPE_UPDATE_COUNT_DOWN_CONFIG: String = "updateCountDownConfig"

    /**
     * 更新倒计时配置
     */
    const val COMMAND_TYPE_UPDATE_COUNT_DOWN_CONFIG_DATA: String = "updateEventData"

    /**
     * 更新自定义屏幕内容
     */
    const val COMMAND_TYPE_UPDATE_CUSTOM_CONTENT_DATA: String = "updateCustomContentData"

    /**
     * 更新电子相册
     */
    const val COMMAND_TYPE_UPDATE_CUSTOM_PHOTO_DATA: String = "updateCustomPhotoData"

    /**
     * 更新跑马灯数据
     */
    const val COMMAND_TYPE_UPDATE_LAMP_CONTENT_DATA: String = "updateLampContentData"

    /**
     * 更新显示模式
     */
    const val COMMAND_TYPE_APP_DISPLAY_SWITCH_CONFIG: String = "updateDisplaySwitchConfig"

    /**
     * 更新显示模式 --天气页面
     */
    const val COMMAND_TYPE_UPDATE_WEATHER_CONFIG: String = "updateWeatherConfig"

    /**
     * 闹钟信息
     */
    const val COMMAND_TYPE_UPDATE_CLOCK_DATA: String = "updateClockData"

    /**
     * 悬崖开始
     */
    const val COMMAND_TYPE_CONTROL_PRECIPICE_START_DATA: String = "controlStartPrecipice"

    /**
     * 悬崖结束
     */
    const val COMMAND_TYPE_CONTROL_PRECIPICE_STOP_DATA: String = "controlStopPrecipice"

    /**
     * 防跌落，往后退
     */
    const val COMMAND_TYPE_CONTROL_FALL_BACKEND: String = "fallBackend"

    /**
     * 防跌落，往前走
     */
    const val COMMAND_TYPE_CONTROL_FALL_FORWARD: String = "fallForward"

    /**
     * 选择图片
     */
    const val COMMAND_TYPE_CONTROL_SEND_PIC: String = "controlSendPic"

    /**
     * 文字
     */
    const val COMMAND_TYPE_CONTROL_SEND_WORD: String = "controlSendWord"

    /**
     * 重置机器
     */
    const val COMMAND_TYPE_RESET_DEVICE_INFO: String = "resetDeviceInfo"

    var ROBOT_UPDATE_VERSION_IS_READY: String = "latest"

    /**
     * 机器人模式状态切换
     */
    const val COMMAND_TYPE_CHANGE_MODE: String = "changeMode"

    const val COMMAND_VALUE_CHANGE_MODE_TRANSFORM: String = "transform"
    const val COMMAND_VALUE_CHANGE_MODE_SHOW: String = "show"
    const val COMMAND_VALUE_CHANGE_MODE_SLEEP: String = "sleep"
    const val COMMAND_VALUE_CHANGE_MODE_AUTO: String = "auto"
    const val COMMAND_VALUE_CHANGE_MODE_DEMO: String = "demo"
    const val COMMAND_VALUE_CHANGE_MODE_RESET: String = "reset" // 恢复
    const val COMMAND_VALUE_CHANGE_MODE_STATIC: String = "static" // 静止模式
    const val COMMAND_VALUE_CHANGE_MODE_ROBOT: String = "robot" // 机器人模式

    /**
     * 机器人模式状态切换
     */
    const val COMMAND_TYPE_ADD_FACE_FEATURE: String = "addFaceFeature"

    /**
     * 控制音量
     */
    const val COMMAND_TYPE_CONTROL_SOUND_VOLUME: String = "controlSoundVolume"

    /**
     * 演示模式显示切换
     */
    const val COMMAND_TYPE_CONTROL_DISPLAY_MODE: String = "controlDisplayMode"

    const val COMMAND_VALUE_CONTROL_DISPLAY_TIME: String = "time"
    const val COMMAND_VALUE_CONTROL_DISPLAY_WEATHER: String = "weather"
    const val COMMAND_VALUE_CONTROL_DISPLAY_COUNTDOWN: String = "countdown"
    const val COMMAND_VALUE_CONTROL_DISPLAY_FANS: String = "fans"
    const val COMMAND_VALUE_CONTROL_DISPLAY_SCHEDULE: String = "schedule"
    const val COMMAND_VALUE_CONTROL_DISPLAY_EMPTY: String = "empty"
    const val COMMAND_VALUE_CONTROL_DISPLAY_BLACK: String = "darkScreen"
    const val COMMAND_VALUE_CONTROL_DISPLAY_EXT_BLACK: String = "exitDarkScreen"

    const val LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_TIME: String = "display/time"
    const val LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_WEATHER: String = "display/weather"
    const val LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_COUNTDOWN: String = "display/countdown"
    const val LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_FANS: String = "display/fans"
    const val LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_SCHEDULE: String = "display/notice"
    const val LOCAL_COMMAND_VALUE_CONTROL_DISPLAY_EMPTY: String = "display/empty"
    const val LOCAL_COMMAND_VALUE_IDENT_FACE_RESULT: String = "identFaceResult"

    /**
     * 演示模式自动动作
     */
    const val COMMAND_TYPE_CONTROL_AUTO_MODE: String = "controlAutoMode"
    const val COMMAND_VALUE_CONTROL_AUTO_MODE_FOLLOW: String = "follow"
    const val COMMAND_VALUE_CONTROL_AUTO_MODE_EXT_FOLLOW: String = "exitFollow"
    const val COMMAND_VALUE_CONTROL_AUTO_MODE_RANDOM: String = "random"

    /**
     * 应用中心安装更新
     */
    const val COMMAND_TYPE_UPDATE_DEVICE_APP_MODE: String = "updateDeviceAppMode"
    const val COMMAND_TYPE_UNINSTALL_DEVICE_APP: String = "uninstall"

    /**
     * 更新提醒，喝水，坐姿
     */
    const val COMMAND_TYPE_UPDATE_REMIND_INFO_DATA: String = "updateRemindInfoData"
}
