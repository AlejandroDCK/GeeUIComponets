package com.letianpai.robot.components.expressionres.parser

class ExpressionFileData {
    var version: String? = null
    var file_list: Array<ExpressionFileInfo?>? = null

    override fun toString(): String {
        return "{" +
                "version='" + version + '\'' +
                ", file_list=" + file_list.contentToString() +
                '}'
    }
}
