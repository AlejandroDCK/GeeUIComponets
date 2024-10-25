package com.letianpai.robot.components.parser.filetoken

class PhotoInfo {
    var code: Int = 0
    var data: PhotoInfoData? = null
    var msg: String? = null


    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", data:" + data +
                ", msg:'" + msg + '\'' +
                '}'
    }
}
