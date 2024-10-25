package com.letianpai.robot.components.parser.logo

class LogoInfo {
    var code: Int = 0
    var data: LogoData? = null
    var msg: String? = null

    override fun toString(): String {
        return "LogoInfo{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}'
    }
}
