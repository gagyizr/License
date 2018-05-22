package com.example.gagyi.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class EducatorLog extends AppCompatActivity {

    ListView listView;

    Button mitButton;
    Button kiadottButton;
    Button megoldottButton;

    String[] tempMitCsinalt = {"MitCsinalt","MitCsinalt","MitCsinalt","MitCsinalt","MitCsinalt","MitCsinalt","MitCsinalt","MitCsinalt","MitCsinalt","MitCsinalt",};
    String[] tempKiadottFeladatok = {"KiadottFeladat","KiadottFeladat","KiadottFeladat","KiadottFeladat","KiadottFeladat","KiadottFeladat","KiadottFeladat","KiadottFeladat","KiadottFeladat","KiadottFeladat"};
    String[] tempMegoldottFeladatok = {"MegoldottFeladat","MegoldottFeladat","MegoldottFeladat","MegoldottFeladat","MegoldottFeladat","MegoldottFeladat","MegoldottFeladat","MegoldottFeladat","MegoldottFeladat","MegoldottFeladat"};

    ArrayAdapter<String> mitAdatpet;
    ArrayAdapter<String> kiadottAdapter ;
    ArrayAdapter<String> megoldottAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educator_log);

        listView = (ListView)findViewById(R.id.logListView);
        mitButton = (Button)findViewById(R.id.logMitCsinalt);
        kiadottButton = (Button)findViewById(R.id.logKiadott);
        megoldottButton = (Button)findViewById(R.id.logMegoldott);

        mitAdatpet = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempMitCsinalt);
        kiadottAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempKiadottFeladatok);
        megoldottAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempMegoldottFeladatok);

        //listView.setAdapter(mitAdatpet);
        //namesListView.setAdapter(namesAdapter);

        mitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(mitAdatpet);
            }
        });

        kiadottButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(kiadottAdapter);
            }
        });

        megoldottButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(megoldottAdapter);
            }
        });
    }
}
