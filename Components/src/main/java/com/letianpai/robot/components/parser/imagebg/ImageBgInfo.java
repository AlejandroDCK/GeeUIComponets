package com.letianpai.robot.components.parser.imagebg;

public class ImageBgInfo {

    private int code;
    private ImageBgData data;
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

    public ImageBgData getData() {
        return data;
    }

    public void setData(ImageBgData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImageBgInfo{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
