package com.example.srirang.nira_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Date;

public class AddSampleFormActivity extends AppCompatActivity {
    private ArrayList<Parameter> parameterList;

    private boolean mFieldsHasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mFieldsHasChanged = true;
            return false;
        }
    };
    private String url;
    private String lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sample_form);

        Intent intent = getIntent();
        if (intent.hasExtra("KEY_CLASS")) {
            parameterList = new ArrayList<>();
            String userClass = intent.getStringExtra("KEY_CLASS");
            if (userClass.equals("a")) {
                parameterList = getClassAList();
            } else if (userClass.equals("b")) {
                parameterList = getClassBList();
            } else {
                parameterList = getClassCList();
            }
        }
        RecyclerView etRecycler = findViewById(R.id.rv_add_sample);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        etRecycler.setLayoutManager(llm);

        AddSampleAdapter adapter = new AddSampleAdapter();
        etRecycler.setAdapter(adapter);

        etRecycler.setOnTouchListener(mTouchListener);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(0f);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                onAddSubmitClicked();
                return true;
            case android.R.id.home:
                //Show warning because maybe user pressed back button accidently.
                if (!mFieldsHasChanged) {
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }

                showUnsavedChangesDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!mFieldsHasChanged) {
            super.onBackPressed();
            return;
        }
        showUnsavedChangesDialog();
    }

    public void onAddSubmitClicked() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("sampler")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("partial");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                url = String.valueOf(dataSnapshot.child("url"));
                lat = String.valueOf(dataSnapshot.child("lat"));
                lng = String.valueOf(dataSnapshot.child("lng"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        DMSModal newdms = new DMSModal(Double.parseDouble("45"),
                Double.parseDouble("32"),
                Double.parseDouble("75"),
                Double.parseDouble("12"),
                Double.parseDouble("6"),
                Double.parseDouble("23"),
                Double.parseDouble("12"),
                Double.parseDouble("9"),
                Double.parseDouble("10")
        );
        WQIModal newwqi = new WQIModal("32",
                "brownish",
                Double.parseDouble("5"),
                newdms, Integer.parseInt("34"));


        Date date = new Date();
        //Not using Location API AS OF NOW HARDCODED
        AddModal newpost = new AddModal(lat + " " + lng, "kolhapur", "up", newwqi, Double.parseDouble("23"),
                date, url, 5);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference()
                .child("sampler")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("completed");
        db.push().setValue(newpost);
        Toast.makeText(this, "Entry Completed", Toast.LENGTH_LONG).show();
    }

    private void showUnsavedChangesDialog() {
        DialogInterface.OnClickListener yesButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NavUtils.navigateUpFromSameTask(AddSampleFormActivity.this);
            }
        };

        DialogInterface.OnClickListener noButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Discard Changes?")
                .setPositiveButton("Yes", yesButtonClickListener)
                .setNegativeButton("No", noButtonClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private class AddSampleAdapter extends RecyclerView.Adapter<AddSampleViewHolder> {

        @NonNull
        @Override
        public AddSampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_sample_list_item, parent, false);
            return new AddSampleViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AddSampleViewHolder holder, int position) {
            holder.editText.setInputType(parameterList.get(position).getInputType());
            holder.editText.setHint(parameterList.get(position).getHint());
        }

        @Override
        public int getItemCount() {
            return parameterList.size();
        }
    }

    public class AddSampleViewHolder extends RecyclerView.ViewHolder {
        private EditText editText;

        public AddSampleViewHolder(View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.et_add_sample_data);
        }
    }

    private class Parameter {
        private String hint;
        private int inputType;

        public Parameter(String hint, int inputType) {
            this.hint = hint;
            this.inputType = inputType;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public int getInputType() {
            return inputType;
        }

        public void setInputType(int inputType) {
            this.inputType = inputType;
        }
    }

    private ArrayList<Parameter> getClassAList() {
        ArrayList<Parameter> list = new ArrayList<>();
        list.add(new Parameter("Temperature", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Colour", InputType.TYPE_CLASS_TEXT));
        list.add(new Parameter("Odour", InputType.TYPE_CLASS_TEXT));
        list.add(new Parameter("TDS", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("pH", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Dissolved Oxygen", InputType.TYPE_CLASS_NUMBER));
        return list;

    }

    private ArrayList<Parameter> getClassBList() {
        ArrayList<Parameter> list = new ArrayList<>();
        list.add(new Parameter("Temperature", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("TDS", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("pH", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Dissolved Oxygen", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("BOD", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("COD", InputType.TYPE_CLASS_TEXT));
        list.add(new Parameter("Sodium", InputType.TYPE_CLASS_TEXT));
        list.add(new Parameter("Calcium", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Magnesium", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Potassium", InputType.TYPE_CLASS_NUMBER));
        return list;
    }

    private ArrayList<Parameter> getClassCList() {
        ArrayList<Parameter> list = new ArrayList<>();
        list.add(new Parameter("Temperature", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("TDS", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("pH", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Dissolved Oxygen", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("BOD", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("COD", InputType.TYPE_CLASS_TEXT));
        list.add(new Parameter("Sodium", InputType.TYPE_CLASS_TEXT));
        list.add(new Parameter("Calcium", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Magnesium", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Potassium", InputType.TYPE_CLASS_NUMBER));
        list.add(new Parameter("Iron", InputType.TYPE_CLASS_NUMBER));
        return list;
    }
}
