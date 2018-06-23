package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EducatorInterface extends AppCompatActivity {

    private Button addGameButton;
    private Button selectChildButton;

    ListView childActivtyLV;
    List<User> childActivityList;
    List<User> namesList;
    List<String> childIDList = new ArrayList<>();

    ListView activityListiew;
    ListView namesListView;

    String loggedInEducator;

    DatabaseReference databaseReference;

   @Override
    protected void onStart() {
        super.onStart();


        loggedInEducator = FirebaseDatabase.getInstance().getReference("educators")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getKey();


        activityListiew = (ListView)findViewById(R.id.activities_listview);
        namesListView = (ListView)findViewById(R.id.names_listview);

       final FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference ref = database.getReference("children");

        namesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                OpenLog(i);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                childActivityList.clear();
                childIDList.clear();

                Log.e("LOG",dataSnapshot.getChildrenCount() + " ");
                for (DataSnapshot gameSnapShot : dataSnapshot.getChildren()){

                    User user = gameSnapShot.getValue(User.class);

                    //populate the educators children only into the list
                    if(user.getEducator() != null && user.getEducator().contentEquals(loggedInEducator)) {
                        childIDList.add(gameSnapShot.getKey());
                        childActivityList.add(user);
                    }

                }


                ActivityList adapter = new ActivityList(EducatorInterface.this, childActivityList);
                childActivtyLV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       //final FirebaseDatabase database = FirebaseDatabase.getInstance();

       DatabaseReference ref2 = database.getReference("children");
       ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PopulateChildrenList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("names");

        setContentView(R.layout.activity_educator_interface);
        addGameButton = (Button) findViewById(R.id.addGameButton);
        selectChildButton = (Button) findViewById(R.id.selectChildrenButton);
        childActivtyLV = (ListView) findViewById(R.id.activities_listview);

        childActivityList = new ArrayList<>();
        namesList = new ArrayList<>();

        addGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducatorInterface.this, ListApplications.class);
                startActivity(intent);
                //finish();
            }
        });

        selectChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EducatorInterface.this, SelectChildren.class);
                startActivity(intent);
            }
        });

   }

   private void OpenLog(int i){

       Bundle bundle = new Bundle();
       bundle.putString("childID",childIDList.get(i));

       Intent intent = new Intent(EducatorInterface.this,EducatorLog.class);
       intent.putExtras(bundle);

       startActivity(intent);
   }

   void PopulateChildrenList(DataSnapshot dataSnapshot){

       namesList.clear();

       //Log.e("LOG",dataSnapshot.getChildrenCount() + " ");
       for (DataSnapshot usersDataSnapshot : dataSnapshot.getChildren()){

           User user = usersDataSnapshot.getValue(User.class);

           //populate the educators children only into the list
           if(user.getEducator()!= null && user.getEducator().contentEquals(loggedInEducator)) {
               namesList.add(user);
           }

       }


       UsersList adapter = new UsersList(EducatorInterface.this,namesList);
       namesListView.setAdapter(adapter);
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        FirebaseAuth.getInstance().signOut();
    }
}
