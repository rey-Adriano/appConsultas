package com.example.consultasapp.SesionStart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.consultasapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void IntoNewUser(View view) {
        Intent  i = new Intent(LoginActivity.this, RegistrerActivity.class);
        startActivity(i);
    }

    public void ingresar(View view) {
    }
}