package com.parrotsoft.mydirectorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parrotsoft.mydirectorio.services.Aut;

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
                Aut.login(this,this.edUsuario.getText().toString(),edClave.getText().toString());
                break;
            case R.id.btnRegistrate:
                break;
        }
    }


}