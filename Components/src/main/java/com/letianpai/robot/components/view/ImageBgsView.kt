package com.letianpai.robot.components.view

import com.letianpai.robot.components.network.nets.GeeUiNetManager
import com.letianpai.robot.components.network.system.SystemUtil
import kotlin.Throws
import com.google.gson.Gson
import android.text.TextUtils
import com.letianpai.robot.components.R
import android.widget.RelativeLayout
import android.content.Context
import com.letianpai.robot.components.parser.imagebg.ImageBgInfo
import com.squareup.picasso.Picasso
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception
import java.lang.ref.WeakReference

/**
 * @author liujunbin
 */
class ImageBgsView : RelativeLayout {
    private var mContext: Context? = null
    private var bgImage: ImageView? = null
    private var updateViewHandler: UpdateViewHandler? = null

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
        inflate(context, R.layout.image_bg_view, this)
        initView()
        imageUrl
    }

    private val imageUrl: Unit
        get() {
            Thread {
                GeeUiNetManager.getAppBgInfo(mContext, SystemUtil.isInChinese(), object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    @Throws(IOException::class)
                    override fun onResponse(call: Call, response: Response) {
                        if (response?.body() != null) {
                            var info = ""
                            if (response?.body() != null) {
                                info = response.body()!!.string()
                            }
                            val imageBgInfo: ImageBgInfo?
                            try {
                                if (info != null) {
                                    Log.e(
                                        "letianpai_RemoteCmdResponser",
                                        "commandDistribute:command ========= 6 ======== info: $info"
                                    )
                                    imageBgInfo = Gson().fromJson(info, ImageBgInfo::class.java)
                                    if (imageBgInfo != null && imageBgInfo.data != null && (!TextUtils.isEmpty(
                                            imageBgInfo.data.bg_url
                                        ))
                                    ) {
                                        uploadBackground(imageBgInfo.data.bg_url)
                                    }
                                }
                            } catch (e: Exception) {
                            }
                        }
                    }
                })
            }.start()
        }

    private fun initView() {
        updateViewHandler = UpdateViewHandler(
            mContext!!.applicationContext
        )
        bgImage = findViewById(R.id.iv_bg)
    }

    private inner class UpdateViewHandler(context: Context) : Handler() {
        private val context = WeakReference(context)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                UPDATE_BACKGROUND -> //                    Glide.with(mContext)
//                            .load((String) msg.obj)
//                            .centerCrop()
//                            .into(bgImage);
                    Picasso.with(mContext)
                        .load(msg.obj as String)
                        .resize(480, 480)
                        .centerCrop()
                        .into(bgImage)
            }
        }
    }

    private fun uploadBackground(url: String?) {
        val message = Message()
        message.what = UPDATE_BACKGROUND
        message.obj = url
        updateViewHandler!!.sendMessage(message)
    }


    companion object {
        private const val UPDATE_BACKGROUND = 110
    }
}
