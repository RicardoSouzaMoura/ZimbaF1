package com.zimba.f1.feature.activity.raceresults;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zimba.f1.feature.R;
import com.zimba.f1.feature.entity.RacePositionEntity;
import com.zimba.f1.feature.service.ConstructorResultsListenerInterface;
import com.zimba.f1.feature.service.DriverResultsListenerInterface;
import com.zimba.f1.feature.service.F1Service;

public class RacesResultsGeneralActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TabLayout tabLayout;

    private String seasonYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.seasonYear = getIntent().getStringExtra("SEASON_YEAR");
        setContentView(R.layout.activity_race_results_general);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this.seasonYear);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerResultsGeneral);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = this.findViewById(R.id.tabsRacesResultsGeneral);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_race_results_general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class GeneralDriverFragment extends Fragment {


        public GeneralDriverFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GeneralDriverFragment newInstance(String pSeasonYear) {
            GeneralDriverFragment fragment = new GeneralDriverFragment();
            Bundle bundle = new Bundle();
            bundle.putString("SEASON_YEAR", pSeasonYear);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_race_results_general, container, false);
            final ListView listViewDrivers = rootView.findViewById(R.id.raceResults_general);

            try {
                F1Service f1Service = new F1Service(this.getContext());
                f1Service.findAllDriverResultsBySeasonYear(this.getArguments().getString("SEASON_YEAR"), new DriverResultsListenerInterface() {
                    @Override
                    public void onResponse(RacePositionEntity[] pEntities) {
                        ListViewRaceResultsGeneralDriversAdapter listViewAdapter = new ListViewRaceResultsGeneralDriversAdapter(pEntities);
                        listViewDrivers.setAdapter(listViewAdapter);
                    }

                    @Override
                    public void onError(Exception error) {
                        error.printStackTrace();
                    }
                });
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }

            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class GeneralConstructorFragment extends Fragment {


        public GeneralConstructorFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GeneralConstructorFragment newInstance(String pSeasonYear) {
            GeneralConstructorFragment fragment = new GeneralConstructorFragment();
            Bundle bundle = new Bundle();

            bundle.putString("SEASON_YEAR", pSeasonYear);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_race_results_general, container, false);
            final ListView listViewConstructors = rootView.findViewById(R.id.raceResults_general);

            try {
                F1Service f1Service = new F1Service(this.getContext());
                f1Service.findAllConstructorResultsBySeasonYear(this.getArguments().getString("SEASON_YEAR"), new ConstructorResultsListenerInterface() {
                    @Override
                    public void onResponse(RacePositionEntity[] pEntities) {
                        ListViewRaceResultsGeneralConstructorAdapter listViewAdapter = new ListViewRaceResultsGeneralConstructorAdapter(pEntities);
                        listViewConstructors.setAdapter(listViewAdapter);
                    }

                    @Override
                    public void onError(Exception error) {
                        error.printStackTrace();
                    }
                });
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String seasonYear;
        private F1Service f1Service;

        public SectionsPagerAdapter(FragmentManager fm, String pSeasonYear) {
            super(fm);
            this.seasonYear = pSeasonYear;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0: {
                    GeneralDriverFragment generalDriverFragment = GeneralDriverFragment.newInstance(this.seasonYear);
                    return generalDriverFragment;
                }
                case 1: {
                    GeneralConstructorFragment generalConstructorFragment = GeneralConstructorFragment.newInstance(this.seasonYear);
                    return generalConstructorFragment;
                }
            }
            return null;

        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: {
                    return "Pilotos";
                }
                case 1: {
                    return "Construtores";
                }
            }
            return super.getPageTitle(position);
        }
    }
}
