package com.letianpai.robot.components.parser.remind

class ReminderData {
    var user_id: Int = 0
    var client_id: String? = null
    var remind_type: String? = null
    lateinit var week: Array<String>
    var start_time: String? = null
    var end_time: String? = null
    lateinit var point_time: Array<String>
    var status: Int = 0

    override fun toString(): String {
        return "ReminderData{" +
                "user_id=" + user_id +
                ", client_id='" + client_id + '\'' +
                ", remind_type='" + remind_type + '\'' +
                ", week='" + week + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", point_time=" + point_time.contentToString() +
                ", status=" + status +
                '}'
    }
}
