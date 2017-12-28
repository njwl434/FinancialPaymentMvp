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
import com.wl.caiwushoukuan.bean.TestInfo;
import com.wl.caiwushoukuan.ui.base.BaseActivity;
import com.wl.wllibrary.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.tv_finance_work)
    TextView tvFinanceWork;
    @Bind(R.id.tv_other_work)
    TextView tvOtherWork;
    @Bind(R.id.rv_content_work)
    RecyclerView rvContentWork;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    WorkAdapter workAdapter;
    List<TestInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        rvContentWork.setLayoutManager(layoutManage);
        workAdapter = new WorkAdapter();
        rvContentWork.setAdapter(workAdapter);
        workAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        getData();
        workAdapter.setNewData(list);
    }

    /**
     *
     */
    private void getData() {
        TestInfo in = new TestInfo();
        in.setName("我的客户");
        in.setImg(R.mipmap.myclient);
        list.add(in);
        TestInfo in1 = new TestInfo();
        in1.setName("添加客户");
        in1.setImg(R.mipmap.myclient);
        list.add(in);
    }

    @OnClick({R.id.tv_number_work, R.id.tv_client_work, R.id.tv_finance_work, R.id.tv_other_work})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_number_work:
                break;
            //客户
            case R.id.tv_client_work:

                break;
            //订单
            case R.id.tv_finance_work:
                break;
            //其他
            case R.id.tv_other_work:
                break;
            default:
        }
    }
}
