package com.saileikeji.ebangren.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.adapter.CourseBaseAdapte;
import com.saileikeji.ebangren.adapter.GridViewAdapter;
import com.saileikeji.ebangren.adapter.TuijianBaseAdapter;
import com.saileikeji.ebangren.bean.QuanziBean;
import com.saileikeji.ebangren.bean.TestInfo;
import com.saileikeji.ebangren.ui.CourseActivity;
import com.saileikeji.ebangren.ui.OrganizationActivity;
import com.saileikeji.ebangren.ui.base.BaseFragment;
import com.saileikeji.ebangren.widgit.MyGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/1/27 10:17
 * @mailbox: 1034905058@qq.com
 */

public class HomeFragment extends BaseFragment {
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
    @Bind(R.id.roll_view_pager)
    RollPagerView mRollViewPager;
    @Bind(R.id.RecycleaHome)
    MyGridView RecycleaHome;
    @Bind(R.id.RecyclebHome)
    RecyclerView RecyclebHome;

    @Bind(R.id.mTvhome)
    TextView mTvhome;
    @Bind(R.id.mIvImghome)
    ImageView mIvImghome;
    @Bind(R.id.mTvahome)
    TextView mTvahome;
    @Bind(R.id.mIvImgahome)
    ImageView mIvImgahome;
    @Bind(R.id.RecyclebJigou)
    RecyclerView RecyclebJigou;
    @Bind(R.id.mTvbhome)
    TextView mTvbhome;
    @Bind(R.id.mIvImgbhome)
    ImageView mIvImgbhome;
    @Bind(R.id.RecyclebTuijian)
    RecyclerView RecyclebTuijian;
    @Bind(R.id.mTvchome)
    TextView mTvchome;
    @Bind(R.id.mIvImgchome)
    ImageView mIvImgchome;
    @Bind(R.id.mTvdhome)
    TextView mTvdhome;
    @Bind(R.id.mIvImgdhome)
    ImageView mIvImgdhome;
    @Bind(R.id.RecyclebHuodong)
    RecyclerView RecyclebHuodong;
    CourseBaseAdapte adaptebase, adaptebaseb;
    @Bind(R.id.Recycleaquanzi)
    MyGridView Recycleaquanzi;
    private int[] images = {R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei};
    private String[] titles = {"美妆", "数码", "电器", "男装", "女装", "美妆", "数码", "电器", "男装", "女装", "美妆", "数码", "电器", "男装", "女装",};
    private int[] images1 = {R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei};
    private String[] titles1 = {"E帮人妈妈帮", "北京幼升小", "小中年级", "小低年级"};
    private List<TestInfo> testInfoList = new ArrayList<>();
    TuijianBaseAdapter adaptertuijian;
    TuijianBaseAdapter adaptertuijiana;
    List<QuanziBean> mList=new ArrayList<>();
    List<QuanziBean> mquanziList=new ArrayList<>();
    View rootView;
    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void initView(View rootView) {



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
         rootView = super.onCreateView(inflater, container, savedInstanceState);
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


        testInfoList.clear();
        for (int i = 0; i < 6; i++) {
            TestInfo in = new TestInfo();
            in.setName("测试" + 1);
            testInfoList.add(in);
        }
//        RecycleaHome.post(new Runnable() {
//            @Override
//            public void run() {
                mList.clear();
                for (int i = 0; i < titles.length; i++) {
                    QuanziBean bean=new QuanziBean();
                    bean.setImages(images[i]);
                    bean.setTitles(titles[i]);
                    mList.add(bean);
                }
                //上面的grid
                RecycleaHome.setAdapter(new GridViewAdapter(getContext(), mList));
//            }
//        });
//        Recycleaquanzi.post(new Runnable() {
//            @Override
//            public void run() {
                mquanziList.clear();
                for (int i = 0; i < titles1.length; i++) {
                    QuanziBean bean=new QuanziBean();
                    bean.setImages(images1[i]);
                    bean.setTitles(titles1[i]);
                    mquanziList.add(bean);
                }
                //推荐圈子
                Recycleaquanzi.setAdapter(new GridViewAdapter(getContext(), mquanziList));
//            }
//        });
//        RecyclebHome.post(new Runnable() {
//            @Override
//            public void run() {
                //推荐课程
                if (adaptebase==null) {
                    adaptebase = new CourseBaseAdapte();
                }
                GridLayoutManager gridLayoutManagera = new GridLayoutManager(getActivity(), 2);
//        gridLayoutManagera.setSmoothScrollbarEnabled(true);
//        gridLayoutManagera.setAutoMeasureEnabled(true);
                RecyclebHome.setHasFixedSize(true);
                RecyclebHome.setNestedScrollingEnabled(false);
                RecyclebHome.setLayoutManager(gridLayoutManagera);
                RecyclebHome.setAdapter(adaptebase);
                adaptebase.setNewData(testInfoList);
                adaptebase.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        startActivity(new Intent(getContext(),CourseActivity.class));
                        getActivity().finish();
                    }
                });
