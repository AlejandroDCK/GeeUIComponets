package com.letianpai.robot.widget.abstractview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button

/**
 * @author liujunbin
 */
abstract class AbstractKeyButton : Button {
    @JvmField
    protected var keyButton: AbstractKeyButton? = null
    @JvmField
    protected var mContext: Context? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        this.mContext = context
        keyButton = this@AbstractKeyButton
        initData()
        setButtonUnPressed()
        addKeyButtonListeners()
    }

    protected abstract fun initData()

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

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }


    private fun addKeyButtonListeners() {
        keyButton!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                setButtonUnPressed()
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                setButtonUnPressed()
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                setButtonPressed()
            }
            false
        }
    }

    abstract fun setButtonUnPressed()

    //    {
    //        keyButton.setBackgroundColor(mContext.getResources().getColor(R.color.background));
    //        keyButton.setTextColor(mContext.getColor(R.color.keyboard_text_color));
    //    }
    abstract fun setButtonPressed() //    {
    //        keyButton.setBackgroundColor(mContext.getResources().getColor(R.color.keyboard_highlight));
    //        keyButton.setTextColor(mContext.getColor(R.color.white));
    //
    //    }
}
