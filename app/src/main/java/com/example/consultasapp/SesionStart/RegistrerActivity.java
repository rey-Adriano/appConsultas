package com.example.consultasapp.SesionStart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.consultasapp.MainActivity;
import com.example.consultasapp.R;
import com.example.consultasapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrerActivity extends AppCompatActivity {

    Button Bregistrar;
    EditText Inombre, Ipassword, Iemail, IpassConf;

    FirebaseFirestore UFirebase;
    FirebaseAuth UAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);
        Bregistrar = findViewById(R.id.tvRegistarUser);
        Inombre = findViewById(R.id.InputName);
        Ipassword = findViewById(R.id.InputPassword);
        Iemail = findViewById(R.id.InputEmial);
        IpassConf = findViewById(R.id.InputPasswordConfirm);

        UFirebase = FirebaseFirestore.getInstance();
        UAuth = FirebaseAuth.getInstance();

        Bregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = Inombre.getText().toString().trim();
                String newEmail = Iemail.getText().toString().trim();
                String newPassword = Ipassword.getText().toString().trim();
                String confPass = IpassConf.getText().toString().trim();

                if (newName.isEmpty()&& newEmail.isEmpty() && newPassword.isEmpty() && confPass.isEmpty()){
                    Toast.makeText(RegistrerActivity.this, "complete los campos", Toast.LENGTH_SHORT).show();
                    if (newPassword != confPass){
                        Toast.makeText(RegistrerActivity.this, "las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegistrerActivity.this, "las contraseñas coinciden", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    registrerUser(newName, newEmail, newPassword);
                }

            }
        });


    }

    private void registrerUser(String newName, String newEmail, String newPassword) {
        UAuth.createUserWithEmailAndPassword(newEmail, newPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = UAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                 map.put("id", id);
                map.put("name", newName);
                map.put("email", newEmail);
                map.put("password", newPassword);

                UFirebase.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(RegistrerActivity.this, MainActivity.class));
                        Toast.makeText(RegistrerActivity.this, "Usuario Guardado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrerActivity.this, "error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrerActivity.this, "error en registro de usuario", Toast.LENGTH_SHORT).show();

            }
        }); 
    }


    public void GuardarNewUser(View view) {

    }

    public void IntoSesion(View view) {
        Intent i = new Intent(RegistrerActivity.this, LoginActivity.class);
        startActivity(i);
    }




}