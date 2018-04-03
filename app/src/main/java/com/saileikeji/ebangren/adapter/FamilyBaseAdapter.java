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

public class FamilyBaseAdapter extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
    public FamilyBaseAdapter() {
        super(R.layout.item_main_family, null);
    }
    @Override
    protected void convert(BaseViewHolder helper,TestInfo item) {
        helper.setText(R.id.mTvTitle, item.getName());
//        helper.setText(R.id.mTvjuan, item.getCouponInfo());
//        helper.setText(R.id.tvccommunity,item.getzkFinalPrice());
//        helper.setText(R.id.mTvYishou, item.getVolume());
        ImageView mImglist=helper.getView(R.id.mIvImg);
        Glide.with(mContext)
                .load(R.mipmap.ic_zhanwei)
                .placeholder(R.mipmap.default_error)
                .error(R.mipmap.default_error)
                .centerCrop()
                .crossFade()
                .into(mImglist);
    }
}