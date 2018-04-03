package com.saileikeji.ebangren.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.TestInfo;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/2 09:50
 * @mailbox: 1034905058@qq.com
 */

public class CourseBaseAdapte extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
    public CourseBaseAdapte() {
        super(R.layout.item_course_base, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestInfo item) {
        helper.setText(R.id.TvItemHome, item.getName());
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
