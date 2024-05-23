package com.letianpai.robot.components.network.callback.qrcode;

import com.letianpai.robot.components.network.callback.apptips.AppData;

public class QRCodeTips {

    private int code;
    private String msg;

    private QRCodeData data;

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

    public QRCodeData getData() {
        return data;
    }

    public void setData(QRCodeData data) {
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
