package com.letianpai.robot.components.view;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.letianpai.robot.components.R;
import com.letianpai.robot.components.network.nets.GeeUiNetManager;
import com.letianpai.robot.components.network.system.SystemUtil;
import com.letianpai.robot.components.parser.imagebg.ImageBgInfo;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liujunbin
 */
public class ImageBgsView extends RelativeLayout {
    private Context mContext;
    private ImageView bgImage;
    private static final int UPDATE_BACKGROUND = 110;
    private UpdateViewHandler updateViewHandler;

    public ImageBgsView(Context context) {
        super(context);
        init(context);
    }

    public ImageBgsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageBgsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ImageBgsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        inflate(context, R.layout.image_bg_view, this);
        initView();
        getImageUrl();
    }

    private void getImageUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GeeUiNetManager.getAppBgInfo(mContext, SystemUtil.isInChinese(), new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response != null && response.body() != null) {

                            String info = "";
                            if (response != null && response.body() != null) {
                                info = response.body().string();
                            }
                            ImageBgInfo imageBgInfo;
                            try {
                                if (info != null) {
                                    Log.e("letianpai_RemoteCmdResponser", "commandDistribute:command ========= 6 ======== info: " + info);
                                    imageBgInfo = new Gson().fromJson(info, ImageBgInfo.class);
                                    if (imageBgInfo != null && imageBgInfo.getData() != null && (!TextUtils.isEmpty(imageBgInfo.getData().getBg_url()))) {
                                        uploadBackground(imageBgInfo.getData().getBg_url());
                                    }
                                }
                            } catch (Exception e) {

                            }

                        }
                    }
                });
            }
        }).start();

    }

    private void initView() {
        updateViewHandler = new UpdateViewHandler(mContext.getApplicationContext());
        bgImage = findViewById(R.id.iv_bg);
    }

    private class UpdateViewHandler extends android.os.Handler {
        private final WeakReference<Context> context;

        public UpdateViewHandler(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_BACKGROUND:
//                    Glide.with(mContext)
//                            .load((String) msg.obj)
//                            .centerCrop()
//                            .into(bgImage);
                    Picasso.with(mContext)
                            .load((String) msg.obj)
                            .resize(480, 480)
                            .centerCrop()
                            .into(bgImage);
                    break;
            }
        }
    }

    private void uploadBackground(String url) {
        Message message = new Message();
        message.what = UPDATE_BACKGROUND;
        message.obj = url;
        updateViewHandler.sendMessage(message);
    }


}
