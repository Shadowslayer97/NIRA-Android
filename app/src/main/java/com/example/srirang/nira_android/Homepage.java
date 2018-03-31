package com.example.srirang.nira_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Srirang on 3/11/2018.
 */

public class Homepage extends AppCompatActivity {
    private LinearLayout mNewButton;
    private LinearLayout mUpdateButton;
    private LinearLayout mPartialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        String userClassEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        final String userClass;
        if (userClassEmail.contains("classa")) {
            userClass = "A";
        } else if (userClassEmail.contains("classb")) {
            userClass = "B";
        } else {
            userClass = "C";
        }
        Toast.makeText(this, "Class " + userClass + " logged in", Toast.LENGTH_LONG).show();
        mNewButton = (LinearLayout) findViewById(R.id.cv_new_sample);
        mUpdateButton = (LinearLayout) findViewById(R.id.cv_update_sample);
        mPartialButton = (LinearLayout) findViewById(R.id.cv_edit_partial_sample);

        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, CameraPreview.class);
                startActivity(intent);
            }
        });

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, UpdateSampleActivity.class);
                startActivity(intent);
            }
        });

        mPartialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, AddSampleFormActivity.class);
                intent.putExtra("KEY_CLASS", userClass.toLowerCase());
                startActivity(intent);
            }
        });
    }
}
