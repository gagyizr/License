package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectChildren extends AppCompatActivity {

    ListView childrenLV;
    List<User> childrenList;

    private List<String> childrenReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_children);

        childrenLV  = (ListView)findViewById(R.id.selectChildrenLV);
        childrenList = new ArrayList<>();
        childrenReferences = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("children");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                childrenList.clear();


                for (DataSnapshot userDS : dataSnapshot.getChildren()){


                    User user = userDS.getValue(User.class);
                    if(!user.getEducator().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()) && user.getEducator().length() < 2 || user.getEducator() == null) {
                        childrenReferences.add(userDS.getKey());
                        childrenList.add(user);
                    }

                }

                UsersList adapter = new UsersList(SelectChildren.this, childrenList);
                childrenLV.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        childrenLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                FirebaseDatabase.getInstance().getReference("children").child(childrenReferences.get(i)).child("educator").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
                Intent intent = new Intent(SelectChildren.this,EducatorInterface.class);
                finish();
            }
        });
    }
}
