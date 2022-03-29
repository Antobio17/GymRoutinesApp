package com.example.gymroutinesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.gymroutinesapp.model.entity.Exercise;

public class ExerciseActivity extends AppCompatActivity {

    TextView exerciseNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Exercise exercise = (Exercise) getIntent().getSerializableExtra("Exercise");
        exerciseNameTextView = findViewById(R.id.exerciseName_activity);
        exerciseNameTextView.setText(exercise.getName());
    }
}