//            }
//        });
//        RecyclebJigou.post(new Runnable() {
//            @Override
//            public void run() {
                //推荐机构
                if (adaptebaseb==null) {
                    adaptebaseb = new CourseBaseAdapte();
                }
                GridLayoutManager gridLayoutManagerb = new GridLayoutManager(getActivity(), 2);
//        gridLayoutManagerb.setSmoothScrollbarEnabled(true);
//        gridLayoutManagerb.setAutoMeasureEnabled(true);
                RecyclebJigou.setHasFixedSize(true);
                RecyclebJigou.setNestedScrollingEnabled(false);
                RecyclebJigou.setLayoutManager(gridLayoutManagerb);
                RecyclebJigou.setAdapter(adaptebaseb);
                adaptebaseb.setNewData(testInfoList);
                adaptebaseb.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        startActivity(new Intent(getContext(),OrganizationActivity.class));
                    }
                });
//            }
//        });
//        RecyclebTuijian.post(new Runnable() {
//            @Override
//            public void run() {
                //热帖推荐
                if (adaptertuijian==null) {
                    adaptertuijian = new TuijianBaseAdapter();
                }
                LinearLayoutManager lin  = new LinearLayoutManager(RecyclebTuijian.getContext());
//        lin.setSmoothScrollbarEnabled(true);
//        lin.setAutoMeasureEnabled(true);
                RecyclebTuijian.setHasFixedSize(true);
                RecyclebTuijian.setNestedScrollingEnabled(false);
                RecyclebTuijian.setLayoutManager(lin);
                RecyclebTuijian.setAdapter(adaptertuijian);
                adaptertuijian.setNewData(testInfoList);
//            }
//        });
//        RecyclebHuodong.post(new Runnable() {
//            @Override
//            public void run() {
                //活动推荐
                if (adaptertuijiana==null){
                    adaptertuijiana = new TuijianBaseAdapter();
                }
                LinearLayoutManager lina  = new LinearLayoutManager(RecyclebTuijian.getContext());
//        lina.setSmoothScrollbarEnabled(true);
//        lina.setAutoMeasureEnabled(true);
                RecyclebHuodong.setHasFixedSize(true);
                RecyclebHuodong.setNestedScrollingEnabled(false);
                RecyclebHuodong.setLayoutManager(lina);
                RecyclebHuodong.setAdapter(adaptertuijiana);
                adaptertuijiana.setNewData(testInfoList);
//            }
//        });

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

    private class TestNormalAdapter extends StaticPagerAdapter {
        ImageView view;
        @Override
        public View getView(ViewGroup container,final int position) {
                view = new ImageView(container.getContext());
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getActivity())
                            .load(images[position])
                            .placeholder(R.mipmap.default_error)
                            .error(R.mipmap.default_error)
                            .centerCrop()
                            .crossFade()
                            .into(view);
                }
            });

            return view;
        }


        @Override
        public int getCount() {
            return images.length;
        }
    }
}
