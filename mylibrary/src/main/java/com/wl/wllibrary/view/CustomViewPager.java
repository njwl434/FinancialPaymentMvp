package com.wl.wllibrary.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wl.wllibrary.util.DensityUtil;
import com.wl.wllibrary.util.LogUtil;

public class CustomViewPager extends ViewPager {

    float mStartDragX;
    OnSwipeOutListener mListener;
    float min_distance;
    public int i = 0 ;
    public int minHeight = 0;
    public String tag;

    public CustomViewPager(Context context) {
        super(context);
        min_distance = DensityUtil.dip2px(context,25);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        min_distance = DensityUtil.dip2px(context,25);
    }

    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        switch (ev.getAction()& MotionEventCompat.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:

            mStartDragX = x;
            break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(getCurrentItem()==0 || getCurrentItem()==getAdapter().getCount()-1){
            final int action = ev.getAction();
            float x = ev.getX();
            switch(action & MotionEventCompat.ACTION_MASK){
                case MotionEvent.ACTION_MOVE:
                    x = ev.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    if (Math.abs(x-mStartDragX)>min_distance) {
                        if (getCurrentItem() == 0 && x > mStartDragX) {
                            mListener.onSwipeOutAtStart();
                        }
                        if (getCurrentItem() == getAdapter().getCount() - 1 && x < mStartDragX) {
                            mListener.onSwipeOutAtEnd();
                        }
                    }
                    break;
            }
        }else{
            mStartDragX=0;
        }
        return super.onTouchEvent(ev);

    }

    public interface OnSwipeOutListener {
        void onSwipeOutAtStart();
        void onSwipeOutAtEnd();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        LogUtil.e("order-->"+i);
        int height = minHeight;
        for(int i = 0; i < getChildCount(); i++)
        {
//        if (getChildCount()>i) {
            View child = getChildAt(i);
            if (child.getTag().equals(tag)) {
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

                int h = child.getMeasuredHeight();
                LogUtil.e("order-->" + i);
                LogUtil.e("order-->" + h);
                if (h > height) height = h;
                break;
            }

//        }
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
