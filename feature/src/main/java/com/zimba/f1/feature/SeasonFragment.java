package com.zimba.f1.feature;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zimba.f1.feature.entity.SeasonGridEntity;
import com.zimba.f1.feature.service.F1Service;
import com.zimba.f1.feature.service.SeasonGridListener;
import com.zimba.f1.feature.service.SeasonGridListenerInterface;


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

        F1Service f1Service = null;
        try{
            f1Service = new F1Service(getActivity().getApplicationContext());

        } catch(java.lang.InstantiationException e){
            e.printStackTrace();
        }

        SeasonGridListenerInterface lSeasonGridListener = new SeasonGridListener();
        f1Service.findAllSeasonGridEntities(lSeasonGridListener);

        SeasonAdapter seasonAdapter = new SeasonAdapter(this.getContext(), lSeasonGridListener.getArraySeason());

        seasonAdapter.notifyDataSetChanged();

        gridview.setAdapter(seasonAdapter);

        return seasonFragment;

    }

}
