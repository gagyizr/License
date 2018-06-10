package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EducatorLog extends AppCompatActivity {

    ListView listView;
    TextView nameOfChild;

    private Button addDiaryButton;
    private Button addObservationButton;
    private Button addGameToChildButton;
    private Button addtaskButton;

    Button mitButton;
    Button kiadottButton;
    Button megoldottButton;

    List<String> tempMitCsinalt = new ArrayList<>();
    List<String> tempKiadottFeladatok = new ArrayList<>();
    List<String> tempObservations = new ArrayList<>();

    ArrayAdapter<String> mitAdatpet;
    ArrayAdapter<String> kiadottAdapter ;
    ArrayAdapter<String> observationsAdapter;

    String idOfChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educator_log);

        listView = (ListView)findViewById(R.id.logListView);
        mitButton = (Button)findViewById(R.id.logMitCsinalt);
        kiadottButton = (Button)findViewById(R.id.logKiadott);
        megoldottButton = (Button)findViewById(R.id.logMegoldott);
        nameOfChild = (TextView)findViewById(R.id.educatorLogChildName);

        addDiaryButton = (Button)findViewById(R.id.addDiary);
        addObservationButton = (Button)findViewById(R.id.addObservation);
        addGameToChildButton = (Button)findViewById(R.id.addGameToChild);
        addtaskButton = (Button)findViewById(R.id.addTask);

        Intent intent = getIntent();
        Bundle getBundle = intent.getExtras();
        if(!getBundle.isEmpty()){
            idOfChild = getBundle.getString("childID");
        }


        FirebaseDatabase.getInstance().getReference("children").child(idOfChild).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                nameOfChild.setText("Gyerek neve:\n" + user.getFirstName() + " " + user.getLastName());

                tempMitCsinalt.addAll(user.getDiary());
                tempKiadottFeladatok.addAll(user.getTasks());
                tempObservations.addAll(user.getObservations());

                mitAdatpet = new ArrayAdapter<>(EducatorLog.this,android.R.layout.simple_list_item_1,tempMitCsinalt);
                kiadottAdapter = new ArrayAdapter<>(EducatorLog.this,android.R.layout.simple_list_item_1,tempKiadottFeladatok);
                observationsAdapter = new ArrayAdapter<>(EducatorLog.this,android.R.layout.simple_list_item_1, tempObservations);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(mitAdatpet);
            }
        });

        kiadottButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(kiadottAdapter);
            }
        });

        megoldottButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(observationsAdapter);
            }
        });

        addDiaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("childID",idOfChild);
                Intent intent = new Intent(EducatorLog.this,AddDiary.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        addtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("childID",idOfChild);
                Intent intent = new Intent(EducatorLog.this,AddTask.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        addObservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("childID",idOfChild);
                Intent intent = new Intent(EducatorLog.this,AddObservation.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        addGameToChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
