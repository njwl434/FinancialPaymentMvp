package com.saileikeji.ebangren.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.saileikeji.ebangren.ui.fragment.CommunityFragment;
import com.saileikeji.ebangren.ui.fragment.FamilyEducationFrament;
import com.saileikeji.ebangren.ui.fragment.HomeFragment;
import com.saileikeji.ebangren.ui.fragment.MeFragment;
import com.saileikeji.ebangren.ui.fragment.OrganizationFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private int size;

    public MyViewPagerAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return HomeFragment.newInstance(position + "a");
        }
        if (position==1){
            return OrganizationFragment.newInstance(position + "a");
        }
        if (position==2){
            return CommunityFragment.newInstance(position + "a");
        }
        if (position==3){
            return FamilyEducationFrament.newInstance(position + "a");
        }
        if (position==4){
            return MeFragment.newInstance(position + "a");
        }
        return null;
    }

    @Override
    public int getCount() {
        return size;
    }
}
