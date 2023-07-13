package com.example.consultasapp.SesionStart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.consultasapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button Bingresar;
    TextView Lpassword, Lemail;
    FirebaseAuth UAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bingresar = findViewById(R.id.BtnIngresar);
        Lpassword = findViewById(R.id.InputPassword1);
        Lemail= findViewById(R.id.InputEmial1);
        UAuth = FirebaseAuth.getInstance();

        Bingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = Lemail.getText().toString().trim();
                String passUser = Lpassword.getText().toString().trim();


                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Ingresar datos", Toast.LENGTH_SHORT).show();

                } else {
                    loginUser(emailUser, passUser);

                }
            }
        });

    }

    private void loginUser(String emailUser, String passUser) {
        UAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Error Usuario Invalido", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "error en el inicio de sesión", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void IntoNewUser(View view) {
        Intent i = new Intent(LoginActivity.this, RegistrerActivity.class);
        startActivity(i);

    }

    public void ingresar(View view) {

    }

}