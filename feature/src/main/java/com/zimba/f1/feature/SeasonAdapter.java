package com.zimba.f1.feature;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimba.f1.feature.activity.raceresults.RacesResultsGpsActivity;
import com.zimba.f1.feature.entity.SeasonGridEntity;

public class SeasonAdapter extends BaseAdapter {

    private Context context;
    private SeasonGridEntity[] seasonGridEntities;

    public SeasonAdapter(Context pContext, SeasonGridEntity[] pSeasonGridEntities) {
        this.context = pContext;
        this.seasonGridEntities = pSeasonGridEntities;
    }

    @Override
    public int getCount() {

        return seasonGridEntities != null ? seasonGridEntities.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return seasonGridEntities[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout seasonGridLayout;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            seasonGridLayout = new LinearLayout(context);
            final SeasonGridEntity seasonGridEntity = seasonGridEntities[position];

            seasonGridLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RacesResultsGpsActivity.class);
                    intent.putExtra("SEASON_YEAR", seasonGridEntity.getSeasonYear());
                    context.startActivity(intent);
                }
            });

            seasonGridLayout.setOrientation(LinearLayout.VERTICAL);
            //seasonGridLayout.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams paramsss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //seasonGridLayout.setLayoutParams(paramsss);
            seasonGridLayout.setBackgroundColor(Color.WHITE);

            TextView driverChampionNameTV = new TextView(context);
            driverChampionNameTV.setText(seasonGridEntity.getDriverChampionName());
            driverChampionNameTV.setLayoutParams(paramsss);
            driverChampionNameTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            driverChampionNameTV.setGravity(Gravity.CENTER);
            seasonGridLayout.addView(driverChampionNameTV);

            TextView seasonYearTV = new TextView(context);
            seasonYearTV.setText(seasonGridEntity.getSeasonYear());
            seasonYearTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            seasonYearTV.setTextColor(Color.RED);
            seasonYearTV.setLayoutParams(paramsss);
            seasonYearTV.setGravity(Gravity.CENTER);
            seasonGridLayout.addView(seasonYearTV);

            TextView constructorChampionNameTV = new TextView(context);
            constructorChampionNameTV.setText(seasonGridEntity.getConstructorChampionName());
            constructorChampionNameTV.setLayoutParams(paramsss);
            constructorChampionNameTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            constructorChampionNameTV.setGravity(Gravity.CENTER);
            seasonGridLayout.addView(constructorChampionNameTV);

        } else {
            seasonGridLayout = (LinearLayout) convertView;
            SeasonGridEntity seasonGridEntity = seasonGridEntities[position];
            ((TextView) ((LinearLayout) convertView).getChildAt(0))
                    .setText(seasonGridEntity.getDriverChampionName());
            ((TextView) ((LinearLayout) convertView).getChildAt(1))
                    .setText(seasonGridEntity.getSeasonYear());
            ((TextView) ((LinearLayout) convertView).getChildAt(2))
                    .setText(seasonGridEntity.getConstructorChampionName());


        }
        return seasonGridLayout;
    }
}
