package com.zimba.f1.feature;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView driverChampionName;
    public TextView seasonYear;
    public TextView constructorChampionName;

    public LineHolder(View itemView) {
        super(itemView);
        driverChampionName = (TextView) itemView.findViewById(R.id.driverChampionNameId);
        seasonYear = (TextView) itemView.findViewById(R.id.seasonYearId);
        constructorChampionName = (TextView) itemView.findViewById(R.id.constructorChampionNameId);
    }
}
