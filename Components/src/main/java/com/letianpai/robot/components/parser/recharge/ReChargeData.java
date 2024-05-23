package com.letianpai.robot.components.parser.recharge;

/**
 * 回充数据
 */
public class ReChargeData {

    private int automatic_recharge_switch;
    private int automatic_recharge_val;

    public int getAutomatic_recharge_switch() {
        return automatic_recharge_switch;
    }

    public void setAutomatic_recharge_switch(int automatic_recharge_switch) {
        this.automatic_recharge_switch = automatic_recharge_switch;
    }

    public int getAutomatic_recharge_val() {
        return automatic_recharge_val;
    }

    public void setAutomatic_recharge_val(int automatic_recharge_val) {
        this.automatic_recharge_val = automatic_recharge_val;
    }

    @Override
    public String toString() {
        return "{" +
                "automatic_recharge_switch:" + automatic_recharge_switch +
                ", automatic_recharge_val:" + automatic_recharge_val +
                '}';
    }
}
