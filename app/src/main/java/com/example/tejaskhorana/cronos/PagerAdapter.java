package com.example.tejaskhorana.cronos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tejaskhorana on 7/31/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public enum PageType {TODO, CALENDAR, NOTES, ALARM};
    int numberTabs;

    public PagerAdapter(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                TodoFragment tab1 = new TodoFragment();
                return tab1;
            case 1:
                CalendarFragment tab2 = new CalendarFragment();
                return tab2;
            case 2:
                NotesFragment tab3 = new NotesFragment();
                return tab3;
            case 3:
                AlarmFragment tab4 = new AlarmFragment();
                return tab4;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numberTabs;
    }

    public PageType getPageType(int position) {
        switch(position) {
            case 0:
                return PageType.TODO;
            case 1:
                return PageType.CALENDAR;
            case 2:
                return PageType.NOTES;
            case 3:
                return PageType.ALARM;
            default:
                return null;
        }
    }

}
