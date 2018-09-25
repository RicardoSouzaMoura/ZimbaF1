package com.zimba.f1.feature.service;

import com.zimba.f1.feature.entity.RaceResultsEntity;
import com.zimba.f1.feature.entity.SeasonGridEntity;

public interface RacesResultsListenerInterface {

    void onResponse(RaceResultsEntity[] pEntities);

    void onError(Exception error);
}
