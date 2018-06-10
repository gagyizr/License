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

public class AddDiary extends AppCompatActivity {

    Button goButton;
    EditText diaryET;
    String idOfChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        goButton = (Button)findViewById(R.id.addDiaryButton);
        diaryET = (EditText)findViewById(R.id.addDiaryET);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                Bundle getBundle = intent.getExtras();
                if(!getBundle.isEmpty()){
                    idOfChild = getBundle.getString("childID");
                }

                FirebaseDatabase.getInstance().getReference("children").child(idOfChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        List<String> diaryList = user.getDiary();
                        diaryList.add(diaryET.getText().toString());
                        FirebaseDatabase.getInstance().getReference("children").child(idOfChild).child("diary").setValue(diaryList);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
