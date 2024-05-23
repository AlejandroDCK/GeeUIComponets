package com.letianpai.robot.components.parser.uploadconfig;

import com.letianpai.robot.components.parser.tips.TipsData;

/**
 * @author liujunbin
 */
public class UploadDataConfig {

    private int code;
    private String msg;
    private UploadData data;

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

    public UploadData getData() {
        return data;
    }

    public void setData(UploadData data) {
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
