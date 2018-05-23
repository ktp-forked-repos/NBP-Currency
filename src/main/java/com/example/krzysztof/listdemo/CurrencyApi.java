package com.example.krzysztof.listdemo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrencyApi {
    private OnCurrencyReceived currencyReceived;

    private Context mContext;
    // http://api.nbp.pl/#kursyWalut
    final private String apiUrl = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";
    private List<Currency> currencyData;
    private RequestQueue queue;

    public CurrencyApi(OnCurrencyReceived currencyReceived, Context pContext) {
        this.currencyReceived = currencyReceived;
        this.mContext = pContext;
        this.queue = Volley.newRequestQueue(pContext);
        this.currencyData = new ArrayList<>();
    }

    public void fetchData(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, apiUrl, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject data = response.getJSONObject(0);
                            JSONArray rates = data.getJSONArray("rates");
                            Gson gson = new Gson();
                            currencyData = gson.fromJson(rates.toString(),  new TypeToken<ArrayList<Currency>>(){}.getType());
                            currencyReceived.onDataHandle(currencyData);
                        } catch (JSONException e) {
                            Toast.makeText(mContext, "Could not parse server response", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Could not connect server: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jsonArrayRequest);
    }
}
