package com.zimba.f1.feature.activity.raceresults;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zimba.f1.feature.entity.RaceResultsEntity;

public class RacesResultsGpsAdapter extends FragmentStatePagerAdapter {

    private RaceResultsEntity[] raceResults;

    public RacesResultsGpsAdapter(FragmentManager fm, RaceResultsEntity[] pRacesResults) {
        super(fm);
        this.raceResults = pRacesResults;
    }

    @Override
    public Fragment getItem(int i) {
        RaceResultsEntity raceResultsEntity = raceResults[i];
        Bundle args = new Bundle();
        args.putSerializable("raceResults", raceResultsEntity);
        RaceResultsGpsFragment raceResultsFragment = new RaceResultsGpsFragment();
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
