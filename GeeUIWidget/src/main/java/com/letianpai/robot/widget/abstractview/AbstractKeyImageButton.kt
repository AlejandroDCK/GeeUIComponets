package com.letianpai.robot.widget.abstractview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.RelativeLayout
import com.letianpai.robot.widget.R
import com.letianpai.robot.widget.consts.KeyConst

/**
 * @author liujunbin
 */
abstract class AbstractKeyImageButton : RelativeLayout {
    @JvmField
    protected var keyImageButton: AbstractKeyImageButton? = null
    @JvmField
    protected var mContext: Context? = null
    @JvmField
    protected var buttonImage: ImageView? = null
    @JvmField
    protected var rlButtonRoot: RelativeLayout? = null
    @JvmField
    protected var pressedImage: Int = 0
    @JvmField
    protected var unPressedImage: Int = 0
    protected var isEnable: Boolean = true

    constructor(context: Context) : super(context) {
        init(context)
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

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }


    private fun init(context: Context) {
        this.mContext = context
        keyImageButton = this@AbstractKeyImageButton
        inflate(mContext, R.layout.key_image_button, this)
        initView()
        setButtonUnPressed()
    }


    private fun initView() {
        rlButtonRoot = findViewById(R.id.rl_button_root)
        buttonImage = findViewById(R.id.button_image)
        keyImageButton!!.setOnTouchListener(OnTouchListener { v, event ->
            if (!isEnable) {
                return@OnTouchListener false
            }
            if (event.action == MotionEvent.ACTION_UP) {
                setButtonUnPressed()
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                setButtonUnPressed()
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                setButtonPressed()
            }
            false
        })
    }

    override fun setOnClickListener(l: OnClickListener?) {
        if (isEnable) {
            super.setOnClickListener(l)
        }
    }

    fun setPressedImage(pressedImage: Int) {
        this.pressedImage = pressedImage
    }

    fun setUnPressedImage(unPressedImage: Int) {
        if (this.unPressedImage == 0) {
            this.unPressedImage = unPressedImage
            setButtonUnPressed()
        } else {
            this.unPressedImage = unPressedImage
        }
    }

    abstract fun setButtonPressed()

    //    private void setButtonPressed() {
    //        rlButtonRoot.setBackgroundColor(mContext.getResources().getColor(R.color.keyboard_highlight));
    //        buttonImage.setImageResource(pressedImage);
    //    }
    abstract fun setButtonUnPressed()

    //    private void setButtonUnPressed() {
    //        rlButtonRoot.setBackgroundColor(mContext.getResources().getColor(R.color.image_keyboard_normal));
    //        buttonImage.setImageResource(unPressedImage);
    //
    //    }
    fun setImagePosition(
        position: Int,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        imageWidth: Int,
        imageHeight: Int
    ) {
        val params = LayoutParams(imageWidth, imageHeight)
        //        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (position == KeyConst.LEFT) {
            params.addRule(ALIGN_PARENT_LEFT)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.TOP) {
            params.addRule(ALIGN_PARENT_TOP)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.RIGHT) {
            params.addRule(ALIGN_PARENT_RIGHT)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.BOTTOM) {
            params.addRule(ALIGN_PARENT_BOTTOM)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.LEFT_TOP) {
            params.addRule(ALIGN_PARENT_LEFT)
            params.addRule(ALIGN_PARENT_TOP)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.RIGHT_TOP) {
            params.addRule(ALIGN_PARENT_RIGHT)
            params.addRule(ALIGN_PARENT_TOP)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.LEFT_BOTTOM) {
            params.addRule(ALIGN_PARENT_LEFT)
            params.addRule(ALIGN_PARENT_BOTTOM)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.RIGHT_BOTTOM) {
            params.addRule(ALIGN_PARENT_RIGHT)
            params.addRule(ALIGN_PARENT_BOTTOM)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.CENTER) {
            params.addRule(CENTER_IN_PARENT)
        } else if (position == KeyConst.CENTER_VERTICAL) {
            params.addRule(CENTER_VERTICAL)
            params.setMargins(left, top, right, bottom)
        } else if (position == KeyConst.CENTER_HORIZONTAL) {
            params.addRule(CENTER_HORIZONTAL)
            params.setMargins(left, top, right, bottom)
        }

        //        params.setMargins(left, top, right, bottom);
        buttonImage!!.layoutParams = params
    }


    fun setEnable(enable: Boolean) {
        isEnable = enable
    }
}
