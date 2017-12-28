package com.wl.caiwushoukuan.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wl.caiwushoukuan.R;
import com.wl.caiwushoukuan.bean.TestInfo;

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
