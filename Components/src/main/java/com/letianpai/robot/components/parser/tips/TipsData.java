package com.letianpai.robot.components.parser.tips;

/**
 * @author liujunbin
 */
public class TipsData {

    private String config_key;
    private String config_title;
    private ConfigData config_data;

    public ConfigData getConfig_data() {
        return config_data;
    }

    public String getConfig_key() {
        return config_key;
    }

    public String getConfig_title() {
        return config_title;
    }

    public void setConfig_data(ConfigData config_data) {
        this.config_data = config_data;
    }

    public void setConfig_key(String config_key) {
        this.config_key = config_key;
    }

    public void setConfig_title(String config_title) {
        this.config_title = config_title;
    }
}
