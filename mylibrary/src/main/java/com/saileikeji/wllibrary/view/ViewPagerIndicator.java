package com.saileikeji.wllibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saileikeji.wllibrary.R;
import com.saileikeji.wllibrary.util.DensityUtil;

import java.util.List;

/**
 * http://blog.csdn.net/lmj623565791/article/details/42160391
 * @author zhy
 *
 */
public class ViewPagerIndicator extends LinearLayout
{
	/**
	 * 绘制三角形的画笔
	 */
	private Paint mPaint;
	/**
	 * path构成一个三角形
	 */
	private Path mPath;
	/**
	 * 三角形的宽度
	 */
	private int mTriangleWidth;
	private int mRectWidth;
	/**
	 * 三角形的高度
	 */
	private int mTriangleHeight;
	private int mRectHeight;
	/**
	 * 三角形的宽度为单个Tab的1/6
	 */
	private static final float RADIO_TRIANGEL = 1.0f / 6;
	private static final float RADIO_RECT = 0.5f;
	/**
	 * 三角形的最大宽度
	 */
	private final int DIMENSION_TRIANGEL_WIDTH = (int) (getScreenWidth() / 2 * RADIO_TRIANGEL);
	private final int DIMENSION_RECT_WIDTH = getScreenWidth()/2;
	/**
	 * 初始时，三角形指示器的偏移量
	 */
	private int mInitTranslationX;
	/**
	 * 手指滑动时的偏移量
	 */
	private float mTranslationX;

	private RectF rect;
	/**
	 * 默认的Tab数量
	 */
	private static final int COUNT_DEFAULT_TAB = 4;
	/**
	 * tab数量
	 */
	private int mTabVisibleCount = COUNT_DEFAULT_TAB;
	/**
	 * tv 宽度
	 */
	private int mTvWidth = 200;
	/**
	 * tv 是否match parent
	 */
	private boolean mTvWidthMatchParent = false;

	/**
	 * tab上的内容
	 */
	private List<String> mTabTitles;
	/**
	 * 与之绑定的ViewPager
	 */
	public ViewPager mViewPager;

	/**
	 * 标题正常时的颜色
	 */
	private static final int COLOR_TEXT_NORMAL = 0xFFA2CEFF;
	/**
	 * 标题选中时的颜色
	 */
	private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;

	public ViewPagerIndicator(Context context)
	{
		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		// 获得自定义属性，tab的数量
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ViewPagerIndicator);
		mTabVisibleCount = a.getInt(R.styleable.ViewPagerIndicator_item_count,
				COUNT_DEFAULT_TAB);
		mTvWidth = a.getInt(R.styleable.ViewPagerIndicator_tv_width,
				200);
		mTvWidthMatchParent = a.getBoolean(R.styleable.ViewPagerIndicator_width_match, false);
		if (mTvWidthMatchParent) {
			mTvWidth = DensityUtil.px2dip(context, (float) (getScreenWidth()) / 2)-7;
		}
		if (mTabVisibleCount < 0) {
			mTabVisibleCount = COUNT_DEFAULT_TAB;
		}
		a.recycle();

		// 初始化画笔
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#ff489cf9"));
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));

	}

	/**
	 * 绘制指示器
	 */
	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		canvas.save();
		// 画笔平移到正确的位置
		canvas.translate(mInitTranslationX + mTranslationX,0);
