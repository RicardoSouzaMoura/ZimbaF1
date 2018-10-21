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

public class ListViewRaceResultsGeneralDriversAdapter extends BaseAdapter {

    private RacePositionEntity[] racePositionEntities;

    public ListViewRaceResultsGeneralDriversAdapter(RacePositionEntity[] pRacePositionEntity) {
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
            layoutLayer1.setBackgroundColor(Color.WHITE);
            //ImageView fotoDriver = new ImageView(parent.getContext());

            LinearLayout layoutDriverInfo = new LinearLayout(parent.getContext());
            layoutDriverInfo.setOrientation(LinearLayout.VERTICAL);

            TextView driverNameTV = new TextView(parent.getContext());
            driverNameTV.setText(racePositionEntity.getDriverName());
            driverNameTV.setTextAppearance(parent.getContext(), android.R.style.TextAppearance_DeviceDefault_Large);

            TextView constructNameTV = new TextView(parent.getContext());
            constructNameTV.setText(racePositionEntity.getConstructorName());
            constructNameTV.setTextAppearance(parent.getContext(), android.R.style.TextAppearance_DeviceDefault_Medium);

            layoutDriverInfo.addView(driverNameTV);
            layoutDriverInfo.addView(constructNameTV);

            LinearLayout layoutPoints = new LinearLayout(parent.getContext());
            TextView pointsTV = new TextView(parent.getContext());
            pointsTV.setText(racePositionEntity.getPoints() + " pontos");
            pointsTV.setTextAppearance(parent.getContext(), android.R.style.TextAppearance_DeviceDefault_Large);
            layoutPoints.setOrientation(LinearLayout.VERTICAL);
            layoutPoints.addView(pointsTV);

            //layoutLayer1.addView(fotoDriver);
            RelativeLayout.LayoutParams paramsLeft = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            layoutLayer1.addView(layoutDriverInfo, paramsLeft);

            RelativeLayout.LayoutParams paramsRight = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            layoutLayer1.addView(layoutPoints, paramsRight);
        }
        else {
                layoutLayer1 = (RelativeLayout) convertView;
                //ImageView fotoDriver = (ImageView) layoutLayer1.getChildAt(0);

                LinearLayout layoutDriverInfo = (LinearLayout) layoutLayer1.getChildAt(0);
                TextView driverNameTV = (TextView)layoutDriverInfo.getChildAt(0);
                driverNameTV.setText(racePositionEntity.getDriverName());
                TextView constructNameTV = (TextView)layoutDriverInfo.getChildAt(1);
                constructNameTV.setText(racePositionEntity.getConstructorName());

                LinearLayout layoutPoints = (LinearLayout) layoutLayer1.getChildAt(1);
                TextView pointsTV = (TextView)layoutPoints.getChildAt(0);
                pointsTV.setText(racePositionEntity.getPoints() + " pontos");

            }

        return layoutLayer1;
    }
}
