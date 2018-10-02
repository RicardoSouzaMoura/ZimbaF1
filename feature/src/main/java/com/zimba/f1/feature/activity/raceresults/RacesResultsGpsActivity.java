package com.zimba.f1.feature.activity.raceresults;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zimba.f1.feature.R;
import com.zimba.f1.feature.entity.RaceResultsEntity;
import com.zimba.f1.feature.service.F1Service;
import com.zimba.f1.feature.service.RacesResultsListenerInterface;

public class RacesResultsGpsActivity extends AppCompatActivity {

    private RacesResultsGpsAdapter racesResultsAdapter;
    private String seasonYear;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);



        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        this.seasonYear = getIntent().getStringExtra("SEASON_YEAR");

        viewPager = this.findViewById(R.id.pagerRaceResults);
        tabLayout = this.findViewById(R.id.tabsRacesResults);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        try {
            F1Service f1Service = new F1Service(this.getApplicationContext());
            f1Service.findRaceResultsBySeasonYear(this.seasonYear, new RacesResultsListenerInterface() {
                @Override
                public void onResponse(RaceResultsEntity[] pEntities) {
                    racesResultsAdapter = new RacesResultsGpsAdapter(getSupportFragmentManager(), pEntities);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.raceresults, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.raceresults_menu_geral) {// User chose the "General" item, show the app settings UI...
            Intent intent = new Intent(this, RacesResultsGeneralActivity.class);
            intent.putExtra("SEASON_YEAR", seasonYear);
            this.startActivity(intent);
            return true;
        } else {// If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }
}
