package com.letianpai.robot.components.network.callback.apptips

class AppTipsInfo {
    var app_name: String? = null
    var tip_content: String? = null
    var tip_content_en: String? = null
    var tip_icon: String? = null
    var packageName: String? = null


    override fun toString(): String {
        return "{" +
                "app_name:'" + app_name + '\'' +
                ", tip_content:'" + tip_content + '\'' +
                ", tip_content_en:'" + tip_content_en + '\'' +
                ", tip_icon:'" + tip_icon + '\'' +
                ", packageName:'" + packageName + '\'' +
                '}'
    }
}
