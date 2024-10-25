package com.letianpai.robot.widget.button

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

/**
 * @author liujunbin
 */
open class KeyButton : AppCompatButton {
    private var keyButton: KeyButton? = null
    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        this.mContext = context
        keyButton = this@KeyButton
        setButtonUnPressed()
        addKeyButtonListeners()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }


    private fun addKeyButtonListeners() {
        keyButton!!.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    setButtonUnPressed()
                    v.performClick()
                }
                MotionEvent.ACTION_CANCEL -> {
                    setButtonUnPressed()
                }
                MotionEvent.ACTION_DOWN -> {
                    setButtonPressed()
                }
            }
            false
        }
    }


    private fun setButtonUnPressed() {
//        keyButton.setBackgroundColor(mContext.getResources().getColor(R.color.background));
//        keyButton.setTextColor(mContext.getColor(R.color.keyboard_text_color));
//        keyButton.setBackgroundColor(mContext.getResources().getColor(R.color.white60));
//        keyButton.setTextColor(mContext.getColor(R.color.keyboard_text_color));
    }

    private fun setButtonPressed() {
//        keyButton.setBackgroundColor(mContext.getResources().getColor(R.color.keyboard_highlight));
//        keyButton.setTextColor(mContext.getColor(R.color.white));

//        keyButton.setBackgroundColor(mContext.getResources().getColor(R.color.white60));
//        keyButton.setTextColor(mContext.getColor(R.color.keyboard_text_color));
    }
}
