package com.letianpai.robot.components.network.nets

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.NetworkInfo.DetailedState
import android.net.wifi.SupplicantState
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.core.app.ActivityCompat

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
     * Try to connect to the specified wifi
     *
     * @param ssid wifi name
     * @param password Password
     * @return Is the connection successful?
     */
    fun connect(ssid: String, password: String): Boolean {
        this.currentSsid = ssid
        this.currentPassword = password
        Log.e("auto_connect", "connect() called with: ssid = [$ssid], password = [$password]")
        Log.e("auto_connect", "connect: wifi opened = " + openWifi())

        val isConnected = isConnected(ssid) //Currently connected to the specified wifi
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
     * Try to connect to the specified wifi
     * @return Whether the connection is successful or not
     */
    fun connect(): Boolean {
        return if (TextUtils.isEmpty(currentSsid) || TextUtils.isEmpty(currentPassword)) {
            false
        } else {
            connect(currentSsid!!, currentPassword!!)
        }
    }

    /**
     * Configure WiFiConfiguration based on wifi name and password, each attempt will first disconnect the existing connection.
     * @param isClient Whether the current device is a client or a server, affects SSID and PWD.
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
        if (isClient) { //As a client, connect to the server's wifi hotspot in double quotes.
            config.SSID = "\"" + ssid + "\""
            config.preSharedKey = "\"" + password + "\""
        } else { //As a server, open wifi hotspot without double quotes
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

    val isWifiEnabled: Boolean =
        try {
            val methodIsWifiApEnabled =
                WifiManager::class.java.getDeclaredMethod("isWifiApEnabled")
            methodIsWifiApEnabled.invoke(wifiManager) as Boolean
        } catch (e: Exception) {
            Log.e(TAG, "isWifiEnabled: " + e.message)
            false
        }

    /**
     * Whether the specified wifi is connected
     */
    fun isConnected(ssid: String?): Boolean {
        val wifiInfo = wifiManager.connectionInfo ?: return false
        when (wifiInfo.supplicantState) {
            SupplicantState.AUTHENTICATING, SupplicantState.ASSOCIATING, SupplicantState.ASSOCIATED, SupplicantState.FOUR_WAY_HANDSHAKE, SupplicantState.GROUP_HANDSHAKE, SupplicantState.COMPLETED -> {
                Log.e(
                    "auto_connect",
                    " wifiInfo.getSSID(): " + wifiInfo.ssid.replace("\"", "")
                )
                return wifiInfo.ssid.replace("\"", "") == ssid
            }

            else -> return false
        }
    }
    /**
     * Whether the specified wifi is connected
     */
    val isConnected: Boolean = if (TextUtils.isEmpty(currentSsid)) {
        false
    } else {
        isConnected(currentSsid) && isNetworkAvailable(mContext)
    }

    /**
     * Turn on the WiFi.
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
     * Turn off wifi
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
     * Disconnect
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
     * Delete Network
     * @return
     */
    fun removeNetwork(): Boolean {
        if (networkId != 0) {
            return wifiManager.removeNetwork(networkId)
        }
        return false
    }

    /**
     * Whether the specified Wifi has been connected
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
        val existingConfigs = if (ActivityCompat.checkSelfPermission(
                mContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e(TAG, "Permissions not granted")
            null
        } else {
            wifiManager.configuredNetworks
        }
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

    /**
     * Get local ip address
     */
    val localIp: String = convertIp(wifiManager.connectionInfo.ipAddress).toString()


    private fun convertIp(ipAddress: Int): String? {
        if (ipAddress == 0) return null
        return ((ipAddress and 0xff).toString() + "." + (ipAddress shr 8 and 0xff) + "."
                + (ipAddress shr 16 and 0xff) + "." + (ipAddress shr 24 and 0xff))
    }

    fun getConnectState(context: Context, SSID: String): Int {
        var connectState = WIFI_STATE_NONE

        if (Build.VERSION.SDK_INT >= 26) {
            //Qualcomm 8.0 GO
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
        val wifiConfigs = if (ActivityCompat.checkSelfPermission(
                mContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e(TAG, "Permissions not granted")
            null
        } else {
            wifiManager.configuredNetworks
        }
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
                //If it is only used to determine network connectivity
                //then you can use cm.getActiveNetworkInfo().isAvailable();
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

        /*
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
         */

    }
}
