package com.zimba.f1.feature.activity.raceresults;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zimba.f1.feature.R;
import com.zimba.f1.feature.entity.RaceResultsEntity;


/**
 * A simple {@link Fragment} subclass.
 */
public class RaceResultsGpsFragment extends Fragment {


    public RaceResultsGpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_race_results, container, false);

        TextView circuitNameTV = inflate.findViewById(R.id.circuitName);
        TextView dateTimeRaceTV = inflate.findViewById(R.id.dateTimeRaceId);

        ListView listViewPositions = inflate.findViewById(R.id.raceResults_drivers);

        RaceResultsEntity entity = (RaceResultsEntity) getArguments().get("raceResults");

        ListViewRaceResultsGpsDriversAdapter listViewAdapter = new ListViewRaceResultsGpsDriversAdapter(entity.getPositions());
        listViewPositions.setAdapter(listViewAdapter);

        circuitNameTV.setText(entity.getCircuitName());
        dateTimeRaceTV.setText(entity.getDate() + " " + entity.getTime());

        return inflate;

    }

}
