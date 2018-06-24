package com.example.gagyi.test;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button loginButton;
    public Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = (Button)findViewById(R.id.buttonreg);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent RegisterIntent = new Intent(MainActivity.this, Register.class);
                startActivity(RegisterIntent);

            }
        });

        loginButton = (Button)findViewById(R.id.buttonlog);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LoginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(LoginIntent);

            }
        });

    }



    @Override
    public void onBackPressed() {
        Log.i("Back Button", "Clicked");
        //super.onBackPressed();
    }
}
