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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentInterface extends AppCompatActivity {

    ListView listView;
    TextView childNameTV;

    Button selectChild;
    Button feladatokButton;
    Button statisztikaButton;
    Button mitcsinalButton;

    String[] tempFeladatok = {"Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat",};
    String[] tempStatisztika = {"Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika"};
    String[] tempMitCsinal = {"MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal"};

    ArrayAdapter<String> feladatokAdapter;
    ArrayAdapter<String> statisztikaAdapter ;
    ArrayAdapter<String> mitcsinalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_interface);

        childNameTV = (TextView)findViewById(R.id.childNameTV);

        FirebaseDatabase.getInstance().getReference("parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("child").addListenerForSingleValueEvent(new ValueEventListener() {
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

        listView = (ListView)findViewById(R.id.logParentListView);
        feladatokButton = (Button)findViewById(R.id.logParentFeladatok);
        statisztikaButton = (Button)findViewById(R.id.logParentStatisztika);
        mitcsinalButton = (Button)findViewById(R.id.logParentMitCsinal);
        selectChild = (Button)findViewById(R.id.logParentSelectChildButton);

        feladatokAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempFeladatok);
        statisztikaAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempStatisztika);
        mitcsinalAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempMitCsinal);

        feladatokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(feladatokAdapter);
            }
        });

        statisztikaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(statisztikaAdapter);
            }
        });

        mitcsinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(mitcsinalAdapter);
            }
        });

        selectChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference("parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("child").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue().toString().length()<2){
                            Intent intent = new Intent(ParentInterface.this,SelectChildrenParent.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        FirebaseDatabase.getInstance().getReference("parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("child").addListenerForSingleValueEvent(new ValueEventListener() {
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
}
