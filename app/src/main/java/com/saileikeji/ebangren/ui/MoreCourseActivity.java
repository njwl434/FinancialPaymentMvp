package com.saileikeji.ebangren.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.adapter.OrganizationAdapter;
import com.saileikeji.ebangren.bean.TestInfo;
import com.saileikeji.ebangren.ui.base.BaseActivity;
import com.saileikeji.wllibrary.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @describe: 更多课程
 * @author: 武梁
 * @date: 2018/4/9 08:55
 * @mailbox: 1034905058@qq.com
 */

public class MoreCourseActivity extends BaseActivity implements TopBar.OnTopBarClickListenner{

    @Bind(R.id.mTopBar)
    TopBar mTopBar;
    @Bind(R.id.a_tv_typepaixu)
    TextView aTvTypepaixu;
    @Bind(R.id.mlaytypesort)
    LinearLayout mlaytypesort;
    @Bind(R.id.a_tv_pricesort)
    TextView aTvPricesort;
    @Bind(R.id.mlayNearbysort)
    LinearLayout mlayNearbysort;
    @Bind(R.id.a_tv_zhinengpaixu)
    TextView aTvZhinengpaixu;
    @Bind(R.id.mlayCapacitybysort)
    LinearLayout mlayCapacitybysort;
    @Bind(R.id.a_tv_fujinpaixu)
    TextView aTvFujinpaixu;
    @Bind(R.id.mlayCapacitybyfujin)
    LinearLayout mlayCapacitybyfujin;
    @Bind(R.id.a_recycle_courseview)
    RecyclerView aRecycleCourseview;
    MoreCourseAdapter adapter;
    List<TestInfo> testInfoList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_course);
        ButterKnife.bind(this);
        mTopBar.setOnTopBarClickListenner(this);
        testInfoList.clear();
        for (int i = 0; i < 6; i++) {
            TestInfo in = new TestInfo();
            in.setName("测试" + 1);
            testInfoList.add(in);
        }
        adapter = new MoreCourseAdapter();
        aRecycleCourseview.setLayoutManager(new LinearLayoutManager(aRecycleCourseview.getContext()));
        aRecycleCourseview.setAdapter(adapter);
        adapter.setNewData(testInfoList);
    }

    @OnClick({R.id.mlaytypesort, R.id.mlayNearbysort, R.id.mlayCapacitybysort, R.id.mlayCapacitybyfujin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mlaytypesort:
                aTvTypepaixu.setTextColor(getResources().getColor(R.color.lay_blue));
                aTvPricesort.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvZhinengpaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvFujinpaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                break;
            case R.id.mlayNearbysort:
                aTvTypepaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvPricesort.setTextColor(getResources().getColor(R.color.lay_blue));
                aTvZhinengpaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvFujinpaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                break;
            case R.id.mlayCapacitybysort:
                aTvTypepaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvPricesort.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvZhinengpaixu.setTextColor(getResources().getColor(R.color.lay_blue));
                aTvFujinpaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                break;
            case R.id.mlayCapacitybyfujin:
                aTvTypepaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvPricesort.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvZhinengpaixu.setTextColor(getResources().getColor(R.color.text_gray33));
                aTvFujinpaixu.setTextColor(getResources().getColor(R.color.lay_blue));
                break;
                default:
        }
    }

    @Override
    public void onLeftClicked() {
        onBackPressed();
    }

    @Override
    public void onRightClicked() {

    }

    @Override
    public void onRightImgClicked() {

    }

    public class MoreCourseAdapter extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
        public MoreCourseAdapter() {
            super(R.layout.item_main_morecourse, null);
        }
        @Override
        protected void convert(BaseViewHolder helper,TestInfo item) {
            helper.setText(R.id.tvacommunity, item.getName());
//        helper.setText(R.id.mTvjuan, item.getCouponInfo());
//        helper.setText(R.id.tvccommunity,item.getzkFinalPrice());
//        helper.setText(R.id.mTvYishou, item.getVolume());
//        ImageView mImglist=helper.getView(R.id.mImglist);
//        Glide.with(mContext)
//                .load(item.getPictUrl())
//                .placeholder(R.mipmap.default_error)
//                .error(R.mipmap.default_error)
//                .centerCrop()
//                .crossFade()
//                .into(mImglist);
        }
    }
}
