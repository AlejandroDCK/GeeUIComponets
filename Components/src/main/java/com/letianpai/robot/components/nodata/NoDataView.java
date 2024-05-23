package com.letianpai.robot.components.nodata;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.letianpai.robot.components.R;

import java.lang.ref.WeakReference;

/**
 * @author liujunbin
 */
public class NoDataView extends LinearLayout {
    private TextView noData;
    private static final int UPDATE_STATUS = 111;
    private UpdateViewHandler mHandler;

    public NoDataView(Context context) {
        super(context);
        init(context);
    }

    public NoDataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NoDataView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        inflate(context, R.layout.robot_display_no_data,this);
        noData = findViewById(R.id.noData);
        mHandler = new UpdateViewHandler(context);
    }

    public void setNoDataText(String content) {
        update(content);
    }
    private void update(String content) {
        Message message = new Message();
        message.what = UPDATE_STATUS;
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
                    if (msg.obj != null){
                        noData.setText(((String)(msg.obj)));
                    }
                    break;
            }
        }
    }


}
