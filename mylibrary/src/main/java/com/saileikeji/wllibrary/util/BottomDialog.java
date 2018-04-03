package com.saileikeji.wllibrary.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.saileikeji.wllibrary.R;


/**
 *
 * @author lqy
 * @date 2016/1/26
 */
public class BottomDialog extends Dialog{
    private LinearLayout mLayoutContainer;
    private boolean isEdit = true;

    public BottomDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }
    public BottomDialog(Context context, boolean isEdit) {
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
        dialogWindow.setGravity(Gravity.BOTTOM);
//		dialogWindow.clearFlags(
//				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        if (isEdit) {
            dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.dimAmount = 0.7f;
//        p.height = (int) (d.getHeight() * 0.4);
//        p.y = (int) (d.getHeight() * 0.2);
        p.width = d.getWidth() * 1;
        dialogWindow.setAttributes(p);
    }
}
