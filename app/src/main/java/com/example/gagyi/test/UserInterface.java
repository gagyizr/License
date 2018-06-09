package com.example.gagyi.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserInterface extends AppCompatActivity {

    private ImageSwitcher imgsw;
    private ImageButton prev;
    private ImageButton next;

    ListView gamesLV;
    List<Game> gamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);

        gamesList = new ArrayList<>();
        gamesLV = (ListView)findViewById(R.id.userInterfaceGamesLV);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("games");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gamesList.clear();

                for(DataSnapshot gamesDS : dataSnapshot.getChildren()){
                    Game game = gamesDS.getValue(Game.class);
                    gamesList.add(game);
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
                if(LaunchIntent != null)
                    startActivity( LaunchIntent );
                else{
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + gamesList.get(i).getPackageName())));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + gamesList.get(i).getPackageName())));
                    }
                }
            }
        });

        imgsw = (ImageSwitcher) findViewById(R.id.imgsw);

        imgsw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });


        imgsw.setImageResource(R.drawable.common_google_signin_btn_text_light_pressed);

        /*
        prev = (ImageButton) findViewById(R.id.imageButton);
        next = (ImageButton) findViewById(R.id.imageButton4);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgsw.setImageResource(R.drawable.common_google_signin_btn_icon_dark_normal);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgsw.setImageResource(R.drawable.common_google_signin_btn_text_light_pressed);
            }
        });*/
    }
}
