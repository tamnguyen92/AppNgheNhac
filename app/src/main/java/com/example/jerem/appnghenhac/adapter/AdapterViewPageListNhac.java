package com.example.jerem.appnghenhac.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterViewPageListNhac extends FragmentPagerAdapter {
    public  final ArrayList<Fragment> fragments=new ArrayList<>();
    public AdapterViewPageListNhac(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void addFragment(Fragment fragment)
    {
        fragments.add(fragment);
    }
}
