package com.letianpai.robot.components.parser.imagebg

class ImageBgData {
    var client_id: String? = null
    var package_name: String? = null
    var bg_url: String? = null
    var user_id: Int = 0
    var create_time: Int = 0
    var update_time: Int = 0

    override fun toString(): String {
        return "ImageBgData{" +
                "client_id='" + client_id + '\'' +
                ", package_name='" + package_name + '\'' +
                ", bg_url='" + bg_url + '\'' +
                ", user_id=" + user_id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}'
    }
}
