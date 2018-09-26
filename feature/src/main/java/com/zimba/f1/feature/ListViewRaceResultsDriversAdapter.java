package com.zimba.f1.feature;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimba.f1.feature.entity.RacePositionEntity;
import com.zimba.f1.feature.entity.SeasonGridEntity;

import org.w3c.dom.Text;

public class ListViewRaceResultsDriversAdapter extends BaseAdapter {

    private RacePositionEntity[] racePositionEntities;

    public ListViewRaceResultsDriversAdapter(RacePositionEntity[] pRacePositionEntity) {
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
            ImageView fotoDriver = new ImageView(parent.getContext());

            LinearLayout layoutDriverInfo = new LinearLayout(parent.getContext());
            TextView driverNameTV = new TextView(parent.getContext());
            driverNameTV.setText(racePositionEntity.getDriverName());
            TextView constructNameTV = new TextView(parent.getContext());
            constructNameTV.setText(racePositionEntity.getConstructorName() + " #" + racePositionEntity.getConstructorNumber());
            layoutDriverInfo.setOrientation(LinearLayout.VERTICAL);
            layoutDriverInfo.addView(driverNameTV);
            layoutDriverInfo.addView(constructNameTV);

            LinearLayout layoutDateTime = new LinearLayout(parent.getContext());
            TextView pointsTV = new TextView(parent.getContext());
            pointsTV.setText(racePositionEntity.getPoints() + " pontos");
            TextView dateTimeTV = new TextView(parent.getContext());
            dateTimeTV.setText(racePositionEntity.getTime());
            layoutDateTime.setOrientation(LinearLayout.VERTICAL);
            layoutDateTime.addView(pointsTV);
            layoutDateTime.addView(dateTimeTV);

            layoutLayer1.addView(fotoDriver);
            layoutLayer1.addView(layoutDriverInfo);
            layoutLayer1.addView(layoutDateTime);
        }
        else {
                layoutLayer1 = (LinearLayout) convertView;
                ImageView fotoDriver = (ImageView) layoutLayer1.getChildAt(0);

                LinearLayout layoutDriverInfo = (LinearLayout) layoutLayer1.getChildAt(1);
                TextView driverNameTV = (TextView)layoutDriverInfo.getChildAt(0);
                driverNameTV.setText(racePositionEntity.getDriverName());
                TextView constructNameTV = (TextView)layoutDriverInfo.getChildAt(1);
                constructNameTV.setText(racePositionEntity.getConstructorName() + " #" + racePositionEntity.getConstructorNumber());

                LinearLayout layoutDateTime = (LinearLayout) layoutLayer1.getChildAt(2);
                TextView pointsTV = (TextView)layoutDateTime.getChildAt(0);
                pointsTV.setText(racePositionEntity.getPoints() + " pontos");
                TextView dateTimeTV = (TextView)layoutDateTime.getChildAt(1);
                dateTimeTV.setText(racePositionEntity.getTime());

            }

        return layoutLayer1;
    }
}
