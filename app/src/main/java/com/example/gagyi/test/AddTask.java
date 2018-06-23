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

public class AddTask extends AppCompatActivity {

    Button goButton;
    EditText taskET;
    String idOfChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        goButton = (Button)findViewById(R.id.addTaskButton);
        taskET = (EditText)findViewById(R.id.addTaskET);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetIdOfChild();

                FirebaseDatabase.getInstance().getReference("children").child(idOfChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        AddTask(dataSnapshot);
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

    void AddTask(DataSnapshot dataSnapshot){

        User user = dataSnapshot.getValue(User.class);
        List<String> tasksList = user.getTasks();
        tasksList.add(taskET.getText().toString());
        FirebaseDatabase.getInstance().getReference("children").child(idOfChild).child("tasks").setValue(tasksList);
        finish();
    }
}