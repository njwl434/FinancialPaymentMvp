package com.wl.caiwushoukuan.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wl.caiwushoukuan.R;
import com.wl.caiwushoukuan.adapter.WorkAdapter;
import com.wl.caiwushoukuan.ui.base.BaseActivity;
import com.wl.wllibrary.view.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: 主页面
 * @author: 武梁
 * @date: 2017/12/26 13:57
 * @mailbox: 1034905058@qq.com
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_number_work)
    TextView tvNumberWork;
    @Bind(R.id.tv_client_work)
    TextView tvClientWork;
    @Bind(R.id.tv_order_work)
    TextView tvOrderWork;
    @Bind(R.id.tv_warehouse_work)
    TextView tvWarehouseWork;
    @Bind(R.id.tv_finance_work)
    TextView tvFinanceWork;
    @Bind(R.id.tv_other_work)
    TextView tvOtherWork;
    @Bind(R.id.rv_content_work)
    RecyclerView rvContentWork;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    WorkAdapter workAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        rvContentWork.setLayoutManager(layoutManage);
        workAdapter = new WorkAdapter();
        rvContentWork.setAdapter(workAdapter);
        workAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }
}
