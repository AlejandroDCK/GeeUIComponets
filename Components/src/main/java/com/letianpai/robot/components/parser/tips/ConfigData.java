package com.letianpai.robot.components.parser.tips;

import java.util.Arrays;

/**
 * @author liujunbin
 */
public class ConfigData {

    private TipsName[] tips_list;
    private TipsName[] tips_en_list;

    public TipsName[] getTips_list() {
        return tips_list;
    }

    public TipsName[] getTips_en_list() {
        return tips_en_list;
    }

    public void setTips_list(TipsName[] tips_list) {
        this.tips_list = tips_list;
    }

    public void setTips_en_list(TipsName[] tips_en_list) {
        this.tips_en_list = tips_en_list;
    }

    @Override
    public String toString() {
        return "{" +
                "tips_list=" + Arrays.toString(tips_list) +
                '}';
    }
}
