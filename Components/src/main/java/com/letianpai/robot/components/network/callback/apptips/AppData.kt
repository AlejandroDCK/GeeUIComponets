package com.letianpai.robot.components.network.callback.apptips

class AppData {
    var config_key: String? = null
    var config_title: String? = null
    lateinit var config_data: Array<AppTipsInfo>

    override fun toString(): String {
        return "AppData{" +
                "config_key='" + config_key + '\'' +
                ", config_title='" + config_title + '\'' +
                ", config_data=" + config_data.contentToString() +
                '}'
    }
}
