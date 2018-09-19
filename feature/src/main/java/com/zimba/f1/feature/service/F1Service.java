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

import org.json.JSONObject;


public class F1Service {

    private Context context;

    public F1Service(Context pContext) throws InstantiationException{
        if (pContext == null){
            throw new InstantiationException("atributo context obrigatório");
        }
        this.context = pContext;
    }

    public SeasonGridEntity[] findAllSeasonGridEntities(){
        SeasonGridEntity[] entities = null;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://ergast.com/api/f1/driverStandings/1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTextView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);



        return entities;
    }

}
