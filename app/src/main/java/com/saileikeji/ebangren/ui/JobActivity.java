package com.saileikeji.ebangren.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.adapter.JobBaseAdapte;
import com.saileikeji.ebangren.bean.TestInfo;
import com.saileikeji.ebangren.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/7 14:31
 * @mailbox: 1034905058@qq.com
 */

public class JobActivity extends BaseActivity {

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
    @Bind(R.id.mIvImgjob)
    ImageView mIvImgjob;
    @Bind(R.id.mTvTitle)
    TextView mTvTitle;
    @Bind(R.id.mTvTitlejob)
    TextView mTvTitlejob;
    @Bind(R.id.mTvTitleajob)
    TextView mTvTitleajob;
    @Bind(R.id.mlaylotone)
    LinearLayout mlaylotone;
    @Bind(R.id.mlaylottwo)
    LinearLayout mlaylottwo;
    @Bind(R.id.mRecycleor)
    RecyclerView mRecycleor;
    JobBaseAdapte adapte;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        ButterKnife.bind(this);
        adapte=new JobBaseAdapte();

    }



}
