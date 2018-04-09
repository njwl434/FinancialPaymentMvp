package com.saileikeji.ebangren.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * @describe: 更多机构
 * @author: 武梁
 * @date: 2018/4/9 09:16
 * @mailbox: 1034905058@qq.com
 */

public class MoreOrganizationActivity extends BaseActivity {

    @Bind(R.id.mTopBar)
    TopBar mTopBar;
    @Bind(R.id.a_tv_typepaixu)
    TextView aTvTypepaixu;
    @Bind(R.id.mlaytypesort)
    LinearLayout mlaytypesort;
    @Bind(R.id.a_tv_fujinpaixu)
    TextView aTvFujinpaixu;
    @Bind(R.id.mlayNearbysort)
    LinearLayout mlayNearbysort;
    @Bind(R.id.a_tv_zhinengpaixu)
    TextView aTvZhinengpaixu;
    @Bind(R.id.mlayCapacitybysort)
    LinearLayout mlayCapacitybysort;
    @Bind(R.id.a_recycle_courseview)
    RecyclerView mRecycleor;
    OrganizationAdapter adapter;
    List<TestInfo> testInfoList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_orgennization);
        ButterKnife.bind(this);
        testInfoList.clear();
        for (int i = 0; i < 6; i++) {
            TestInfo in = new TestInfo();
            in.setName("测试" + 1);
            testInfoList.add(in);
        }
        adapter = new OrganizationAdapter();
        mRecycleor.setLayoutManager(new LinearLayoutManager(mRecycleor.getContext()));
        mRecycleor.setAdapter(adapter);
        adapter.setNewData(testInfoList);
    }

    @OnClick({R.id.mlaytypesort, R.id.mlayNearbysort, R.id.mlayCapacitybysort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mlaytypesort:
                aTvTypepaixu.setTextColor( getResources().getColor(R.color.lay_blue));
                aTvFujinpaixu.setTextColor( getResources().getColor(R.color.text_gray33));
                aTvZhinengpaixu.setTextColor( getResources().getColor(R.color.text_gray33));
                break;
            case R.id.mlayNearbysort:
                aTvTypepaixu.setTextColor( getResources().getColor(R.color.text_gray33));
                aTvFujinpaixu.setTextColor( getResources().getColor(R.color.lay_blue));
                aTvZhinengpaixu.setTextColor( getResources().getColor(R.color.text_gray33));
                break;
            case R.id.mlayCapacitybysort:
                aTvTypepaixu.setTextColor( getResources().getColor(R.color.text_gray33));
                aTvFujinpaixu.setTextColor( getResources().getColor(R.color.text_gray33));
                aTvZhinengpaixu.setTextColor( getResources().getColor(R.color.lay_blue));
                break;
                default:
        }
    }
}
