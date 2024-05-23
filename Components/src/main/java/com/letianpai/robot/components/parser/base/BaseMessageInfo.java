package com.letianpai.robot.components.parser.base;

import com.letianpai.robot.components.parser.remind.ReminderData;

import java.util.Arrays;

public class BaseMessageInfo {

    private int code;
    private String msg;

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
                ", msg:'" + msg + '\'' +
                '}';
    }
}
