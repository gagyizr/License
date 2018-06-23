package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AddObservation extends AppCompatActivity {

    Button goButton;
    EditText observationET;
    String idOfChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_observation);

        goButton = (Button)findViewById(R.id.addObservationButton);
        observationET = (EditText)findViewById(R.id.addObservationET);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetIdOfChild();

                FirebaseDatabase.getInstance().getReference("children").child(idOfChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        AddObservation(dataSnapshot);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    void GetIdOfChild(){

        Intent intent = getIntent();
        Bundle getBundle = intent.getExtras();
        if(!getBundle.isEmpty()){
            idOfChild = getBundle.getString("childID");
        }
    }

    void AddObservation(DataSnapshot dataSnapshot){
        User user = dataSnapshot.getValue(User.class);
        List<String> observationList = user.getObservations();
        observationList.add(observationET.getText().toString());
        FirebaseDatabase.getInstance().getReference("children").child(idOfChild).child("observations").setValue(observationList);
        finish();
    }
}