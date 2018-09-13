package com.zimba.f1.feature;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPageAdapter extends FragmentPagerAdapter {
    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0: {
                SeasonFragment seasonFragment = new SeasonFragment();
                return seasonFragment;
            }
            case 1: {
                ConstructorFragment constructorFragment = new ConstructorFragment();
                return constructorFragment;
            }
            case 2: {
                DriverFragment driverFragment = new DriverFragment();
                return driverFragment;
            }
            case 3: {
                RaceFragment raceFragment = new RaceFragment();
                return raceFragment;
            }
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: {
                return "Season";
            }
            case 1: {
                return "Constructor";
            }
            case 2: {
                return "Driver";
            }
            case 3: {
                return "Race";
            }
        }

        return null;
    }
}
