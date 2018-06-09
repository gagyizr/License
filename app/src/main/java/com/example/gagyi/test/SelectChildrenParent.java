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

    ListView childrenLV;
    List<User> childrenList;

    private List<String> childrenReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_children_parent);

        childrenLV  = (ListView)findViewById(R.id.selectChildrenParentLV);
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
                    if(!user.getParent().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()) && user.getParent().length() < 2) {
                        childrenReferences.add(userDS.getKey());
                        childrenList.add(user);
                    }

                }

                UsersList adapter = new UsersList(SelectChildrenParent.this, childrenList);
                childrenLV.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        childrenLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                FirebaseDatabase.getInstance().getReference("children").child(childrenReferences.get(i)).child("parent").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
                FirebaseDatabase.getInstance().getReference("parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("child").setValue(childrenReferences.get(i));
                Intent intent = new Intent(SelectChildrenParent.this,ParentInterface.class);
                finish();
            }
        });
    }
}
