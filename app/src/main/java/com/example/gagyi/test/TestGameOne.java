package com.example.gagyi.test;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TestGameOne extends AppCompatActivity implements GestureDetector.OnGestureListener , GestureDetector.OnDoubleTapListener{

    Button dB1;
    Button dB2;
    Button dB3;
    Button dB4;

    Button bB1;
    Button bB2;

    TextView correctTV;
    TextView incorrectTV;

    public TextView totalTap ;
    public TextView totalSwipe ;
    public TextView totalFling ;

    private int totalTaps = 0;
    private int totalSwipes = 0;
    private int totalFlings = 0;

    int correct = 0;
    int incorrect = 0;

    private GestureDetectorCompat gestureDetectorCompat;


    public void updateTotalTap() {
        totalTaps++;
        this.totalTap.setText("Összes tap száma: " + totalTaps);
    }

    public void updateTotalSwipe() {
        totalSwipes++;
        this.totalSwipe.setText("Összes swipe száma: " + totalSwipes);
    }

    public void updateTotalFling() {
        totalFlings++;
        this.totalFling.setText("Összes fling száma: " + totalFlings);
    }

    private View.OnClickListener correctOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            correct ++;
            correctTV.setText("Helyes valaszok: " + correct);
        }
    };

    private View.OnClickListener incorrectOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            incorrect ++;
            incorrectTV.setText("Helytelen valaszok: " + incorrect);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_game_one);

        dB1 = (Button)findViewById(R.id.dButton1);
        dB2 = (Button)findViewById(R.id.dButton2);
        dB3 = (Button)findViewById(R.id.dButton3);
        dB4 = (Button)findViewById(R.id.dButton4);

        bB1 = (Button)findViewById(R.id.bButton1);
        bB2 = (Button)findViewById(R.id.bButton2);

        correctTV = (TextView)findViewById(R.id.gameOneCorrectTV);
        incorrectTV = (TextView)findViewById(R.id.gameOneIncorrectTV);

        dB1.setOnClickListener(correctOnClick);
        dB2.setOnClickListener(correctOnClick);
        dB3.setOnClickListener(correctOnClick);
        dB4.setOnClickListener(correctOnClick);

        bB1.setOnClickListener(incorrectOnClick);
        bB2.setOnClickListener(incorrectOnClick);

        totalTap = (TextView)findViewById(R.id.gameOneTotalTaps);
        totalSwipe = (TextView)findViewById(R.id.gameOneTotalSwipes);
        totalFling = (TextView)findViewById(R.id.gameOneTotalFlings);

        this.gestureDetectorCompat = new GestureDetectorCompat(this,this);
        gestureDetectorCompat.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

        updateTotalTap();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        //gestureMessage.setText("Double Tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        //gestureMessage.setText("Double Tap Event");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
       // gestureMessage.setText(" on Down");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        //gestureMessage.setText("Show Press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
       // gestureMessage.setText("Single Tap Up");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        updateTotalSwipe();

        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

        //gestureMessage.setText("Long press");

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

      updateTotalFling();

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
