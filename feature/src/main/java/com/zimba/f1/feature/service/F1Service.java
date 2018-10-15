package com.zimba.f1.feature.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zimba.f1.feature.entity.RacePositionEntity;
import com.zimba.f1.feature.entity.RaceResultsEntity;
import com.zimba.f1.feature.entity.SeasonGridEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class F1Service {

    private Context context;

    public F1Service(Context pContext) throws InstantiationException {
        if (pContext == null) {
            throw new InstantiationException("atributo context obrigatório");
        }
        this.context = pContext;
    }

    public void findAllConstructorResultsBySeasonYear(final String pSeasonYear, final ConstructorResultsListenerInterface pListener){
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ergast.com/api/f1/"+pSeasonYear+"/constructorStandings.json?limit=1000";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        RacePositionEntity[] entities = null;
                        try {
                            JSONObject mRData = response.getJSONObject("MRData");
                            JSONArray constructors = mRData
                                    .getJSONObject("StandingsTable")
                                    .getJSONArray("StandingsLists")
                                    .getJSONObject(0)
                                    .getJSONArray("ConstructorStandings");
                            entities = new RacePositionEntity[constructors.length()];
                            for (int i = 0; i < constructors.length(); i++) {
                                RacePositionEntity entity = new RacePositionEntity();
                                JSONObject constructor = constructors.getJSONObject(i);
                                entity.setPoints(constructor.getString("points"));

                                JSONObject jsonConstructor = constructor.getJSONObject("Constructor");
                                entity.setConstructorName(jsonConstructor.getString("name"));
                                entity.setConstructorCountry(jsonConstructor.getString("nationality"));

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

    public void findAllDriverResultsBySeasonYear(final String pSeasonYear, final DriverResultsListenerInterface pListener){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ergast.com/api/f1/"+pSeasonYear+"/driverStandings.json?limit=1000";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        RacePositionEntity[] entities = null;
                        try {
                            JSONObject mRData = response.getJSONObject("MRData");
                            JSONArray drivers = mRData
                                    .getJSONObject("StandingsTable")
                                    .getJSONArray("StandingsLists")
                                    .getJSONObject(0)
                                    .getJSONArray("DriverStandings");
                            entities = new RacePositionEntity[drivers.length()];
                            for (int i = 0; i < drivers.length(); i++) {
                                RacePositionEntity entity = new RacePositionEntity();
                                JSONObject driver = drivers.getJSONObject(i);
                                entity.setPoints(driver.getString("points"));

                                JSONObject jsonDriver = driver.getJSONObject("Driver");
                                entity.setDriverName(jsonDriver.getString("givenName") + " " + jsonDriver.getString("familyName"));

                                JSONObject jsonConstructor = driver.getJSONArray("Constructors").getJSONObject(0);
                                entity.setConstructorName(jsonConstructor.getString("name"));

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
                            entities = new SeasonGridEntity[mRData.getInt("total")+1];
                            int j = standingsList.length();
                            String lastYear = "";
                            for (int i = 0; i < standingsList.length(); i++) {
                                SeasonGridEntity entity = new SeasonGridEntity();
                                JSONObject standing = standingsList.getJSONObject(i);
                                entity.setSeasonYear(standing.getString("season"));
                                lastYear = entity.getSeasonYear();
                                JSONArray driverStandings = standing.getJSONArray("DriverStandings");

                                JSONObject driver = driverStandings.getJSONObject(0).getJSONObject("Driver");
                                entity.setDriverChampionName(driver.getString("givenName") + " " + driver.getString("familyName"));

                                JSONArray constructors = driverStandings.getJSONObject(0).getJSONArray("Constructors");
                                JSONObject constructor = constructors.getJSONObject(0);
                                entity.setConstructorChampionName(constructor.getString("name"));

                                entities[j--] = entity;
                            }
                            // colocando o ano mais recente. Se o campeonato ainda nao tiver acabado
                            // nao vem no resultado da query feita. Mas se tiver acabado deve vir.
                            // entao ou coloco o ano corrente (no caso do campeonato nao tiver acabado)
                            // ou o ano seguinte (caso contrário)
                            Date dataHoje = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(dataHoje);
                            if (!lastYear.equalsIgnoreCase(String.valueOf(calendar.get(Calendar.YEAR)))){
                                SeasonGridEntity entity = new SeasonGridEntity();
                                entity.setSeasonYear(String.valueOf(calendar.get(Calendar.YEAR)));
                                entities[0] = entity;
                            } else{
                                SeasonGridEntity entity = new SeasonGridEntity();
                                entity.setSeasonYear(String.valueOf(calendar.get(Calendar.YEAR)+1));
                                entities[0] = entity;
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
                                    JSONObject result = results.getJSONObject(j);

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
