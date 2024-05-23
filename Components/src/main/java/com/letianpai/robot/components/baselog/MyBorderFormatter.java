package com.letianpai.robot.components.baselog;


import com.elvishew.xlog.formatter.border.BorderFormatter;

public class MyBorderFormatter implements BorderFormatter {

    private String VERTICAL_BORDER_CHAR = "║";
    // Length: 100.
//    private val TOP_HORIZONTAL_BORDER = "╔═════════════════════════════════════════════════" +
//            "══════════════════════════════════════════════════"
    private String TOP_HORIZONTAL_BORDER = "";
    // Length: 99.
    private String DIVIDER_HORIZONTAL_BORDER = "╟─────────────────────────────────────────────────" +
            "──────────────────────────────────────────────────";
    // Length: 100.
//    private val BOTTOM_HORIZONTAL_BORDER = "╚═════════════════════════════════════════════════" +
//            "══════════════════════════════════════════════════"
    private String BOTTOM_HORIZONTAL_BORDER = "";


    @Override
    public String format(String[] segments) {
        if (segments == null || segments.length == 0){
            return "";
        }
        String[] nonNullSegments = new String[segments.length];
        int nonNullCount = 0;
        for (int i = 0; i < segments.length; i++) {
            String segment = segments[i];
            if (segment != null){
                nonNullSegments[nonNullCount++] = segment;
            }
        }

        if (nonNullCount == 0) {
            return "";
        }
        StringBuilder msgBuilder = new StringBuilder();
        //        msgBuilder.append("     ").append(SystemCompat.lineSeparator)
        //        msgBuilder.append(TOP_HORIZONTAL_BORDER).append(SystemCompat.lineSeparator)
        for (int i = 0; i < nonNullCount; i++) {
            msgBuilder.append(appendVerticalBorder(nonNullSegments[i]));
            if (i != nonNullCount - 1) {
                msgBuilder.append( System.lineSeparator())
                        .append(DIVIDER_HORIZONTAL_BORDER)
                        .append( System.lineSeparator());
            } else {
                // msgBuilder.append(SystemCompat.lineSeparator()).append(BOTTOM_HORIZONTAL_BORDER);
            }
        }
        return msgBuilder.toString();
    }

    private String appendVerticalBorder(String msg) {
        StringBuilder borderedMsgBuilder = new StringBuilder(msg.length() + 10);
        String[] lines = msg.split( System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            if (i != 0) {
                borderedMsgBuilder.append( System.lineSeparator());
            }
            String line = lines[i];
            borderedMsgBuilder.append(VERTICAL_BORDER_CHAR).append(line);
        }
        return borderedMsgBuilder.toString();
    }
}
