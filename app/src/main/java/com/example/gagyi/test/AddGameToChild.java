package com.example.gagyi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddGameToChild extends AppCompatActivity {

    ListView gamesListView;
    List<Game> gamesInDatabase;
    List<String> usersGames;
    String idOfChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_to_child);

        gamesListView = (ListView)findViewById(R.id.gamesOnDatabaseLV);
        gamesInDatabase = new ArrayList<>();
        usersGames = new ArrayList<>();

        GetIdOfChild();

        FirebaseDatabase.getInstance().getReference("games").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               UpdateGameList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        gamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, final long l) {
                FirebaseDatabase.getInstance().getReference("children").child(idOfChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        SelectGame(dataSnapshot,i);
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

    void UpdateGameList(DataSnapshot dataSnapshot){

        gamesInDatabase.clear();
        for (DataSnapshot game : dataSnapshot.getChildren()) {
            Game tempgame = game.getValue(Game.class);
            gamesInDatabase.add(tempgame);
        }

        GamesList adapter = new GamesList(AddGameToChild.this,gamesInDatabase);
        gamesListView.setAdapter(adapter);
    }

    void SelectGame(DataSnapshot dataSnapshot,int i){

        User user = dataSnapshot.getValue(User.class);
        usersGames.clear();
        usersGames.addAll(user.getGamesToPlay());
        usersGames.add(gamesInDatabase.get(i).getId());
        user.setGamesToPlay(usersGames);
        FirebaseDatabase.getInstance().getReference("children").child(idOfChild).setValue(user);
    }
}