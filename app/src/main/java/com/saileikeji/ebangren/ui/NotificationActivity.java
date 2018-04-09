package com.saileikeji.ebangren.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.TestInfo;
import com.saileikeji.ebangren.ui.base.BaseActivity;
import com.saileikeji.wllibrary.view.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: 消息通知
 * @author: 武梁
 * @date: 2018/4/9 13:32
 * @mailbox: 1034905058@qq.com
 */

public class NotificationActivity extends BaseActivity {

    @Bind(R.id.mTopBar)
    TopBar mTopBar;
    @Bind(R.id.a_recycle_notification)
    RecyclerView aRecycleNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
    }

    public class NotificationAdapter extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
        public NotificationAdapter() {
            super(R.layout.item_notification, null);
        }
        @Override
        protected void convert(BaseViewHolder helper,TestInfo item) {
            helper.setText(R.id.item_tv_name, item.getName());
//        helper.setText(R.id.mTvjuan, item.getCouponInfo());
//        helper.setText(R.id.tvccommunity,item.getzkFinalPrice());
//        helper.setText(R.id.mTvYishou, item.getVolume());
//        ImageView mImglist=helper.getView(R.id.mImglist);
//        Glide.with(mContext)
//                .load(item.getPictUrl())
//                .placeholder(R.mipmap.default_error)
//                .error(R.mipmap.default_error)
//                .centerCrop()
//                .crossFade()
//                .into(mImglist);
        }
    }
}
