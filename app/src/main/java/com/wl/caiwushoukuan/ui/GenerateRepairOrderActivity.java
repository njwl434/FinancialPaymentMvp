package com.wl.caiwushoukuan.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.wl.caiwushoukuan.R;
import com.wl.caiwushoukuan.WlApplication;
import com.wl.caiwushoukuan.dao.Loan;
import com.wl.caiwushoukuan.dao.LoanDaoUtil;
import com.wl.caiwushoukuan.ui.base.BaseActivity;
import com.wl.caiwushoukuan.util.Util;
import com.wl.wllibrary.view.CapacityEditText;
import com.wl.wllibrary.view.DeleteEditText;
import com.wl.wllibrary.view.TopBar;

import java.text.ParseException;
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
public class GenerateRepairOrderActivity extends BaseActivity implements TopBar.OnTopBarClickListenner {
    @Bind(R.id.mTopBar)
    TopBar mTopBar;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_name_old)
    DeleteEditText etNameOld;
    @Bind(R.id.et_phone_old)
    DeleteEditText etPhoneOld;
    @Bind(R.id.et_address_old)
    DeleteEditText etAddressOld;
    @Bind(R.id.et_date_olda)
    TextView etDateOlda;
    @Bind(R.id.iv_date_olda)
    ImageView ivDateOlda;
    @Bind(R.id.line_manager_detail)
    TextView lineManagerDetail;
    @Bind(R.id.et_date_oldb)
    TextView etDateOldb;
    @Bind(R.id.iv_date_oldb)
    ImageView ivDateOldb;
    @Bind(R.id.ll_manager_edit)
    LinearLayout llManagerEdit;
    @Bind(R.id.et_cs_olda)
    DeleteEditText etCsOlda;
    @Bind(R.id.iv_district_old)
    ImageView ivDistrictOld;
    @Bind(R.id.et_sum_old)
    DeleteEditText etSumOld;
    @Bind(R.id.et_goods_old)
    DeleteEditText etGoodsOld;
    @Bind(R.id.et_goods_olda)
    DeleteEditText etGoodsOlda;
    @Bind(R.id.et_goods_oldb)
    DeleteEditText etGoodsOldb;
    @Bind(R.id.et_goods_oldc)
    DeleteEditText etGoodsOldc;
    @Bind(R.id.et_goods_oldd)
    DeleteEditText etGoodsOldd;
    @Bind(R.id.et_record_old)
    CapacityEditText etRecordOld;
    @Bind(R.id.ll_old_client_detail)
    LinearLayout llOldClientDetail;
    @Bind(R.id.et_communication_edit)
    CapacityEditText etCommunicationEdit;
    @Bind(R.id.et_remark_old)
    CapacityEditText etRemarkOld;
    private int mYear, mMonth, mDay;
    private int mYearA, mMonthA, mDayA;
    final List<String> strings = new ArrayList<>();
    String areaId;
    private String timePoint = "";
    String id;
    private boolean isOldClient;
    private String daya,dayb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让布局向上移来显示软键盘
        //    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_edit_old_client);
        ButterKnife.bind(this);
        mTopBar.setOnTopBarClickListenner(this);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        Calendar caA = Calendar.getInstance();
        mYearA = caA.get(Calendar.YEAR);
        mMonthA = caA.get(Calendar.MONTH);
        mDayA = caA.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onLeftClicked() {
        onBackPressed();
    }

    @Override
    public void onRightClicked() {
        if (checkEmpty(etNameOld)) {
            toast("请输入贷款人姓名");
            return;
        }
        if (checkEmpty(etPhoneOld)) {
            toast("请输入贷款金额");
            return;
        }
        if (checkEmpty(etAddressOld)) {
            toast("请输入每日利息");
            return;
        }
        if (TextUtils.isEmpty(daya)) {
            toast("请输入贷款日期");
            return;
        }
        if (TextUtils.isEmpty(dayb)) {
            toast("请输入还款日期");
            return;
        }

        if (checkEmpty(etCsOlda)) {
            toast("请输入手机号码");
            return;
        }
        if (checkEmpty(etRecordOld)) {
            toast("请输入家庭住址");
            return;
        }
        if (checkEmpty(etSumOld)) {
            toast("请输入家属姓名");
            return;
        }
        if (checkEmpty(etGoodsOld)) {
            toast("请输入还款额");
            return;
        }
        if (checkEmpty(etGoodsOlda)) {
            toast("请输入第一月到手金额");
            return;
        }
        if (checkEmpty(etGoodsOldb)) {
            toast("请输入欠条额");
            return;
        }
        if (checkEmpty(etGoodsOldc)) {
            toast("请输入业务员");
            return;
        }
        if (checkEmpty(etGoodsOldd)) {
            toast("请输入违约次数");
            return;
        }
        Loan data=new Loan();
        data.setName(etNameOld.getText().toString());
        data.setLoanprice(etPhoneOld.getText().toString());
        data.setInterest(etAddressOld.getText().toString());
        data.setLoandate(daya);
        data.setUnloandate(dayb);
        data.setPhone(etCsOlda.getText().toString());
        data.setAddress(etRecordOld.getText().toString());
        data.setFamilyname(etSumOld.getText().toString());
        data.setPayments(etGoodsOld.getText().toString());
        data.setMonthone(etGoodsOlda.getText().toString());
        data.setLousfrontal(etGoodsOldb.getText().toString());
        data.setSalesman(etGoodsOldc.getText().toString());
        data.setDefaultnumber(Integer.valueOf(etGoodsOldd.getText().toString()));
        try {
            WlApplication.getDaoInstant().getLoanDao().insertOrReplace(data);
            toast("添加成功");
            finish();
        }catch (Exception e){
            toast("添加失败");
        }

    }


    @OnClick({ R.id.iv_district_old, R.id.et_date_olda, R.id.et_date_oldb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_district_old:
//                if (strings.isEmpty()) {
//                    toast("网络欠佳，请稍后重试");
//                } else {
//                    StyledDialog.buildIosSingleChoose(strings, new MyItemDialogListener() {
//                        @Override
//                        public void onItemClick(CharSequence charSequence, int i) {
//                            etDistrictOld.setText(charSequence.toString());
//                            if (districtBeanList != null) {
//                                areaId = districtBeanList.get(i).getId();
//                            }
//                        }
//                    }).setCancelable(true, true).show();
//                }
                break;
            case R.id.et_date_olda:
                new DatePickerDialog(GenerateRepairOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYearA = year;
                        mMonthA = month;
                        mDayA = dayOfMonth;
                        String days;
                        if (mMonth + 1 < 10) {
                            if (mDayA < 10) {
                                days = new StringBuffer().append(mYearA).append("年").append("0").
                                        append(mMonthA + 1).append("月").append("0").append(mDayA).append("日").toString();
                            } else {
                                days = new StringBuffer().append(mYearA).append("年").append("0").
                                        append(mMonthA + 1).append("月").append(mDayA).append("日").toString();
                            }
                        } else {
                            if (mDayA < 10) {
                                days = new StringBuffer().append(mYearA).append("年").
                                        append(mMonthA + 1).append("月").append("0").append(mDayA).append("日").toString();
                            } else {
                                days = new StringBuffer().append(mYearA).append("年").
                                        append(mMonthA + 1).append("月").append(mDayA).append("日").toString();
                            }
                        }
                        etDateOlda.setText(days);
                        daya=days;
                        String timestr = mYearA + "-" + mMonthA + "-" + mDayA + " " + 12 + ":" + "00" + ":" + "00";
                        try {
                            timePoint = Util.dateToStamp(timestr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYearA, mMonthA, mDayA).show();
                break;
            case R.id.et_date_oldb:
                new DatePickerDialog(GenerateRepairOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        etDateOldb.setText(days);
                        dayb=days;
                        String timestr = mYear + "-" + mMonth + "-" + mDay + " " + 12 + ":" + "00" + ":" + "00";
                        try {
                            timePoint = Util.dateToStamp(timestr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay).show();
                break;
            default:
                break;
        }
    }


    private boolean checkEmpty(EditText et) {
        return et.getText().toString().trim().isEmpty();
    }


}
