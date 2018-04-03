package com.saileikeji.ebangren.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.adapter.MyViewPagerAdapter;
import com.saileikeji.ebangren.ui.base.BaseActivity;
import com.saileikeji.ebangren.widgit.SpecialTab;
import com.saileikeji.wllibrary.view.NoTouchViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

/**
 * @describe: 主页面
 * @author: 武梁
 * @date: 2017/12/26 13:57
 * @mailbox: 1034905058@qq.com
 */

public class MainActivity extends BaseActivity {
    NavigationController navigationController;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tab)
    PageNavigationView tab;
    private long mExitTime = 0;
    MyViewPagerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PageNavigationView tab = (PageNavigationView) findViewById(R.id.tab);

        navigationController = tab.custom()
                .addItem(newItem(R.drawable.ic_zhanwei, R.drawable.ic_zhanwei, "首页"))
                .addItem(newItem(R.drawable.ic_zhanwei, R.drawable.ic_zhanwei, "机构"))
                .addItem(newItem(R.drawable.ic_zhanwei, R.drawable.ic_zhanwei, "社区"))
                .addItem(newItem(R.drawable.ic_zhanwei, R.drawable.ic_zhanwei, "家教"))
                .addItem(newItem(R.drawable.ic_zhanwei, R.drawable.ic_zhanwei, "我们"))

                .build();
        if (adapter==null) {
            adapter=new MyViewPagerAdapter(getSupportFragmentManager(), navigationController.getItemCount());
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);
    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(getResources().getColor(R.color.text_gray66));
        mainTab.setTextCheckedColor(getResources().getColor(R.color.text_blue));
        return mainTab;
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
        if (System.currentTimeMillis() - mExitTime > 2000) {
            //2.保存当前时间
            mExitTime = System.currentTimeMillis();
            //3.提示
            toast("再按一次后退键退出程序");
        } else {
            //4.点击的时间差小于2000，调用父类onBackPressed方法执行退出。
            super.onBackPressed();
        }
    }
}
