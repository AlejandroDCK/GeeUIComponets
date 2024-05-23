package com.letianpai.robot.components.expressionres.parser;

public class ExpressionFileInfo {

    private String file_tag;
    private String file_name;
    private String file_url;
    private long update_time;

    public String getFile_tag() {
        return file_tag;
    }

    public void setFile_tag(String file_tag) {
        this.file_tag = file_tag;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "{" +
                "file_tag:'" + file_tag + '\'' +
                ", file_name:'" + file_name + '\'' +
                ", file_url:'" + file_url + '\'' +
                ", update_time:" + update_time +
                '}';
    }

    //    			"file_tag": "h0059",
//                        "file_name": "h0059",
//                        "file_name": "h0059.mp4",
//                        "update_time": 1699375660
}
