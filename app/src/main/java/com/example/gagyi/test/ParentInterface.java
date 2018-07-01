package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParentInterface extends AppCompatActivity {

    ListView listView;
    TextView childNameTV;

    Button feladatokButton;
    Button diaryButton;
    Button observationsButton;
    Button mitCsinalButton;

    private List<String> tempFeladatok = new ArrayList<>();
    private List<String> tempDiary = new ArrayList<>();
    private List<String> tempObservations = new ArrayList<>();

    ArrayAdapter<String> feladatokAdapter;
    ArrayAdapter<String> diaryAdapter;
    ArrayAdapter<String> observationsAdapter;

    private User ownChild;
    private String whatIsChildDoing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_interface);

        childNameTV = (TextView)findViewById(R.id.childNameTV);

        GetChildName();

        PopulateData();

        listView = (ListView)findViewById(R.id.logParentListView);

        feladatokButton = (Button)findViewById(R.id.logParentFeladatok);
        diaryButton = (Button)findViewById(R.id.logParentDiaryButton);
        observationsButton = (Button)findViewById(R.id.logParentStatisztika);
        //selectChild = (Button)findViewById(R.id.logParentSelectChildButton);


        ////

        feladatokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(feladatokAdapter);
            }
        });

        diaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(diaryAdapter);
            }
        });

        observationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(observationsAdapter);
            }
        });

        mitCsinalButton = (Button)findViewById(R.id.logParentMitCsinal) ;
        mitCsinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ParentInterface.this, whatIsChildDoing, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        GetChildName();
    }

    void GetChildName(){

        FirebaseDatabase.getInstance().getReference("parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("child").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().length() > 0)
                {
                    FirebaseDatabase.getInstance().getReference("parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("child").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            FirebaseDatabase.getInstance().getReference("children").child(dataSnapshot.getValue().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    childNameTV.setText("Gyerek neve:\n"+ user.getFirstName() + " " + user.getLastName());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    childNameTV.setText("Gyerek neve:");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void PopulateData(){

        FirebaseDatabase.getInstance().getReference("children").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childrenDS : dataSnapshot.getChildren()){
                    if(childrenDS.child("parent").exists() && childrenDS.child("parent").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())){
                        ownChild = childrenDS.getValue(User.class);
                        //Log.e("Parent Interface",user.getFirstName());
                    }
                }

                //init lists
                tempFeladatok = new ArrayList<>();
                tempDiary = new ArrayList<>();
                tempObservations = new ArrayList<>();

                if(ownChild!=null) {
                    //add values
                    //fel.clear();
                    tempDiary.clear();
                    tempObservations.clear();

                    for (String feladat : ownChild.getTasks()) {
                        tempFeladatok.add(feladat);
                    }

                    for (String diaryLog : ownChild.getDiary()) {
                        tempDiary.add(diaryLog);
                    }

                    for (String observation : ownChild.getObservations()) {
                        tempObservations.add(observation);
                    }

                    whatIsChildDoing = ownChild.getCurrentActivity();

                    feladatokAdapter = new ArrayAdapter<>(ParentInterface.this, android.R.layout.simple_list_item_1, tempFeladatok);
                    diaryAdapter = new ArrayAdapter<>(ParentInterface.this, android.R.layout.simple_list_item_1, tempDiary);
                    observationsAdapter = new ArrayAdapter<>(ParentInterface.this, android.R.layout.simple_list_item_1, tempObservations);

                    listView.setAdapter(observationsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        FirebaseAuth.getInstance().signOut();
    }
}
