package com.example.wearos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wearos.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import bluetooth.AppServer;
import bluetooth.Communicator;
import model.adapter.ExerciseAdapter;

public class MainActivity extends Activity {
    // adb connect 192.168.1.164:5555
    public final String GET_EXERCISES = "GET_EXERCISES";

    public static final String APP_NAME = "gymroutineapp";
    public static final UUID APP_UUID = UUID.fromString("6723d740-cb08-11ec-9d64-0242ac120002");

    private TextView mTextView;
    private ActivityMainBinding binding;
    private Communicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;

        AppServer serverClass = new AppServer();
        serverClass.start();

        do {
            communicator = serverClass.getCommunication();
        } while (communicator == null);

        communicator.write((GET_EXERCISES).getBytes());

        String exercises = null;
        do {
            exercises = communicator.getBuffer();
        } while (exercises == null);

        this._initializeView(binding.getRoot(), exercises);
    }

    private void _initializeView(View view, String exercises) {
        String[] exerciseSplits = exercises.split(";");
        String routineName = exerciseSplits[0];
        List<String> exerciseNames = new ArrayList<>(exerciseSplits.length - 1);

        exerciseNames.addAll(Arrays.asList(exerciseSplits).subList(1, exerciseSplits.length));

        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(exerciseNames, view.getContext(),
                new ExerciseAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String exerciseName) {
//                        _openExercise(exercise);
                    }
                }
        );
        mTextView.setText(routineName);
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(exerciseAdapter);
    }
}