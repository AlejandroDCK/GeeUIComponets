package com.letianpai.robot.components.parser.uploadconfig

class UploadData {
    var config_key: String? = null
    var config_title: String? = null
    lateinit var config_data: Array<UploadDataInfo>

    override fun toString(): String {
        return "{" +
                "config_key:'" + config_key + '\'' +
                ", config_title:'" + config_title + '\'' +
                ", config_data:" + config_data.contentToString() +
                '}'
    }
}
