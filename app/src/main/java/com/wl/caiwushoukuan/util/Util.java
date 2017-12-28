package com.wl.caiwushoukuan.util;


import android.annotation.SuppressLint;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import java.io.File;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fym on 2017/4/21.
 */

public class Util {

    private static String timea;
    static String len;

    public static String timeParse(Double duration) {
        String time = "";
        long minute = (long) (duration / 60);
        long seconds = (long) (duration % 60);
        long second = Math.round((float) seconds);
//        if( minute < 10 ){
//            time += "0" ;
//        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;
    }

    public static String timeParseMin(Double duration) {
        String time = "";
        long minute = (long) (duration / 60);
        long hour = (long) (duration / 3600);
        time = hour + "小时";
        if (minute < 10) {
            time += "0";
        }
        time += minute + "分钟";

        return time;
    }


    public static boolean fileExist(String resourceId) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/seed/" + resourceId + ".mp3");
        return file.isFile() && file.exists();
    }

    /**
     * 删除单个文件
     *
     * @param resourceId 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String resourceId) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/seed/" + resourceId + ".mp3");
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 买卖用的json长度
     *
     * @param json
     * @return
     */
    public static String getjsonlength(String json) {
        if ((json.length() + 6 + 3 + 5 + 1) + "".length() > 0 && (json.length() + 6 + 3 + 5 + 1) + "".length() <= 10) {
            len = "00" + (json.length() + 6 + 3 + 5 + 1);
        }
        if ((json.length() + 6 + 3 + 5 + 1) + "".length() > 100 && (json.length() + 6 + 3 + 5 + 1) + "".length() <= 999) {
            len = "0" + (json.length() + 6 + 3 + 5 + 1);
        }
        if ((json.length() + 6 + 3 + 5 + 1) + "".length() > 1000 && (json.length() + 6 + 3 + 5 + 1) + "".length() <= 9999) {
            len = (json.length() + 6 + 3 + 5 + 1) + "";
        }
        return len;
    }

    public static void hideViewDuirngScrolling(RecyclerView rv, final View view) {
        if (rv == null) {
            return;
        }
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("hideViewDuirngScrolling", "newState: " + newState);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();//获取控件的布局参数
                int fabBottomMargin = lp.bottomMargin;//与父控件的底部间隔
                if (newState == 1 || newState == 2) {
                    view.animate()//动画
                            .translationY(view.getHeight() + fabBottomMargin)//Y轴上的变换高度
                            .setInterpolator(new AccelerateInterpolator(2))//播放速度
                            .start();
                } else if (newState == 0) {
                    view.animate()
                            .translationY(0)
                            .setInterpolator(new DecelerateInterpolator(2))
                            .start();
                }
            }
        });
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static void toggleVisibility(View view) {

        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(long s) {
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(s);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return res;
    }

    /*
* 将时间戳转换为时间
*/
    public static String stampToDateFormat(long s) {
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date(s);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return res;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 传入：网络获取的时间格式为yyyy-MM-dd kk:mm:ss，如果不是这个格式，
     * 请在simpleDateFormat中设置格式
     * 输出：String 类型，格式是：f4中的格式 ，需要其他格式，请自行设置。
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDataTime(String time) {
        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //必须捕获异常
        try {
            Date date = simpleDateFormat.parse(time);
            SimpleDateFormat f4 = new SimpleDateFormat("yyyy-MM-dd");
            timea = f4.format(date);
        } catch (Exception px) {
            px.printStackTrace();
        }

        return timea;
    }

    /*
   * 将时间转换为时间戳
   */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static int getYearByTimeStamp(long timeStamp) {
        String date = stampToDate(timeStamp);
        String year = date.substring(0, 4);
        return Integer.parseInt(year);
    }

    public static int getMonthByTimeStamp(long timeStamp) {
        String date = stampToDate(timeStamp);
        String month = date.substring(5, 7);
        return Integer.parseInt(month);
    }

    public static int getDayByTimeStamp(long timeStamp) {
        String date = stampToDate(timeStamp);
        String day = date.substring(8, 10);
        return Integer.parseInt(day);
    }

    public static int getHourByTimeStamp(long timeStamp) {
        String date = stampToDate(timeStamp);
        String hour = date.substring(11, 13);
        return Integer.parseInt(hour);
    }

    public static int getMinuteByTimeStamp(long timeStamp) {
        String date = stampToDate(timeStamp);
        String minute = date.substring(14, 16);
        return Integer.parseInt(minute);
    }
}
