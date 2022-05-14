package com.example.wearos;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.wearos.bluetooth.Communicator;
import com.example.wearos.databinding.ActivityMainBinding;

public class ExerciseActivity extends Activity {

    private boolean running = false;
    private String exerciseName;
    private ActivityMainBinding binding;
    TextView mTextView;
    private Chronometer chronometer;
    private Communicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mTextView = findViewById(R.id.exerciseName);
        chronometer = findViewById(R.id.chronometer_activity);

        exerciseName = (String)getIntent().getSerializableExtra("ExerciseName");
        communicator = MainActivity.communicator;
        mTextView.setText(exerciseName);
    }

    public void startChronometer(View v)
    {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            running = true;
        }
    }

    public void finishChronometer(View v)
    {
        if (running) {
            chronometer.stop();
            running = false;

            long time = SystemClock.elapsedRealtime() - chronometer.getBase();
            int h = (int)(time /3600000);
            int m = (int)(time - h*3600000)/60000;
            int seconds = (int)(time - h*3600000- m*60000)/1000;
            int timeInSeconds = (h * 3600) + (m * 60) + seconds;

            communicator.write((exerciseName + ";" + timeInSeconds).getBytes());
        }
    }

}