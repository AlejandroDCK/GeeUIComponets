package com.letianpai.robot.components.titletip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class TitleTip extends TextView {

    public TitleTip(Context context) {
        super(context);

    }

    public TitleTip(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleTip(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
