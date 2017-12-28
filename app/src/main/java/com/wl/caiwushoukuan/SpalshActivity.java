package com.wl.caiwushoukuan;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.wl.caiwushoukuan.ui.MainActivity;
import com.wl.caiwushoukuan.ui.base.BaseActivity;
import com.wl.caiwushoukuan.widgit.SPConstant;
import com.wl.wllibrary.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @describe: 闪屏页面
 * @author: 武梁
 * @date: 2017/11/22 13:35
 * @mailbox: 1034905058@qq.com
 */
 public class SpalshActivity extends BaseActivity {

    @Bind(R.id.img)
    ImageView img;
    private int LOGIN_RESULT_CODE = 100;
    private int GOOGLE_PLAY_RESULT_CODE = 200;
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private static final String TAG = SpalshActivity.class.getSimpleName();
    private String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clearNotification();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        String useridA = SharedPreferenceUtil.getSharedStringData(this, SPConstant.userId);
        Log.e("------useridA------", useridA + "---");

        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)) {
                permissionsList.add(Manifest.permission.READ_PHONE_STATE);
            }
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissionsList.size() == 0) {
                userid = SharedPreferenceUtil.getSharedStringData(this, SPConstant.userId);
                date();
            } else {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_PHONE_PERMISSIONS);
            }
        } else {
            userid = SharedPreferenceUtil.getSharedStringData(this, SPConstant.userId);
            date();

        }

    }

    private void date() {
        SharedPreferences shared=getSharedPreferences("is", MODE_PRIVATE);
        boolean isfer=shared.getBoolean("isfer", true);
        SharedPreferences.Editor editor=shared.edit();
        if(isfer){
            //第一次进入跳转
            editor.putBoolean("isfer", false).commit();
            Intent in=new Intent(SpalshActivity.this,MainActivity.class);
            startActivity(in);
            finish();
        }else{
            //第二次进入跳转
            if (TextUtils.isEmpty(userid)) {
                startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                finish();
            }else {
                startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult code:" + requestCode);
        if (LOGIN_RESULT_CODE == requestCode && resultCode == RESULT_OK) {
        } else if (LOGIN_RESULT_CODE == requestCode && resultCode == RESULT_CANCELED) {
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    userid = SharedPreferenceUtil.getSharedStringData(this, SPConstant.userId);
                    date();
                } else {
                    Toast.makeText(this, getString(R.string.need_permission), Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 清楚所有通知栏通知
     */
    private void clearNotification() {
        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }


}
