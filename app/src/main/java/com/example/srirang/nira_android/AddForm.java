package com.example.srirang.nira_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Map;

/**
 * Created by Srirang on 3/11/2018.
 */

public class AddForm extends AppCompatActivity {

    private EditText alkanity,color,ph,sodium,chloride,potassium,calcium,manganese,
                     magnesium,lead,mercury,arsenic,dissolved_oxyen,temperature,time;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addform);

        alkanity = findViewById(R.id.alkanity);
        color = findViewById(R.id.color);
        ph = findViewById(R.id.ph);
        sodium = findViewById(R.id.sodium);
        chloride = findViewById(R.id.chloride);
        potassium = findViewById(R.id.potassium);
        calcium = findViewById(R.id.calcium);
        manganese = findViewById(R.id.manganese);
        magnesium = findViewById(R.id.magnesium);
        lead = findViewById(R.id.lead);
        mercury = findViewById(R.id.mercury);
        arsenic = findViewById(R.id.arsenic);
        dissolved_oxyen = findViewById(R.id.dissolvedoxygen);
        temperature = findViewById(R.id.temperature);
        time = findViewById(R.id.time);



    }

    public void onAddSubmitClicked(View view)
    {
       DMSModal newdms = new DMSModal(Double.parseDouble(sodium.getText().toString()),
               Double.parseDouble(chloride.getText().toString()),
               Double.parseDouble(potassium.getText().toString()),
               Double.parseDouble(calcium.getText().toString()),
               Double.parseDouble(manganese.getText().toString()),
               Double.parseDouble(magnesium.getText().toString()),
               Double.parseDouble(lead.getText().toString()),
               Double.parseDouble(mercury.getText().toString()),
               Double.parseDouble(arsenic.getText().toString())
               );
       WQIModal newwqi = new WQIModal(alkanity.getText().toString(),
                color.getText().toString(),
                Double.parseDouble(ph.getText().toString()),
                newdms,Integer.parseInt(dissolved_oxyen.getText().toString()));


        Date date = new Date();
        //Not using Location API AS OF NOW HARDCODED
        AddModal newpost = new AddModal("35.09 67.98","kolhapur","up",newwqi,Double.parseDouble(temperature.getText().toString()),
                   date ,"img/src/images/public/3332434gfw2",5);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference().child("sample");
        db.push().setValue(newpost);
        Toast.makeText(AddForm.this,"Entry completed",Toast.LENGTH_LONG).show();




    }
}
