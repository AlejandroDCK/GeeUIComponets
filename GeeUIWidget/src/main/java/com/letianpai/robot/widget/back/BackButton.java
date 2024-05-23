package com.letianpai.robot.widget.back;

import android.content.Context;
import android.util.AttributeSet;

import com.letianpai.robot.widget.R;
import com.letianpai.robot.widget.base.KeyImageButton;
import com.letianpai.robot.widget.consts.KeyConsts;


public class BackButton extends KeyImageButton {
    public BackButton(Context context) {
        super(context);
        initData();
    }
    public BackButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public BackButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public BackButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initData();
    }

    private void initData() {
        keyImageButton.setPressedImage(R.drawable.back_press);
        keyImageButton.setUnPressedImage(R.drawable.back_normal);
        keyImageButton.setImagePosition(KeyConsts.CENTER,0,0,0,0,40,40);
    }
}
