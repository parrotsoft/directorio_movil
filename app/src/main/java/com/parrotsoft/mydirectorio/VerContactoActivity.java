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

public class VerContactoActivity extends AppCompatActivity implements View.OnClickListener {

    int contacto_id, usuario_id;
    EditText edNombres, edApellidos, edCorreo, edCelular;
    Button btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contacto);

        edNombres = (EditText) findViewById(R.id.edNombres);
        edApellidos = (EditText) findViewById(R.id.edApellidos);
        edCorreo = (EditText) findViewById(R.id.edCorreo);
        edCelular = (EditText) findViewById(R.id.edCelular);

        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            contacto_id = Integer.parseInt(extras.getString("contacto_id"));
            cargarContacto();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActualizar:
                onActualizar();
                break;
            case R.id.btnEliminar:
                onEliminar();
                break;
        }
    }

    private void cargarContacto() {
        Contacto.ver(this, contacto_id, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result, Context context) {
                try {
                    edNombres.setText(result.getString("nombres"));
                    edApellidos.setText(result.getString("apellidos"));
                    edCorreo.setText(result.getString("correo"));
                    edCelular.setText(result.getString("celular"));
                    usuario_id = Integer.parseInt(result.getString("usuario_id"));
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void onActualizar() {
        Map<String, String> params = new HashMap();
        params.put("nombres", edNombres.getText().toString());
        params.put("apellidos", edApellidos.getText().toString());
        params.put("correo", edCorreo.getText().toString());
        params.put("celular", edCelular.getText().toString());
        Contacto.actualizar(this, params, contacto_id, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result, Context context) {
                try {
                    if (result.has("mensaje")) {
                        if (result.getString("mensaje").equals("Contacto actualizado")) {
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

    private void onEliminar() {
        Contacto.eliminar(this, contacto_id, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result, Context context) {
                try {
                    if (result.has("mensaje")) {
                        if (result.getString("mensaje").equals("Contacto eliminado")) {
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