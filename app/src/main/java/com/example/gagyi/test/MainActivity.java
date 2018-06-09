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

    Button educatorInterfaceButton;
    Button parentInterfaceButton;
    Button kidInterfaceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        educatorInterfaceButton = (Button)findViewById(R.id.educatorInterfaceButton);
        parentInterfaceButton = (Button)findViewById(R.id.parentInterfaceButton2);
        kidInterfaceButton = (Button)findViewById(R.id.kidInterfaceButton);

        Button button = (Button)findViewById(R.id.buttonreg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent RegisterIntent = new Intent(MainActivity.this, Register.class);
                startActivity(RegisterIntent);
                //finish();
            }
        });

        Button loginButton = (Button)findViewById(R.id.buttonlog);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LoginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(LoginIntent);
                //finish();
            }
        });

        Button gesturesButton = (Button)findViewById(R.id.testGestureButton);
        gesturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent GestureIntent = new Intent(MainActivity.this, GestureDetection.class);
                startActivity(GestureIntent);
                //finish();
            }
        });

        educatorInterfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Educator = new Intent(MainActivity.this, EducatorInterface.class);
                startActivity(Educator);
                //finish();
            }
        });

        parentInterfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Parent = new Intent(MainActivity.this, ParentInterface.class);
                startActivity(Parent);
                //finish();
            }
        });

        kidInterfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Kid = new Intent(MainActivity.this, UserInterface.class);
                startActivity(Kid);
                //finish();
            }
        });
    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("Random Key","Clicked");

        if(keyCode == KeyEvent.KEYCODE_HOME){
            Log.i("Home Button","Clicked");
            Toast.makeText(MainActivity.this,"Back Button Pressed", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }*/

    /*
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d("Keycode" , event.getKeyCode()+" key");
            return true;
        }

        return super.dispatchKeyEvent(event);
    }*/


    //for testing buttons
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.d("Keycode" , event.getKeyCode()+" key");
                return true;
            case KeyEvent.KEYCODE_MENU:
                Log.d("Keycode" , event.getKeyCode()+" key");
                return true;
            case KeyEvent.KEYCODE_SEARCH:
                Log.d("Keycode" , event.getKeyCode()+" key");
                return true;
        }

        return super.onKeyUp(keyCode, event);
    }

    /*
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }*/

    @Override
    public void onBackPressed() {
        Log.i("Back Button", "Clicked");
        //super.onBackPressed();
    }
}
