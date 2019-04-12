package com.example.jerem.appnghenhac.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterMainViewPage extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayFragment=new ArrayList<>();
    private ArrayList<String>arrayTitle=new ArrayList<>();
    public AdapterMainViewPage(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return arrayFragment.get(i);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }

    public  void addFragment(Fragment fragment, String title){
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }
}
