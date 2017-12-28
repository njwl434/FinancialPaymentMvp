package com.wl.caiwushoukuan;

import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;

import com.hss01248.dialog.StyledDialog;
import com.wl.wllibrary.Tool;

/**
 * @describe: WlApplication
 * @author: 武梁
 * @date: 2017/11/9 09:57
 * @mailbox: 1034905058@qq.com
 */

public class WlApplication extends MultiDexApplication {

    public static Context context;
    private static WlApplication application;
    private static Handler mHandler;//主线程Handler
    private static final String TAG = "WlApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = getApplicationContext();
        mHandler = new Handler();
        Tool.init(context);
        StyledDialog.init(this);
    }
    public static Context getContext() {
        return context;
    }
    public static Handler getMainHandler() {
        return mHandler;
    }
    public static WlApplication getInstance() {
        return application;
    }

}
