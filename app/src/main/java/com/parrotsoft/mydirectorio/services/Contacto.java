package com.parrotsoft.mydirectorio.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.parrotsoft.mydirectorio.R;
import com.parrotsoft.mydirectorio.services.helper.Helpers;
import com.parrotsoft.mydirectorio.services.helper.VolleyCallback;
import com.parrotsoft.mydirectorio.services.helper.VolleyCallbackArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class Contacto {

    public static void listar(Context context, int usuario_id, final VolleyCallbackArray callback) {
        String url = context.getString(R.string.url)+"directorio/listar/"+usuario_id;
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray  response) {
                        callback.onSuccess(response, context);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsonRequest);
    }

    public static void guardar(Context context, Map<String, String> params, final VolleyCallback callback) {
        JSONObject parameters = new JSONObject(params);
        String url = context.getString(R.string.url)+"directorio";

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

    public static void ver(Context context, int contacto_id, final VolleyCallback callback) {

        String url = context.getString(R.string.url)+"directorio/"+contacto_id;

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
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

    public static void actualizar(Context context, Map<String, String> params, int contacto_id, final VolleyCallback callback) {
        JSONObject parameters = new JSONObject(params);
        String url = context.getString(R.string.url)+"directorio/"+contacto_id;

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url,
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


    public static void eliminar(Context context, int contacto_id, final VolleyCallback callback) {
        String url = context.getString(R.string.url)+"directorio/"+contacto_id;

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.DELETE, url,
                null, new Response.Listener<JSONObject>() {
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
