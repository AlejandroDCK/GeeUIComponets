package com.letianpai.robot.components.network.callback.qrcode

class QRCodeInfo {
    var package_name: String? = null
    var sn: String? = null
    var app_path: String? = null
    var mini_path: String? = null
    var url: String? = null
    var en_url: String? = null
    var isShow_qrcode: Boolean = false

    override fun toString(): String {
        return "{" +
                "package_name:'" + package_name + '\'' +
                ", sn:'" + sn + '\'' +
                ", app_path:'" + app_path + '\'' +
                ", mini_path:'" + mini_path + '\'' +
                ", url:'" + url + '\'' +
                ", en_url:'" + en_url + '\'' +
                ", show_qrcode:" + isShow_qrcode +
                '}'
    }
}
