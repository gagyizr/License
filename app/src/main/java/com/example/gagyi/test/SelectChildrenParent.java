package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SelectChildrenParent extends AppCompatActivity {

    ListView parentLV;
    List<Parent> parentsList;
    String idOfChild;

    private List<String> childrenReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_children_parent);

        parentLV = (ListView)findViewById(R.id.selectChildrenParentLV);
        parentsList = new ArrayList<>();
        childrenReferences = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("parents");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                parentsList.clear();

                for (DataSnapshot userDS : dataSnapshot.getChildren()){

                    Parent parent = userDS.getValue(Parent.class);

                        childrenReferences.add(userDS.getKey());
                        parentsList.add(parent);
                }

                ParentList adapter = new ParentList(SelectChildrenParent.this, parentsList);
                parentLV.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        parentLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AddChildToParent(i);

            }
        });
    }

    void AddChildToParent(int i){
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        if(!bundle.isEmpty()) {
            idOfChild = bundle.getString("childID");
        }
        FirebaseDatabase.getInstance().getReference("children").child(idOfChild).child("parent").setValue(childrenReferences.get(i));
        FirebaseDatabase.getInstance().getReference("parents").child(childrenReferences.get(i)).child("child").setValue(idOfChild);
        finish();

    }
}
