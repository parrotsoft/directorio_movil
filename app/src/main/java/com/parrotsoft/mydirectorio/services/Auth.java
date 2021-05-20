package com.parrotsoft.mydirectorio.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.parrotsoft.mydirectorio.R;
import com.parrotsoft.mydirectorio.services.helper.Helpers;
import com.parrotsoft.mydirectorio.services.helper.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;

public class Auth {

    public static void login(Context context, Map<String, String> params, final VolleyCallback callback) {

        JSONObject parameters = new JSONObject(params);
        String url = context.getString(R.string.url)+"login";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,
                parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response, context);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Helpers.dialog(context,"Error",error.getMessage());
            }
        });
        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonRequest);
    }

    public static void registro(Context context, Map<String, String> params, final VolleyCallback callback) {
        JSONObject parameters = new JSONObject(params);
        String url = context.getString(R.string.url)+"usuario";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,
                parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response, context);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Helpers.dialog(context,"Error",error.getMessage());
            }
        });
        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonRequest);
    }

}
