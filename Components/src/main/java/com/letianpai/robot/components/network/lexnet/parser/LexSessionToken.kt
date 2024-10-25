package com.letianpai.robot.components.network.lexnet.parser

class LexSessionToken {
    var code: Int = 0

    var msg: String? = null
    var data: SessionTokenData? = null

    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}'
    }
}
