package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {
    private int second = 0;
    private boolean running;
    private boolean isRunning;

    //Build Instance
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        setTimer();
    }

    //Instance State
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("second", second);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("isRunning", isRunning);
    }

    //Resuming the stopwatch 
    @Override
    protected void onResume() {
        super.onResume();
        if (isRunning) {
            running = true;
        }
    }

    //Pause the stopwatch
    @Override
    protected void onPause() {
        super.onPause();
        isRunning = running;
        running = false;
    }

    //Start the stopwatch
    public void onClickStart(View view) {
        running = true;
    }

    //Stop the stopwatch
    public void onClickStop(View view) {
        running = false;
    }

    //Reset the stopwatch
    public void onClickReset(View view) {
        running = false;
        second = 0;
    }

    //Seconds number of the timer
    private void setTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 600) / 60;
                int secs = second % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
