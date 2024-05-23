package com.letianpai.robot.components.parser.remind;

import java.util.Arrays;

public class ReminderInfo {

    private int code;
    private ReminderData[] data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ReminderData[] getData() {
        return data;
    }

    public void setData(ReminderData[] data) {
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
        return "ReminderInfo{" +
                "code=" + code +
                ", data=" + Arrays.toString(data) +
                ", msg='" + msg + '\'' +
                '}';
    }
}
