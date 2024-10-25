package com.letianpai.robot.components.nodata

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.letianpai.robot.components.R
import com.letianpai.robot.components.network.nets.GeeUiNetManager
import com.letianpai.robot.components.utils.QRCodeUtil
import com.letianpai.robot.components.utils.ViewUtils
import java.lang.ref.WeakReference

/**
 * @author liujunbin
 */
class NoDataQRImageView : RelativeLayout {
    private var noData: TextView? = null
    private var mHandler: UpdateViewHandler? = null
    private var imageQRView: ImageView? = null
    private var qrBitmap: Bitmap? = null
    private var logoBitmap: Bitmap? = null
    private var mergedBitmap: Bitmap? = null
    private var qrcodeContent: String? = null
    private var mContext: Context? = null

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
        inflate(context, R.layout.robot_display_qr_no_data, this)
        noData = findViewById(R.id.noData)
        imageQRView = findViewById(R.id.image_qr_view)
        mHandler = UpdateViewHandler(context)
        qRImageInfo
    }

    private val qRImageInfo: Unit
        /**
         * 获取QR Image code;
         */
        get() {
            GeeUiNetManager.getAppQrcodeInfo(mContext!!, true) { qrcodeString: String, isShowQrcode: Boolean ->
                Log.e("letianpai_qrcode", "qrcodeString: $qrcodeString")
                Log.e("letianpai_qrcode", "isShowQrcode: $isShowQrcode")
            }

        }

    fun setNoDataText(content: String) {
        update(content)
    }

    fun setNoDataQRInfo(content: String) {
        updateQRCode(content)
    }

    private fun updateQRCode(content: String) {
        val message = Message()
        message.what = UPDATE_STATUS
        message.obj = content
        mHandler!!.sendMessage(message)
    }

    private fun update(content: String) {
        val message = Message()
        message.what = UPDATE_QR_CODE
        message.obj = content
        mHandler!!.sendMessage(message)
    }

    private inner class UpdateViewHandler(context: Context) : Handler() {
        private val context = WeakReference(context)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                UPDATE_STATUS -> updateBottomText(msg)
                UPDATE_QR_CODE -> updateQRCodeDisplay(msg)
            }
        }
    }

    private fun updateBottomText(msg: Message) {
        if (msg.obj != null) {
            noData!!.text = msg.obj as String
        }
    }

    private fun updateQRCodeDisplay(msg: Message) {
        if (msg.obj != null) {
            qrcodeContent = msg.obj as String
            if (TextUtils.isEmpty(qrcodeContent)) {
                return
            }
            ViewUtils.resizeImageViewSize(imageQRView!!, 180, 180)
            try {
                qrBitmap = QRCodeUtil.createQRCode(qrcodeContent, 200)
                logoBitmap = QRCodeUtil.getLogoBitmap(mContext!!, R.drawable.logo)
                mergedBitmap = QRCodeUtil.addLogo(qrBitmap, logoBitmap)
                imageQRView!!.setImageBitmap(mergedBitmap)
                imageQRView!!.setBackgroundColor(resources.getColor(R.color.white))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    companion object {
        private const val UPDATE_STATUS = 111
        private const val UPDATE_QR_CODE = 112
    }
}
