package com.parrotsoft.mydirectorio.services.helper;

import android.content.Context;

import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONObject result, Context context);
}
