package com.letianpai.robot.components.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.letianpai.robot.components.R;
import com.letianpai.robot.components.network.nets.GeeUiNetManager;
import com.letianpai.robot.components.network.system.SystemUtil;
import com.letianpai.robot.components.parser.tips.Tips;
import com.letianpai.robot.components.parser.tips.TipsName;
import com.letianpai.robot.components.utils.GeeUILogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.Random;


public class BottomStatusBar extends RelativeLayout {

    private static  String TAG = BottomStatusBar.class.getName();
    private Context mContext;
    private TextView bottomText;
    private TipsName[] tipsList = null;
    private CountDownTimer mainCountDownTimer;
    private Random random;
    Handler handler = new Handler(Looper.getMainLooper());

    public BottomStatusBar(Context context) {
        super(context);
        init(context);
    }

    public BottomStatusBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomStatusBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        requestTips();
        inflate(mContext, R.layout.bottom_status_bar, this);
        initView();
        initCountDownTimer();
    }

    //请求接口
    private void requestTips(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SystemUtil.isInChinese()){
                    getCommandWords(true);
                }else{
                    getCommandWords(false);
                }
            }
        }).start();
    }

    private void initCountDownTimer() {

        mainCountDownTimer = new CountDownTimer(10 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                updateTips();
                mainCountDownTimer.start();
            }
        };
        mainCountDownTimer.start();
    }

    private void updateTips() {
        if (tipsList != null && tipsList.length > 0) {
            random = new Random();
            int index = random.nextInt(tipsList.length);
//            GeeUILogUtils.logi("<<<<", "updateTips:"+tipsList[index].getTips_name());
            if (!TextUtils.isEmpty(tipsList[index].getTips_name())) {
                bottomText.setText("");
                bottomText.setText(tipsList[index].getTips_name());
            }
        }
    }

    private void initView() {
        bottomText = findViewById(R.id.bottom_part);
        if (SystemUtil.isInChinese()){
            TipsName tipsName1 = new TipsName();
            tipsName1.setTips_name("试试说“嗨，小乐”，天气");
            TipsName tipsName2 = new TipsName();
            tipsName2.setTips_name("试试说“嗨，小乐”，跳舞");
            tipsList = new TipsName[]{tipsName1, tipsName2};
        }else{
            TipsName tipsName1 = new TipsName();
            tipsName1.setTips_name("Try saying 'Hi,Rux', weather");
            TipsName tipsName2 = new TipsName();
            tipsName2.setTips_name("Try saying 'Hi,Rux', show dance");
            tipsList = new TipsName[]{tipsName1, tipsName2};
        }
        updateTips();
    }

    //---------------------请求网络
    private void getCommandWords(boolean isChinese) {
        GeeUiNetManager.getTipsList(mContext, isChinese, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response != null && response.body() != null) {
                    Tips tips = null;
                    String info = "";
                    if (response != null && response.body() != null) {
                        info = response.body().string();
                    }
//                    Log.d("<<<<", "---info::"+info);
                    try {
                        if (info != null) {
                            tips = new Gson().fromJson(info, Tips.class);
                            if (tips != null) {
                                if (isChinese){
                                    tipsList = tips.getData().getConfig_data().getTips_list();
                                }else{
                                    tipsList = tips.getData().getConfig_data().getTips_en_list();
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateTips();
                                    }
                                });
                            }
                        }
                    }catch (Exception e){
//                        GeeUILogUtils.logi(Log.getStackTraceString(e));
                    }
                }
            }
        });
    }
}
