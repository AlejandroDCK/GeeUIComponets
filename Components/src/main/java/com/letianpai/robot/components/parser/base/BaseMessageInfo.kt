package com.letianpai.robot.components.parser.base

class BaseMessageInfo {
    var code: Int = 0
    var msg: String? = null


    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                '}'
    }
}
