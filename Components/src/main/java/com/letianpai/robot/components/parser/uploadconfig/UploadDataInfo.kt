package com.letianpai.robot.components.parser.uploadconfig

class UploadDataInfo {
    var app_name: String? = null
    var upload_frequency: Int = 0
    var packageName: String? = null

    override fun toString(): String {
        return "{" +
                "app_name:'" + app_name + '\'' +
                ", upload_frequency:" + upload_frequency +
                ", packageName:'" + packageName + '\'' +
                '}'
    }
}
