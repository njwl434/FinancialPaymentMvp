package com.wl.caiwushoukuan.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wl.caiwushoukuan.R;
import com.wl.caiwushoukuan.WlApplication;
import com.wl.caiwushoukuan.adapter.ClinchDealSummaryAdapter;
import com.wl.caiwushoukuan.dao.Loan;
import com.wl.caiwushoukuan.dao.LoanDao;
import com.wl.caiwushoukuan.dao.LoanDaoUtil;
import com.wl.caiwushoukuan.ui.base.BaseActivity;
import com.wl.wllibrary.view.TopBar;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @describe: FinancialPaymentMvp
 * @author: 武梁
 * @date: 2017/12/31 18:33
 * @mailbox: 1034905058@qq.com
 */
public class WorkOrderSummaryActivity extends BaseActivity implements TopBar.OnTopBarClickListenner {

    public int mDay;
    public int mMonth;
    public int mYear;
    public long timePointStart;
    public long timePointEnd;
    @Bind(R.id.mTopBar)
    TopBar mTopBar;
    @Bind(R.id.ll_time_deal)
    LinearLayout llTimeDeal;
    @Bind(R.id.ll_filter_deal)
    LinearLayout llFilterDeal;
    @Bind(R.id.ll_search_deal)
    LinearLayout llSearchDeal;
    @Bind(R.id.summarylaytva)
    TextView summarylaytva;
    @Bind(R.id.viewb)
    View viewb;
    @Bind(R.id.summarylaytvb)
    TextView summarylaytvb;
    @Bind(R.id.viewc)
    View viewc;
    @Bind(R.id.textView6)
    TextView textView6;
    @Bind(R.id.summarylaytvc)
    TextView summarylaytvc;
    @Bind(R.id.dddd)
    LinearLayout dddd;
    @Bind(R.id.rv_deal_list)
    RecyclerView rvDealList;
    @Bind(R.id.workordergetImg)
    ImageView workordergetImg;
    @Bind(R.id.workordergetTv)
    TextView workordergetTv;
    private String status = "", startTime = "", endTime = "",
            worktype = "", searchkey = "";
    private int page = 1;
    private int pageSize = 20;
    private int total;
    ClinchDealSummaryAdapter adapter;
    List<Loan> beanlist = new ArrayList<>();
    private String start = "3";
    double allloanprice = 0;
    double allpayments = 0;
    double alllousfrontal = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_finance);
        ButterKnife.bind(this);
        mTopBar.setOnTopBarClickListenner(this);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        adapter = new ClinchDealSummaryAdapter();
        rvDealList.setLayoutManager(new LinearLayoutManager(rvDealList.getContext()));
        rvDealList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        beanlist = LoanDaoUtil.queryAll();
        adapter.setNewData(beanlist);
        int size = beanlist.size();
        for (int i = 0; i < size; i++) {
            double loanprice = Double.valueOf(beanlist.get(i).getLoanprice());
            double payments = Double.valueOf(beanlist.get(i).getPayments());
            double lousfrontal = Double.valueOf(beanlist.get(i).getLousfrontal());
            allloanprice = allloanprice + loanprice;
            allpayments = payments + allpayments;
            alllousfrontal = alllousfrontal + lousfrontal;
        }
        summarylaytva.setText("¥" + allloanprice);
        summarylaytvb.setText("¥" + allpayments);
        summarylaytvc.setText("¥" + alllousfrontal);
        QueryBuilder qb = WlApplication.getDaoInstant().getLoanDao().queryBuilder();

        qb.or(LoanDao.Properties.Loandate.gt(1970),
                qb.and(LoanDao.Properties.Loandate.eq(2017), LoanDao.Properties.Loandate.ge(10)));
        List youngJoes = qb.list();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onLeftClicked() {
        onBackPressed();
    }

    @Override
    public void onRightClicked() {

    }

    @OnClick({R.id.ll_time_deal, R.id.ll_filter_deal, R.id.ll_search_deal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time_deal:
                View contentView = LayoutInflater.from(WorkOrderSummaryActivity.this).inflate(R.layout.pop_filter_deal_time, null);
                final PopupWindow popupWindow = new PopupWindow(contentView,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.showAsDropDown(view);
                final TextView startTime = (TextView) contentView.findViewById(R.id.tv_time_deallist);
                final TextView finishTime = (TextView) contentView.findViewById(R.id.tv_time_finish_deallist);
                TextView tvReset = (TextView) contentView.findViewById(R.id.tv_reset_deal);
                TextView tvOk = (TextView) contentView.findViewById(R.id.tv_ok_deal);
                LinearLayout llStart = (LinearLayout) contentView.findViewById(R.id.ll_start_deal);
                LinearLayout llFinish = (LinearLayout) contentView.findViewById(R.id.ll_finish_deal);

                llStart.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        new DatePickerDialog(WorkOrderSummaryActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear = year;
                                mMonth = month;
                                mDay = dayOfMonth;
                                String days;
                                if (mMonth + 1 < 10) {
                                    if (mDay < 10) {
                                        days = new StringBuffer().append(mYear).append("年").append("0").
                                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                                    } else {
                                        days = new StringBuffer().append(mYear).append("年").append("0").
                                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                                    }
                                } else {
                                    if (mDay < 10) {
                                        days = new StringBuffer().append(mYear).append("年").
                                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                                    } else {
                                        days = new StringBuffer().append(mYear).append("年").
                                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                                    }
                                }
                                String datatime = null;
                                startTime.setText(days);
                            }
                        }, mYear, mMonth, mDay).show();
                    }
                });
                llFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new DatePickerDialog(WorkOrderSummaryActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear = year;
                                mMonth = month;
                                mDay = dayOfMonth;
                                String days;
                                if (mMonth + 1 < 10) {
                                    if (mDay < 10) {
                                        days = new StringBuffer().append(mYear).append("年").append("0").
                                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                                    } else {
                                        days = new StringBuffer().append(mYear).append("年").append("0").
                                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                                    }
                                } else {
                                    if (mDay < 10) {
                                        days = new StringBuffer().append(mYear).append("年").
                                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                                    } else {
                                        days = new StringBuffer().append(mYear).append("年").
                                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                                    }
                                }
                                finishTime.setText(days);
                                String data = null;

                            }
                        }, mYear, mMonth, mDay).show();
                    }
                });
                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
