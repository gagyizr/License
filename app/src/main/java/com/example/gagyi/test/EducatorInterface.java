package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EducatorInterface extends AppCompatActivity {

    private Button addGameButton;

    ListView gamesListView;
    List<Game> gamesList;
    List<User> namesList;

    ListView activityListiew;
    ListView namesListView;

    String[] tempActivities = {"Activity1","Activity2","Activity3","Activity4","Activity5","Activity6","Activity7","Activity8","Activity9","Activity10",};
    String[] tempNames = {"Nev1","Nev2","Nev3","Nev4","Nev5","Nev6","Nev7","Nev8","Nev9","Nev10"};

    DatabaseReference databaseReference;

   @Override
    protected void onStart() {
        super.onStart();

        activityListiew = (ListView)findViewById(R.id.activities_listview);
        namesListView = (ListView)findViewById(R.id.names_listview);

        ArrayAdapter<String> activitiesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempActivities);
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempNames);

        activityListiew.setAdapter(activitiesAdapter);
        namesListView.setAdapter(namesAdapter);

       final FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference ref = database.getReference("games");

        namesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(EducatorInterface.this,EducatorLog.class);
                startActivity(intent);
            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gamesList.clear();

                Log.e("LOG",dataSnapshot.getChildrenCount() + " ");
                for (DataSnapshot gameSnapShot : dataSnapshot.getChildren()){

                    Game game = gameSnapShot.getValue(Game.class);
                    Log.e("asddsa" , game.getName());
                    gamesList.add(game);

                }


                GamesList adapter = new GamesList(EducatorInterface.this,gamesList);
                gamesListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       //final FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference ref2 = database.getReference("names");
       ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                namesList.clear();

                Log.e("LOG",dataSnapshot.getChildrenCount() + " ");
                for (DataSnapshot usersDataSnapshot : dataSnapshot.getChildren()){

                   User user = usersDataSnapshot.getValue(User.class);
                    //Log.e("asddsa" , user.getName());
                    namesList.add(user);

                }


                UsersList adapter = new UsersList(EducatorInterface.this,namesList);
                namesListView.setAdapter(adapter);
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
        addGameButton = (Button) findViewById(R.id.button9);
        gamesListView = (ListView) findViewById(R.id.activities_listview);

        gamesList = new ArrayList<>();
        namesList = new ArrayList<>();

        addGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducatorInterface.this, ListApplications.class);
                startActivity(intent);
                finish();
            }
        });


            }
}
