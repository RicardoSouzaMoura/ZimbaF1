package com.zimba.f1.feature.service;

import com.zimba.f1.feature.entity.RacePositionEntity;

public interface DriverResultsListenerInterface {

    void onResponse(RacePositionEntity[] pEntities);

    void onError(Exception error);
}
