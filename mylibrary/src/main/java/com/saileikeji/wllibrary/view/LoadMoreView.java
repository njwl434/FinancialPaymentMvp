package com.saileikeji.wllibrary.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saileikeji.wllibrary.R;


/**
 * 加载更多的页脚
 * @author lqy
 * @time 2016/4/21 13:34
 */
public class LoadMoreView extends LinearLayout{
    private ProgressBar mProgressBar;
    private ViewPager mViewPager;
    private TextView mTv;
    private FooterState state = FooterState.HASMORE;
    private String mStrNodata="暂无数据";
    public LoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadMoreView(Context context) {
        this(context,null);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        LayoutInflater.from(getContext()).inflate(R.layout.footer_view, this);
        // setVisibility(View.INVISIBLE);
        mProgressBar = (ProgressBar) findViewById(R.id.view_footer_progressbar);
        mTv = (TextView) findViewById(R.id.view_footer_tv);
        mTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == FooterState.RETRY){

                }
            }
        });
    }
    public void changeState(FooterState mFooterState) {
        state = mFooterState;
        switch (mFooterState) {
            case NOMORE:
                mProgressBar.setVisibility(View.GONE);
                mTv.setText("没有更多内容了");
                break;
            case HASMORE:
            case LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                mTv.setText("奋力加载中。。。。");
                break;
            case RETRY:
                mProgressBar.setVisibility(View.GONE);
                mTv.setText("加载失败，点击重试");
                break;
            case NODATA:
                mProgressBar.setVisibility(View.GONE);
                mTv.setText(mStrNodata);

        }

    }
    public void setmStrNodata(String nodata){
        this.mStrNodata = nodata;
    }
    public FooterState getState() {
        return state;
    }

    public enum FooterState {
        NOMORE, HASMORE, LOADING, RETRY,NODATA
    }
}
