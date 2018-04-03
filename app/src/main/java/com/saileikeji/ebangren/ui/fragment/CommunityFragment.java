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
import com.saileikeji.ebangren.adapter.GridViewAdapter;
import com.saileikeji.ebangren.adapter.GridViewCommunityAdapter;
import com.saileikeji.ebangren.adapter.TuijianBaseAdapter;
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
 * @date: 2018/2/2 16:22
 * @mailbox: 1034905058@qq.com
 */

public class CommunityFragment extends BaseFragment {
    private final static String ARG_C = "centent";
//    RollPagerView mRollViewPager;
//    MyGridView RecycleaHome, Recycleaquanzi;
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
    @Bind(R.id.Recycleaquanzi)
    MyGridView Recycleaquanzi;
    @Bind(R.id.mTvbhome)
    TextView mTvbhome;
    @Bind(R.id.mIvImgbhome)
    ImageView mIvImgbhome;
    @Bind(R.id.RecyclebTuijian)
    RecyclerView RecyclebTuijian;
    @Bind(R.id.mTvdhome)
    TextView mTvdhome;
    @Bind(R.id.mIvImgdhome)
    ImageView mIvImgdhome;
    @Bind(R.id.RecyclebHuodong)
    RecyclerView RecyclebHuodong;
    @Bind(R.id.mTvChanpin)
    TextView mTvChanpin;
    @Bind(R.id.mIvImgChanpin)
    ImageView mIvImgChanpin;
    @Bind(R.id.RecyclebChanpin)
    RecyclerView RecyclebChanpin;
    private List<QuanziBean> mList = new ArrayList<>();
    private List<QuanziBean> quanzimList = new ArrayList<>();
    private int[] images1 = {R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei};
    private String[] titles = {"爱心社团", "兼职信息", "教育视频", "优选商品", "活动发布", "查看更多"};
    private String[] message = {"爱心社团", "兼职信息", "教育视频", "优选商品", "活动发布", "查看更多"};
    private int[] images = {R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei};
    private int[] images2 = {R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei, R.mipmap.ic_zhanwei};
    private String[] titles1 = {"教育资讯", "学习方法", "性格培养", "心理辅导"};
//    private RecyclerView RecyclebTuijian, RecyclebHuodong, RecyclebChanpin;
    TuijianBaseAdapter adaptertuijian, adaptertuijiana, adaptertuijianb;
    private List<TestInfo> testInfoList = new ArrayList<>();
    View rootView;

    public static CommunityFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_community;
    }

    @Override
    public void initView(View rootView) {
        this.rootView = rootView;
//        mRollViewPager = rootView.findViewById(R.id.roll_view_pager);
//        RecycleaHome = rootView.findViewById(R.id.RecycleaHome);
//        Recycleaquanzi = rootView.findViewById(R.id.Recycleaquanzi);
//        RecyclebTuijian = rootView.findViewById(R.id.RecyclebTuijian);
//        RecyclebHuodong = rootView.findViewById(R.id.RecyclebHuodong);
//        RecyclebChanpin = rootView.findViewById(R.id.RecyclebChanpin);



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


        RecycleaHome.post(new Runnable() {
            @Override
            public void run() {
                mList.clear();
                for (int i = 0; i < titles.length; i++) {
                    QuanziBean bean = new QuanziBean();
                    bean.setImages(images1[i]);
                    bean.setTitles(titles[i]);
                    bean.setMessage(message[i]);
                    mList.add(bean);
                }
                RecycleaHome.setAdapter(new GridViewCommunityAdapter(getContext(), mList));
            }
        });
        Recycleaquanzi.post(new Runnable() {
            @Override
            public void run() {
                quanzimList.clear();
                for (int i = 0; i < titles1.length; i++) {
                    QuanziBean bean = new QuanziBean();
                    bean.setImages(images2[i]);
                    bean.setTitles(titles1[i]);
                    quanzimList.add(bean);
                }
                Recycleaquanzi.setAdapter(new GridViewAdapter(getContext(), quanzimList));
            }
        });


        RecyclebTuijian.post(new Runnable() {
            @Override
            public void run() {
                testInfoList.clear();
                for (int i = 0; i < 6; i++) {
                    TestInfo in = new TestInfo();
                    in.setName("测试" + 1);
                    testInfoList.add(in);
                }
                //热帖推荐
                if (adaptertuijian == null) {
                    adaptertuijian = new TuijianBaseAdapter();
                }
                LinearLayoutManager lin = new LinearLayoutManager(RecyclebTuijian.getContext());
//        lin.setSmoothScrollbarEnabled(true);
//        lin.setAutoMeasureEnabled(true);
                RecyclebTuijian.setHasFixedSize(true);
                RecyclebTuijian.setNestedScrollingEnabled(false);
                RecyclebTuijian.setLayoutManager(lin);
                RecyclebTuijian.setAdapter(adaptertuijian);
                adaptertuijian.setNewData(testInfoList);
            }
        });

        RecyclebHuodong.post(new Runnable() {
            @Override
            public void run() {
                //活动推荐
                if (adaptertuijiana == null) {
                    adaptertuijiana = new TuijianBaseAdapter();
                }
                LinearLayoutManager lina = new LinearLayoutManager(RecyclebTuijian.getContext());
//        lina.setSmoothScrollbarEnabled(true);
//        lina.setAutoMeasureEnabled(true);
                RecyclebHuodong.setHasFixedSize(true);
                RecyclebHuodong.setNestedScrollingEnabled(false);
                RecyclebHuodong.setLayoutManager(lina);
                RecyclebHuodong.setAdapter(adaptertuijiana);
                adaptertuijiana.setNewData(testInfoList);
            }
        });

        RecyclebChanpin.post(new Runnable() {
            @Override
            public void run() {
                //活动推荐
                if (adaptertuijianb == null) {
                    adaptertuijianb = new TuijianBaseAdapter();
                }
                LinearLayoutManager linb = new LinearLayoutManager(RecyclebChanpin.getContext());
//        lina.setSmoothScrollbarEnabled(true);
//        lina.setAutoMeasureEnabled(true);
                RecyclebChanpin.setHasFixedSize(true);
                RecyclebChanpin.setNestedScrollingEnabled(false);
                RecyclebChanpin.setLayoutManager(linb);
                RecyclebChanpin.setAdapter(adaptertuijiana);
                adaptertuijianb.setNewData(testInfoList);
            }
        });
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
