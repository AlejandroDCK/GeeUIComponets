package com.letianpai.robot.widget.button

import android.content.Context
import android.util.AttributeSet
import com.letianpai.robot.widget.R
import com.letianpai.robot.widget.abstractview.AbstractKeyButton

/**
 * 设置按钮
 * @author liujunbin
 */
class SettingsButton : AbstractKeyButton {
    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context!!, attrs, defStyleAttr, defStyleRes
    )

    override fun initData() {
//        keyButton.setText("Settings");
    }

    override fun setButtonUnPressed() {
//        keyButton.setTextColor(mContext.getColor(R.color.commit_background_color));

//        keyButton.setTextColor(mContext.getColor(R.color.white));

        keyButton!!.setTextColor(mContext!!.getColor(R.color.image_button_normal_bg))
    }

    override fun setButtonPressed() {
        keyButton!!.setTextColor(mContext!!.getColor(R.color.white))
        //        keyButton.setTextColor(mContext.getColor(R.color.image_button_normal_bg));
    }
}
