package com.letianpai.robot.components.network.callback.qrcode

class QRCodeData {
    var config_key: String? = null

    var config_title: String? = null
    lateinit var config_data: Array<QRCodeInfo>

    override fun toString(): String {
        return "{" +
                "config_key:'" + config_key + '\'' +
                ", config_title:'" + config_title + '\'' +
                ", config_data:" + config_data.contentToString() +
                '}'
    }
}
