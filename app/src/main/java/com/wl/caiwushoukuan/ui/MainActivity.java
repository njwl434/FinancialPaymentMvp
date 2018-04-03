package com.wl.caiwushoukuan.ui;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wl.caiwushoukuan.R;
import com.wl.caiwushoukuan.adapter.WorkAdapter;
import com.wl.caiwushoukuan.bean.TestInfo;
import com.wl.caiwushoukuan.ui.base.BaseActivity;
import com.wl.caiwushoukuan.widgit.UIUtils;
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
    List<TestInfo> lista = new ArrayList<>();
    private WorkAdapter workAdapter;
    List<TextView> list = new ArrayList<>();
    List<Zone> clientList = new ArrayList<>();
    List<Zone> orderList = new ArrayList<>();
    List<Zone> financeList = new ArrayList<>();
    List<Zone> otherList = new ArrayList<>();
    private long exitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activi4ty_main);
        ButterKnife.bind(this);
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        rvContentWork.setLayoutManager(layoutManage);
        workAdapter = new WorkAdapter();
        rvContentWork.setAdapter(workAdapter);
        workAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Zone zone = (Zone) adapter.getData().get(position);
                startActivity(zone.getIntent());
            }
        });
        list.add(tvClientWork);
        list.add(tvFinanceWork);
        list.add(tvOtherWork);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        getData();
        move(tvFinanceWork);
        workAdapter.setNewData(orderList);
    }

    private void getData() {
        addChildZone(clientList, "myCustomer");
//        addChildZone(clientList, "addCollect");
        addChildZone(orderList, "makeWorksheet");
        addChildZone(orderList, "collectWorksheet");
        addChildZone(otherList, "areaManage");


    }


    @OnClick({R.id.tv_client_work, R.id.tv_finance_work, R.id.tv_other_work})
    public void onViewClicked(View view) {
        if (System.currentTimeMillis() - exitTime > 400) {
            exitTime = System.currentTimeMillis();
            Log.i(TAG, "onViewClicked: 大于600,  " + exitTime);
            moveBack(list);
            switch (view.getId()) {
                case R.id.tv_client_work:
                    move(tvClientWork);
                    workAdapter.setNewData(clientList);
                    break;
                case R.id.tv_finance_work:
                    move(tvFinanceWork);
                    workAdapter.setNewData(orderList);
                    break;
                case R.id.tv_other_work:
                    move(tvOtherWork);
                    workAdapter.setNewData(otherList);
                    break;
                default:
                    break;
            }
            workAdapter.notifyDataSetChanged();
            rvContentWork.scrollToPosition(0);
        } else {
//            Toast.makeText(MainActivity.this, "请不要过快点击", Toast.LENGTH_SHORT).show();
        }
    }

    private void move(TextView textView) {
        if (textView.getTag() == null || textView.getTag().equals(true)) {
            textView.setTag(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView.setBackground(getResources().getDrawable(R.drawable.shape_half_circle_right));
                textView.setTextColor(getResources().getColor(R.color.white));
            }
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "translationX", UIUtils.dip2Px(8), UIUtils.dip2Px(16), UIUtils.dip2Px(25), UIUtils.dip2Px(16));
            animator1.setDuration(400);
            animator1.start();
        } else {
            textView.setTag(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView.setBackground(getResources().getDrawable(R.drawable.shape_half_circle_right_light));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "translationX", 0);
            animator1.setDuration(100);
            animator1.start();
        }
    }

    private void moveBack(List<TextView> list) {
        for (TextView textView : list
                ) {
            if (textView.getTag() != null && textView.getTag().equals(false)) {
                textView.setTag(true);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "translationX", 0);
                animator1.setDuration(100);
                animator1.start();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView.setBackground(getResources().getDrawable(R.drawable.shape_half_circle_right_light));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }


    public class Zone {
        String name;
        int imgSrc;
        Intent intent;

        public void setName(String name) {
            this.name = name;
        }

        public void setImgSrc(int imgSrc) {
            this.imgSrc = imgSrc;
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        public Zone() {
        }

        public Zone(String name, int imgSrc, Intent intent) {
            this.name = name;
            this.imgSrc = imgSrc;
            this.intent = intent;
        }

        public Zone(String name, int imgSrc, Class<?> aClass) {
            this.name = name;
            this.imgSrc = imgSrc;
            this.intent = new Intent(MainActivity.this, aClass);
        }

        public Intent getIntent() {
            return intent;
        }

        public String getName() {
            return name;
        }

        public int getImgSrc() {
            return imgSrc;
        }
    }


    private void addChildZone(List<Zone> zoneList, String childCode) {

        switch (childCode) {

            //客户
            case "myCustomer":
                zoneList.add(new Zone("我的客户", R.mipmap.myclient, new Intent(MainActivity.this, MyClientActivity.class)));
                break;
            case "addCollect":
                zoneList.add(new Zone("添加客户", R.mipmap.workorderget, new Intent(MainActivity.this, MyClientActivity.class)));
                break;
            case "makeWorksheet":
                zoneList.add(new Zone("生成订单", R.mipmap.workordergeneration, GenerateRepairOrderActivity.class));
                break;
            case "collectWorksheet":
                zoneList.add(new Zone("订单汇总", R.mipmap.workordersummary, WorkOrderSummaryActivity.class));
                break;
            case "areaManage":
                zoneList.add(new Zone("生成Excel", R.mipmap.districtmanage, ExcelActivity.class));
                break;
//            case "getWorksheet":
//                zoneList.add(new Zone("我要接单", R.mipmap.workorderget, WorkOrderGetActivity.class));
//                break;
//            case "myWorksheet":
//                zoneList.add(new Zone("我的工单", R.mipmap.workorderme, WorkOrderMeActivity.class));
//                break;
//            //其他
//            case "organization":
//                zoneList.add(new Zone("组织架构", R.mipmap.ic_orgnize, ManageActivity.class));
//                break;
//            case "selectList":
//                zoneList.add(new Zone("下拉分类", R.mipmap.ic_pull, PullDownActivity.class));
//                break;
//            case "productCenter":
//                zoneList.add(new Zone("公司产品", R.mipmap.ic_product, GoodsListActivity.class));
//                break;
//            case "dailyRecord":
////                zoneList.add(new Zone("日志计划", R.mipmap.ic_diary, LogAndPlanActivity.class));
//                break;
//            case "companySetting":
//                zoneList.add(new Zone("公司设置", R.mipmap.ic_company, CompanySetActivity.class));
//                break;
            //            case "orderCollectForService":
////                zoneList.add(new Zone("成交汇总（客服）", R.mipmap.deallist, DealListActivity.class));
//                break;
//            case "orderCollectForFinance":
//                zoneList.add(new Zone("成交汇总（财务）", R.mipmap.deallist, DealListFinanceActivity.class));
//                break;
//            case "areaManage":
//                zoneList.add(new Zone("区域管理", R.mipmap.districtmanage, EditDistrictActivity.class));
//                break;
//            case "customerRecycleBin":
//                zoneList.add(new Zone("客户回收站", R.mipmap.clientrecycler, RecyclerClientActivity.class));
//                break;
//            //库房
//            case "myInventory":
//                zoneList.add(new Zone("我的库房", R.mipmap.ic_mywarehouse, MyWarehouseActivity.class));
//                break;
//            case "purchase":
//                zoneList.add(new Zone("进货", R.mipmap.ic_buygoods, BuyGoodsActivity.class));
//                break;
//            case "inInventory":
//                zoneList.add(new Zone("入库", R.mipmap.ic_income, CheckInActivity.class));
//                break;
//            case "outInventory":
////                zoneList.add(new Zone("出库", R.mipmap.clientrecycler, ManageActivity.class));
//                break;
//            case "pendingIn":
//                zoneList.add(new Zone("待入库", R.mipmap.ic_toincome, new Intent(MainActivity.this, ToCheckActivity.class).putExtra("isCheckIn", true)));
//                break;
//            case "pendingOut":
//                zoneList.add(new Zone("待出库", R.mipmap.ic_toout, ToCheckActivity.class));
//                break;
//            case "inventorySetting":
//                zoneList.add(new Zone("库房设置", R.mipmap.ic_warehousesetting, new Intent(MainActivity.this, WareHouseSetActivity.class).putExtra("type", WARE_HOUSE)));
//                break;
//            case "productSetting":
//                zoneList.add(new Zone("商品设置", R.mipmap.ic_goodsetting, GoodSettingActivity.class));
//                break;
//            case "inventoryDayBook":
//                zoneList.add(new Zone("库房流水", R.mipmap.ic_warehouse_record, WarehouseHistoryActivity.class));
//                break;
//            case "inventoryProduct":
//                zoneList.add(new Zone("库存商品", R.mipmap.ic_goods, StockActivity.class));
//                break;
//            case "transGoods":
////                zoneList.add(new Zone("调货", R.mipmap.clientrecycler, EmptyActivity.class));
//                break;
//            //财务
//            case "checkReimbursement":
//                zoneList.add(new Zone("审核", R.mipmap.ic_auditing, AuditingActivity.class));
//                break;
//            case "reimbursement":
//                zoneList.add(new Zone("报销", R.mipmap.reimbursement, WipeOutListActivity.class));
//                break;
//            case "income":
//                zoneList.add(new Zone("收入", R.mipmap.ic_incomeing, IncomeActivity.class));
//                break;
//            case "expend":
//                zoneList.add(new Zone("支出", R.mipmap.ic_pay, PayActivity.class));
//                break;
//            case "pendingInAccount":
//                zoneList.add(new Zone("待入账", R.mipmap.ic_tobecredited, ToBeCreditedActivity.class));
//                break;
//            case "pendingReceipt":
//                zoneList.add(new Zone("应收账款", R.mipmap.ic_accountsreceivable, AccountsReceivableActivity.class));
//                break;
//            case "badDebt":
//                zoneList.add(new Zone("坏账", R.mipmap.clientrecycler, EmptyActivity.class));
//                break;
//            case "bonus":
//                zoneList.add(new Zone("奖金", R.mipmap.ic_wipeout, BonusActivity.class));
//                break;
//            case "chart":
//                zoneList.add(new Zone("报表", R.mipmap.ic_reportforms, ReportFormsActivity.class));
//                break;
////工单
            default:
                break;
        }
    }

}
