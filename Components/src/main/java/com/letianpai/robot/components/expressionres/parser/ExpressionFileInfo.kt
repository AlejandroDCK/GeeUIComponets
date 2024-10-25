package com.letianpai.robot.components.expressionres.parser

class ExpressionFileInfo {
    var file_tag: String? = null
    var file_name: String? = null
    var file_url: String? = null
    var update_time: Long = 0

    override fun toString(): String {
        return "{" +
                "file_tag:'" + file_tag + '\'' +
                ", file_name:'" + file_name + '\'' +
                ", file_url:'" + file_url + '\'' +
                ", update_time:" + update_time +
                '}'
    } //    			"file_tag": "h0059",
    //                        "file_name": "h0059",
    //                        "file_name": "h0059.mp4",
    //                        "update_time": 1699375660
}
