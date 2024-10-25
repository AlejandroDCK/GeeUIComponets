package com.letianpai.robot.components.network.nets

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.NetworkInfo.DetailedState
import android.net.wifi.SupplicantState
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.os.Build
import android.text.TextUtils
import android.util.Log

/**
 * @author liujunbin
 */
class WIFIConnectionManager(private val mContext: Context) {
    val wifiManager: WifiManager =
        mContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private var networkId = 0
    private var currentSsid: String? = null
    private var currentPassword: String? = null
    var isSetIncorrectPassword: Boolean = false

    /**
     * 尝试连接指定wifi
     *
     * @param ssid     wifi名
     * @param password 密码
     * @return 是否连接成功
     */
    fun connect(ssid: String, password: String): Boolean {
        this.currentSsid = ssid
        this.currentPassword = password
        Log.e("auto_connect", "connect() called with: ssid = [$ssid], password = [$password]")
        Log.e("auto_connect", "connect: wifi opened = " + openWifi())

        val isConnected = isConnected(ssid) //当前已连接至指定wifi
        Log.e(TAG, "connect: is already connected = $isConnected")
        if (isConnected) {
            return true
        }
        networkId = wifiManager.addNetwork(newWifiConfig(ssid, password, true))
        val result = wifiManager.enableNetwork(networkId, true)

        //        mWifiManager.removeNetwork(networkId);
        Log.e("auto_connect", "connect: network enabled = $result")
        return result
    }

    /**
     * 尝试连接指定wifi
     *
     * @return 是否连接成功
     */
    fun connect(): Boolean {
        return if (TextUtils.isEmpty(currentSsid) || TextUtils.isEmpty(currentPassword)) {
            false
        } else {
            connect(currentSsid!!, currentPassword!!)
        }
    }

