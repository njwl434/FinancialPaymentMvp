package com.saileikeji.ebangren.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.adapter.OrganizationAdapter;
import com.saileikeji.ebangren.bean.TestInfo;
import com.saileikeji.ebangren.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/2 13:51
 * @mailbox: 1034905058@qq.com
 */

public class OrganizationFragment extends BaseFragment {
    private final static String ARG_C = "centent";

    @Bind(R.id.ImageSearch)
    ImageView ImageSearch;
    @Bind(R.id.ImageSearchx)
    ImageView ImageSearchx;
    @Bind(R.id.editSearch)
    TextView editSearch;
    @Bind(R.id.etlay)
    RelativeLayout etlay;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.mlaytypesort)
    LinearLayout mlaytypesort;
    @Bind(R.id.mlayNearbysort)
    LinearLayout mlayNearbysort;
    @Bind(R.id.mlayCapacitybysort)
    LinearLayout mlayCapacitybysort;
    @Bind(R.id.mRecycleor)
    RecyclerView mRecycleor;
    OrganizationAdapter adapter;
    List<TestInfo> testInfoList=new ArrayList<>();
    View rootView;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home_organization;
    }

    @Override
    public void initView(View rootView) {
        this.rootView=rootView;
    }

    public static OrganizationFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        OrganizationFragment fragment = new OrganizationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        testInfoList.clear();
        for (int i = 0; i < 6; i++) {
            TestInfo in = new TestInfo();
            in.setName("测试" + 1);
            testInfoList.add(in);
        }
        adapter=new OrganizationAdapter();
        mRecycleor.setLayoutManager(new LinearLayoutManager(mRecycleor.getContext()));
        mRecycleor.setAdapter(adapter);
        adapter.setNewData(testInfoList);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.mlaytypesort, R.id.mlayNearbysort, R.id.mlayCapacitybysort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mlaytypesort:
                break;
            case R.id.mlayNearbysort:
                break;
            case R.id.mlayCapacitybysort:
                break;
                default:
        }
    }
}
