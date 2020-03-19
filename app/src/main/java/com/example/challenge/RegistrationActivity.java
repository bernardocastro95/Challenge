package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button btnRegister;
    private TextView userLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //Data to database
                }
            }
        });
        /*userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });*/

    }
    private void setupUIViews(){
        userName = findViewById(R.id.editNameRegister);
        userPassword = findViewById(R.id.editPasswordRegister);
        userEmail = findViewById(R.id.editEmailRegister);
        btnRegister = findViewById(R.id.btnRegister);
        userLogin = findViewById(R.id.textLogin);
    }
    private boolean validate(){
        boolean result = false;

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(name.isEmpty() && password.isEmpty() && email.isEmpty()){
            Toast.makeText(RegistrationActivity.this, "Please enter data in fields ", Toast.LENGTH_LONG);
        }
        else {
            result = true;
        }
        return result;
    }
}
