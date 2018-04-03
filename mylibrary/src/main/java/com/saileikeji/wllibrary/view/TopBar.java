package com.saileikeji.wllibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saileikeji.wllibrary.R;


/**
 * Created by lqy on 16/9/1.
 */
public class TopBar extends RelativeLayout {
    private int mLeftTextColor;
    private Drawable mLeftSrc,mLeftSrca;
    private String mLeftText;
    private int mRightTextColor;
    private Drawable mRightSrc, mCenterSrc;
    private Drawable mLayoutbg;
    private String mRightText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;
    private ImageView mIvLeft;
    private ImageView mIvRight, mIvCenter,mImgLeft;
    private TextView mTvTitle;
    private OnTopBarClickListenner onTopBarClickListenner;
    private TextView mTvLeft;
    private TextView mTvRight;


    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(final Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, getResources().getColor(R.color.lay_depthbluea));
        mLeftSrc = ta.getDrawable(R.styleable.TopBar_leftSrc);
        mLeftSrca = ta.getDrawable(R.styleable.TopBar_leftSrca);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, getResources().getColor(R.color.lay_depthbluea));
        mRightSrc = ta.getDrawable(R.styleable.TopBar_rightSrc);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 18);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTxtColor, getResources().getColor(R.color.white));
        mTitle = ta.getString(R.styleable.TopBar_title);
        mCenterSrc = ta.getDrawable(R.styleable.TopBar_centerSrc);

        mLayoutbg = ta.getDrawable(R.styleable.TopBar_layoutbg);
        ta.recycle();
        LayoutInflater.from(context).inflate(R.layout.layout_topbar, this, true);
        mIvLeft = (ImageView) findViewById(R.id.topbar_iv_left);
        mTvLeft = (TextView) findViewById(R.id.topbar_tv_left);
        mIvRight = (ImageView) findViewById(R.id.topbar_iv_right);
        mTvRight = (TextView) findViewById(R.id.topbar_tv_right);
        mIvCenter = (ImageView) findViewById(R.id.topbar_iv_center);
        mTvTitle = (TextView) findViewById(R.id.topbar_tv_title);
        mImgLeft = (ImageView) findViewById(R.id.topbar_img_left);

        if (mLeftSrc ==null)
            mIvLeft.setVisibility(GONE);
        else
            mIvLeft.setImageDrawable(mLeftSrc);
        mTvLeft.setText(mLeftText);
        mIvCenter.setImageDrawable(mCenterSrc);
        if (mLeftSrca==null){
            mImgLeft.setVisibility(GONE);
        }else {
            mImgLeft.setVisibility(VISIBLE);
            mImgLeft.setImageDrawable(mLeftSrca);
        }
        if (mRightSrc ==null)
            mIvRight.setVisibility(GONE);
        else
            mIvRight.setImageDrawable(mRightSrc);
        mTvRight.setText(mRightText);
        mTvTitle.setText(mTitle);
        mTvTitle.setTextSize(mTitleTextSize);
        mTvTitle.setTextColor(mTitleTextColor);
        if (mLayoutbg == null) {//好像是不会==null
            getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorPrimary));//
        } else
            getChildAt(0).setBackgroundDrawable(mLayoutbg);
        mImgLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTopBarClickListenner != null) {
                    onTopBarClickListenner.onRightImgClicked();
                }
            }
        });
        mIvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTopBarClickListenner != null) {
                    onTopBarClickListenner.onLeftClicked();
                }
//                else throw new RuntimeException("没有设置TopbarclickListenner");
            }
        });

        mIvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTopBarClickListenner != null) {
                    onTopBarClickListenner.onRightClicked();
                }
//                else {
//                    throw new RuntimeException("没有设置TopbarclickListenner");
//                }
            }
        });

    }

    public void setRightSrc(int resouceId) {
        if (mIvRight.getVisibility() == GONE)
            mIvRight.setVisibility(VISIBLE);
        mIvRight.setImageResource(resouceId);
    }

    public void setLefSrc(int resouceId) {
        mIvLeft.setImageResource(resouceId);
    }

    public void setRightSrcVisibility(int v) {
        mIvRight.setVisibility(v);
    }
    public void setCenterSrcVisibility(int v) {
        mIvCenter.setVisibility(v);
    }

    public void setTitle(String mTitle) {
        mTvTitle.setText(mTitle);
    }

    public void setOnTopBarClickListenner(OnTopBarClickListenner onTopBarClickListenner) {
        this.onTopBarClickListenner = onTopBarClickListenner;
    }

    public interface OnTopBarClickListenner {
        void onLeftClicked();
        void onRightClicked();
        void onRightImgClicked();
    }
    public void setRightText(String text){
        this.mTvRight.setText(text);
    }
}
