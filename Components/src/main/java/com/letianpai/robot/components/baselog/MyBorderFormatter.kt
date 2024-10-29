package com.letianpai.robot.components.baselog

import com.elvishew.xlog.formatter.border.BorderFormatter


class MyBorderFormatter : BorderFormatter {
    private val VERTICAL_BORDER_CHAR: String = "║"

    // Length: 100.
    //    private val TOP_HORIZONTAL_BORDER = "╔═════════════════════════════════════════════════" +
    //            "══════════════════════════════════════════════════"
    private val TOP_HORIZONTAL_BORDER: String = ""

    // Length: 99.
    private val DIVIDER_HORIZONTAL_BORDER: String =
        "╟─────────────────────────────────────────────────" +
                "──────────────────────────────────────────────────"

    // Length: 100.
    //    private val BOTTOM_HORIZONTAL_BORDER = "╚═════════════════════════════════════════════════" +
    //            "══════════════════════════════════════════════════"
    private val BOTTOM_HORIZONTAL_BORDER: String = ""


    override fun format(segments: Array<String?>): String {
        if (segments == null || segments.size == 0) {
            return ""
        }
        val nonNullSegments: Array<String?> = arrayOfNulls(segments.size)
        var nonNullCount: Int = 0
        for (i in segments.indices) {
            val segment: String? = segments[i]
            if (segment != null) {
                nonNullSegments[nonNullCount++] = segment
            }
        }


        if (nonNullCount == 0) {
            return ""
        }
        val msgBuilder: StringBuilder = StringBuilder()
        //        msgBuilder.append("     ").append(SystemCompat.lineSeparator)
        //        msgBuilder.append(TOP_HORIZONTAL_BORDER).append(SystemCompat.lineSeparator)
        for (i in 0 until nonNullCount) {
            msgBuilder.append(appendVerticalBorder(nonNullSegments.get(i)))
            if (i != nonNullCount - 1) {
                msgBuilder.append(System.lineSeparator())
                    .append(DIVIDER_HORIZONTAL_BORDER)
                    .append(System.lineSeparator())
            } else {
                // msgBuilder.append(SystemCompat.lineSeparator()).append(BOTTOM_HORIZONTAL_BORDER);
            }
        }
        return msgBuilder.toString()
    }

    private fun appendVerticalBorder(msg: String?): String {
        val borderedMsgBuilder: StringBuilder = StringBuilder(msg!!.length + 10)
        val lines: Array<String> =
            msg.split(System.lineSeparator().toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        for (i in lines.indices) {
            if (i != 0) {
                borderedMsgBuilder.append(System.lineSeparator())
            }
            val line: String = lines.get(i)
            borderedMsgBuilder.append(VERTICAL_BORDER_CHAR).append(line)
        }
        return borderedMsgBuilder.toString()
    }
}
