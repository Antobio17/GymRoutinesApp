package com.example.gymroutinesapp.bluetooth;

import android.bluetooth.BluetoothSocket;

import com.example.gymroutinesapp.MainActivity;
import com.example.gymroutinesapp.model.dao.ExerciseDao;
import com.example.gymroutinesapp.model.dao.RoutineDao;
import com.example.gymroutinesapp.model.entity.Exercise;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

public class Communicator extends Thread {
    public final String GET_EXERCISES = "GET_EXERCISES";
    public final String SET_MEASUREMENTS = "SET_MEASUREMENTS";

    private final BluetoothSocket bluetoothSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

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
    }

    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        while (true) {
            try {
                bytes = inputStream.read(buffer);
                String message = new String(buffer,0, bytes);
                boolean equal = message.equals(GET_EXERCISES);

                if(message.equals(GET_EXERCISES)) {
                        // Recuperación de ejercicios
                        StringBuilder response = new StringBuilder();
                        RoutineDao routineDao = MainActivity.db.routineDao();
                        ExerciseDao exerciseDao = MainActivity.db.exerciseDao();
                        List<Exercise> exercises = exerciseDao.findByRoutineID(
                                routineDao.findActiveRoutine().getId()
                        );

                        response = response.append(routineDao.findActiveRoutine().getName());
                        response = response.append(";");
                        for (Exercise exercise : exercises) {
                            response.append(exercise.getName()).append(";");
                        }

                        this.write(response.toString().getBytes());
                } else if(message.equals(SET_MEASUREMENTS)) {

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
