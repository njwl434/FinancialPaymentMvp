package com.wl.wllibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wl.wllibrary.R;
import com.wl.wllibrary.util.DensityUtil;


/**
 * Created by lqy on 16/9/8.
 */
public class MySwipeRefreshLayout extends SuperSwipeRefreshLayout {
    private TextView textView;
//    private ImageView imageViewLeft;
//    private ImageView imageViewRight;
    private ProgressBar progressBar;
    private ProgressBar footerProgressBar;
    private ImageView footerImageView;
    private TextView footerTextView;
    private View headerView;
//    private ObjectAnimator rightAnimation;
//    private ObjectAnimator leftAnimation;

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public void init() {
        setDistanceToTriggerSync(DensityUtil.dip2px(getContext(),80));
        setHeaderViewBackgroundColor(0xffffff);
        setHeaderView(createHeaderView());// add headerView
        setFooterView(createFooterView());
        setTargetScrollWithLayout(true);
//        setOnPushLoadMoreListener(new OnPushLoadMoreListener() {
//
//            @Override
//            public void onLoadMore() {
//                footerTextView.setText("正在加载...");
//                footerImageView.setVisibility(View.GONE);
//                footerProgressBar.setVisibility(View.VISIBLE);
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        footerImageView.setVisibility(View.VISIBLE);
//                        footerProgressBar.setVisibility(View.GONE);
//                        setLoadMore(false);
//                    }
//                }, 5000);
//            }
//
//            @Override
//            public void onPushEnable(boolean enable) {
//                footerTextView.setText(enable ? "松开加载" : "上拉加载");
//                footerImageView.setVisibility(View.VISIBLE);
//                footerImageView.setRotation(enable ? 0 : 180);
//            }
//
//            @Override
//            public void onPushDistance(int distance) {
//                // TODO Auto-generated method stub
//
//            }
//
//        });
    }

    private View createFooterView() {
        View footerView = LayoutInflater.from(getContext())
                .inflate(R.layout.footer_refreshlayout, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.mipmap.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }

    private View createHeaderView() {
         headerView = LayoutInflater.from(getContext())
                .inflate(R.layout.header_refreshlayout, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
//        imageViewLeft = (ImageView) headerView.findViewById(R.id.image_viewLeft);
//        imageViewRight = (ImageView) headerView.findViewById(R.id.image_viewRight);
//        imageViewLeft.setVisibility(View.VISIBLE);
//        imageViewLeft.setImageResource(R.mipmap.down_arrow);
//        progressBar.setVisibility(View.VISIBLE);
        return headerView;
    }

    public void onRefresh() {
        textView.setText("正在刷新");
        progressBar.setVisibility(View.VISIBLE);
//        leftAnimation = ObjectAnimator.ofFloat(imageViewLeft, "rotation", 0.0f, 359.0f);
//
//        leftAnimation.setDuration(2000);
//        leftAnimation.setInterpolator(new LinearInterpolator());
//        leftAnimation.setRepeatCount(ValueAnimator.INFINITE);
//        leftAnimation.start();
//         rightAnimation = ObjectAnimator.ofFloat(imageViewRight, "rotation", 0.0f, -359.0f);;
//        rightAnimation.setRepeatCount(Animation.INFINITE);
//        rightAnimation.setDuration(2000);
//        rightAnimation.setInterpolator(new LinearInterpolator());
//        rightAnimation.start();
    }
    public void onPullEnable(boolean enable) {
        textView.setText(enable ? "松开刷新" : "下拉刷新");
//        imageViewLeft.setVisibility(View.VISIBLE);
//        imageViewLeft.setRotation(enable ? 180 : 0);
    }
    public void onPullDistance(int distance){
//        float rate = (float)distance/(float)headerView.getMeasuredHeight();
//        imageViewLeft.setRotation(360 * rate);
//        imageViewRight.setRotation(-360*rate);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
        if (!refreshing)
            progressBar.setVisibility(View.GONE);
//            leftAnimation.cancel();
//            rightAnimation.cancel();
    }
}
