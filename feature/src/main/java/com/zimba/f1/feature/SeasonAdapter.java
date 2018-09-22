package com.zimba.f1.feature;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            seasonGridLayout.setOrientation(LinearLayout.VERTICAL);

            SeasonGridEntity seasonGridEntity = seasonGridEntities[position];

            TextView driverChampionNameTV = new TextView(context);
            driverChampionNameTV.setText(seasonGridEntity.getDriverChampionName());
            seasonGridLayout.addView(driverChampionNameTV);

            TextView seasonYearTV = new TextView(context);
            seasonYearTV.setText(seasonGridEntity.getSeasonYear());
            seasonGridLayout.addView(seasonYearTV);

            TextView constructorChampionNameTV = new TextView(context);
            constructorChampionNameTV.setText(seasonGridEntity.getConstructorChampionName());
            seasonGridLayout.addView(constructorChampionNameTV);

        } else {
            seasonGridLayout = (LinearLayout) convertView;
            SeasonGridEntity seasonGridEntity = seasonGridEntities[position];
            ((TextView)((LinearLayout) convertView).getChildAt(0))
                    .setText(seasonGridEntity.getDriverChampionName());
            ((TextView)((LinearLayout) convertView).getChildAt(1))
                    .setText(seasonGridEntity.getSeasonYear());
            ((TextView)((LinearLayout) convertView).getChildAt(2))
                    .setText(seasonGridEntity.getConstructorChampionName());


        }
        return seasonGridLayout;
    }
}
