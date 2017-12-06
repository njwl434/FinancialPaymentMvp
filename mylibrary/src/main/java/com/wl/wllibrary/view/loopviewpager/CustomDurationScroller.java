package com.wl.wllibrary.view.loopviewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * CustomDurationScroller
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-3-2
 */
public class CustomDurationScroller extends Scroller {
	 private int mDuration = 2500; // default time is 1500ms
    private double scrollFactor = 1;

    public CustomDurationScroller(Context context) {
        super(context);
    }

    public CustomDurationScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    /**
     * not exist in android 2.3
     * 
     * @param context
     * @param interpolator
     * @param flywheel
     */
    // @SuppressLint("NewApi")
    // public CustomDurationScroller(Context context, Interpolator interpolator, boolean flywheel){
    // super(context, interpolator, flywheel);
    // }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        this.scrollFactor = scrollFactor;
    }

    @Override  
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {  
    // Ignore received duration, use fixed one instead  
    super.startScroll(startX, startY, dx, dy, mDuration);  
    }  
  
    @Override  
    public void startScroll(int startX, int startY, int dx, int dy) {  
    // Ignore received duration, use fixed one instead  
    super.startScroll(startX, startY, dx, dy, mDuration);  
    } 
    public void setmDuration(int mDuration) {
		this.mDuration = mDuration;
	}
    public int getmDuration() {
		return mDuration;
	}
}
