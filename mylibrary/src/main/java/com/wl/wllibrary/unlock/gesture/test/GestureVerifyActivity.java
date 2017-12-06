package com.wl.wllibrary.unlock.gesture.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wl.wllibrary.R;
import com.wl.wllibrary.unlock.gesture.widget.GestureContentView;
import com.wl.wllibrary.unlock.gesture.widget.GestureDrawline;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


/**
 *
 * 手势绘制/校验界面
 *
 */
public class GestureVerifyActivity extends Activity implements android.view.View.OnClickListener {
	/** 手机号码*/
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** 意图 */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	private RelativeLayout mTopLayout;
	private TextView mTextTitle;
	private TextView mTextCancel;
	private TextView mTextPhoneNumber;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextForget;
	private TextView mTextOther;
	private String mParamPhoneNumber;
	private long mExitTime = 0;
	private int mParamIntentCode;
	private String type;
    private String pwd="123";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_verify);
		Intent in=getIntent();
		//deblocking
		type=in.getStringExtra("type");
////        String phone=ToSharedpreference.getphoneSharedPrefernces(GestureVerifyActivity.this);
//		String one=phone.substring(0,3);
//		String tow=phone.substring(8,11);
		ObtainExtraData();
		setUpViews();
		setUpListeners();
//		Glide
//				.with(this)
//				.load(ToSharedpreference.getUrl(this) + ToSharedpreference.getHeadurl(this))
//				.skipMemoryCache(true)
//				.diskCacheStrategy(DiskCacheStrategy.NONE)
//				.into(mImgUserLogo);

//		mTextPhoneNumber.setText(one+"****"+tow);
	}

	private void ObtainExtraData() {
		mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
		mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
	}

	private void setUpViews() {
		mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
		mTextTitle = (TextView) findViewById(R.id.text_title);
		mTextCancel = (TextView) findViewById(R.id.text_cancel);
//		mImgUserLogo = (RoundedImageView) findViewById(R.id.user_logo);
		mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
		mTextOther = (TextView) findViewById(R.id.text_other_account);
//		String pwd=ToSharedpreference.getGesturePwd(GestureVerifyActivity.this);

		// 初始化一个显示各个点的viewGroup
		mGestureContentView = new GestureContentView(this, true,pwd,
				new GestureDrawline.GestureCallBack() {

					@Override
					public void onGestureCodeInput(String inputCode) {

					}

					@Override
					public void checkedSuccess() {
						mGestureContentView.clearDrawlineState(0L);
						Toast.makeText(GestureVerifyActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
//						L.e("-------type-----",type);
//						if (type.equals("deblocking")) {
//							AppManager.getAppManager().finishActivity(TabmainActivity.class);
//							Intent in=new Intent(GestureVerifyActivity.this,TabmainActivity.class);
//							in.putExtra("message","1");
//							in.putExtra("version","1");
//							in.putExtra("url","1");
//							startActivity(in);
//							GestureVerifyActivity.this.finish();
//						}else {
//							L.e("-------jinr-----",type);
//							setResult(1,getIntent());//定义返回的参数parama
//							finish();
////							Intent in=new Intent(GestureVerifyActivity.this,MeGestureActivity.class);
////							in.putExtra(type,"true");
////                            setResult(1,in);
//						}

					}

					@Override
					public void checkedFail() {
						mGestureContentView.clearDrawlineState(1300L);
						mTextTip.setVisibility(View.VISIBLE);
						mTextTip.setText(Html
								.fromHtml("<font color='#c70c1e'>密码错误</font>"));
						// 左右移动动画
						Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
					}
				});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
	}

	private void setUpListeners() {
		mTextCancel.setOnClickListener(this);
		mTextForget.setOnClickListener(this);
		mTextOther.setOnClickListener(this);
	}

	private String getProtectedMobile(String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(phoneNumber.subSequence(0,3));
		builder.append("****");
		builder.append(phoneNumber.subSequence(7,11));
		return builder.toString();
	}

	@Override
	public void onClick(View view) {

	}


//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//			case R.id.text_cancel:
//				this.finish();
//				break;
//			case R.id.text_forget_gesture:
//				ToastUtils.showShort(this,"该功能正在开发中~");
//                break;
//			case R.id.text_other_account:
//				Intent in=new Intent(this, LoginActivity.class);
//				startActivity(in);
//				finish();
//				break;
//			default:
//				break;
//		}
//	}

}
