package com.saileikeji.ebangren.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.TestInfo;

/**
 * @describe: FinancialPaymentMvp
 * @author: 武梁
 * @date: 2017/12/26 14:26
 * @mailbox: 1034905058@qq.com
 */

public class WorkAdapter extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
    public WorkAdapter() {
        super(R.layout.item_work, null);
    }
    @Override
    protected void convert(BaseViewHolder helper, TestInfo item) {
        helper.setText(R.id.mTvName, item.getName());
        helper.setImageResource(R.id.mIvImg,item.getImg());

    }
}
