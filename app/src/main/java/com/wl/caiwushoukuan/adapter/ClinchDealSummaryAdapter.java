package com.wl.caiwushoukuan.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wl.caiwushoukuan.R;
import com.wl.caiwushoukuan.dao.Loan;


/**
 * 描述: 成交汇总
 * 作者: 武梁
 * 时间: 2017/10/23 10:23
 * 邮箱: 1034905058@qq.com
 */

public class ClinchDealSummaryAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public ClinchDealSummaryAdapter() {
        super( R.layout.item_clinchdealsummary, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, T t) {
        if (t instanceof Loan) {
            Loan a=(Loan)t;
                helper.setText(R.id.itemRecodrdTva,"¥"+ a.getLoanprice());
            helper.setText(R.id.itemRecodrdTvOrder, a.getName());
            helper.setText(R.id.itemRecodrdphone,a.getPhone());
            helper.setText(R.id.itemadress,a.getAddress());
            helper.setText(R.id.itimea,a.getLoandate());
            helper.setText(R.id.itimeb,a.getUnloandate());
            helper.setText(R.id.summarylaytva,a.getInterest());
            helper.setText(R.id.summarylaytvb,a.getPayments());
            helper.setText(R.id.summarylaytvc,a.getLousfrontal());
        }

}

}