package com.saileikeji.wllibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.saileikeji.wllibrary.R;


/**
 * @author Fan
 *
 *
 * <com.redsoft.mylibrary.view.CapacityEditText
android:id="@+id/et_checkin"
android:layout_width="match_parent"
android:layout_marginBottom="38dp"
android:layout_marginLeft="15dp"
android:layout_marginRight="15dp"
android:layout_marginTop="20dp"
android:layout_height="match_parent"
android:background="@drawable/shape_gray_round_corner"
android:gravity="left"
android:maxLength="140"
android:minHeight="72dp"
android:paddingBottom="5dp"
android:paddingLeft="5dp"
android:paddingRight="5dp"
android:paddingTop="5dp"
android:textSize="15sp"
app:txtSize="13sp" />
 */
public class CapacityEditText extends android.support.v7.widget.AppCompatEditText implements
        TextWatcher {
    String capacityText = "";
    int textLength = 0;
    float capacityTextSize = 0;
    int capacityTextColor = 0xFF000000;
    Context mContext;
    Paint paint;
    Paint redPaint;

    public CapacityEditText(Context context) {
        super(context);
        mContext = context;
    }

    public CapacityEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initResource(context, attrs);
        initPaint();
    }

    public CapacityEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initResource(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCapacityText(canvas);
    }

    /**
     * 初始化 资源
     *
     * @param context
     * @param attrs
     */
    private void initResource(Context context, AttributeSet attrs) {
        float density = mContext.getResources().getDisplayMetrics().density;

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CapacityEditText);
        capacityTextSize = mTypedArray.getDimension(R.styleable.CapacityEditText_txtSize, (int)(14 * density));
        capacityTextColor = mTypedArray.getColor(R.styleable.CapacityEditText_txtColor, 0xFF999999);
        int maxsize = mTypedArray.getInteger(R.styleable.CapacityEditText_maxSize,140);
        capacityText = "/"+maxsize;
        mTypedArray.recycle();

        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + (int)(26 * density));
    }

    /**
     * 初始化 画笔
     */
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(capacityTextColor);
        paint.setTextSize(capacityTextSize);
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setColor(Color.RED);
        redPaint.setTextSize(capacityTextSize);

    }

    /**
     * @param canvas
     */
    private void drawCapacityText(Canvas canvas) {
        float density = mContext.getResources().getDisplayMetrics().density;
        float textWidth = paint.measureText(capacityText);
        float textWidth2 = paint.measureText(capacityText + textLength);
        float dx = (getWidth() - textWidth - 28);
        float dx2 = (getWidth() - textWidth2 - 28);
        float dy = (getHeight()) / 2 + 5 * density;
        canvas.save();
        canvas.translate(getScrollX() + dx, getScrollY() + dy - 5 * density);
        canvas.drawText(capacityText, getScrollX() + 8, getScrollY() + getHeight() - paint.getFontMetrics().bottom - dy, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(getScrollX() + dx2, getScrollY() + dy - 5 * density);
        canvas.drawText(textLength + "", getScrollX() + 8, getScrollY() + getHeight() - paint.getFontMetrics().bottom - dy, redPaint);
        canvas.restore();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        textLength = s.length();

    }


}
