package com.letianpai.robot.components.titletip

import android.widget.TextView
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet

@SuppressLint("AppCompatCustomView")
class TitleTip : TextView {
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
