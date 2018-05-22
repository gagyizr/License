package com.example.gagyi.test;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GestureDetection extends AppCompatActivity implements GestureDetector.OnGestureListener ,
        GestureDetector.OnDoubleTapListener {

    private TextView gestureMessage;
    private GestureDetectorCompat gestureDetectorCompat;

    Button testGame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        testGame1 = (Button)findViewById(R.id.goToGameOne);
        gestureMessage = (TextView)findViewById(R.id.gesture);

        this.gestureDetectorCompat = new GestureDetectorCompat(this,this);
        gestureDetectorCompat.setOnDoubleTapListener(this);

        testGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestureDetection.this,TestGameOne.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        gestureMessage.setText("Single Tap Confirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gestureMessage.setText("Double Tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        gestureMessage.setText("Double Tap Event");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gestureMessage.setText(" on Down");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gestureMessage.setText("Show Press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gestureMessage.setText("Single Tap Up");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gestureMessage.setText("Scoll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

        gestureMessage.setText("Long press");

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        gestureMessage.setText("Fling");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
