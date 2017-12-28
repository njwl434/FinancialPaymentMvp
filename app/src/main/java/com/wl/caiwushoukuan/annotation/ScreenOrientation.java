package com.wl.caiwushoukuan.annotation;

import android.content.pm.ActivityInfo;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
@Inherited
public @interface ScreenOrientation {
	/**
	 * 设置屏幕方向
	 * 
	 * 参数为ActivityInfo 默认值ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
	 * 
	 */
	public abstract int orientation() default ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
}
