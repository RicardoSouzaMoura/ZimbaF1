package com.zimba.f1.feature;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zimba.f1.feature.entity.RaceResultsEntity;

public class RacesResultsAdapter extends FragmentStatePagerAdapter {

    private RaceResultsEntity[] raceResults;

    public RacesResultsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
