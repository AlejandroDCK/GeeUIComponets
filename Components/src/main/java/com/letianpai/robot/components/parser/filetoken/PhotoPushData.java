package com.letianpai.robot.components.parser.filetoken;

import java.util.Arrays;

public class PhotoPushData {

    private int photo_id;
    private int[] xyxy;
    private float conf;
    private int cls;

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public int[] getXyxy() {
        return xyxy;
    }

    public void setXyxy(int[] xyxy) {
        this.xyxy = xyxy;
    }

    public float getConf() {
        return conf;
    }

    public void setConf(float conf) {
        this.conf = conf;
    }

    public int getCls() {
        return cls;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "{" +
                "photo_id:" + photo_id +
                ", xyxy:" + Arrays.toString(xyxy) +
                ", conf:" + conf +
                ", cls:" + cls +
                '}';
    }
}
