package com.example.consultasapp.SesionStart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.consultasapp.R;

public class RegistrerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);
    }

    public void GuardarNewUser(View view) {
    }

    public void IntoSesion(View view) {
        Intent i = new Intent(RegistrerActivity.this, LoginActivity.class);
        startActivity(i);
    }
}