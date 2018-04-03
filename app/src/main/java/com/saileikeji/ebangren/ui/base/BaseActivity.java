package com.saileikeji.ebangren.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.saileikeji.ebangren.annotation.FullScreen;
import com.saileikeji.ebangren.annotation.TranslucentStatusBar;
import com.saileikeji.ebangren.widgit.StatusBarUtil;
import com.saileikeji.ebangren.widgit.UIUtils;
import com.saileikeji.wllibrary.util.ActivityTool;
import com.saileikeji.wllibrary.util.DensityUtil;
import com.saileikeji.wllibrary.view.LoadMoreView;
import com.saileikeji.wllibrary.view.loadingview.AVLoadingIndicatorView;
import com.saileikeji.wllibrary.view.loadingview.LoadingDialog;

/**
 * @describe: BaseActivity
 * @author: 武梁
 * @date: 2017/11/9 09:50
 * @mailbox: 1034905058@qq.com
 */

public class BaseActivity extends AppCompatActivity {
    private int screenWidth;
    private int screenHeight;
//    public ApiManager apiManager;
    public LoadingDialog mLoadingDialog;
    public AVLoadingIndicatorView loadingView;
    public LoadMoreView loadMoreView;
    public String accessToken = "";
    public String userId = "";
    private View mStatusBar;
    protected String TAG = getClass().getSimpleName();
    protected Context context;
    public String userTel = "";
    private View contentView;
    boolean isAdmin, isCreator, isAdminOrCreator;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {// FIXME: 17/10/14 第一次进入状态栏会闪白
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;
        ActivityTool.addActivity(this);
        mLoadingDialog = new LoadingDialog(this);
        screenWidth = DensityUtil.getScreenWidth(this);
        screenHeight = DensityUtil.getScreenHeight(this);
        loadingView = new AVLoadingIndicatorView(this);
        loadMoreView = new LoadMoreView(this);

    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
    /**
     * 处理全屏
     */
    private void setFullScreen() {
        boolean isFullScreen = getClass().isAnnotationPresent(FullScreen.class);
        if (isFullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
    /**
     * 处理透明状态栏
     */
    private void setStatusBarTranslucent() {
        boolean isFullScreen = getClass().isAnnotationPresent(FullScreen.class);
        boolean isTranslucent = getClass().isAnnotationPresent(TranslucentStatusBar.class);
        boolean isArrowTranslucent = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (!isFullScreen && isTranslucent && isArrowTranslucent) {
            StatusBarUtil.initBar(this);
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.VERTICAL);
            mStatusBar = new View(this);
            layout.addView(mStatusBar, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mStatusBar
                    .getLayoutParams();
            int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
            params.height = statusBarHeight == 0 ? DensityUtil.dip2px(this,
                    25) : statusBarHeight;
            mStatusBar.setLayoutParams(params);
            TranslucentStatusBar translucentStatusBar = BaseActivity.this
                    .getClass().getAnnotation(TranslucentStatusBar.class);
            if (translucentStatusBar.color() != -1) {
                mStatusBar.setBackgroundResource(translucentStatusBar.color());
            } else if (translucentStatusBar.sColor()!=null) {
                mStatusBar.setBackgroundColor(Color
                        .parseColor(translucentStatusBar.sColor()));
            } else {
                mStatusBar.setBackgroundColor(Color.BLACK);
            }
            View v =  ((ViewGroup)this.findViewById(android.R.id.content)).getChildAt(0);
            layout.addView(v, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            layout.setBackgroundDrawable(v.getBackground());
            contentView = layout;
        }
    }
    //设置状态栏颜色
    public void setWindowStatusBarColor(int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void hideKeyboard(BaseActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void toast(String str) {
        UIUtils.showToastSafely(str);
    }
    @Override
    public void setContentView(int resId){
        super.setContentView(resId);
        contentView =  ((ViewGroup)this.findViewById(android.R.id.content)).getChildAt(0);
        setFullScreen();
        setStatusBarTranslucent();
    };

    public View getContentView() {
        return contentView;
    }
}
