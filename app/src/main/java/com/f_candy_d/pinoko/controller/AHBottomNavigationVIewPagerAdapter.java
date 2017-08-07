package com.f_candy_d.pinoko.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.view.CardListFragment;

import java.util.ArrayList;

/**
 * Created by daichi on 17/08/07.
 */

public class AHBottomNavigationVIewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<CardListFragment> mFragments;
    private CardListFragment mCurrentFragment;

    public AHBottomNavigationVIewPagerAdapter(FragmentManager fragmentManager, ArrayList<CardListFragment> fragments) {
        super(fragmentManager);

        mFragments = new ArrayList<>(fragments);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = (CardListFragment) object;
        }

        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public CardListFragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
