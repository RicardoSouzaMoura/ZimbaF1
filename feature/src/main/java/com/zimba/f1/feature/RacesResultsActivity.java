package com.zimba.f1.feature;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zimba.f1.feature.service.F1Service;

public class RacesResultsActivity extends AppCompatActivity {

    private RacesResultsAdapter racesResultsAdapter;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);

        racesResultsAdapter = new RacesResultsAdapter(getSupportFragmentManager());

        viewPager = this.findViewById(R.id.pagerRaceResults);
        viewPager.setAdapter(racesResultsAdapter);

        tabLayout = this.findViewById(R.id.tabsRacesResults);
        tabLayout.setupWithViewPager(viewPager);
    }
}
