package com.wl.wllibrary.util;

import android.os.CountDownTimer;

/**
 * @describe: TimeCount 倒计时
 * @author: 武梁
 * @date: 2017/11/28 15:49
 * @mailbox: 1034905058@qq.com
 */

public abstract class TimeCount extends CountDownTimer {

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
//            btnGetcode.setBackgroundColor(Color.parseColor("#B6B6D8"));
//        btnGetcode.setClickable(false);
//        btnGetcode.setText((millisUntilFinished / 1000) + "");
        onTickProcess(millisUntilFinished);

    }

    @Override
    public void onFinish() {
//        btnGetcode.setText("重新获取验证码");
//        btnGetcode.setClickable(true);
        onTimeFinish();
    }

    public abstract void onTickProcess(long millisUntilFinished);
    public abstract void onTimeFinish();
}