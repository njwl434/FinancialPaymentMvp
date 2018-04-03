package com.wl.caiwushoukuan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;

import com.hss01248.dialog.StyledDialog;
import com.wl.caiwushoukuan.dao.DaoMaster;
import com.wl.caiwushoukuan.dao.DaoSession;
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
    static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = getApplicationContext();
        mHandler = new Handler();
        Tool.init(context);
        StyledDialog.init(this);
        //配置数据库
        setupDatabase();
    }
    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
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
