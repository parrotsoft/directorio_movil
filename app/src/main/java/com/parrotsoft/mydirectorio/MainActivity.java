package com.parrotsoft.mydirectorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parrotsoft.mydirectorio.services.Auth;
import com.parrotsoft.mydirectorio.services.helper.DialogCallback;
import com.parrotsoft.mydirectorio.services.helper.Helpers;
import com.parrotsoft.mydirectorio.services.helper.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edUsuario, edClave;

    Button btnEntrar, btnRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edUsuario.setText("mlopez");
        edClave = (EditText) findViewById(R.id.edClave);
        edClave.setText("123456789");

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistrate = (Button) findViewById(R.id.btnRegistrate);

        this.btnEntrar.setOnClickListener(this);
        this.btnRegistrate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEntrar:
                onEntrar();
                break;
            case R.id.btnRegistrate:
                onRegistrate();
                break;
        }
    }

    private void onEntrar() {
        Map<String, String> params = new HashMap();
        params.put("usuario", this.edUsuario.getText().toString());
        params.put("clave", edClave.getText().toString());
        Auth.login(this, params, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result, Context context) {
                try {
                    if (result.has("id")) {
                        Helpers.dialog(context, "Perfecto", "Datos correctos", new DialogCallback() {
                                    @Override
                                    public void onOk(Context context) {
                                        Intent intent = new Intent(context, HomeActivity.class);
                                        startActivity(intent);
                                    }
                                }
                        );
                    } else {
                        Helpers.dialog(context,"Error",result.getString("mensaje"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void onRegistrate() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}