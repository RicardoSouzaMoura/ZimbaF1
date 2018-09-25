package com.zimba.f1.feature.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zimba.f1.feature.entity.RacePositionEntity;
import com.zimba.f1.feature.entity.RaceResultsEntity;
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

    public void findAllSeasonGridEntities(final SeasonGridListenerInterface pListener) {

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
                            for (int i = 0; i < standingsList.length(); i++) {
                                SeasonGridEntity entity = new SeasonGridEntity();
                                JSONObject standing = standingsList.getJSONObject(i);
                                entity.setSeasonYear(standing.getString("season"));
                                JSONArray driverStandings = standing.getJSONArray("DriverStandings");

                                JSONObject driver = driverStandings.getJSONObject(0).getJSONObject("Driver");
                                entity.setDriverChampionName(driver.getString("givenName") + " " + driver.getString("familyName"));

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
    }

    public void findRaceResultsBySeasonYear(final String pSeasonYear, final RacesResultsListenerInterface pListener) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ergast.com/api/f1/"+pSeasonYear+"/results.json?limit=1000";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        RaceResultsEntity[] entities = null;
                        try {
                            JSONObject mRData = response.getJSONObject("MRData");
                            JSONArray races = mRData
                                    .getJSONObject("RaceTable")
                                    .getJSONArray("Races");
                            entities = new RaceResultsEntity[races.length()];
                            for (int i = 0; i < races.length(); i++) {
                                RaceResultsEntity entity = new RaceResultsEntity();
                                JSONObject race = races.getJSONObject(i);
                                entity.setRaceName(race.getString("raceName"));
                                entity.setSeasonYear(pSeasonYear);
                                entity.setDate(race.getString("date"));
                                entity.setTime(race.getString("time"));
                                JSONObject circuit = race.getJSONObject("Circuit");
                                entity.setCircuitName(circuit.getString("circuitName"));
                                JSONObject location = circuit.getJSONObject("Location");
                                entity.setLocality(location.getString("locality"));
                                entity.setCountry(location.getString("country"));

                                JSONArray results = race.getJSONArray("Results");

                                RacePositionEntity[] racePositionEntities = new RacePositionEntity[results.length()];
                                for (int j = 0; j < results.length(); j++) {
                                    RacePositionEntity racePositionEntity = new RacePositionEntity();
                                    JSONObject result = results.getJSONObject(i);

                                    racePositionEntity.setConstructorName(result.getJSONObject("Constructor").getString("name"));
                                    racePositionEntity.setConstructorNumber(result.getString("number"));
                                    JSONObject driver = result.getJSONObject("Driver");
                                    racePositionEntity.setDriverName(driver.getString("givenName") + driver.getString("familyName"));
                                    racePositionEntity.setPoints(result.getString("points"));
                                    racePositionEntity.setPosition(result.getString("position"));
                                    String status = result.getString("status");
                                    // quando o status é diferente de Finished nem vem o atributo Time
                                    if ("Finished".equalsIgnoreCase(status)) {
                                        racePositionEntity.setTime(result.getJSONObject("Time").getString("time"));
                                    }
                                    else{
                                        racePositionEntity.setTime(status);
                                    }

                                    racePositionEntities[j] = racePositionEntity;
                                }
                                entity.setPositions(racePositionEntities);
                                entities[i] = entity;

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
    }

}
