package com.zimba.f1.feature;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimba.f1.feature.entity.RacePositionEntity;

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
        LinearLayout layoutLayer1 = new LinearLayout(parent.getContext());
        layoutLayer1.setOrientation(LinearLayout.HORIZONTAL);
        ImageView fotoDriver = new ImageView(parent.getContext());

        LinearLayout layoutDriverInfo = new LinearLayout(parent.getContext());
        TextView driverNameTV = new TextView(parent.getContext());
        driverNameTV.setText("Driver Name");
        TextView constructNameTV = new TextView(parent.getContext());
        constructNameTV.setText("Ferrari #22");
        layoutDriverInfo.setOrientation(LinearLayout.VERTICAL);
        layoutDriverInfo.addView(driverNameTV);
        layoutDriverInfo.addView(constructNameTV);

        LinearLayout layoutDateTime = new LinearLayout(parent.getContext());
        TextView pointsTV = new TextView(parent.getContext());
        pointsTV.setText("25 pontos");
        TextView dateTimeTV = new TextView(parent.getContext());
        dateTimeTV.setText("+ 8.234s");
        layoutDateTime.setOrientation(LinearLayout.VERTICAL);
        layoutDateTime.addView(pointsTV);
        layoutDateTime.addView(dateTimeTV);

        layoutLayer1.addView(fotoDriver);
        layoutLayer1.addView(layoutDriverInfo);
        layoutLayer1.addView(layoutDateTime);

        return layoutLayer1;
    }
}
