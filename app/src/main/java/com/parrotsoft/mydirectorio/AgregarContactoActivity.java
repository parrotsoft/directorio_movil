package com.parrotsoft.mydirectorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parrotsoft.mydirectorio.services.Contacto;
import com.parrotsoft.mydirectorio.services.helper.DialogCallback;
import com.parrotsoft.mydirectorio.services.helper.Helpers;
import com.parrotsoft.mydirectorio.services.helper.VolleyCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AgregarContactoActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edNombres, edApellidos, edCorreo, edCelular;
    Button  btnGuardar;
    int usuario_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuario_id = extras.getInt("usuario_id");
        }

        edNombres = (EditText) findViewById(R.id.edNombres);
        edApellidos = (EditText) findViewById(R.id.edApellidos);
        edCorreo = (EditText) findViewById(R.id.edCorreo);
        edCelular = (EditText) findViewById(R.id.edCelular);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                onGuardar();
                break;
        }
    }

    private void onGuardar() {
        Map<String, String> params = new HashMap();
        params.put("nombres", edNombres.getText().toString());
        params.put("apellidos", edApellidos.getText().toString());
        params.put("correo", edCorreo.getText().toString());
        params.put("celular", edCelular.getText().toString());
        params.put("usuario_id", ""+usuario_id);
        Contacto.guardar(this, params, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result, Context context) {
                try {
                    if (result.has("mensaje")) {
                        if (result.getString("mensaje").equals("Contacto creado")) {
                            Helpers.dialog(context, "Perfecto", result.getString("mensaje"), new DialogCallback() {
                                @Override
                                public void onOk(Context context) {
                                    Intent intent = new Intent(context, HomeActivity.class);
                                    intent.putExtra("usuario_id", usuario_id);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}