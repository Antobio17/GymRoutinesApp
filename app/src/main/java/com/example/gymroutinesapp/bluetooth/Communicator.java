package com.example.gymroutinesapp.bluetooth;

import android.bluetooth.BluetoothSocket;

import com.example.gymroutinesapp.MainActivity;
import com.example.gymroutinesapp.model.AppDatabase;
import com.example.gymroutinesapp.model.dao.ExerciseDao;
import com.example.gymroutinesapp.model.dao.RoutineDao;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.Measurements;
import com.example.gymroutinesapp.model.entity.Routine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Communicator extends Thread {
    public final String GET_EXERCISES = "GET_EXERCISES";
    public final String SET_MEASUREMENTS = "SET_MEASUREMENTS";

    private final BluetoothSocket bluetoothSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    Routine activeRoutine;

    public Communicator(BluetoothSocket socket) {
        this.bluetoothSocket = socket;
        InputStream inputTmp = null;
        OutputStream outputTmp = null;

        try {
            inputTmp = this.bluetoothSocket.getInputStream();
            outputTmp = this.bluetoothSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.inputStream = inputTmp;
        this.outputStream = outputTmp;

        RoutineDao routineDao = MainActivity.db.routineDao();
        activeRoutine = routineDao.findActiveRoutine();
    }

    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        while (true) {
            try {
                bytes = inputStream.read(buffer);
                String message = new String(buffer, 0, bytes);

                if (message.equals(GET_EXERCISES)) {
                    // Recuperación de ejercicios
                    StringBuilder response = new StringBuilder();
                    ExerciseDao exerciseDao = MainActivity.db.exerciseDao();
                    List<Exercise> exercises = exerciseDao.findByRoutineID(
                            activeRoutine.getId()
                    );


                    response = response.append(activeRoutine.getName());
                    response = response.append(";");
                    for (Exercise exercise : exercises) {
                        response.append(exercise.getName()).append(";");
                    }

                    this.write(response.toString().getBytes());
                } else {
                    AppDatabase db = MainActivity.db;
                    String[] measurementsSplits = message.split(";");
                    String exerciseName = measurementsSplits[0];
                    String timeInSeconds = measurementsSplits[1];
                    String reps = measurementsSplits[2];

                    ExerciseDao exerciseDao = db.exerciseDao();
                    Exercise exercise = db.exerciseDao().findOneByName(exerciseName);

                    List<Measurements> measurementsList = db.measurementsDao().findBy(
                            activeRoutine.getId(), exercise.getId()
                    );

                    Measurements lastMeasurements = null;
                    float lastWeight = (float) -1.0;
                    if (!measurementsList.isEmpty()) {
                        lastMeasurements = measurementsList.get(0);
                        lastWeight = lastMeasurements.getWeight();
                    }

                    int measurementsId = lastMeasurements != null ? lastMeasurements.getId() + 1 : 1;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
                    Measurements newMeasurements = new Measurements(
                            measurementsId,
                            activeRoutine.getId(),
                            exercise.getId(),
                            Integer.parseInt(timeInSeconds),
                            lastWeight,
                            calendar.getTimeInMillis(),
                            Integer.parseInt(reps)
                    );
                    MainActivity.db.measurementsDao().insertMeasurements(newMeasurements);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(byte[] buffer) {
        try {
            outputStream.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
