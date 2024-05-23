package com.letianpai.robot.components.network.lexnet.parser;

import com.letianpai.robot.components.network.callback.apptips.AppData;

public class LexSessionToken {

    private int code;

    private String msg;
    private SessionTokenData data;

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

    public SessionTokenData getData() {
        return data;
    }

    public void setData(SessionTokenData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}';
    }
}
