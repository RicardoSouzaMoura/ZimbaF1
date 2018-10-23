package com.zimba.f1.feature;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.zimba.f1.feature.entity.SeasonGridEntity;
import com.zimba.f1.feature.service.F1Service;
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
        final ProgressBar cursor = seasonFragment.findViewById(R.id.progress_season);
        cursor.setVisibility(View.VISIBLE);

        //final GridView gridview = seasonFragment.findViewById(R.id.gridSeason);
        final RecyclerView recyclerView = seasonFragment.findViewById(R.id.recycler_view_layout_recycler);

        F1Service f1Service = null;
        try {
            f1Service = new F1Service(getActivity().getApplicationContext());

        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        final Context context = this.getContext();

        f1Service.findAllSeasonGridEntities(new SeasonGridListenerInterface() {
            @Override
            public void onResponse(SeasonGridEntity[] pEntities) {
                GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
                recyclerView.setLayoutManager(layoutManager);

                SeasonRecyclerAdapter mAdapter = new SeasonRecyclerAdapter(pEntities);
                recyclerView.setAdapter(mAdapter);

                // Configurando um dividr entre linhas, para uma melhor visualização.
                recyclerView.addItemDecoration(
                        new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

                // Configurando um dividr entre colunas, para uma melhor visualização.
                recyclerView.addItemDecoration(
                        new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));

                cursor.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception error) {
                error.printStackTrace();
            }
        });

        //seasonAdapter.notifyDataSetChanged();

        return seasonFragment;

    }

}