    /**
     * 根据wifi名与密码配置 WiFiConfiguration, 每次尝试都会先断开已有连接
     *
     * @param isClient 当前设备是作为客户端,还是作为服务端, 影响SSID和PWD
     */
    private fun newWifiConfig(
        ssid: String,
        password: String,
        isClient: Boolean
    ): WifiConfiguration {
        val config = WifiConfiguration()
        config.allowedAuthAlgorithms.clear()
        config.allowedGroupCiphers.clear()
        config.allowedKeyManagement.clear()
        config.allowedPairwiseCiphers.clear()
        config.allowedProtocols.clear()
        if (isClient) { //作为客户端, 连接服务端wifi热点时要加双引号
            config.SSID = "\"" + ssid + "\""
            config.preSharedKey = "\"" + password + "\""
        } else { //作为服务端, 开放wifi热点时不需要加双引号
            config.SSID = ssid
            config.preSharedKey = password
        }
        config.hiddenSSID = true
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP)
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
        config.status = WifiConfiguration.Status.ENABLED
        return config
    }

    val isWifiEnabled: Boolean
        /**
         * @return 热点是否已开启
         */
        get() {
            try {
                val methodIsWifiApEnabled =
                    WifiManager::class.java.getDeclaredMethod("isWifiApEnabled")
                return methodIsWifiApEnabled.invoke(wifiManager) as Boolean
            } catch (e: Exception) {
                Log.e(TAG, "isWifiEnabled: " + e.message)
                return false
            }
        }

    /**
     * 是否已连接指定wifi
     */
    fun isConnected(ssid: String?): Boolean {
        val wifiInfo = wifiManager.connectionInfo ?: return false
        when (wifiInfo.supplicantState) {
            SupplicantState.AUTHENTICATING, SupplicantState.ASSOCIATING, SupplicantState.ASSOCIATED, SupplicantState.FOUR_WAY_HANDSHAKE, SupplicantState.GROUP_HANDSHAKE, SupplicantState.COMPLETED -> {
                Log.e(
                    "auto_connect",
                    " wifiInfo.getSSID(): " + wifiInfo.ssid.replace("\"", "").toString()
                )
                return wifiInfo.ssid.replace("\"", "") == ssid
            }

            else -> return false
        }
    }

    val isConnected: Boolean
        /**
         * 是否已连接指定wifi
         */
        get() = if (TextUtils.isEmpty(currentSsid)) {
            false
        } else {
            isConnected(currentSsid) && isNetworkAvailable(mContext)
        }

    /**
     * 打开WiFi
     * @return
     */
    fun openWifi(): Boolean {
        var opened = true
        if (!wifiManager.isWifiEnabled) {
            opened = wifiManager.setWifiEnabled(true)
        }
        return opened
    }

    /**
     * 关闭wifi
     * @return
     */
    fun closeWifi(): Boolean {
        var closed = true
        if (wifiManager.isWifiEnabled) {
            closed = wifiManager.setWifiEnabled(false)
        }
        return closed
    }

    /**
     * 断开连接
     * @return
     */
    fun disconnect(): WIFIConnectionManager {
        if (networkId != 0) {
            wifiManager.disableNetwork(networkId)
        }
        wifiManager.disconnect()
        return this
    }

    /**
     * 删除网络
     * @return
     */
    fun removeNetwork(): Boolean {
        if (networkId != 0) {
            return wifiManager.removeNetwork(networkId)
        }
        return false
    }

    /**
     * 是否连接过指定Wifi
     */
    fun everConnected(ssid: String): WifiConfiguration? {
//        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
////            return TODO;
//        }

        var ssid = ssid
        val existingConfigs = wifiManager.configuredNetworks
        if (existingConfigs == null || existingConfigs.isEmpty()) {
            return null
        }
        ssid = "\"" + ssid + "\""
        for (existingConfig in existingConfigs) {
            if (existingConfig.SSID == ssid) {
                return existingConfig
            }
        }
        return null
    }

    val localIp: String?
        /**
         * 获取本机的ip地址
         */
        get() = convertIp(wifiManager.connectionInfo.ipAddress)

    private fun convertIp(ipAddress: Int): String? {
        if (ipAddress == 0) return null
        return ((ipAddress and 0xff).toString() + "." + (ipAddress shr 8 and 0xff) + "."
                + (ipAddress shr 16 and 0xff) + "." + (ipAddress shr 24 and 0xff))
    }

    fun getConnectState(context: Context, SSID: String): Int {
        var connectState = WIFI_STATE_NONE

        if (Build.VERSION.SDK_INT >= 26) {
            //高通8.0 GO
            val wifiInfo = wifiManager.connectionInfo
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

            if (wifiInfo != null) {
                Log.e(
                    "",
                    "getConnectState()..connectState: " + connectState + "；ossid: " + SSID + ":wifiinfo ssid: " + wifiInfo.ssid
                )
                if (wifiInfo.ssid == "\"" + SSID + "\"" || wifiInfo.ssid == SSID) {
                    if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                        Log.e("", "getConnectState().. network type: " + networkInfo.type)
                        val detailedState = networkInfo.detailedState
                        //Log.e(TAG, ".SSID = " + wifiConfiguration.SSID + " " + detailedState + " status = " + wifiConfiguration.status + " rssi = " + rssi);
                        if (detailedState == DetailedState.CONNECTING || detailedState == DetailedState.AUTHENTICATING || detailedState == DetailedState.OBTAINING_IPADDR) {
                            connectState = WIFI_STATE_CONNECTED
                        } else if (detailedState == DetailedState.CONNECTED || detailedState == DetailedState.CAPTIVE_PORTAL_CHECK) {
                            connectState = WIFI_STATE_CONNECTED
                        }

                        Log.e("", "getConnectState().. detailedState: $detailedState")
                    }
                }
            }
        }
        return connectState
    }

    private fun findNetworkidBySsid(ssid: String): Int {
        val wifiConfigs = wifiManager.configuredNetworks
        var curNetworkId = -1
        if (wifiConfigs != null) {
            for (existingConfig in wifiConfigs) {
                Log.e(TAG, "removeNetwork() wifiConfigs.. networkId: " + existingConfig.networkId)
                if (existingConfig.SSID == "\"" + ssid + "\"" || existingConfig.SSID == ssid) {
                    Log.e(TAG, "removeNetwork().. networkId: " + existingConfig.networkId)
                    curNetworkId = existingConfig.networkId
                    break
                }
            }
        }
        Log.e(TAG, "removeNetwork().. return networkId: $curNetworkId")
        return curNetworkId
    }

    fun removeNetwork(ssid: String) {
        var curNetworkId = -1

        curNetworkId = findNetworkidBySsid(ssid)
        if (curNetworkId != -1) {
            wifiManager.disconnect()
            val removeResult = wifiManager.removeNetwork(curNetworkId)
            Log.e("auto_connect", "removeResult = $removeResult")
        }
    }


    companion object {
        private val TAG: String = WIFIConnectionManager::class.java.name
        private var sInstance: WIFIConnectionManager? = null
        const val WIFI_STATE_NONE: Int = 0
        const val WIFI_STATE_CONNECTING: Int = 2
        const val WIFI_STATE_CONNECTED: Int = 4
        fun getInstance(context: Context): WIFIConnectionManager? {
            if (sInstance == null) {
                synchronized(WIFIConnectionManager::class.java) {
                    if (sInstance == null) {
                        sInstance = WIFIConnectionManager(context)
                    }
                }
            }
            return sInstance
        }


        fun getSSID(ctx: Context): String {
            val wifiManager =
                ctx.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            val ssid = wifiInfo.ssid
            return ssid.replace("\"".toRegex(), "")
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm == null) {
            } else {
                //如果仅仅是用来判断网络连接
                //则可以使用 cm.getActiveNetworkInfo().isAvailable();
                val info = cm.allNetworkInfo
                if (info != null) {
                    for (i in info.indices) {
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        val isWifiConnected: Boolean
            get() {
                if (sInstance!!.mContext != null) {
                    val mConnectivityManager = sInstance!!.mContext
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val mWiFiNetworkInfo =
                        mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    if (mWiFiNetworkInfo != null) {
                        return mWiFiNetworkInfo.isAvailable && mWiFiNetworkInfo.isConnected
                    }
                }
                return false
            }
    }
}
