package com.zimba.f1.feature;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zimba.f1.feature.activity.raceresults.RacesResultsGpsActivity;
import com.zimba.f1.feature.entity.SeasonGridEntity;

import java.util.ArrayList;

public class SeasonRecyclerAdapter extends RecyclerView.Adapter<LineHolder>{


    private final SeasonGridEntity[] mSeasons;

    public SeasonRecyclerAdapter(SeasonGridEntity[] pSeasons) {
        mSeasons = pSeasons;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_season, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {
        final SeasonGridEntity entity = mSeasons[position];
        holder.constructorChampionName.setText(entity.getConstructorChampionName());
        holder.driverChampionName.setText(entity.getDriverChampionName());
        holder.seasonYear.setText(entity.getSeasonYear());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RacesResultsGpsActivity.class);
                intent.putExtra("SEASON_YEAR", entity.getSeasonYear());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.length : 0;
    }

}