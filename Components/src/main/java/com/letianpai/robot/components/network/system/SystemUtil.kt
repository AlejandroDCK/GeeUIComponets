package com.letianpai.robot.components.network.system

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.text.TextUtils
import android.util.Log
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.net.NetworkInterface
import java.net.SocketException
import java.util.Locale

/**
 * @author liujunbin
 */
object SystemUtil {
    private var sysPropGet: Method? = null
    private var sysPropGetInt: Method? = null
    private var sysPropSet: Method? = null
    private const val SN = "ro.serialno"
    private const val MCU_VERSION = "persist.sys.mcu.version"
    const val HARD_CODE: String = "persist.sys.hardcode"
    const val DEVICE_SIGN: String = "persist.sys.device.sign"
    private const val ROBOT_STATUS = "persist.sys.robot.status"
    private const val TITLE_TOUCH_STATUS = "persist.sys.title.touch"
    const val REGION_LANGUAGE: String = "persist.sys.region.language"

    private const val ROBOT_STATUS_TRUE = "true"
    const val REGION_LANGUAGE_ZH: String = "zh"
    const val REGION_LANGUAGE_EN: String = "en"
    const val TITLE_TOUCH_STATUS_OPENED: String = "open"
    const val TITLE_TOUCH_STATUS_CLOSED: String = "close"


    init {
        try {
            val S = Class.forName("android.os.SystemProperties")
            val M = S.methods
            for (m in M) {
                val n = m.name
                if (n == "get") {
                    sysPropGet = m
                } else if (n == "getInt") {
                    sysPropGetInt = m
                } else if (n == "set") {
                    sysPropSet = m
                }
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    fun get(name: String?, default_value: String): String {
        try {
            return sysPropGet!!.invoke(null, name, default_value) as String
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return default_value
    }

    fun set(name: String?, value: String?) {
        try {
            sysPropSet!!.invoke(null, name, value)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    fun setRobotActivated() {
        set(ROBOT_STATUS, ROBOT_STATUS_TRUE)
    }


    val robotActivateStatus: Boolean
        get() {
            val status = get(ROBOT_STATUS, "")
            if (status == ROBOT_STATUS_TRUE) {
                return true
            }
            return false
        }

    fun openTitleTouchStatus() {
        set(TITLE_TOUCH_STATUS, TITLE_TOUCH_STATUS_OPENED)
    }

    fun closeTitleTouchStatus() {
        set(TITLE_TOUCH_STATUS, TITLE_TOUCH_STATUS_CLOSED)
    }

    val titleTouchStatus: Boolean
        get() {
            val status = get(TITLE_TOUCH_STATUS, TITLE_TOUCH_STATUS_OPENED)
            if (status == TITLE_TOUCH_STATUS_OPENED) {
                return true
            }
            return false
        }


    val ltpSn: String = Build.getSerial()

    val ltpLastSn: String? = if (TextUtils.isEmpty(ltpSn)) { null }
            else { ltpSn.substring(ltpSn.length - 4) }

    var hardCode: String? = get(HARD_CODE, "")
        set(hardCode) {
            set(HARD_CODE, hardCode)
        }

    fun hasHardCode(): Boolean {
        return !TextUtils.isEmpty(hardCode)
    }

    var deviceSign: String? = get(DEVICE_SIGN, "")
        set(deviceSign) {
            set(DEVICE_SIGN, deviceSign)
        }

    val mcu: String = get(MCU_VERSION, "")

    fun hadDeviceSign(): Boolean {
        return !TextUtils.isEmpty(deviceSign)
    }


    val wlanMacAddress: String?
        get() {
            try {
                val networkInterfaces = NetworkInterface.getNetworkInterfaces()
                while (networkInterfaces.hasMoreElements()) {
                    val networkInterface = networkInterfaces.nextElement()
                    if (networkInterface.name == "wlan0") {
                        val mac = StringBuilder()
                        val hardwareAddress = networkInterface.hardwareAddress
                        var hex = Integer.toHexString(hardwareAddress[0].toInt() and 0xff)
                        if (hex.length == 1) {
                            mac.append('0')
                        }
                        mac.append(hex)
                        for (i in 1 until hardwareAddress.size) {
                            mac.append(':')
                            hex = Integer.toHexString(hardwareAddress[i].toInt() and 0xff)
                            if (hex.length == 1) {
                                mac.append('0')
                            }
                            mac.append(hex)
                        }
                        return mac.toString()
                    }
                }
            } catch (ex: SocketException) {
                Log.i("", ex.message!!)
            }
            return null
        }

    fun getIp(context: Context): String? {
        val wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiManager != null) {
            val wifiInfo = wifiManager.connectionInfo
            val ip = wifiInfo.ipAddress

            val ipAddress = String.format(
                "%d.%d.%d.%d",
                (ip and 0xff),
                (ip shr 8 and 0xff),
                (ip shr 16 and 0xff),
                (ip shr 24 and 0xff)
            )
            return ipAddress
        }
        return null
    }

    val robotStatus: Boolean
        get() {
            val status = get(ROBOT_STATUS, "")
            if (status == ROBOT_STATUS_TRUE) {
                return true
            }
            return false
        }


    val robotInChineseStatus: String?
        get() {
            val pro = get(REGION_LANGUAGE, "zh")
            return pro
        }

    val isInChinese: Boolean
        get() {
            val pro = get(REGION_LANGUAGE, "zh")
            return if ("zh" == pro) {
                true
            } else {
                false
            }
            //        return false;
        }

    val language: String? = get(REGION_LANGUAGE, REGION_LANGUAGE_ZH)

    val isChineseLanguage: Boolean = language == REGION_LANGUAGE_ZH


    /**
     * @param context
     * @param language
     */
    fun setDefaultLanguage(context: Context, language: String) {
        if (TextUtils.isEmpty(language)) {
            return
        }

        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        val metrics = context.resources.displayMetrics

        var loc = Locale.CHINA
        if (language == REGION_LANGUAGE_EN) {
            loc = Locale.ENGLISH
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(loc)
        } else {
            configuration.locale = loc
        }

        context.resources.updateConfiguration(configuration, metrics)
    }

    /**
     * @param context
     */
    fun setAppLanguage(context: Context) {
        if (isInChinese) {
            setDefaultLanguage(context, "zh")
        } else {
            setDefaultLanguage(context, REGION_LANGUAGE_EN)
        }
    }
}
