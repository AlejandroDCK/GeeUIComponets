package com.letianpai.robot.components.parser.recharge

/**
 * 回充数据
 */
class ReChargeData {
    var automatic_recharge_switch: Int = 0
    var automatic_recharge_val: Int = 0

    override fun toString(): String {
        return "{" +
                "automatic_recharge_switch:" + automatic_recharge_switch +
                ", automatic_recharge_val:" + automatic_recharge_val +
                '}'
    }
}
