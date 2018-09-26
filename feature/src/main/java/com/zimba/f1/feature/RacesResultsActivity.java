package com.zimba.f1.feature;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zimba.f1.feature.entity.RaceResultsEntity;
import com.zimba.f1.feature.service.F1Service;
import com.zimba.f1.feature.service.RacesResultsListenerInterface;

public class RacesResultsActivity extends AppCompatActivity {

    private RacesResultsAdapter racesResultsAdapter;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);

        RaceResultsEntity raceResults = null;
        viewPager = this.findViewById(R.id.pagerRaceResults);
        tabLayout = this.findViewById(R.id.tabsRacesResults);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        try {
            F1Service f1Service = new F1Service(this.getApplicationContext());
            f1Service.findRaceResultsBySeasonYear("2018", new RacesResultsListenerInterface() {
                @Override
                public void onResponse(RaceResultsEntity[] pEntities) {
                    racesResultsAdapter = new RacesResultsAdapter(getSupportFragmentManager(), pEntities);
                    viewPager.setAdapter(racesResultsAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }

                @Override
                public void onError(Exception error) {
                    error.printStackTrace();
                }
            });
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
