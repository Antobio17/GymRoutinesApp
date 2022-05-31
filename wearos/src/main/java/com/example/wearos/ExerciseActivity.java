package com.example.wearos;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagnetic;
    SensorEventListener sensorAccelerometerListener;
    SensorEventListener sensorMagneticListener;
    private Integer intValue = null;
    private boolean changed = false;
    private int repetitions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mTextView = findViewById(R.id.exerciseName);
        chronometer = findViewById(R.id.chronometer_activity);

        exerciseName = (String) getIntent().getSerializableExtra("ExerciseName");
        communicator = MainActivity.communicator;
        mTextView.setText(exerciseName);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorAccelerometerListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                double value = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
                if (intValue == null) {
                    intValue = (int)value;
                } else if (!changed) {
                    changed = (intValue + 5 == (int)value);
                } else if ((int)value <= (intValue + 2) && (int)value >= (intValue - 2)) {
                    changed = false;
                    repetitions++;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorMagneticListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            running = true;
            sensorManager.registerListener(
                    sensorAccelerometerListener, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL
            );
            sensorManager.registerListener(
                    sensorMagneticListener, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL
            );
        }
    }

    public void finishChronometer(View v) {
        if (running) {
            chronometer.stop();
            running = false;
            sensorManager.unregisterListener(sensorAccelerometerListener);
            sensorManager.unregisterListener(sensorMagneticListener);
            intValue = null;
            changed = false;
            int reps = repetitions/2;
            long time = SystemClock.elapsedRealtime() - chronometer.getBase();
            int h = (int) (time / 3600000);
            int m = (int) (time - h * 3600000) / 60000;
            int seconds = (int) (time - h * 3600000 - m * 60000) / 1000;
            int timeInSeconds = (h * 3600) + (m * 60) + seconds;
            chronometer.setBase(SystemClock.elapsedRealtime());

            repetitions = 0;
            communicator.write((exerciseName + ";" + timeInSeconds + ";" + reps).getBytes());
        }
    }

}