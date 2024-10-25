package com.renhejia.robot.letianpaiservice.parser

/**
 * @author liujunbin
 */
class CmdInfo(var command: String, var data: String) {
    override fun toString(): String {
        return "{" +
                "command:'" + command + '\'' +
                ", data:'" + data + '\'' +
                '}'
    }
}