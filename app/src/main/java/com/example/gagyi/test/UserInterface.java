package com.example.gagyi.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

public class UserInterface extends AppCompatActivity {

    private ImageSwitcher imgsw;
    private ImageButton prev;
    private ImageButton next;

    ListView gamesLV;
    List<Game> gamesList;

    User user;
    List<String> gamestoPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);

        gamesList = new ArrayList<>();
        gamesLV = (ListView)findViewById(R.id.userInterfaceGamesLV);

        FirebaseDatabase.getInstance().getReference("children").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        MakeOffline();



        imgsw = (ImageSwitcher) findViewById(R.id.imgsw);

        imgsw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });


        imgsw.setImageResource(R.drawable.userinterface2);

    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("games");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gamesList.clear();

                if(user!=null)
                   gamestoPlay = user.getGamesToPlay();
                for(DataSnapshot gamesDS : dataSnapshot.getChildren()){
                    Game game = gamesDS.getValue(Game.class);
                    if(gamestoPlay!=null) {
                        for (String temp :
                                user.getGamesToPlay()) {
                            if (game.getId().equals(temp))
                                gamesList.add(game);
                        }
                    }

                }

                GamesList adapter = new GamesList(UserInterface.this,gamesList);
                gamesLV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        gamesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(gamesList.get(i).getPackageName());
                if(LaunchIntent != null) {

                    StartGame(LaunchIntent);
                }
                else{
                    try {
                        Intent intent = new Intent(UserInterface.this,TimerService.class);
                        startService(intent);
                        MakeOnline();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + gamesList.get(i).getPackageName())));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        Intent intent = new Intent(UserInterface.this,TimerService.class);
                        startService(intent);
                        MakeOnline();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + gamesList.get(i).getPackageName())));
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        MakeOffline();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LoggedOut();

    }

    private void MakeOffline(){

        FirebaseDatabase.getInstance().getReference("children").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("currentActivity").setValue("Nem játszik");

    }

    private void MakeOnline(){

        FirebaseDatabase.getInstance().getReference("children").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("currentActivity").setValue("Játszik");

    }

    private void LoggedOut(){

        FirebaseDatabase.getInstance().getReference("children").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("currentActivity").setValue("Kijelentkezve");
        user = null;
        FirebaseAuth.getInstance().signOut();
    }

    public void StartGame(Intent intent){

        Intent Serviceintent = new Intent(UserInterface.this,TimerService.class);
        startService(Serviceintent);
        MakeOnline();
        startActivity(intent);
    }


}
