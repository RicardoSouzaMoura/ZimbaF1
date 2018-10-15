package com.zimba.f1.feature.activity.raceresults;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimba.f1.feature.entity.RacePositionEntity;

public class ListViewRaceResultsGeneralConstructorAdapter extends BaseAdapter {

    private RacePositionEntity[] racePositionEntities;

    public ListViewRaceResultsGeneralConstructorAdapter(RacePositionEntity[] pRacePositionEntity) {
        super();
        this.racePositionEntities = pRacePositionEntity;
    }

    @Override
    public int getCount() {
        return racePositionEntities != null ? racePositionEntities.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return this.racePositionEntities[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutLayer1;
        RacePositionEntity racePositionEntity = racePositionEntities[position];

        if (convertView == null) {

            layoutLayer1 = new LinearLayout(parent.getContext());

            layoutLayer1.setOrientation(LinearLayout.HORIZONTAL);
            ImageView fotoConstructor = new ImageView(parent.getContext());

            layoutLayer1.addView(fotoConstructor);

            LinearLayout layoutLayer2 = new LinearLayout(parent.getContext());
            layoutLayer2.setOrientation(LinearLayout.VERTICAL);

            TextView constructorNameTV = new TextView(parent.getContext());
            constructorNameTV.setText(racePositionEntity.getConstructorName());

            TextView constructorCountryTV = new TextView(parent.getContext());
            constructorCountryTV.setText(racePositionEntity.getConstructorCountry());

            layoutLayer2.addView(constructorNameTV);
            layoutLayer2.addView(constructorCountryTV);

            layoutLayer1.addView(layoutLayer2);

            TextView pointsTV = new TextView(parent.getContext());
            pointsTV.setText(racePositionEntity.getPoints() + " pontos");
            layoutLayer1.addView(pointsTV);

        }
        else {
                layoutLayer1 = (LinearLayout) convertView;
                ImageView fotoConstructor = (ImageView) layoutLayer1.getChildAt(0);

                LinearLayout layoutLayer2 = (LinearLayout)layoutLayer1.getChildAt(1);

                TextView constructNameTV = (TextView)layoutLayer2.getChildAt(0);
                constructNameTV.setText(racePositionEntity.getConstructorName());

                TextView constructCountryTV = (TextView)layoutLayer2.getChildAt(1);
                constructCountryTV.setText(racePositionEntity.getConstructorCountry());

                TextView pointsTV = (TextView)layoutLayer1.getChildAt(2);
                pointsTV.setText(racePositionEntity.getPoints() + " pontos");

            }

        return layoutLayer1;
    }
}
