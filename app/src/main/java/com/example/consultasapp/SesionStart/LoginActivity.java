package com.example.consultasapp.SesionStart;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.consultasapp.R;
import com.example.consultasapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.consultasapp.databinding.ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);
        setContentView(binding.getRoot());

    }

    public void IntoNewUser(View view) {
        Intent i = new Intent(LoginActivity.this, RegistrerActivity.class);
        startActivity(i);

    }


    public void ingresar(View view) {
        Button miBoton = findViewById(R.id.ingresar);
        miBoton.setOnClickListener(v -> addDataFirestore()
            );

    }

    private void addDataFirestore(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("nombre", "greys");
        user.put("apellido",  "ponce");
        user.put("edad", 23);
        db.collection("usuarios")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "added ID: "+documentReference.getId());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "error add documents", e);
                    }
                });

    }


}