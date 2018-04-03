package com.saileikeji.ebangren.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.TestInfo;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/2 14:35
 * @mailbox: 1034905058@qq.com
 */

public class OrganizationAdapter extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
    public OrganizationAdapter() {
        super(R.layout.item_main_organization, null);
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
