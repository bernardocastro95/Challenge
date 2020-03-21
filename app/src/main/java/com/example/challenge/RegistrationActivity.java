package com.example.challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, userAge;
    private Button btnRegister, btnForLogin;
    private FirebaseAuth fba;
    private ImageView userProfile;
    String name, password, email, age;
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
                    String email = userEmail.getText().toString().trim();
                    String password = userPassword.getText().toString().trim();

                    fba.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //sendMail();
                                sendData();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload Completed", Toast.LENGTH_LONG).show();
                                finish();
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
        userAge = findViewById(R.id.editAge);
        userProfile = findViewById(R.id.profileImage);
    }
    private boolean validate(){
        boolean result = false;

       name = userName.getText().toString();
       password = userPassword.getText().toString();
       email = userEmail.getText().toString();
       age = userAge.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()){
            Toast.makeText(RegistrationActivity.this, "Please enter data in fields ", Toast.LENGTH_LONG);
        }
        else {
            result = true;
        }
        return result;
    }
    /*private void sendMail(){
        final FirebaseUser fbu = fba.getCurrentUser();
        if(fbu != null){
            fbu.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendData();
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification Mail has Been Sent", Toast.LENGTH_LONG).show();
                        fba.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }
                }
            });
        }
        else {
            Toast.makeText(RegistrationActivity.this, "Failed To Send Email", Toast.LENGTH_LONG).show();
        }
    }*/
    private void sendData(){
        FirebaseDatabase fbd = FirebaseDatabase.getInstance();
        DatabaseReference dr = fbd.getReference(fba.getUid());
        UserProfile up = new UserProfile(age, email, name);
        dr.setValue(up);
    }
}
