package com.letianpai.robot.components.network.lexnet.callback

import com.letianpai.robot.components.network.lexnet.parser.SessionTokenData


/**
 * Created by liujunbin
 */
class SessionTokenUpdateCallback private constructor() {
    private var mSessionTokenUpdateListener: SessionTokenUpdateListener? = null

    private object RobotCommandWordsCallbackHolder {
        val instance: SessionTokenUpdateCallback = SessionTokenUpdateCallback()
    }

    fun interface SessionTokenUpdateListener {
        fun updateSessionTokenInfo(sessionTokenData: SessionTokenData?)
    }

    fun setSessionTokenUpdateListener(listener: SessionTokenUpdateListener?) {
        this.mSessionTokenUpdateListener = listener
    }

    fun updateSessionToken(sessionTokenData: SessionTokenData?) {
        if (mSessionTokenUpdateListener != null) {
            mSessionTokenUpdateListener!!.updateSessionTokenInfo(sessionTokenData)
        }
    }


    companion object {
        val instance: SessionTokenUpdateCallback = RobotCommandWordsCallbackHolder.instance
    }
}
