package com.letianpai.robot.components.parser.tips

/**
 * @author liujunbin
 */
class ConfigData {
    lateinit var tips_list: Array<TipsName>
    lateinit var tips_en_list: Array<TipsName>

    override fun toString(): String {
        return "{" +
                "tips_list=" + tips_list.contentToString() +
                '}'
    }
}
