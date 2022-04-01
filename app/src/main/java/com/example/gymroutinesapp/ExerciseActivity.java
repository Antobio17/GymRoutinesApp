package com.example.gymroutinesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.gymroutinesapp.model.AppDatabase;
import com.example.gymroutinesapp.model.adapter.MeasurementsAdapter;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.Measurements;
import com.example.gymroutinesapp.model.entity.Routine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    private TextView exerciseNameTextView, weightMeasurementTextView;
    private Chronometer chronometer;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchWeightMeasurement;
    private Button buttonIncreaseWeight, buttonReduceWeight;
    private RecyclerView measurementsRecyclerView;

    private float lastWeight = 0;
    private boolean running = false;
    List<Measurements> measurements;
    private Exercise exercise;
    private Routine activeRoutine;
    private Measurements lastMeasurements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exerciseNameTextView = findViewById(R.id.exerciseName_activity);
        weightMeasurementTextView = findViewById(R.id.weightMeasurement);
        chronometer = findViewById(R.id.chronometer_activity);
        switchWeightMeasurement = findViewById(R.id.switchWeightMeasurement);
        buttonIncreaseWeight = findViewById(R.id.buttonIncreaseWeight);
        buttonReduceWeight = findViewById(R.id.buttonReduceWeight);
        measurementsRecyclerView = findViewById(R.id.measurementsRecyclerView);

        exercise = (Exercise) getIntent().getSerializableExtra("Exercise");
        exerciseNameTextView.setText(exercise.getName());

        View view = findViewById(android.R.id.content).getRootView();
        _initializeExerciseActivity(MainActivity.db, view);
    }

    public void startChronometer(View v)
    {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            switchWeightMeasurement.setClickable(false);
            switchWeightMeasurement.setClickable(false);
            buttonIncreaseWeight.setClickable(false);
            buttonReduceWeight.setClickable(false);
            running = true;
        }
    }

    public void finishChronometer(View v)
    {
        if (running) {
            chronometer.stop();
            switchWeightMeasurement.setClickable(true);
            switchWeightMeasurement.setClickable(true);
            buttonIncreaseWeight.setClickable(true);
            buttonReduceWeight.setClickable(true);
            running = false;

            long time = SystemClock.elapsedRealtime() - chronometer.getBase();
            int h = (int)(time /3600000);
            int m = (int)(time - h*3600000)/60000;
            int seconds = (int)(time - h*3600000- m*60000)/1000 ;

            lastWeight = switchWeightMeasurement.isChecked() ? lastWeight : -1;
            int measurementsId = lastMeasurements != null ? lastMeasurements.getId() + 1 : 1;
            Measurements newMeasurements = new Measurements(
                    measurementsId,
                    activeRoutine.getId(),
                    exercise.getId(),
                    seconds,
                    lastWeight,
                    (int) (new Date().getTime()/1000)
            );
            MainActivity.db.measurementsDao().insertMeasurements(newMeasurements);
            this.measurements.add(newMeasurements);
            this._initializeExerciseActivity(MainActivity.db, v);
        }
    }

    public void onChangeSwitch(View v)
    {
        RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams)measurementsRecyclerView.getLayoutParams();
        if (switchWeightMeasurement.isChecked()) {
            buttonIncreaseWeight.setVisibility(View.VISIBLE);
            buttonReduceWeight.setVisibility(View.VISIBLE);
            weightMeasurementTextView.setVisibility(View.VISIBLE);
            params.setMargins(0, 800, 0 , 0);
        } else {
            buttonIncreaseWeight.setVisibility(View.INVISIBLE);
            buttonReduceWeight.setVisibility(View.INVISIBLE);
            weightMeasurementTextView.setVisibility(View.INVISIBLE);
            params.setMargins(0, 600, 0 , 0);
        }
        measurementsRecyclerView.setLayoutParams(params);
    }

    @SuppressLint("SetTextI18n")
    public void reduceWeight(View v)
    {
        lastWeight -= 0.5;
        lastWeight = lastWeight > 0 ? lastWeight : 0;
        weightMeasurementTextView.setText(lastWeight + " KG");
    }

    @SuppressLint("SetTextI18n")
    public void increaseWeight(View v)
    {
        lastWeight = lastWeight > 0 ? lastWeight : 0;
        lastWeight += 0.5;
        weightMeasurementTextView.setText(lastWeight + " KG");
    }

    @SuppressLint("SetTextI18n")
    private void _initializeExerciseActivity(AppDatabase db, View view)
    {
        chronometer.setBase(SystemClock.elapsedRealtime());
        activeRoutine = db.routineDao().findActiveRoutine();
        List<Measurements> measurementsList = db.measurementsDao().findBy(
                activeRoutine.getId(), exercise.getId()
        );

        if (measurementsList != null) {
            this.measurements = new ArrayList<>();
            this.measurements.addAll(measurementsList);
            MeasurementsAdapter measurementsAdapter =
                    new MeasurementsAdapter(this.measurements, view.getContext());
            measurementsRecyclerView.setHasFixedSize(true);
            measurementsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            measurementsRecyclerView.setAdapter(measurementsAdapter);

            // Inicialización de Weight
            if (!measurementsList.isEmpty()) {
                lastMeasurements = measurementsList.get(0);
                if (lastMeasurements.getWeight() != -1) {
                    switchWeightMeasurement.setChecked(true);
                    lastWeight = lastMeasurements.getWeight();
                    weightMeasurementTextView.setText(lastWeight + " KG");
                } else {
                    weightMeasurementTextView.setText("0.0 KG");
                }
            }
            onChangeSwitch(view);
        }
    }

}