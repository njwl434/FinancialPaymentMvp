package com.wl.wllibrary.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wl.wllibrary.R;

/**
 * Created by lqy on 17/7/25.
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 */
public abstract class ShareDialog extends BottomDialog {

    private LinearLayout dialogShareWechat;
    private LinearLayout dialogShareCircle;


    public ShareDialog(Context context, boolean isEdit) {
        super(context, isEdit);
        isEdit = false;
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_share, null, false);
        setContentView(v);
        dialogShareWechat = (LinearLayout) v.findViewById(R.id.dialog_share_wechat);
        dialogShareCircle = (LinearLayout) v.findViewById(R.id.dialog_share_circle);
        dialogShareCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareCircleClicked();
            }
        });
        dialogShareWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWechatClicked();
            }
        });
    }
     public abstract void onShareCircleClicked();
     public abstract void onWechatClicked();

}

