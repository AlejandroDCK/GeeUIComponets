package com.letianpai.robot.components.parser.appstatus;

public class AppPushInfo {

    //"app_id":1536,"event_type":0,"app_package_name":"com.letianpai.robot.geeuistock","app_sign":"stock"

    private int app_id;
    private int event_type;
    private String app_package_name;
    private String app_sign;

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
    }

    public String getApp_package_name() {
        return app_package_name;
    }

    public void setApp_package_name(String app_package_name) {
        this.app_package_name = app_package_name;
    }

    public String getApp_sign() {
        return app_sign;
    }

    public void setApp_sign(String app_sign) {
        this.app_sign = app_sign;
    }


}
