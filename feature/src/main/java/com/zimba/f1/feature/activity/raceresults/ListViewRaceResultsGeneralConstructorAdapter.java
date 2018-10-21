package com.zimba.f1.feature.activity.raceresults;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        RelativeLayout layoutLayer1;
        RacePositionEntity racePositionEntity = racePositionEntities[position];

        if (convertView == null) {

            layoutLayer1 = new RelativeLayout(parent.getContext());
            layoutLayer1.setPadding(16, 16, 16, 16);

            //layoutLayer1.setOrientation(LinearLayout.HORIZONTAL);
            //ImageView fotoConstructor = new ImageView(parent.getContext());

            //layoutLayer1.addView(fotoConstructor);
            layoutLayer1.setBackgroundColor(Color.WHITE);

            LinearLayout layoutLayer2 = new LinearLayout(parent.getContext());
            layoutLayer2.setOrientation(LinearLayout.VERTICAL);

            TextView constructorNameTV = new TextView(parent.getContext());
            constructorNameTV.setText(racePositionEntity.getConstructorName());
            constructorNameTV.setTextAppearance(parent.getContext(), android.R.style.TextAppearance_DeviceDefault_Large);

            TextView constructorCountryTV = new TextView(parent.getContext());
            constructorCountryTV.setTextAppearance(parent.getContext(), android.R.style.TextAppearance_DeviceDefault_Medium);
            constructorCountryTV.setText(racePositionEntity.getConstructorCountry());

            layoutLayer2.addView(constructorNameTV);
            layoutLayer2.addView(constructorCountryTV);

            RelativeLayout.LayoutParams paramsLeft = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            layoutLayer1.addView(layoutLayer2, paramsLeft);

            TextView pointsTV = new TextView(parent.getContext());
            pointsTV.setText(racePositionEntity.getPoints() + " pontos");
            pointsTV.setTextAppearance(parent.getContext(), android.R.style.TextAppearance_DeviceDefault_Large);

            RelativeLayout.LayoutParams paramsRight = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            layoutLayer1.addView(pointsTV, paramsRight);

        }
        else {
                layoutLayer1 = (RelativeLayout) convertView;
                //ImageView fotoConstructor = (ImageView) layoutLayer1.getChildAt(0);

                LinearLayout layoutLayer2 = (LinearLayout)layoutLayer1.getChildAt(0);

                TextView constructNameTV = (TextView)layoutLayer2.getChildAt(0);
                constructNameTV.setText(racePositionEntity.getConstructorName());

                TextView constructCountryTV = (TextView)layoutLayer2.getChildAt(1);
                constructCountryTV.setText(racePositionEntity.getConstructorCountry());

                TextView pointsTV = (TextView)layoutLayer1.getChildAt(1);
                pointsTV.setText(racePositionEntity.getPoints() + " pontos");

            }

        return layoutLayer1;
    }
}
