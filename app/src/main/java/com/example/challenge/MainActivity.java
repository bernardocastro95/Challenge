package com.example.challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnAccess, btnNewUser;
    private EditText name;
    private EditText password;
    private FirebaseAuth fab;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccess = findViewById(R.id.btnLogin);
        name = findViewById(R.id.editName);
        password = findViewById(R.id.editPassword);
        btnNewUser = findViewById(R.id.btnNewUser);

        fab = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);


        FirebaseUser fbu = fab.getCurrentUser();

        if(fbu != null){
            finish();
            startActivity(new Intent(MainActivity.this, DatabaseActivity.class));
        }

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

        pd.setMessage("Login in Progress");
        pd.show();

        fab.signInWithEmailAndPassword(userLogin, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    pd.dismiss();
                    checkMailVerification();
                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                }
                else {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkMailVerification(){
        FirebaseUser fbu = fab.getInstance().getCurrentUser();
        Boolean mailFlag = fbu.isEmailVerified();

        if(mailFlag){
            finish();
            startActivity(new Intent(MainActivity.this, DatabaseActivity.class));
        }
        else {
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_LONG).show();
            fab.signOut();
        }
    }
}
