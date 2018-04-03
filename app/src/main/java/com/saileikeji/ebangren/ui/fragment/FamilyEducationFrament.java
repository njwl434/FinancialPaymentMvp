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

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.adapter.FamilyBaseAdapter;
import com.saileikeji.ebangren.adapter.GridViewAdapter;
import com.saileikeji.ebangren.bean.QuanziBean;
import com.saileikeji.ebangren.bean.TestInfo;
import com.saileikeji.ebangren.ui.base.BaseFragment;
import com.saileikeji.ebangren.widgit.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/2 16:53
 * @mailbox: 1034905058@qq.com
 */

public class FamilyEducationFrament extends BaseFragment {
    private final static String ARG_C = "centent";
    @Bind(R.id.mlaylout)
    LinearLayout mlaylout;
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
    @Bind(R.id.roll_view_pager)
    RollPagerView mRollViewPager;
    @Bind(R.id.RecycleaHome)
    MyGridView RecycleaHome;
    @Bind(R.id.mTvchome)
    TextView mTvchome;
    @Bind(R.id.mIvImgchome)
    ImageView mIvImgchome;
    @Bind(R.id.mRecycleorFam)
    RecyclerView mRecycleorFam;
    private List<QuanziBean> mList = new ArrayList<>();
    private int[] images1 = {R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei};
    private String[] titles = {"数学", "英语", "语文", "物理", "化学"};
    private int[] images = {R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei};
    FamilyBaseAdapter adaptertuijian;
    private List<TestInfo> testInfoList = new ArrayList<>();
    View rootView;

    public static FamilyEducationFrament newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        FamilyEducationFrament fragment = new FamilyEducationFrament();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home_familyeducation;
    }

    @Override
    public void initView(View rootView) {
        this.rootView = rootView;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        //设置播放时间间隔
//        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), R.color.text_orange, R.color.white));
        mRollViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });

//        RecycleaHome.post(new Runnable() {
//            @Override
//            public void run() {
        mList.clear();
        for (int i = 0; i < titles.length; i++) {
            QuanziBean bean = new QuanziBean();
            bean.setImages(images1[i]);
            bean.setTitles(titles[i]);
            mList.add(bean);
        }
        RecycleaHome.setAdapter(new GridViewAdapter(getContext(), mList));
//            }
//        });
//        mRecycleorFam.post(new Runnable() {
//            @Override
//            public void run() {
        testInfoList.clear();
        for (int i = 0; i < 6; i++) {
            TestInfo in = new TestInfo();
            in.setName("测试" + 1);
            testInfoList.add(in);
        }
        //热帖推荐
        if (adaptertuijian == null) {
            adaptertuijian = new FamilyBaseAdapter();
        }
        LinearLayoutManager lin = new LinearLayoutManager(mRecycleorFam.getContext());
//        lin.setSmoothScrollbarEnabled(true);
//        lin.setAutoMeasureEnabled(true);
        mRecycleorFam.setHasFixedSize(true);
        mRecycleorFam.setNestedScrollingEnabled(false);
        mRecycleorFam.setLayoutManager(lin);
        mRecycleorFam.setAdapter(adaptertuijian);
        adaptertuijian.setNewData(testInfoList);
//            }
//        });
        return rootView;
    }

    private class TestNormalAdapter extends StaticPagerAdapter {

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(getActivity())
                    .load(images[position])
                    .placeholder(R.mipmap.default_error)
                    .error(R.mipmap.default_error)
                    .centerCrop()
                    .crossFade()
                    .into(view);
            return view;
        }


        @Override
        public int getCount() {
            return images.length;
        }
    }
}
