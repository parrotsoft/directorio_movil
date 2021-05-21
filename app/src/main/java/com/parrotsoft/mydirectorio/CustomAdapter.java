package com.parrotsoft.mydirectorio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflter;
    JSONArray data;

    public CustomAdapter(Context applicationContext, JSONArray data) {
        this.context = applicationContext;
        this.data = data;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.item_listview, null);
        try {
            JSONObject responseObj = data.getJSONObject(position);
            TextView tvNombres = (TextView) convertView.findViewById(R.id.tvNombres);
            TextView tvCorreo = (TextView) convertView.findViewById(R.id.tvCorreo);
            TextView tvCelular = (TextView) convertView.findViewById(R.id.tvCelular);

            tvNombres.setText(responseObj.getString("nombres")+" "+responseObj.getString("apellidos"));
            tvCorreo.setText(responseObj.getString("correo"));
            tvCelular.setText(responseObj.getString("celular"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
