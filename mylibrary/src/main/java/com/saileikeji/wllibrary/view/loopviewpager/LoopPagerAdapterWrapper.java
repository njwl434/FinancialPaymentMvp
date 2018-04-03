package com.saileikeji.wllibrary.view.loopviewpager;


import android.os.Parcelable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.saileikeji.wllibrary.util.LogUtil;


/**
 * Created by qiuyun on 2015/3/26.
 */
public class LoopPagerAdapterWrapper extends PagerAdapter {
    private PagerAdapter mAdapter;
    private boolean mBoundaryCaching;
    private SparseArray<ToDestroy> mToDestroy = new SparseArray();

    public LoopPagerAdapterWrapper(PagerAdapter paramPagerAdapter) {
        this.mAdapter = paramPagerAdapter;
    }

    private int getRealFirstPosition() {
        return 1;
    }

    private int getRealLastPosition() {
        return (-1 + getRealFirstPosition() + getRealCount());
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        int realFirstPosition = getRealFirstPosition();
        int realLastPosition = getRealLastPosition();
        int realPosition = mAdapter instanceof FragmentPagerAdapter || mAdapter instanceof FragmentStatePagerAdapter ? position : toRealPosition(position);
        if ((this.mBoundaryCaching) && ((position == realFirstPosition) || (position == realLastPosition))) {
            this.mToDestroy.put(position, new ToDestroy(container, realPosition, object));

        } else {
            this.mAdapter.destroyItem(container, realPosition, object);
        }
    }

    public void finishUpdate(ViewGroup paramViewGroup) {
        this.mAdapter.finishUpdate(paramViewGroup);
    }

    public int getCount() {
        return (2 + this.mAdapter.getCount());
    }

    public PagerAdapter getMAdapter() {
        return this.mAdapter;
    }

    public PagerAdapter getRealAdapter() {
        return this.mAdapter;
    }

    public int getRealCount() {
        return this.mAdapter.getCount();
    }

    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = (mAdapter instanceof FragmentPagerAdapter || mAdapter instanceof FragmentStatePagerAdapter)
                ? position
                : toRealPosition(position);

        if (mBoundaryCaching) {
            ToDestroy toDestroy = mToDestroy.get(position);
            if (toDestroy != null) {
                mToDestroy.remove(position);
                return toDestroy.object;
            }
        }
        return mAdapter.instantiateItem(container, realPosition);
    }

    public boolean isViewFromObject(View paramView, Object paramObject) {
        return this.mAdapter.isViewFromObject(paramView, paramObject);
    }

    public void notifyDataSetChanged() {
        this.mToDestroy = new SparseArray();
        super.notifyDataSetChanged();
    }

    public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {
        this.mAdapter.restoreState(paramParcelable, paramClassLoader);
    }

    public Parcelable saveState() {
        return this.mAdapter.saveState();
    }

    public void setBoundaryCaching(boolean paramBoolean) {
        this.mBoundaryCaching = paramBoolean;
    }

    public void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
        this.mAdapter.setPrimaryItem(paramViewGroup, paramInt, paramObject);
    }

    public void startUpdate(ViewGroup paramViewGroup) {
        this.mAdapter.startUpdate(paramViewGroup);
    }

    public int toInnerPosition(int paramInt) {
        LogUtil.d("position--->"+(paramInt+1));
        return (paramInt + 1);
    }

    public int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = (position - 1) % realCount;//5:
        if (realPosition < 0)
            realPosition += realCount;
        return realPosition;
    }

    static class ToDestroy {
        ViewGroup container;
        Object object;
        int position;

        public ToDestroy(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
            this.container = paramViewGroup;
            this.position = paramInt;
            this.object = paramObject;
        }
    }
}
