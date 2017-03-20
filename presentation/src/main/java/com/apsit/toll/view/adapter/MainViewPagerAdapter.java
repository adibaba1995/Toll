package com.apsit.toll.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.apsit.toll.view.fragment.PaymentFragment;
import com.apsit.toll.view.fragment.SelectFragment;

/**
 * Created by adityathanekar on 16/03/17.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private static final int SELECT_FRAGMENT_POSITION = 0;
    private static final int PAYMENT_FRAGMENT_POSITION = 1;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case SELECT_FRAGMENT_POSITION:
                return new SelectFragment();
            case PAYMENT_FRAGMENT_POSITION:
                return new PaymentFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case SELECT_FRAGMENT_POSITION:
                return "Select";
            case PAYMENT_FRAGMENT_POSITION:
                return "Payment";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
