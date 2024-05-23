package com.letianpai.robot.components.parser.filetoken;

import com.letianpai.robot.components.parser.remind.ReminderData;

import java.util.Arrays;

public class FileTokenInfo {

    private int code;
    private CloudFileTokenData data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CloudFileTokenData getData() {
        return data;
    }

    public void setData(CloudFileTokenData data) {
        this.data = data;
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
