package com.example.challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button btnRegister, btnForLogin;
    private FirebaseAuth fba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        fba = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //Data to database
                    String email = userEmail.getText().toString().trim();
                    String password = userPassword.getText().toString().trim();

                    fba.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                            else {
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        btnForLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }
    private void setupUIViews(){
        userName = findViewById(R.id.editNameRegister);
        userPassword = findViewById(R.id.editPasswordRegister);
        userEmail = findViewById(R.id.editEmailRegister);
        btnRegister = findViewById(R.id.btnRegister);
        btnForLogin = findViewById(R.id.btnForLogin);
    }
    private boolean validate(){
        boolean result = false;

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(RegistrationActivity.this, "Please enter data in fields ", Toast.LENGTH_LONG);
        }
        else {
            result = true;
        }
        return result;
    }
}
