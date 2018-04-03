package com.saileikeji.ebangren.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.TestInfo;

/**
 * @describe: 
 * @author: 武梁
 * @date: 2017/12/29 10:04
 * @mailbox: 1034905058@qq.com
 */

public class TuijianBaseAdapter extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
    public TuijianBaseAdapter() {
        super(R.layout.item_main_list, null);
    }
    @Override
    protected void convert(BaseViewHolder helper,TestInfo item) {
        helper.setText(R.id.tvacommunity, item.getName());
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