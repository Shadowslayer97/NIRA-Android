package com.example.srirang.nira_android;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText etname, etpassword;
    Button btsubmit;

    //FIREBASE AUTHENTICATION
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Instantiating
        etname = (EditText) findViewById(R.id.etname);
        etpassword = (EditText) findViewById(R.id.etpassword);
        btsubmit = (Button) findViewById(R.id.btsubmit);


        mAuth = FirebaseAuth.getInstance();


    }

    public void onSubmitClicked(View view) {
        String name = etname.getText().toString();
        String password = etpassword.getText().toString();
        mAuth.signInWithEmailAndPassword(name, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Sign in failed", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Homepage.class);
                            startActivity(intent);
                        }

                    }
                });
    }
}
