package com.example.gymroutinesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.gymroutinesapp.model.AppDatabase;
import com.example.gymroutinesapp.model.adapter.MeasurementsAdapter;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.Measurements;
import com.example.gymroutinesapp.model.entity.Routine;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    TextView exerciseNameTextView;
    private Chronometer chronometer;
    private boolean running = false;
    List<Measurements> measurements;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exerciseNameTextView = findViewById(R.id.exerciseName_activity);
        chronometer = findViewById(R.id.chronometer_activity);

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
            running = true;
        }
    }

    public void finishChronometer(View v)
    {
        if (running) {
            chronometer.stop();
            running = false;
        }
    }

    private void _initializeExerciseActivity(AppDatabase db, View view)
    {
        Routine routine = db.routineDao().findActiveRoutine();
        List<Measurements> measurementsList = db.measurementsDao().findBy(
                routine.getId(), exercise.getId()
        );

        if (measurementsList != null) {
            this.measurements = new ArrayList<>();
            this.measurements.addAll(measurementsList);
            MeasurementsAdapter measurementsAdapter = new MeasurementsAdapter(this.measurements, view.getContext());
            RecyclerView recyclerView = view.findViewById(R.id.measurementsRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(measurementsAdapter);
        }
    }

}