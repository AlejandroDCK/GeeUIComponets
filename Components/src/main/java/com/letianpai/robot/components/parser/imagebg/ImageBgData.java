package com.letianpai.robot.components.parser.imagebg;

public class ImageBgData {

    private String client_id;
    private String package_name;
    private String bg_url;
    private int user_id;
    private int create_time;
    private int update_time;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getBg_url() {
        return bg_url;
    }

    public void setBg_url(String bg_url) {
        this.bg_url = bg_url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "ImageBgData{" +
                "client_id='" + client_id + '\'' +
                ", package_name='" + package_name + '\'' +
                ", bg_url='" + bg_url + '\'' +
                ", user_id=" + user_id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
