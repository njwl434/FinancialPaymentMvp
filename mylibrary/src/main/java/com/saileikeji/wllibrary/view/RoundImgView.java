package com.saileikeji.wllibrary.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.saileikeji.wllibrary.R;


/**
 * 智能圆形（带边框）Imageview
 *
 * @author luojj
 * @since thatone
 */
public class RoundImgView extends ImageView {
    private Paint paint = new Paint();
    private boolean isStroke = true;
    private Bitmap bitmap;
    // 定义 Bitmap 的默认配置
    private static final Config BITMAP_CONFIG = Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    public RoundImgView(Context context) {
        super(context);
    }

    public RoundImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RoundImgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.roundImageView);

        isStroke = a.getBoolean(R.styleable.roundImageView_isStroke, true);

        a.recycle();
    }

    public void setStroke(boolean strokeFlag){
        this.isStroke = strokeFlag;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if (null != drawable) {
            //Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            bitmap = getBitmapFromDrawable(drawable);
            Bitmap b = toRoundCorner(bitmap);
            paint.reset();
            canvas.drawBitmap(b, new Rect(0, 0, b.getWidth(), b.getHeight()),
                    new Rect(0, 0, getWidth(), getHeight()), paint);


        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap toRoundCorner(Bitmap bitmap) {

        int w = this.getMeasuredWidth();
        int h = this.getMeasuredHeight();
        //暂时对w,h不做比较
        int radius = h;
        Bitmap background = Bitmap.createBitmap(radius, radius, Config.ARGB_8888);
//        if(!isStroke){
            paint.setColor(getResources().getColor(R.color.white));
//        }else {
//            paint.setColor(getResources().getColor(R.color.colorPrimary));
//        }
        Canvas canvas = new Canvas(background);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setAntiAlias(true);
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        float bian = radius / 20;
        if (!isStroke) {
            bian = 0;
        }
        Bitmap round = createCircleImage(bitmap, (int) (radius - bian));
        float realradius = (radius - round.getHeight()) / 2;
        canvas.drawBitmap(round, realradius, realradius, null);
        return background;

    }

    /**
     * 智能画的圆形bitmap
     *
     * @return bitmap
     * @author luojj
     */
    public Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        final Rect rect1;
        if (source.getWidth() > source.getHeight()) {
            int h1 = source.getHeight();
            int w1 = source.getWidth();
            rect1 = new Rect((w1 - h1) / 2, 0, (w1 + h1) / 2, h1);
        } else {
            int h1 = source.getHeight();
            int w1 = source.getWidth();
            rect1 = new Rect(0, (h1 - w1) / 2, w1, (w1 + h1) / 2);
        }
        paint.setColor(getResources().getColor(R.color.bg_gray));
        paint.setStrokeWidth(100);

        Rect Rrect = new Rect(0, 0, min, min);
        canvas.drawBitmap(source, rect1, Rrect, paint);
        return target;
    }
    /**
     * 这里是参考其他开发者获取Bitmap内容的方法， 之前是因为没有考虑到 Glide 加载的图片
     * 导致drawable 类型是属于 SquaringDrawable 类型，导致强转失败
     * 这里是通过drawable不同的类型来进行获取Bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        try {
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            } else if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}
