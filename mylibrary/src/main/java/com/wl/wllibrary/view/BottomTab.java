package com.wl.wllibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wl.wllibrary.R;


/**
 * 底部tab控件
 *
 * @author lqy
 * @time 2016/4/19 10:24
 */
public class BottomTab extends LinearLayout {
    private int mCurIndex = -1;
    private int mLastIndex = -1;
    private LinearLayout layout;
    private OnTabSelectedListenner onTabSelectedListenner;
    public boolean isForced;
    private int[] needTestify = new int[]{};

    public BottomTab(Context context) {
        this(context, null);
    }

    public BottomTab(Context context, AttributeSet attrs) {
        this(context,attrs, 0);
    }

    public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.BottomTab,
                defStyleAttr, 0);
        int layoutId = attributes.getResourceId(R.styleable.BottomTab_contentlayout,0);
        init(context,layoutId);
    }

    private void init(Context context,int layoutId) {


        LayoutInflater.from(context).inflate(layoutId, this, true);
//        LayoutInflater.from(context).inflate(R.layout.layout_tab,this,true);
        LinearLayout parentLayout = (LinearLayout)getChildAt(0);
        layout = (LinearLayout) parentLayout.getChildAt(1);
        onTabClick(0);
        for (int i = 0; i < layout.getChildCount(); i++) {
             RelativeLayout tabLayout = (RelativeLayout) layout.getChildAt(i);

            final int finalI = i;
            tabLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    isForced  = false;
                    onTabClick(finalI);
                }
            });
        }
    }

    public void setOnTabSelectedListenner(BottomTab.OnTabSelectedListenner onTabSelectedListenner) {
        this.onTabSelectedListenner = onTabSelectedListenner;
    }

    public void setNeedTestify(int[] needTestify) {
        this.needTestify = needTestify;
    }

    private void onTabClick(int i) {
        if (onTabSelectedListenner != null) {
            onTabSelectedListenner.onTabSelected(i);
        }
        for(int position : needTestify){
            if (i == position){
                return;
            }
        }
        if (mCurIndex == i) {
            return;
        }
        //特殊情況处理
        if (i == 1){
            return;
        }
        RelativeLayout tabLayout = (RelativeLayout) layout.getChildAt(i);
        tabLayout.getChildAt(0).setSelected(true);
        tabLayout.getChildAt(1).setSelected(true);
        mLastIndex = mCurIndex;
        mCurIndex = i;
        if (mLastIndex >= 0) {
            RelativeLayout lastLayout = (RelativeLayout) layout.getChildAt(mLastIndex);
            lastLayout.getChildAt(0).setSelected(false);
            lastLayout.getChildAt(1).setSelected(false);
        }


    }

    public interface OnTabSelectedListenner {
        void onTabSelected(int i);
    }
    public void setIndexSelected(int i){
        isForced = true;
        onTabClick(i);
    }



}
