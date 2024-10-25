package com.letianpai.robot.components.network.callback.qrcode

class QRCodeTips {
    var code: Int = 0
    var msg: String? = null

    var data: QRCodeData? = null

    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}'
    }
}
