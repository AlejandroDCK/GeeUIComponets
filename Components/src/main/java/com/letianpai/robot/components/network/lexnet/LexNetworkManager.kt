package com.letianpai.robot.components.network.lexnet

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.letianpai.robot.components.network.lexnet.callback.SessionTokenUpdateCallback
import com.letianpai.robot.components.network.lexnet.callback.SessionTokenUpdateCallback.SessionTokenUpdateListener
import com.letianpai.robot.components.network.lexnet.parser.LexSessionToken
import com.letianpai.robot.components.network.lexnet.parser.SessionTokenData
import com.letianpai.robot.components.network.nets.GeeUiNetManager
import com.letianpai.robot.components.network.nets.WIFIConnectionManager
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * @author liujunbin
 */
class LexNetworkManager private constructor(context: Context) {
    private var mContext: Context = context
    private var gson: Gson? = null
    private var sessionTokenData: SessionTokenData? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        this.mContext = context
        gson = Gson()
    }

    val robotSessionToken: Unit
        /**
         *
         */
        get() {
            if (!WIFIConnectionManager.Companion.isNetworkAvailable(mContext)) {
                return
            }
            Thread(object : Runnable {
                override fun run() {
                    sessionToken
                }
            }).start()
        }

    private val sessionToken: Unit
        get() {
            GeeUiNetManager.getSessionToken(mContext, false, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.body != null) {
                        var info = ""
                        if (response.body != null) {
                            info = response.body!!.string()
                        }
                        val lexSessionToken: LexSessionToken?
                        try {
                            if (info != null) {
                                lexSessionToken = Gson().fromJson(info, LexSessionToken::class.java)
                                if (lexSessionToken != null && lexSessionToken.data != null) {
                                    sessionTokenData = lexSessionToken.data
                                    SessionTokenUpdateCallback.Companion.instance
                                        .updateSessionToken(sessionTokenData)
                                }
                            }
                            Log.e(
                                "letianpai_RemoteCmdResponser",
                                "commandDistribute:command ========= 6 ======== info: $info"
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            })
        }

    fun getSessionTokenData() {
        val currentTime = (System.currentTimeMillis()) / 1000
        if (sessionTokenData != null && (sessionTokenData!!.expire_time > currentTime)) {
            SessionTokenUpdateCallback.Companion.instance.updateSessionToken(sessionTokenData)
        } else {
            robotSessionToken
        }
    }

    /**
     * 注册回调信息
     */
    private fun initSessionCallback() {
        SessionTokenUpdateCallback.Companion.instance.setSessionTokenUpdateListener {
            //TODO 做你想做的事
        }
    }

    companion object {
        private var instance: LexNetworkManager? = null
        fun getInstance(context: Context): LexNetworkManager? {
            synchronized(LexNetworkManager::class.java) {
                if (instance == null) {
                    instance = LexNetworkManager(context.applicationContext)
                }
                return instance
            }
        }
    }
}
