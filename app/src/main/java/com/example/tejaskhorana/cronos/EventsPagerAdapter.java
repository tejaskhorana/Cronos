package com.example.tejaskhorana.cronos;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tejaskhorana on 7/31/17.
 */

public class EventsPagerAdapter extends FragmentPagerAdapter {

    public EventsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int item) {
        android.support.v4.app.Fragment fragment = null;
        if(item == 0) {
            //fragment = new PhotoCommnFragment()
        } else if (item == 1) {
            //fragment = new ShowRestaurant()
        }
        return fragment;
    }

    @Override
    public int getCount() {
        //needs to be changed
        return 0;
    }



}
