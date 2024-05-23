package com.letianpai.robot.components.parser.recharge;

public class ReChargeInfo {

    private int code;
    private ReChargeData data;
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


    public ReChargeData getData() {
        return data;
    }

    public void setData(ReChargeData data) {
        this.data = data;
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
