package com.letianpai.robot.widget.base;

import android.content.Context;
import android.util.AttributeSet;

import com.letianpai.robot.widget.R;
import com.letianpai.robot.widget.abstractview.AbstractKeyImageButton;

/**
 * @author liujunbin
 */
public class KeyImageButton extends AbstractKeyImageButton {
    public KeyImageButton(Context context) {
        super(context);
    }

    public KeyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KeyImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setButtonPressed() {
        rlButtonRoot.setBackgroundColor(mContext.getResources().getColor(R.color.image_button_pressed_bg));
        buttonImage.setImageResource(pressedImage);
    }

    @Override
    public void setButtonUnPressed() {
        rlButtonRoot.setBackgroundColor(mContext.getResources().getColor(R.color.image_button_normal_bg));
        buttonImage.setImageResource(unPressedImage);

    }
}
