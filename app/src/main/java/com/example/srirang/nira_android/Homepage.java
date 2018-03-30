package com.example.srirang.nira_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
                Intent intent = new Intent(Homepage.this, AddForm.class);
                startActivity(intent);
            }
        });
    }
}
