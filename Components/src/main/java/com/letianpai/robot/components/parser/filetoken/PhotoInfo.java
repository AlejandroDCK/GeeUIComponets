package com.letianpai.robot.components.parser.filetoken;

public class PhotoInfo {

    private int code;
    private PhotoInfoData data;
    private String msg;


    public PhotoInfoData getData() {
        return data;
    }

    public void setData(PhotoInfoData data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", data:" + data +
                ", msg:'" + msg + '\'' +
                '}';
    }
}
