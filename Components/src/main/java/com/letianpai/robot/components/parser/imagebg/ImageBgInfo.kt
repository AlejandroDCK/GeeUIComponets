package com.letianpai.robot.components.parser.imagebg

class ImageBgInfo {
    var code: Int = 0
    var data: ImageBgData? = null
    var msg: String? = null


    override fun toString(): String {
        return "ImageBgInfo{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}'
    }
}
