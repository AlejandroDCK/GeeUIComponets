package com.letianpai.robot.components.network.nets

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.letianpai.robot.components.network.lexnet.callback.DeviceChannelLogoCallBack
import com.letianpai.robot.components.network.system.SystemUtil
import com.letianpai.robot.components.parser.logo.LogoInfo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * @author liujunbin
 */
class GeeUINetResponseManager private constructor(context: Context) {
    private var mContext: Context = context
    private var gson: Gson? = null

    val logoInfo: LogoInfo? = null
        get() {
            Thread { getDeviceChannelLogo(SystemUtil.isInChinese) }.start()

            return field
        }

    init {
        init(context)
    }

    private fun init(context: Context) {
        this.mContext = context
        gson = Gson()
    }

    val robotCustomInfo: Unit
        get() {
            if (WIFIConnectionManager.Companion.isNetworkAvailable(mContext)) {
                updateCustomInfo()
            }
        }

    fun dispatchTask(cmd: String?, data: Any?) {
        if (cmd == null) {
            return
        }
        if (cmd == RobotRemoteConsts.COMMAND_TYPE_UPDATE_COUNT_DOWN_CONFIG_DATA) {
            updateCustomInfo()
        }
    }

    /**
     *
     */
    fun updateCustomInfo() {
        Thread { customInfo }.start()
    }

    val customInfo: Unit
        get() {
            GeeUiNetManager.getCustomList(mContext, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.body != null) {
                        var info = ""
                        if (response.body != null) {
                            info = response.body!!.string()
                        }

                        Log.e(
                            "RemoteCmdResponser",
                            "commandDistribute:command ========= 6 ======== info: $info"
                        )
                    }
                }
            })
        }

    fun updateDeviceBindStatus() {
        Thread { isDeviceBind }.start()
    }

    val isDeviceBind: Unit
        get() {
            GeeUiNetManager.isDeviceBind(mContext, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.body != null) {
                        var info = ""
                        if (response.body != null) {
                            info = response.body!!.string()
                        }

                        Log.e(
                            "RemoteCmdResponser",
                            "commandDistribute:command ========= 6 ======== info: $info"
                        )
                    }
                }
            })
        }

    private fun getDeviceChannelLogo(isChinese: Boolean) {
        GeeUiNetManager.getDeviceChannelLogo(mContext, isChinese, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.body != null) {
                    var generalInfo: LogoInfo? = null
                    val info = response.body!!.string()

                    if (info != null) {
                        try {
                            generalInfo = Gson().fromJson(info, LogoInfo::class.java)
                            if (generalInfo != null) {
                                DeviceChannelLogoCallBack.Companion.instance
                                    .setDeviceChannelLogo(generalInfo)
                            } else {
                                Log.e("letianpai_1234", "getDeviceChannelLogo is null: ")
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
        private var instance: GeeUINetResponseManager? = null
        fun getInstance(context: Context): GeeUINetResponseManager? {
            synchronized(GeeUINetResponseManager::class.java) {
                if (instance == null) {
                    instance = GeeUINetResponseManager(context.applicationContext)
                }
                return instance
            }
        }
    }
}
