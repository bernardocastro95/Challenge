package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAccess, btnNewUser;
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccess = findViewById(R.id.btnLogin);
        name = findViewById(R.id.editName);
        password = findViewById(R.id.editPassword);
        btnNewUser = findViewById(R.id.btnNewUser);

        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(), password.getText().toString());
            }
        });
       btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
    private void validate(String userLogin, String userPassword){
        if((userLogin.equals("Admin")) && (userPassword.equals("admin"))){
            Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_LONG).show();
        }
    }
}
