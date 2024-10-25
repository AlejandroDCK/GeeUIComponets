package com.letianpai.robot.widget.base

import android.content.Context
import android.util.AttributeSet
import com.letianpai.robot.widget.R
import com.letianpai.robot.widget.abstractview.AbstractKeyImageButton

/**
 * @author liujunbin
 */
class KeypadImageButton : AbstractKeyImageButton {
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

    override fun setButtonPressed() {
        rlButtonRoot!!.setBackgroundColor(mContext!!.resources.getColor(R.color.image_button_normal_bg))
        buttonImage!!.setImageResource(unPressedImage)
    }

    override fun setButtonUnPressed() {
        rlButtonRoot!!.setBackgroundColor(mContext!!.resources.getColor(R.color.image_button_pressed_bg))
        buttonImage!!.setImageResource(pressedImage)
    }
}
