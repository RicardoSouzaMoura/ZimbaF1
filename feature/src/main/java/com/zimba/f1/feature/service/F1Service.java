package com.zimba.f1.feature.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zimba.f1.feature.entity.SeasonGridEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class F1Service {

    private Context context;

    public F1Service(Context pContext) throws InstantiationException {
        if (pContext == null) {
            throw new InstantiationException("atributo context obrigatório");
        }
        this.context = pContext;
    }

    public SeasonGridEntity[] findAllSeasonGridEntities(final SeasonGridListenerInterface pListener) {
        SeasonGridEntity[] entities = null;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ergast.com/api/f1/driverStandings/1.json?limit=1000";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        SeasonGridEntity[] entities = null;
                        try {
                            JSONObject mRData = response.getJSONObject("MRData");
                            JSONArray standingsList = mRData
                                    .getJSONObject("StandingsTable")
                                    .getJSONArray("StandingsLists");
                            entities = new SeasonGridEntity[mRData.getInt("total")];
                            int j = standingsList.length() - 1;
                            for(int i = 0; i < standingsList.length(); i++) {
                                SeasonGridEntity entity = new SeasonGridEntity();
                                JSONObject standing = standingsList.getJSONObject(i);
                                entity.setSeasonYear(standing.getString("season"));
                                JSONArray driverStandings = standing.getJSONArray("DriverStandings");

                                JSONObject driver = driverStandings.getJSONObject(0).getJSONObject("Driver");
                                entity.setDriverChampionName(driver.getString("givenName")+" "+driver.getString("familyName"));

                                JSONArray constructors = driverStandings.getJSONObject(0).getJSONArray("Constructors");
                                JSONObject constructor = constructors.getJSONObject(0);
                                entity.setConstructorChampionName(constructor.getString("name"));

                                entities[j--] = entity;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        pListener.onResponse(entities);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pListener.onError(error);

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);


        return entities;
    }

}
