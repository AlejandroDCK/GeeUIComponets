package com.letianpai.robot.components.nodata;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.letianpai.robot.components.R;
import com.letianpai.robot.components.network.callback.AppQRCodeInfoCallback;
import com.letianpai.robot.components.network.nets.GeeUiNetManager;
import com.letianpai.robot.components.utils.QRCodeUtil;
import com.letianpai.robot.components.utils.ViewUtils;

import java.lang.ref.WeakReference;

/**
 * @author liujunbin
 */
public class NoDataQRImageView extends RelativeLayout {
    private TextView noData;
    private static final int UPDATE_STATUS = 111;
    private static final int UPDATE_QR_CODE = 112;
    private UpdateViewHandler mHandler;
    private ImageView imageQRView;
    private Bitmap qrBitmap;
    private Bitmap logoBitmap;
    private Bitmap mergedBitmap;
    private String qrcodeContent;
    private Context mContext;

    public NoDataQRImageView(Context context) {
        super(context);
        init(context);
    }

    public NoDataQRImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoDataQRImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NoDataQRImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        inflate(context, R.layout.robot_display_qr_no_data, this);
        noData = findViewById(R.id.noData);
        imageQRView = findViewById(R.id.image_qr_view);
        mHandler = new UpdateViewHandler(context);
        getQRImageInfo();
    }

    /**
     * 获取QR Image code;
     */
    private void getQRImageInfo() {
        GeeUiNetManager.getAppQrcodeInfo(mContext, true, new AppQRCodeInfoCallback.AppQRCodeInfoUpdateListener() {
            @Override
            public void onAppQRCodeInfoUpdateReceived(String qrcodeString, boolean isShowQrcode) {
                Log.e("letianpai_qrcode", "qrcodeString: " + qrcodeString);
                Log.e("letianpai_qrcode", "isShowQrcode: " + isShowQrcode);
            }
        });

    }

    public void setNoDataText(String content) {
        update(content);
    }

    public void setNoDataQRInfo(String content) {
        updateQRCode(content);
    }

    private void updateQRCode(String content) {
        Message message = new Message();
        message.what = UPDATE_STATUS;
        message.obj = content;
        mHandler.sendMessage(message);
    }

    private void update(String content) {
        Message message = new Message();
        message.what = UPDATE_QR_CODE;
        message.obj = content;
        mHandler.sendMessage(message);
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

                case UPDATE_STATUS:
                    updateBottomText(msg);
                    break;

                case UPDATE_QR_CODE:
                    updateQRCodeDisplay(msg);
                    break;
            }
        }
    }

    private void updateBottomText(Message msg) {
        if (msg.obj != null) {
            noData.setText(((String) (msg.obj)));
        }
    }

    private void updateQRCodeDisplay(Message msg) {
        if (msg.obj != null) {
            qrcodeContent = (String) (msg.obj);
            if (TextUtils.isEmpty(qrcodeContent)) {
                return;
            }
            ViewUtils.resizeImageViewSize(imageQRView, 180, 180);
            try {
                qrBitmap = QRCodeUtil.createQRCode(qrcodeContent, 200);
                logoBitmap = QRCodeUtil.getLogoBitmap(mContext, R.drawable.logo);
                mergedBitmap = QRCodeUtil.addLogo(qrBitmap, logoBitmap);
                imageQRView.setImageBitmap(mergedBitmap);
                imageQRView.setBackgroundColor(getResources().getColor(R.color.white));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}
