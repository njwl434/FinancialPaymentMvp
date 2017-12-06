package com.wl.wllibrary.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.wl.wllibrary.R;
import com.wl.wllibrary.util.DensityUtil;
import com.wl.wllibrary.util.LogUtil;

/**
 * Created by ZhengHongEn on 2016/3/31.
 */
public class CustomSearchEditText extends android.support.v7.widget.AppCompatEditText implements
        View.OnFocusChangeListener, TextWatcher {
    String hintText = "";
    float hintTextSize = 0;
    int hintTextColor = 0xFF000000;
    int hintImage;
    float hintImageSize = 0;

    Drawable mDrawable;
    Paint paint;
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;



    public CustomSearchEditText(Context context) {
        super(context);
    }

    public CustomSearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResource(context, attrs);
        initPaint();
    }

    public CustomSearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResource(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHintIcon(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mDrawable == null) {
            try {
                mDrawable = getContext().getResources().getDrawable(hintImage);
                mDrawable.setBounds(0, 0, (int) hintImageSize, (int) hintImageSize);
            } catch (Exception e) {

            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mDrawable != null) {
            mDrawable.setCallback(null);
            mDrawable = null;
        }
        super.onDetachedFromWindow();
    }


    /**
     * 初始化 资源
     *
     * @param context
     * @param attrs
     */
    private void initResource(Context context, AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchEditText);
        float density = context.getResources().getDisplayMetrics().density;
        hintText = mTypedArray.getString(R.styleable.SearchEditText_txt);
        hintTextSize = mTypedArray.getDimension(R.styleable.SearchEditText_txtSize, 14);
        hintTextColor = mTypedArray.getColor(R.styleable.SearchEditText_txtColor, 0xFF999999);

        hintImage = mTypedArray.getResourceId(R.styleable.SearchEditText_image, android.R.drawable.ic_menu_search);
        hintImageSize = mTypedArray.getDimension(R.styleable.SearchEditText_imageSize, 18 * density + 0.5F);
        mTypedArray.recycle();

        mClearDrawable = getResources().getDrawable(R.mipmap.delete_selector);

//        mClearDrawable.setBounds(20, 0, mClearDrawable.getIntrinsicWidth()+20, mClearDrawable.getIntrinsicHeight());
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, mClearDrawable, null);
        //默认设置隐藏图标
        setClearIconVisible(false);
//        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
        this.setCompoundDrawablePadding(DensityUtil.dip2px(getContext(), 10));
        setPadding((int) hintImageSize + 20, getPaddingTop(), getPaddingRight(), getPaddingBottom());
//        setListenerToRootView();
    }

    /**
     * 初始化 画笔
     */
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(hintTextColor);
        paint.setTextSize(hintTextSize);
    }

    /**
     * @param canvas
     */
    private void drawHintIcon(Canvas canvas) {
        if (!this.isFocused()) {//没获取焦点时
            float textWidth = paint.measureText(hintText);
            float textHeight = getFontLeading(paint);


            if (getText().length() == 0) {
                float dx = (getWidth() - hintImageSize - textWidth - 8) / 2;
                float dy = (getHeight() - hintImageSize) / 2;

                canvas.save();
                canvas.translate(getScrollX() + dx, getScrollY() + dy);
                if (mDrawable != null) {
                    mDrawable.draw(canvas);
                }
                canvas.drawText(hintText, getScrollX() + hintImageSize + 8, getScrollY() + (getHeight() - (getHeight() - textHeight) / 2) - paint.getFontMetrics().bottom - dy, paint);

            } else {
                float dx = 20;
                float dy = (getHeight() - hintImageSize) / 2;

                canvas.save();
                canvas.translate(getScrollX() + dx, getScrollY() + dy);
                if (mDrawable != null) {
                    mDrawable.draw(canvas);
                }
            }
            canvas.restore();
        } else {//获取焦点
//            float dx = (getWidth() - hintImageSize - 20);
            setCursorVisible(true);
            float dx = 20;
            float dy = (getHeight() - hintImageSize) / 2;

            canvas.save();
            canvas.translate(getScrollX() + dx, getScrollY() + dy);
            if (mDrawable != null) {
                mDrawable.draw(canvas);
            }

            canvas.restore();
        }
    }

    public void setKeyboardVisibility(boolean show) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } else {
            imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
        }
    }

    /**
     * @param paint
     * @return
     */
    private float getFontLeading(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.bottom - fm.top;
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                    setKeyboardVisibility(false);
                    this.clearFocus();
                    setCursorVisible(false);
                    return true;
                }
            }
        }

        return super.onTouchEvent(event);
    }



    boolean mKeyboardUp = false;

    /**
     * 监听键盘的弹起落下
     */
    public void setListenerToRootView() {
        final View activityRootView = ((Activity) getContext()).getWindow().getDecorView().findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isKeyboardShown(activityRootView)) {
//                    Toast.makeText(getContext(), "show", Toast.LENGTH_SHORT).show();
                    LogUtil.e("键盘弹起");
                    mKeyboardUp = true;
                } else if (mKeyboardUp) {
                    LogUtil.e("键盘落下");
                    if (CustomSearchEditText.this.isFocused()) {
                        CustomSearchEditText.this.clearFocus();
                        setCursorVisible(false);
                        setText("");
                    }
                    mKeyboardUp = false;
                }

            }
        });
    }

    private boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }
}
