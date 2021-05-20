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

import static com.parrotsoft.mydirectorio.R.layout.*;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrate;
    private EditText edUsuario, edClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_registro);

        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edClave = (EditText) findViewById(R.id.edClave);

        btnRegistrate = (Button) findViewById(R.id.btnRegistrate);
        btnRegistrate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistrate:
                onRegistro();
                break;
        }
    }

    private void onRegistro() {
        Map<String, String> params = new HashMap();
        params.put("usuario", this.edUsuario.getText().toString());
        params.put("clave", edClave.getText().toString());

        Auth.registro(this, params, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result, Context context) {
                try {
                    if (result.has("mensaje")) {
                        Helpers.dialog(context, "Perfecto", result.getString("mensaje"), new DialogCallback() {
                            @Override
                            public void onOk(Context context) {
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}