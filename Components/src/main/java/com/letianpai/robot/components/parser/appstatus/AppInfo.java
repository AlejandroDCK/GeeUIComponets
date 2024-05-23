package com.letianpai.robot.components.parser.appstatus;

/**
 * app消息
 */
public class AppInfo {

    private int app_id;
    private String app_version;
    private int upgrade_status;

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public int getUpgrade_status() {
        return upgrade_status;
    }

    public void setUpgrade_status(int upgrade_status) {
        this.upgrade_status = upgrade_status;
    }

    @Override
    public String toString() {
        return "{" +
                "app_id:" + app_id +
                ", app_version:'" + app_version + '\'' +
                ", upgrade_status:" + upgrade_status +
                '}';
    }
}
