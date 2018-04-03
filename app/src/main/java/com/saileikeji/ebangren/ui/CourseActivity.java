package com.saileikeji.ebangren.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/5 12:55
 * @mailbox: 1034905058@qq.com
 */

public class CourseActivity extends BaseActivity {

    @Bind(R.id.topbar_iv_center)
    ImageView topbarIvCenter;
    @Bind(R.id.topbar_tv_title)
    TextView topbarTvTitle;
    @Bind(R.id.topbar_iv_left)
    ImageView topbarIvLeft;
    @Bind(R.id.topbar_tv_left)
    TextView topbarTvLeft;
    @Bind(R.id.topbar_img_left)
    ImageView topbarImgLeft;
    @Bind(R.id.topbar_tv_right)
    TextView topbarTvRight;
    @Bind(R.id.topbar_iv_right)
    ImageView topbarIvRight;
    @Bind(R.id.layoutRight)
    LinearLayout layoutRight;
    @Bind(R.id.mlaycourse)
    LinearLayout mlaycourse;
    @Bind(R.id.roll_view_pager)
    RollPagerView rollViewPager;
    @Bind(R.id.mIvImg)
    ImageView mIvImg;
    @Bind(R.id.mIvImgCoursea)
    ImageView mIvImgCoursea;
    @Bind(R.id.mLineara)
    LinearLayout mLineara;
    @Bind(R.id.mIvImgCourse)
    ImageView mIvImgCourse;
    @Bind(R.id.mLinear)
    LinearLayout mLinear;
    @Bind(R.id.mRecycleorCourse)
    RecyclerView mRecycleorCourse;
    @Bind(R.id.mRecycleorPingjiaCourse)
    RecyclerView mRecycleorPingjiaCourse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);
        Glide.with(this)
                .load(R.drawable.ic_zhanwei)
                .placeholder(R.mipmap.default_error)
                .error(R.mipmap.default_error)
                .centerCrop()
                .crossFade()
                .into(mIvImg);

    }
}
