package com.parrotsoft.mydirectorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.parrotsoft.mydirectorio.services.Contacto;
import com.parrotsoft.mydirectorio.services.helper.VolleyCallback;
import com.parrotsoft.mydirectorio.services.helper.VolleyCallbackArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.parrotsoft.mydirectorio.R.layout.activity_home;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    public int usuario_id;
    ListView lvContactos;
    Button btnAgregar;
    JSONArray contactosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_home);

        lvContactos = (ListView) findViewById(R.id.lvContactos);

        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    JSONObject responseObj = contactosList.getJSONObject(position);
                    Intent intent = new Intent(getApplicationContext(), VerContactoActivity.class);
                    intent.putExtra("contacto_id", responseObj.getString("id"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuario_id = extras.getInt("usuario_id");
            loadListConstactos();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAgregar:
                addContacto();
                break;
        }
    }

    private void loadListConstactos() {
        Contacto.listar(this, usuario_id, new VolleyCallbackArray() {
            @Override
            public void onSuccess(JSONArray result, Context context) {
                try {
                    contactosList = result;
                    CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), result);
                    lvContactos.setAdapter(customAdapter);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addContacto() {
        Intent intent = new Intent(this, AgregarContactoActivity.class);
        intent.putExtra("usuario_id", usuario_id);
        startActivity(intent);
    }


}