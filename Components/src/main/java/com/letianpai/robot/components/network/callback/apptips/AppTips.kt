package com.letianpai.robot.components.network.callback.apptips

class AppTips {
    var code: Int = 0
    var msg: String? = null
    var data: AppData? = null

    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}'
    }
}
