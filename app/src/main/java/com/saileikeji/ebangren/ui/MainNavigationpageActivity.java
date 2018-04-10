package com.saileikeji.ebangren.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.ui.base.BaseActivity;
import com.saileikeji.ebangren.ui.fragment.CommunityFragment;
import com.saileikeji.ebangren.ui.fragment.FamilyEducationFrament;
import com.saileikeji.ebangren.ui.fragment.HomeFragment;
import com.saileikeji.ebangren.ui.fragment.MeFragment;
import com.saileikeji.ebangren.ui.fragment.OrganizationFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: StChart
 * @author: 武梁
 * @date: 2017/11/9 09:15
 * @mailbox: 1034905058@qq.com
 */

public class MainNavigationpageActivity extends BaseActivity {

    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.load_image)
    LinearLayout loadImage;
    @Bind(R.id.mainNavigationPager)
    ViewPager mainNavigationPager;
    @Bind(R.id.mainNavigationtablay)
    TabLayout mainNavigationtablay;

    //Tab 文字
    private final int[] TAB_TITLES = new int[]{R.string.tab_shouye, R.string.tab_jigou, R.string.tab_shequ, R.string.tab_jiajiao,R.string.tab_me
    };
    //Tab 图片
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_homepage_selector, R.drawable.tab_orgenization_selector, R.drawable.tab_community_selector, R.drawable.tab_familyeducation_selector,R.drawable.tab_me_selector};
    //Fragment 数组
    private final Fragment[] TAB_FRAGMENTS = new Fragment[]{new HomeFragment(), new OrganizationFragment(), new CommunityFragment(), new FamilyEducationFrament(),new MeFragment()};
    //Tab 数目
    private final int COUNT = TAB_TITLES.length;
    private MyViewPagerAdapter mAdapter;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private static Handler mHandler1 = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private long mExitTime=0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        ButterKnife.bind(this);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        initViews();
    }

    private void initViews() {
        setTabs(mainNavigationtablay, this.getLayoutInflater(), TAB_TITLES, TAB_IMGS);

        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mainNavigationPager.setAdapter(mAdapter);
        mainNavigationPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainNavigationtablay));
        mainNavigationtablay.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mainNavigationPager));
    }

    /**
     * @description: 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs) {
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_custom, null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);

        }
    }
    /**
     * @description: ViewPager 适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        //1.点击的时间差如果大于2000，则提示用户点击两次退出
        if(System.currentTimeMillis() - mExitTime > 2000) {
            //2.保存当前时间
            mExitTime  = System.currentTimeMillis();
            //3.提示
            toast("再按一次后退键退出程序");
        } else {
            //4.点击的时间差小于2000，调用父类onBackPressed方法执行退出。
            super.onBackPressed();
        }
    }
}
