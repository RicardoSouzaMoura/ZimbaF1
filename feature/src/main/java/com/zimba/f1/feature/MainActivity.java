package com.zimba.f1.feature;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MainPageAdapter mainPageAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());

        viewPager = this.findViewById(R.id.pager);
        viewPager.setAdapter(mainPageAdapter);

        //tabLayout = this.findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);

    }
}
