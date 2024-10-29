package com.letianpai.robot.components.network.nets

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.SystemClock
import android.util.Log
import com.google.gson.Gson
import com.letianpai.robot.components.charging.BatteryReceiver
import com.letianpai.robot.components.charging.ChargingUpdateCallback
import com.letianpai.robot.components.charging.ChargingUpdateCallback.ChargingUpdateListener
import com.letianpai.robot.components.network.callback.AppUploadConfigCallback
import com.letianpai.robot.components.network.system.SystemUtil
import com.letianpai.robot.components.parser.base.BaseMessageInfo
import com.letianpai.robot.components.storage.RobotSubConfigManager
import com.letianpai.robot.components.utils.SDCardUtil
import com.letianpai.robot.components.utils.VolumeManager
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.util.regex.Pattern

/**
 * @author
 */
class GeeUIStatusUploader private constructor(context: Context) {
    private val changingStatus = false
    private val mContext: Context? = context

    init {
        init(context)
    }


    private fun init(context: Context) {
        setBatteryListener()
        ChargingUpdateCallback.instance.registerChargingStatusUpdateListener(object :
            ChargingUpdateListener {
            override fun onChargingUpdateReceived(changingStatus: Boolean, percent: Int) {
                currentPercent = percent
            }

            override fun onChargingUpdateReceived(
                changingStatus: Boolean,
                percent: Int,
                chargePlug: Int
            ) {
            }
        })

        //TODO Block Message Alerts
    }

    //battery monitoring
    private fun setBatteryListener() {
        val batteryReceiver = BatteryReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        mContext!!.registerReceiver(batteryReceiver, intentFilter)
    }


    /**
     * Update robot status
     *
     * @param callback
     */
    private fun uploadStatus(callback: Callback) {
        if (currentPercent == 0) {
            return
        }
        val sn = SystemUtil.ltpSn
        val mac = SystemUtil.wlanMacAddress
        val ip = SystemUtil.getIp(mContext!!)
        val mcu = SystemUtil.mcu
        val wifiSsid = getConnectWifiSsid(mContext)
        val btAddress = btAddressByReflection
        val battery = ChargingUpdateCallback.instance.battery
        val volume: Int = VolumeManager.getInstance(mContext)!!.currentVolume
        val isInHighTemperature = isInHighTemperature
        val space = SDCardUtil.availableSpaceInBytes
        val temp = cpuThermal
        val uptime = (SystemClock.elapsedRealtime()) / 1000

        //        Log.e("letianpai_uploader","sn: "+ sn);
//        Log.e("letianpai_uploader","mac: "+ mac);
//        Log.e("letianpai_uploader","ip: "+ ip);
//        Log.e("letianpai_uploader","mcu: "+ mcu);
//        Log.e("letianpai_uploader","wifiSsid: "+ wifiSsid);
//        Log.e("letianpai_uploader","btAddress: "+ btAddress);
//        Log.e("letianpai_uploader","battery: "+ battery);
//        Log.e("letianpai_uploader","volume: "+ volume);
//        Log.e("letianpai_uploader","isInHighTemperature: "+ isInHighTemperature);
//        Log.e("letianpai_uploader","currentPercent: "+ currentPercent);
//        Log.e("letianpai_uploader","space: "+ space);
//        Log.e("letianpai_uploader","temp: "+ temp);
//        Log.e("letianpai_uploader","uptime: "+ uptime);

        //TODO Increased access
        val hashMap: HashMap<String, Any> = HashMap()
        if (battery > 0) {
            hashMap["battery_percent"] = battery
        }
        hashMap["ble"] = btAddress?: ""
        //        hashMap.put("humidity", 0);
        hashMap["mac"] = mac?: ""
        hashMap["mcu_version"] = mcu?: ""
        hashMap["ip"] = ip?: ""
        hashMap["rom_version"] = Build.DISPLAY
        hashMap["sn"] = sn
        hashMap["sound_volume"] = volume
        hashMap["system_version"] = Build.DISPLAY
        hashMap["temperature"] = temp

        if (isInHighTemperature) {
            hashMap["hot_status"] = 1
        } else {
            hashMap["hot_status"] = 0
        }
        //        hashMap.put("update", 0);
        hashMap["wifi_name"] = wifiSsid
        if (ChargingUpdateCallback.instance.isCharging) {
            hashMap["charge_status"] = 1
        } else {
            hashMap["charge_status"] = 2
        }
        hashMap["space"] = space
        hashMap["uptime"] = uptime
        GeeUiNetManager.uploadStatus(mContext, SystemUtil.isInChinese, hashMap, callback)
    }


