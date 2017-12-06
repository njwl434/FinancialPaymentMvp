package com.wl.wllibrary.view.loopviewpager;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * Created by qiuyun on 2015/3/25.
 */

public class LoopViewPager extends ViewPager {

	private final String TAG = LoopViewPager.this.getClass().getName();
	private static final boolean DEFAULT_BOUNDARY_CASHING = false;
	private static final int SCROLL_INTERVAL = 4000;
	private float downX;
	private float downY;
	private PagerAdapter mAdaperInner;
	private LoopPagerAdapterWrapper mAdapter;
	private boolean mBoundaryCaching = false;
	private boolean isAutoScroll = false;
	OnPageChangeListener mOuterPageChangeListener;
	private OnPageChangeListener onPageChangeListener;
	private CustomDurationScroller scroller = null;
	private Handler handler;
	private Runnable toNext;
	private int index = 0;

	public LoopViewPager(Context paramContext) {
		super(paramContext);

		init();
	}

	public LoopViewPager(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);

		init();
	}

	public void startScroll() {
		isAutoScroll = true;
		handler = new Handler();
		handler.postDelayed(toNext, SCROLL_INTERVAL);
	}
	public void stopScroll(){
		isAutoScroll = false;
		handler.removeCallbacks(toNext);
	}
	public void resetIndex(){
		index = 0;
	}
	private void init() {
		this.onPageChangeListener = new OnPageChangeListener() {
			private float mPreviousOffset = -1.0F;
			private float mPreviousPosition = -1.0F;

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				int realPosition = position;
				if (mAdapter != null) {
					realPosition = mAdapter.toRealPosition(position);
					/*
					 * ���ͣ�������һ�������ǵ�һ�������ǾͲ���Ҫ�����ƶ���ֻ��Ҫת������Ҫչʾ��view����
					 */
					if ((positionOffset == 0) && positionOffsetPixels == 0
							&& (position == 0 || position == mAdapter.getCount() - 1)) {
						setCurrentItem(realPosition, false);
					}
				}
				this.mPreviousOffset = positionOffset;
				if (mOuterPageChangeListener != null && mAdapter != null && mAdapter.getRealAdapter() != null) {
					if (realPosition != -1 + mAdapter.getRealCount()) {
						mOuterPageChangeListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
					} else if (positionOffset > 0.5) {
						mOuterPageChangeListener.onPageScrolled(0, 0F, 0);
					} else {
						mOuterPageChangeListener.onPageScrolled(realPosition, 0, 0);
					}
				}
			}

			@Override
			public void onPageSelected(int position) {
				int realPosition = mAdapter.toRealPosition(position);
				if (mPreviousPosition != realPosition) {
					mPreviousPosition = realPosition;
					System.out.println("lqy--isAutoScroll---->"+isAutoScroll);
					if (isAutoScroll) {
						index = realPosition;
						
						handler.postDelayed(toNext, SCROLL_INTERVAL);
					}
					if (mOuterPageChangeListener != null) {
						mOuterPageChangeListener.onPageSelected(realPosition);
					}
				}
//				else {
//					if (isAutoScroll) {
//						index = realPosition;
//						handler.post(toNext);
//					}
//				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if (mAdapter != null) {
					int i = LoopViewPager.super.getCurrentItem();
					int j = mAdapter.toRealPosition(i);
					if (state == SCROLL_STATE_IDLE && (i == 0 || i == -1 + mAdapter.getCount())) {
						setCurrentItem(j, false);
					}
				}
				if (mOuterPageChangeListener != null) {
					mOuterPageChangeListener.onPageScrollStateChanged(state);
				}
			}
		};
		super.setOnPageChangeListener(this.onPageChangeListener);
		try {
			Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
			scrollerField.setAccessible(true);
			Field interpolatorField = ViewPager.class.getDeclaredField("sInterpolator");
			interpolatorField.setAccessible(true);

			scroller = new CustomDurationScroller(getContext(), (Interpolator) interpolatorField.get(null));
			scroller.setmDuration(1500); // 2000ms
			scrollerField.set(this, scroller);
		} catch (Exception e) {
			e.printStackTrace();
		}
		toNext = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				index++;
				System.out.println("lqyindex---->"+index);
				setCurrentItem(index);
//				handler.removeCallbacks(toNext);
			}
		};
	}

	public static int toRealPosition(int paramInt1, int paramInt2) {
		int i = paramInt1 - 1;
		if (i < 0)
			return (i + paramInt2);
		return (i % paramInt2);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (toNext!=null)
		handler.removeCallbacks(toNext);
		return super.onTouchEvent(arg0);
	}

	public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
		if (Build.VERSION.SDK_INT > 11)
			return super.dispatchTouchEvent(paramMotionEvent);
		if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 1)) {
			getParent().requestDisallowInterceptTouchEvent(false);
			return super.dispatchTouchEvent(paramMotionEvent);
		}
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(paramMotionEvent);
	}

	public PagerAdapter getAdapter() {
		if (this.mAdapter != null)
			return this.mAdapter.getRealAdapter();
		return this.mAdapter;
	}

	public int getCurrentItem() {
		if (this.mAdapter != null)
			return this.mAdapter.toRealPosition(super.getCurrentItem());
		return 0;
	}

	public void setAdapter(PagerAdapter paramPagerAdapter) {
		this.mAdaperInner = paramPagerAdapter;
		this.mAdapter = new LoopPagerAdapterWrapper(paramPagerAdapter);
		this.mAdapter.setBoundaryCaching(this.mBoundaryCaching);
		super.setAdapter(this.mAdapter);
		setCurrentItem(0, false);
	}

	public void setBoundaryCaching(boolean paramBoolean) {
		this.mBoundaryCaching = paramBoolean;
		if (this.mAdapter != null)
			this.mAdapter.setBoundaryCaching(paramBoolean);
	}

	public void setCurrentItem(int paramInt) {
		if (getCurrentItem() != paramInt)
			setCurrentItem(paramInt, true);
	}

	public void setCurrentItem(int paramInt, boolean paramBoolean) {
		if (this.mAdapter == null)
			this.mAdapter = new LoopPagerAdapterWrapper(this.mAdaperInner);
		super.setCurrentItem(this.mAdapter.toInnerPosition(paramInt), paramBoolean);
	}

	public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
		this.mOuterPageChangeListener = paramOnPageChangeListener;
	}
}
