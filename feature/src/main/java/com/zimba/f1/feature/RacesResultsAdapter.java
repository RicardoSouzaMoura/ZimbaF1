package com.zimba.f1.feature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zimba.f1.feature.entity.RaceResultsEntity;

public class RacesResultsAdapter extends FragmentStatePagerAdapter {

    private RaceResultsEntity[] raceResults;

    public RacesResultsAdapter(FragmentManager fm, RaceResultsEntity[] pRacesResults) {
        super(fm);
        this.raceResults = pRacesResults;
    }

    @Override
    public Fragment getItem(int i) {
        RaceResultsEntity raceResultsEntity = raceResults[i];
        Bundle args = new Bundle();
        args.putSerializable("raceResults", raceResultsEntity);
        RaceResultsFragment raceResultsFragment = new RaceResultsFragment();
        raceResultsFragment.setArguments(args);
        return raceResultsFragment;
    }

    @Override
    public int getCount() {
        return raceResults!=null?raceResults.length:0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return raceResults[position].getCountry();
    }
}