    private fun checkUploadFrequency() {
        val uploadFrequencyInternalTime: Long =
            RobotSubConfigManager.Companion.getInstance(mContext)!!.uploadFrequencyInternalTime
        val currentTime = System.currentTimeMillis()
        val isChinese = SystemUtil.isInChinese
        //        GeeUILogUtils.logi("uploadFrequency", "uploadFrequencyInternalTime: " + uploadFrequencyInternalTime);
//        GeeUILogUtils.logi("uploadFrequency", "currentTime: " + currentTime);
        if ((currentTime - uploadFrequencyInternalTime) > UPDATE_INTERNAL_TIME) {
//            GeeUILogUtils.logi("uploadFrequency", "currentTime === 1 ===: ");
            checkRobotUploadFrequency()
            //            GeeUiNetManager.getUploadDataConfig(mContext, isChinese, new AppUploadConfigCallback.AppUploadConfigUpdateListener() {
//                @Override
//                public void onAppUploadConfigUpdate(int uploadFrequency) {
//                    Log.e("letianpai_uploadFrequency", "currentTime === 2 ===: ");
//                    Log.e("letianpai_uploadFrequency", "uploadFrequency: " + uploadFrequency);
//                    if (uploadFrequency > 0) {
//                        Log.e("letianpai_uploadFrequency", "currentTime === 3 ===: ");
//                        RobotSubConfigManager.getInstance(mContext).setUploadFrequency(uploadFrequency);
//                        RobotSubConfigManager.getInstance(mContext).setUploadFrequencyInternalTime(System.currentTimeMillis());
//                        RobotSubConfigManager.getInstance(mContext).commit();
//
//                        Log.e("letianpai_uploadFrequency", "currentTime === 4 ===: ");
//                    }
//                }
//            });
        }
    }

    private fun checkRobotUploadFrequency() {
        val listener = object : AppUploadConfigCallback.AppUploadConfigUpdateListener {
            override fun onAppUploadConfigUpdate(uploadFrequency: Int) {
                if (uploadFrequency > 0) {
                    val configManager = RobotSubConfigManager.getInstance(mContext)
                    if (configManager != null) {
                        configManager.setUploadFrequency(uploadFrequency)
                        configManager.uploadFrequencyInternalTime = System.currentTimeMillis()
                        configManager.commit()
                    } else {
                        Log.e("RobotUploadFrequency", "RobotSubConfigManager instance is null")
                        // Handle the error, e.g., show a message or retry
                    }
                }
            }
        }

        GeeUiNetManager.getUploadDataConfig(mContext!!, SystemUtil.isInChinese, listener)
    }

    fun uploadRobotStatus() {
        if (mContext != null) {
            checkUploadFrequency()
            uploadRobotData()
        }
    }

    fun syncRobotStatus() {
        if (mContext != null) {
            Thread { checkRobotUploadFrequency() }.start()
            Thread { uploadRobotStatusData() }.start()
        }
    }

    private fun uploadRobotData() {
        val internalTime: Long =
            RobotSubConfigManager.getInstance(mContext)!!.uploadFrequencyInternalTime
        val uploadFrequency: Long =
            RobotSubConfigManager.getInstance(mContext)!!.uploadFrequency
        val uploadDataTime: Long =
            RobotSubConfigManager.getInstance(mContext)!!.uploadDataTime

        val current = System.currentTimeMillis()
        //        GeeUILogUtils.logi("uploadFrequency", "internalTime: " + internalTime);
//        GeeUILogUtils.logi("uploadFrequency", "uploadFrequency: " + uploadFrequency);
//        GeeUILogUtils.logi("uploadFrequency", "uploadDataTime: " + uploadDataTime);
//        GeeUILogUtils.logi("uploadFrequency", "current: " + current);
//        GeeUILogUtils.logi("uploadFrequency", "current - uploadDataTime: " + (current - uploadDataTime));
//        GeeUILogUtils.logi("uploadFrequency", "current - uploadDataTime - uploadFrequency * 1000 * 60 :" + (current - uploadDataTime - uploadFrequency * 1000 * 60));
        if (internalTime == 0L || uploadFrequency == 0L || uploadDataTime == 0L || ((current - uploadDataTime) > uploadFrequency * 1000 * 60)) {
            //TODO 直接上传数据
            uploadRobotStatusData()
        }
    }

