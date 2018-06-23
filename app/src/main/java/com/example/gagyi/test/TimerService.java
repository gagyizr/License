package com.example.gagyi.test;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {

    long startTime;

    public TimerService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startTime = SystemClock.elapsedRealtime();

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if ((SystemClock.elapsedRealtime() - startTime) / 1000.0 >= 60) {

                    Bundle b = new Bundle();
                    b.putString("serviceAction", "stop");
                    Intent dialogIntent = new Intent(TimerService.this, UserInterface.class);
                    dialogIntent.putExtras(b);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    cancel();
                    startActivity(dialogIntent);
                }
            }
        }, 0, 1000);//put here time 1000 milliseconds=1 second

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
