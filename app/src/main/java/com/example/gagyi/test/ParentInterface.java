package com.example.gagyi.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ParentInterface extends AppCompatActivity {

    ListView listView;

    Button feladatokButton;
    Button statisztikaButton;
    Button mitcsinalButton;

    String[] tempFeladatok = {"Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat","Feladat",};
    String[] tempStatisztika = {"Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika","Statisztika"};
    String[] tempMitCsinal = {"MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal","MitCsinal"};

    ArrayAdapter<String> feladatokAdapter;
    ArrayAdapter<String> statisztikaAdapter ;
    ArrayAdapter<String> mitcsinalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_interface);

        listView = (ListView)findViewById(R.id.logParentListView);
        feladatokButton = (Button)findViewById(R.id.logParentFeladatok);
        statisztikaButton = (Button)findViewById(R.id.logParentStatisztika);
        mitcsinalButton = (Button)findViewById(R.id.logParentMitCsinal);

        feladatokAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempFeladatok);
        statisztikaAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempStatisztika);
        mitcsinalAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempMitCsinal);

        feladatokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(feladatokAdapter);
            }
        });

        statisztikaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(statisztikaAdapter);
            }
        });

        mitcsinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(mitcsinalAdapter);
            }
        });
    }
}
