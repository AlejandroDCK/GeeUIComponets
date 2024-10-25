package com.letianpai.robot.components.view

import com.letianpai.robot.components.network.nets.GeeUiNetManager
import com.letianpai.robot.components.network.system.SystemUtil
import kotlin.Throws
import com.google.gson.Gson
import android.text.TextUtils
import android.widget.TextView
import com.letianpai.robot.components.R
import android.widget.RelativeLayout
import com.letianpai.robot.components.parser.tips.TipsName
import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import com.letianpai.robot.components.parser.tips.Tips
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception
import java.util.Random

class BottomStatusBar : RelativeLayout {
    private var mContext: Context? = null
    private var bottomText: TextView? = null
    private var tipsList: Array<TipsName?>? = null
    private var mainCountDownTimer: CountDownTimer? = null
    private var random: Random? = null
    var handler: Handler = Handler(Looper.getMainLooper())

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

    private fun init(context: Context) {
        this.mContext = context
        requestTips()
        inflate(mContext, R.layout.bottom_status_bar, this)
        initView()
        initCountDownTimer()
    }

    //请求接口
    private fun requestTips() {
        Thread {
            if (SystemUtil.isInChinese()) {
                getCommandWords(true)
            } else {
                getCommandWords(false)
            }
        }.start()
    }

    private fun initCountDownTimer() {
        mainCountDownTimer = object : CountDownTimer((10 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                updateTips()
                mainCountDownTimer!!.start()
            }
        }
        mainCountDownTimer.start()
    }

    private fun updateTips() {
        if (tipsList != null && tipsList!!.size > 0) {
            random = Random()
            val index = random!!.nextInt(tipsList!!.size)
            //            GeeUILogUtils.logi("<<<<", "updateTips:"+tipsList[index].getTips_name());
            if (!TextUtils.isEmpty(tipsList!![index].getTips_name())) {
                bottomText!!.text = ""
                bottomText.setText(tipsList!![index].getTips_name())
            }
        }
    }

    private fun initView() {
        bottomText = findViewById(R.id.bottom_part)
        if (SystemUtil.isInChinese()) {
            val tipsName1 = TipsName()
            tipsName1.tips_name = "试试说“嗨，小乐”，天气"
            val tipsName2 = TipsName()
            tipsName2.tips_name = "试试说“嗨，小乐”，跳舞"
            tipsList = arrayOf(tipsName1, tipsName2)
        } else {
            val tipsName1 = TipsName()
            tipsName1.tips_name = "Try saying 'Hi,Rux', weather"
            val tipsName2 = TipsName()
            tipsName2.tips_name = "Try saying 'Hi,Rux', show dance"
            tipsList = arrayOf(tipsName1, tipsName2)
        }
        updateTips()
    }

    //---------------------请求网络
    private fun getCommandWords(isChinese: Boolean) {
        GeeUiNetManager.getTipsList(mContext, isChinese, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response?.body() != null) {
                    var tips: Tips? = null
                    var info = ""
                    if (response?.body() != null) {
                        info = response.body()!!.string()
                    }
                    //                    Log.d("<<<<", "---info::"+info);
                    try {
                        if (info != null) {
                            tips = Gson().fromJson(info, Tips::class.java)
                            if (tips != null) {
                                tipsList = if (isChinese) {
                                    tips.data.config_data.tips_list
                                } else {
                                    tips.data.config_data.tips_en_list
                                }
                                handler.post { updateTips() }
                            }
                        }
                    } catch (e: Exception) {
//                        GeeUILogUtils.logi(Log.getStackTraceString(e));
                    }
                }
            }
        })
    }

    companion object {
        private val TAG: String = BottomStatusBar::class.java.name
    }
}
