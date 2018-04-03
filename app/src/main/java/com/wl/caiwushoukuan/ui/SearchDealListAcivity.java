package com.wl.caiwushoukuan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.wl.caiwushoukuan.R;
import com.wl.caiwushoukuan.WlApplication;
import com.wl.caiwushoukuan.adapter.ClinchDealSummaryAdapter;
import com.wl.caiwushoukuan.dao.Loan;
import com.wl.caiwushoukuan.dao.LoanDao;
import com.wl.caiwushoukuan.ui.base.BaseActivity;
import com.wl.caiwushoukuan.widgit.SPConstant;
import com.wl.wllibrary.util.SharedPreferenceUtil;
import com.wl.wllibrary.view.CustomSearchEditText;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @describe: 成交汇总查询
 * @author: 武梁
 * @date: 2017/11/24 00:50
 * @mailbox: 1034905058@qq.com
 */
public class SearchDealListAcivity extends BaseActivity {

    @Bind(R.id.et_search_myclient)
    CustomSearchEditText etSearchMyClient;
    @Bind(R.id.tv_total_client)
    TextView tvTotalClient;
    @Bind(R.id.rv_my_clients)
    RecyclerView rvMyClients;
    @Bind(R.id.tv_cancel_search)
    TextView tvCancelSearch;
    private ClinchDealSummaryAdapter adapter;
    private int page = 1;
    private int pageSize = 10;
    private String range = "all";
    int clientPosition = 0;
    private int isOld = 1;
    private boolean isAllClient;
    private String type;
    String companyID;
    List<Loan> rxlist=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_deallist);
        ButterKnife.bind(this);
        rvMyClients.setLayoutManager(new LinearLayoutManager(SearchDealListAcivity.this));
        adapter = new ClinchDealSummaryAdapter();
        rvMyClients.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {


            }
        });
        RxTextView.textChangeEvents(etSearchMyClient)
                .debounce(500, TimeUnit.MILLISECONDS)//debounce是在600毫秒内没有操作就发生事件
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        rxlist=WlApplication.getDaoInstant().getLoanDao().queryBuilder().where(LoanDao.Properties.Name.like(textViewTextChangeEvent.toString())).list();
                        if (rxlist.size()==0) {
                            rxlist=WlApplication.getDaoInstant().getLoanDao().queryBuilder().where(LoanDao.Properties.Phone.like(textViewTextChangeEvent.toString())).list();
                        }
                        Log.e("----rxlist------",rxlist.size()+"");
                        if (rxlist.size() != 0) {
                            tvTotalClient.setText("共" + rxlist.size() + "个订单");
                            adapter.setNewData(rxlist);
                        } else {
//                            tvTotalClient.setText("");
                        }
                    }
                });
    }

    @OnClick(R.id.tv_cancel_search)
    public void onViewClicked() {
        finish();
    }


}
