package com.letianpai.robot.components.network.callback.qrcode;

public class QRCodeInfo {

    private String package_name;
    private String sn;
    private String app_path;
    private String mini_path;
    private String url;
    private String en_url;
    private boolean show_qrcode;

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getApp_path() {
        return app_path;
    }

    public void setApp_path(String app_path) {
        this.app_path = app_path;
    }

    public String getMini_path() {
        return mini_path;
    }

    public void setMini_path(String mini_path) {
        this.mini_path = mini_path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEn_url() {
        return en_url;
    }

    public void setEn_url(String en_url) {
        this.en_url = en_url;
    }

    public void setShow_qrcode(boolean show_qrcode) {
        this.show_qrcode = show_qrcode;
    }

    public boolean isShow_qrcode() {
        return show_qrcode;
    }

    @Override
    public String toString() {
        return "{" +
                "package_name:'" + package_name + '\'' +
                ", sn:'" + sn + '\'' +
                ", app_path:'" + app_path + '\'' +
                ", mini_path:'" + mini_path + '\'' +
                ", url:'" + url + '\'' +
                ", en_url:'" + en_url + '\'' +
                ", show_qrcode:" + show_qrcode +
                '}';
    }
}
