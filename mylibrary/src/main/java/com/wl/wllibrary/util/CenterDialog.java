package com.wl.wllibrary.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wl.wllibrary.R;


/**
 * Created by lqy on 2016/1/26.
 */
public class CenterDialog extends Dialog{
    private LinearLayout mLayoutContainer;
    private boolean isEdit =false;

    public CenterDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }
    public CenterDialog(Context context, boolean isEdit) {
        super(context, R.style.MyDialogStyle);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.isEdit = isEdit;
        // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }
    public void addView(View v) {
        mLayoutContainer.addView(v,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        super.show();
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//		dialogWindow.clearFlags(
//				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        if (isEdit) {
            dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.dimAmount = 0.3f;
//        p.height = (int) (d.getHeight() * 0.4);
//        p.y = (int) (d.getHeight() * 0.2);
        p.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
    }
}
