package com.letianpai.robot.components.parser.filetoken

class FileTokenInfo {
    var code: Int = 0
    var data: CloudFileTokenData? = null
    var msg: String? = null

    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", data:" + data +
                ", msg:'" + msg + '\'' +
                '}'
    }
}
