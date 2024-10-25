package com.letianpai.robot.components.parser.remind

class ReminderInfo {
    var code: Int = 0
    lateinit var data: Array<ReminderData>
    var msg: String? = null

    override fun toString(): String {
        return "ReminderInfo{" +
                "code=" + code +
                ", data=" + data.contentToString() +
                ", msg='" + msg + '\'' +
                '}'
    }
}