    fun uploadRobotStatusData() {
        getInstance(mContext!!)!!.uploadStatus(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response?.body != null) {
                    var info = ""
                    if (response?.body != null) {
                        info = response.body!!.string()
                    }
                    val baseMessageInfo: BaseMessageInfo?
                    if (info != null) {
                        try {
//                            GeeUILogUtils.logi("uploadFrequency", "currentTime === info === 1 === " + info);
                            baseMessageInfo = Gson().fromJson(info, BaseMessageInfo::class.java)
                            if (baseMessageInfo != null && baseMessageInfo.msg != null) {
                                if (baseMessageInfo.code == 0) {
                                    RobotSubConfigManager.getInstance(mContext)!!.uploadDataTime = (
                                            System.currentTimeMillis()
                                        )
                                    RobotSubConfigManager.getInstance(mContext)!!.commit()
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        })
    }

    companion object {
        const val HIGH_TEMP: Int = 90
        private var currentPercent = 0
        private const val UPDATE_INTERNAL_TIME = (60 * 60 * 1000).toLong()

        private var instance: GeeUIStatusUploader? = null
        fun getInstance(context: Context): GeeUIStatusUploader? {
            synchronized(GeeUIStatusUploader::class.java) {
                if (instance == null) {
                    instance = GeeUIStatusUploader(context.applicationContext)
                }
                return instance
            }
        }

        /**
         * 获取当前wifi名字
         *
         * @param context
         * @return
         */
        fun getConnectWifiSsid(context: Context): String {
            val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            return wifiInfo.ssid
        }

        val btAddressByReflection: String?
            /**
             * 获取蓝牙地址
             *
             * @return
             */
            get() {
                val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                var field: Field? = null
                try {
                    field = BluetoothAdapter::class.java.getDeclaredField("mService")
                    field.isAccessible = true
                    val bluetoothManagerService = field[bluetoothAdapter] ?: return null
                    val method = bluetoothManagerService.javaClass.getMethod("getAddress")
                    if (method != null) {
                        val obj = method.invoke(bluetoothManagerService)
                        if (obj != null) {
                            return obj.toString()
                        }
                    }
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
                return null
            }

        val cpuThermal: Float
            get() {
                val result: MutableList<String> = ArrayList()
                var br: BufferedReader? = null
                var temp = 0f

                try {
                    val dir = File("/sys/class/thermal/")

                    val files = dir.listFiles { file: File ->
                        if (Pattern.matches("thermal_zone[0-9]+", file.name)) {
                            return@listFiles true
                        }
                        false
                    }
                    val SIZE = files.size
                    val line: String?
                    for (i in 0 until SIZE) {
                        br = BufferedReader(FileReader("/sys/class/thermal/thermal_zone$i/temp"))
                        line = br.readLine()
                        if (line != null) {
                            val temperature = line.toLong()
                            if (temperature < 0) {
                                temp = -1f
                                return temp
                            } else {
                                temp = (temperature / 1000.0).toFloat()
                                return temp
                            }
                        }
                        break
                    }
                    return temp
                } catch (e: FileNotFoundException) {
                    result.add(e.toString())
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    if (br != null) {
                        try {
                            br.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
                return temp
            }

        val isInHighTemperature: Boolean
            get() {
                val temp = cpuThermal
                //        GeeUILogUtils.logi("letianpai_uploader: ", "letianpai_temp: " + temp);
                return if (temp > HIGH_TEMP) {
                    true
                } else {
                    false
                }
            }
    }
}
