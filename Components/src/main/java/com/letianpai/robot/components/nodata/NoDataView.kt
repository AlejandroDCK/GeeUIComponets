package com.letianpai.robot.components.nodata

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.letianpai.robot.components.R
import java.lang.ref.WeakReference

/**
 * @author liujunbin
 */
class NoDataView : LinearLayout {
    private var noData: TextView? = null
    private var mHandler: UpdateViewHandler? = null

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
        inflate(context, R.layout.robot_display_no_data, this)
        noData = findViewById(R.id.noData)
        mHandler = UpdateViewHandler(context)
    }

    fun setNoDataText(content: String) {
        update(content)
    }

    private fun update(content: String) {
        val message = Message()
        message.what = UPDATE_STATUS
        message.obj = content
        mHandler!!.sendMessage(message)
    }

    private inner class UpdateViewHandler(context: Context) : Handler() {
        private val context = WeakReference(context)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                UPDATE_STATUS -> if (msg.obj != null) {
                    noData!!.text = msg.obj as String
                }
            }
        }
    }


    companion object {
        private const val UPDATE_STATUS = 111
    }
}
