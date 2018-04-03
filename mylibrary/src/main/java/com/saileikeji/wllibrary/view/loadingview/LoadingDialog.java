package com.saileikeji.wllibrary.view.loadingview;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.saileikeji.wllibrary.R;

/**
 * Created by lqy on 16/9/19.
 */
public class LoadingDialog extends Dialog {
    private AVLoadingIndicatorView loadingIndicatorView;
    public LoadingDialog(Context context) {
        super(context,R.style.MyDialogStyle);
        init();
    }


    public void init() {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.layout_loading);
        Window mWindow = getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.dimAmount =0f;
        loadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.indicator);
        loadingIndicatorView.setIndicator("BallSpinFadeLoaderIndicator");
    }

}