//		canvas.drawPath(mPath, mPaint);
		canvas.drawRoundRect(rect,DensityUtil.dip2px(getContext(),5),DensityUtil.dip2px(getContext(),5),mPaint);
		canvas.restore();

		super.dispatchDraw(canvas);
	}

	/**
	 * 初始化三角形的宽度
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
//		mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGEL);// 1/6 of
//		mRectWidth = (int) (w/mTabVisibleCount*RADIO_RECT);																// width
		mRectWidth = (int) (w/mTabVisibleCount);																// width
//		mTriangleWidth = Math.min(DIMENSION_TRIANGEL_WIDTH, mTriangleWidth);
		mRectWidth = Math.min(DIMENSION_RECT_WIDTH,mRectWidth);
		// 初始化三角形
//		initTriangle();
		initRect();
		// 初始时的偏移量
//		mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth
//				/ 2;
 		mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mRectWidth
				/ 2;

	}

	/**
	 * 设置可见的tab的数量
	 * 
	 * @param count
	 */
	public void setVisibleTabCount(int count)
	{
		this.mTabVisibleCount = count;
	}

	/**
	 * 设置tab的标题内容 可选，可以自己在布局文件中写死
	 * 
	 * @param datas
	 */
	public void setTabItemTitles(List<String> datas)
	{
		// 如果传入的list有值，则移除布局文件中设置的view
		if (datas != null && datas.size() > 0)
		{
			this.removeAllViews();
			this.mTabTitles = datas;

			for (String title : mTabTitles)
			{
				// 添加view
				addView(generateTextView(title));
			}
			// 设置item的click事件
			setItemClickEvent();
		}

	}

	/**
	 * 对外的ViewPager的回调接口
	 * 
	 * @author zhy
	 * 
	 */
	public interface PageChangeListener
	{
		void onPageScrolled(int position, float positionOffset,
                            int positionOffsetPixels);

		void onPageSelected(int position);

		void onPageScrollStateChanged(int state);
	}

	// 对外的ViewPager的回调接口
	private PageChangeListener onPageChangeListener;

	// 对外的ViewPager的回调接口的设置
	public void setOnPageChangeListener(PageChangeListener pageChangeListener)
	{
		this.onPageChangeListener = pageChangeListener;
	}

	// 设置关联的ViewPager
	public void setViewPager(ViewPager mViewPager, int pos)
	{
		this.mViewPager = mViewPager;

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				// 设置字体颜色高亮
				resetTextViewColor();
				highLightTextView(position);

				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageSelected(position);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				// 滚动
				scroll(position, positionOffset);

				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrolled(position,
							positionOffset, positionOffsetPixels);
				}

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrollStateChanged(state);
				}

			}
		});
		// 设置当前页
		mViewPager.setCurrentItem(pos);
		// 高亮
		highLightTextView(pos);
	}

	/**
	 * 高亮文本
	 * 
	 * @param position
	 */
	protected void highLightTextView(int position)
	{
		View view = getChildAt(position);
		if (view instanceof LinearLayout)
		{
			View v = ((LinearLayout) view).getChildAt(0);
			((TextView) v).setTextColor(COLOR_TEXT_HIGHLIGHTCOLOR);
		}

	}

	/**
	 * 重置文本颜色
	 */
	private void resetTextViewColor()
	{
		for (int i = 0; i < getChildCount(); i++)
		{
			View view = getChildAt(i);
			if (view instanceof LinearLayout)
			{
				View v = ((LinearLayout) view).getChildAt(0);
				((TextView) v).setTextColor(COLOR_TEXT_NORMAL);
			}
		}
	}

	/**
	 * 设置点击事件
	 */
	public void setItemClickEvent()
	{
		int cCount = getChildCount();
		for (int i = 0; i < cCount; i++)
		{
			final int j = i;
			View view = getChildAt(i);
			view.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					mViewPager.setCurrentItem(j);
				}
			});
		}
	}

	/**
	 * 根据标题生成我们的TextView
	 * 
	 * @param text
	 * @return
	 */
	private LinearLayout generateTextView(String text)
	{
		LinearLayout layout = new LinearLayout(getContext());
		LayoutParams lpLayout = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//		lpLayout.width = (int) getContext().getResources().getDimension(R.dimen.message_title) / mTabVisibleCount;
		lpLayout.width = ( DensityUtil.dip2px(getContext(),mTvWidth)) ;

		layout.setGravity(Gravity.CENTER);
		layout.setLayoutParams(lpLayout);
		TextView tv = new TextView(getContext());
		LayoutParams lp = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(COLOR_TEXT_NORMAL);
		tv.setText(text);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv.setLayoutParams(lp);
		layout.addView(tv);

		return layout;
	}



	/**
	 * 初始化矩形指示器
	 */
	private void initRect()
	{
//		mPath = new Path();

		mRectHeight = DensityUtil.dip2px(getContext(),30);
		rect = new RectF(0,0,mRectWidth,mRectHeight);
//		mPath.moveTo(0, 0);
//		mPath.lineTo(mRectWidth,0);
//		mPath.lineTo(mRectWidth,-mRectHeight);
//		mPath.lineTo(0,-mRectHeight);
//		mPath.close();
	}


	/**
	 * 指示器跟随手指滚动，以及容器滚动
	 * 
	 * @param position
	 * @param offset
	 */
	public void scroll(int position, float offset)
	{
		/**
		 * <pre>
		 *  0-1:position=0 ;1-0:postion=0;
		 * </pre>
		 */
		// 不断改变偏移量，invalidate
		mTranslationX = getWidth() / mTabVisibleCount * (position + offset);

		int tabWidth = getScreenWidth() / mTabVisibleCount;

		// 容器滚动，当移动到倒数最后一个的时候，开始滚动
		if (offset > 0 && position >= (mTabVisibleCount - 2)
				&& getChildCount() > mTabVisibleCount)
		{
			if (mTabVisibleCount != 1)
			{
				this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
						+ (int) (tabWidth * offset), 0);
			} else
			// 为count为1时 的特殊处理
			{
				this.scrollTo(
						position * tabWidth + (int) (tabWidth * offset), 0);
			}
		}

		invalidate();
	}

	/**
	 * 设置布局中view的一些必要属性；如果设置了setTabTitles，布局中view则无效
	 */
	@Override
	protected void onFinishInflate()
	{
		Log.e("TAG", "onFinishInflate");
		super.onFinishInflate();

		int cCount = getChildCount();

		if (cCount == 0) {
			return;
		}

		for (int i = 0; i < cCount; i++)
		{
			View view = getChildAt(i);
			LayoutParams lp = (LayoutParams) view
					.getLayoutParams();
			lp.weight = 0;
			lp.width = getScreenWidth() / mTabVisibleCount;
			view.setLayoutParams(lp);
		}
		// 设置点击事件
		setItemClickEvent();

	}

	/**
	 * 获得屏幕的宽度
	 * 
	 * @return
	 */
	public int getScreenWidth()
	{
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

}
