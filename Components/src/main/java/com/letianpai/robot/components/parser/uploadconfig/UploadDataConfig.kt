package com.letianpai.robot.components.parser.uploadconfig

/**
 * @author liujunbin
 */
class UploadDataConfig {
    var code: Int = 0
    var msg: String? = null
    var data: UploadData? = null

    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}'
    }
}
