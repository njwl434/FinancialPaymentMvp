package com.saileikeji.ebangren.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.TestInfo;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/7 15:35
 * @mailbox: 1034905058@qq.com
 */

public class JobBaseAdapte extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
    public JobBaseAdapte() {
        super(R.layout.item_job, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestInfo item) {
        helper.setText(R.id.mTvTitle, item.getName());
//        helper.setImageResource(R.id.ImgItemHome, R.mipmap.ic_zhanwei);
//        ImageView imge = helper.getView(R.id.ImgItemHome);
//        helper.setText(R.id.quanhoutv, item.getzkFinalPrice());
//        helper.setText(R.id.quantva, item.getVolume());
//        helper.setText(R.id.itemServiceLogTvBtn, item.getCouponInfo());
//        helper.addOnClickListener(R.id.shouchangImg);
//        if (item.getIsColle().equals("1")) {
//            helper.setImageResource(R.id.shouchangImg, R.drawable.home_collection_r);
//        } else {
//            helper.setImageResource(R.id.shouchangImg, R.drawable.home_collection_g);
//        }
//        Glide.with(getActivity())
//                .load(item.getPictUrl())
//                .placeholder(R.mipmap.default_error)
//                .error(R.mipmap.default_error)
//                .centerCrop()
//                .crossFade()
//                .into(imge);
//    }
    }}