//                        getData(true);
                    }
                });

                break;
            case R.id.ll_filter_deal:
                View contentView1 = LayoutInflater.from(context).inflate(R.layout.pop_filter_deal_status, null);
                final PopupWindow popupWindow1 = new PopupWindow(contentView1,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow1.setTouchable(true);
                popupWindow1.setBackgroundDrawable(new ColorDrawable());
                popupWindow1.showAsDropDown(view);

                View tv_all_filter = contentView1.findViewById(R.id.tv_all_filter);
                tv_all_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        start = "3";
//                        getData(true);
                        popupWindow1.dismiss();
                    }
                });
                //违约1~5次
                View tv_noremind_filter = contentView1.findViewById(R.id.tv_noremind_filter);
                tv_noremind_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        start = "1";
//                        getData(true);
                        popupWindow1.dismiss();
                    }
                });
                //违约10次以上
                View tv_12month_filter = contentView1.findViewById(R.id.tv_12month_filter);
                tv_12month_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        start = "2";
//                        getData(true);
                        popupWindow1.dismiss();
                    }
                });
                //未违约
                View tv_12month_filtera = contentView1.findViewById(R.id.tv_noremind_filtera);
                tv_12month_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        start = "4";
//                        getData(true);
                        popupWindow1.dismiss();
                    }
                });
                break;
            case R.id.ll_search_deal:
                startActivity(new Intent(this, SearchDealListAcivity.class));
                break;
            default:
                break;
        }
    }

}
