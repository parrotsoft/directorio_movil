package com.parrotsoft.mydirectorio.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parrotsoft.mydirectorio.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Auth {

    public static void login(Context context, Map<String, String> params) {

        JSONObject parameters = new JSONObject(params);
        String url = context.getString(R.string.url)+"login";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,
                parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("id")) {
                        Toast.makeText(context.getApplicationContext(),
                                "Datos correctos, entre...", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context.getApplicationContext(),
                                response.getString("mensaje"), Toast.LENGTH_LONG).show();
                        System.out.println(response.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.err.println(error);
                Toast.makeText(context.getApplicationContext(), "Error "+error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonRequest);

    }


}
