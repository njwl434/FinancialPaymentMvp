package com.saileikeji.ebangren.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.ui.base.BaseActivity;
import com.saileikeji.wllibrary.view.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/4/8 11:34
 * @mailbox: 1034905058@qq.com
 */

public class RegisterActivity extends BaseActivity implements TopBar.OnTopBarClickListenner{

    @Bind(R.id.mTopBar)
    TopBar mTopBar;
    @Bind(R.id.a_et_phone)
    EditText aEtPhone;
    @Bind(R.id.a_et_verification)
    EditText aEtVerification;
    @Bind(R.id.a_bt_verification)
    Button aBtVerification;
    @Bind(R.id.a_tv_wjpassword)
    TextView aTvWjpassword;
    @Bind(R.id.a_bt_login)
    Button aBtLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        ButterKnife.bind(this);
        mTopBar.setOnTopBarClickListenner(this);
    }

    @OnClick({R.id.a_bt_verification, R.id.a_bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_bt_verification:
                break;
            case R.id.a_bt_login:
                break;
                default:
        }
    }

    @Override
    public void onLeftClicked() {
        onBackPressed();
    }

    @Override
    public void onRightClicked() {

    }

    @Override
    public void onRightImgClicked() {

    }
}
