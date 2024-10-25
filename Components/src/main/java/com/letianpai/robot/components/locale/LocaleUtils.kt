package com.letianpai.robot.components.locale

import java.util.Locale

object LocaleUtils {
    private const val PRC_LAUNGUAGE = "zh"
    @JvmStatic
    val isChinese: Boolean
        get() {
            val language = Locale.getDefault().language
            return if (language == PRC_LAUNGUAGE) {
                true
            } else {
                false
            }
        }
}
