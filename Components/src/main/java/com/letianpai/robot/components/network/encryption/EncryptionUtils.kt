package com.letianpai.robot.components.network.encryption

import android.content.Context
import com.google.gson.Gson
import com.letianpai.robot.components.network.system.SystemUtil
import java.util.Locale

/**
 * @author liujunbin
 */
class EncryptionUtils private constructor(private val mContext: Context) {
    private var mGson: Gson? = null

    init {
        init()
    }

    private fun init() {
        mGson = Gson()
    }

    private val hardcode: Unit
        get() {
        }

    companion object {
        private var instance: EncryptionUtils? = null
        private const val partSecretKey = "your partSecretKey"

        fun getInstance(context: Context): EncryptionUtils? {
            synchronized(EncryptionUtils::class.java) {
                if (instance == null) {
                    instance = EncryptionUtils(context.applicationContext)
                }
                return instance
            }
        }

        /**
         * 获取签名的方式
         * @param inputValue
         * @param ts
         * @return
         */
        private fun getDeviceSign(inputValue: String, ts: String): String? {
            val deviceSecretKey = MD5.encode(inputValue + ts + partSecretKey)
            val macSign = Sha256Utils.getSha256Str(inputValue + ts + deviceSecretKey)
            return macSign
        }

        val robotSign: String
            get() {
                val mac = robotMac
                val ts = ts
                var robotSign = getDeviceSign(mac, ts)
                robotSign = "Bearer $robotSign"
                return robotSign
            }

        fun getHardCodeSign(ts: String?): String {
            val mac = robotMac
            var robotSign = ts?.let { getDeviceSign(mac, it) }
            robotSign = "Bearer $robotSign"
            return robotSign
        }

        /**
         * 获取机器人签名
         * @param sn
         * @param hardcode
         * @param ts
         * @return
         */
        fun getRobotSign(sn: String, hardcode: String, ts: String?): String {
            var robotSign = ts?.let { getDeviceSign(sn + hardcode, it) }
            robotSign = "Bearer $robotSign"
            return robotSign
        }

        fun getDeviceSign(sn: String, hardcode: String, ts: String?): String? {
            val robotSign = ts?.let { getDeviceSign(sn + hardcode, it) }
            return robotSign
        }

        val robotMac: String = SystemUtil.wlanMacAddress!!.lowercase(Locale.getDefault())
        val ts: String = (System.currentTimeMillis() / 1000).toString() + ""
    }
}
