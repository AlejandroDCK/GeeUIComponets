package com.letianpai.robot.components.parser.uploadconfig;

public class UploadDataInfo {


    private String app_name;
    private int upload_frequency;
    private String packageName;

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public int getUpload_frequency() {
        return upload_frequency;
    }

    public void setUpload_frequency(int upload_frequency) {
        this.upload_frequency = upload_frequency;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "{" +
                "app_name:'" + app_name + '\'' +
                ", upload_frequency:" + upload_frequency +
                ", packageName:'" + packageName + '\'' +
                '}';
    }
}
