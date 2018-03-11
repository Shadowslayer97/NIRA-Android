package com.example.srirang.nira_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Srirang on 3/11/2018.
 */

public class Homepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }


    public void onAddClicked(View view)
    {
        Intent intent = new Intent(getApplicationContext(),AddForm.class);
        startActivity(intent);
    }
}
