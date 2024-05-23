package com.letianpai.robot.components.network.callback.apptips;

public class AppTipsInfo {

    private String app_name;
    private String tip_content;
    private String tip_content_en;
    private String tip_icon;
    private String packageName;

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getTip_content() {
        return tip_content;
    }

    public void setTip_content(String tip_content) {
        this.tip_content = tip_content;
    }

    public String getTip_icon() {
        return tip_icon;
    }

    public void setTip_icon(String tip_icon) {
        this.tip_icon = tip_icon;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getTip_content_en() {
        return tip_content_en;
    }

    public void setTip_content_en(String tip_content_en) {
        this.tip_content_en = tip_content_en;
    }

    @Override
    public String toString() {
        return "{" +
                "app_name:'" + app_name + '\'' +
                ", tip_content:'" + tip_content + '\'' +
                ", tip_content_en:'" + tip_content_en + '\'' +
                ", tip_icon:'" + tip_icon + '\'' +
                ", packageName:'" + packageName + '\'' +
                '}';
    }
}
