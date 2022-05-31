package com.example.wearos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.example.wearos.adapter.ExerciseAdapter;
import com.example.wearos.bluetooth.AppServer;
import com.example.wearos.bluetooth.Communicator;
import com.example.wearos.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainActivity extends Activity {
    // adb connect 192.168.1.164:5555
    public final String GET_EXERCISES = "GET_EXERCISES";

    public static final String APP_NAME = "gymroutineapp";
    public static final UUID APP_UUID = UUID.fromString("6723d740-cb08-11ec-9d64-0242ac120002");

    private TextView mTextView;
    private ImageButton button;
    private ActivityMainBinding binding;
    public static Communicator communicator;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
        button = binding.connectButton;
    }

    public void connectionStart(View view) {
        button.setVisibility(View.INVISIBLE);
        mTextView.setText("Conectando...");

        new android.os.Handler(Looper.getMainLooper()).postDelayed(
                new Runnable() {
                    public void run() {
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

                        _initializeView(binding.getRoot(), exercises);
                    }
                },
                300);
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
                        _openExercise(exerciseName);
                    }
                }
        );
        mTextView.setText(routineName);
        WearableRecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        recyclerView.setLayoutManager(new WearableLinearLayoutManager(this));
        recyclerView.setAdapter(exerciseAdapter);
    }

    private void _openExercise(String exerciseName) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("ExerciseName", exerciseName);
        startActivity(intent);
    }
}