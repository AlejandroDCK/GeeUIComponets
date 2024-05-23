package com.letianpai.robot.widget.button;

import android.content.Context;
import android.util.AttributeSet;

import com.letianpai.robot.widget.R;
import com.letianpai.robot.widget.abstractview.AbstractKeyButton;


/**
 * 设置按钮
 * @author liujunbin
 */
public class SettingsButton extends AbstractKeyButton {

    public SettingsButton(Context context) {
        super(context);
    }

    public SettingsButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SettingsButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SettingsButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initData() {
//        keyButton.setText("Settings");
    }

    @Override
    public void setButtonUnPressed() {
//        keyButton.setTextColor(mContext.getColor(R.color.commit_background_color));

//        keyButton.setTextColor(mContext.getColor(R.color.white));
        keyButton.setTextColor(mContext.getColor(R.color.image_button_normal_bg));
    }

    @Override
    public void setButtonPressed() {
        keyButton.setTextColor(mContext.getColor(R.color.white));
//        keyButton.setTextColor(mContext.getColor(R.color.image_button_normal_bg));
    }

}
