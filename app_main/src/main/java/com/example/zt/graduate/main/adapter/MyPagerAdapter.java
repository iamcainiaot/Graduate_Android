package com.example.zt.graduate.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author taozhu5
 * @date 2019/4/3 17:10
 * @description 底部栏的Adapter
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    /**
     * FragmentPagerAdapter:FragmentPagerAdapter适合使用在固定的数量较少的场景,最多可以保留3个
     * FragmentStatePagerAdapter:
     *
     * @param fm
     */
    private List<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragments = list;
    }


    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}