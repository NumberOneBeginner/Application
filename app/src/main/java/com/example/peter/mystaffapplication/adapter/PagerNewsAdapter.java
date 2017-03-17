package com.example.peter.mystaffapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by peter on 2016/12/28.
 */

public class PagerNewsAdapter extends FragmentPagerAdapter {
    List<Fragment> list;

    public PagerNewsAdapter(FragmentManager fm) {
        super(fm);
    }

    public PagerNewsAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
