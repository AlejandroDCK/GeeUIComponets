package com.letianpai.robot.components.parser.recharge

class ReChargeInfo {
    var code: Int = 0
    var data: ReChargeData? = null
    var msg: String? = null


    override fun toString(): String {
        return "{" +
                "code:" + code +
                ", data:" + data +
                ", msg:'" + msg + '\'' +
                '}'
    }
}
