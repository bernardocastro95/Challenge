package com.example.challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText mailReset;
    private Button btnReset;
    private FirebaseAuth fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mailReset = findViewById(R.id.editResetPassword);
        btnReset = findViewById(R.id.btnReset);
        fab = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mailReset.getText().toString().trim();

                if(email.equals("")){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                }
                else {
                    fab.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this, "An email has been sent", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                            }
                            else {
                                Toast.makeText(ForgotPasswordActivity.this, "Failed to send reset password email", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
