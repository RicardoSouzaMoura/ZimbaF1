package com.zimba.f1.feature.service;

import com.zimba.f1.feature.entity.SeasonGridEntity;

import org.json.JSONObject;

public interface SeasonGridListenerInterface {

    void onResponse(SeasonGridEntity[] pEntities);

    void onError(Exception error);
}
