package com.zimba.f1.feature;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zimba.f1.feature.entity.SeasonGridEntity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeasonFragment extends Fragment {


    public SeasonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View seasonFragment = inflater.inflate(R.layout.fragment_season, container, false);

        GridView gridview = (GridView) seasonFragment.findViewById(R.id.gridSeason);

        SeasonGridEntity[] arraySeason = new SeasonGridEntity[2];

        SeasonGridEntity season1 = new SeasonGridEntity();
        season1.setConstructorChampionName("Ferrari");
        season1.setDriverChampionName("Barrichelo");
        season1.setSeasonYear("2018");
        arraySeason[0] = season1;

        SeasonGridEntity season2 = new SeasonGridEntity();
        season2.setConstructorChampionName("Mercedes");
        season2.setDriverChampionName("Hamilton");
        season2.setSeasonYear("2018");
        arraySeason[1] = season2;

        gridview.setAdapter(new SeasonAdapter(this.getContext(), arraySeason));

        return seasonFragment;

    }

}
