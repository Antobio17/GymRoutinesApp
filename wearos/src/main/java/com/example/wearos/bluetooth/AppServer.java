package com.example.wearos.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.example.wearos.MainActivity;

import java.io.IOException;


public class AppServer extends Thread {
    private final BluetoothServerSocket serverSocket;
    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Communicator communication;

    /**
     *
     */
    @SuppressLint("MissingPermission")
    public AppServer() {
        BluetoothServerSocket tmp = null;
        try {
            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(
                    MainActivity.APP_NAME, MainActivity.APP_UUID
            );
        } catch (IOException e) {
            Log.e("Bluetooth Error", "El metodo listen() del Socket falló", e);
        }
        serverSocket = tmp;
    }

    /**
     *
     */
    public void run() {
        BluetoothSocket socket = null;

        do {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                Log.e("Bluetooth Error", "El metodo accept() del Socket falló", e);
                break;
            }

            if (socket != null && socket.isConnected()) {
                communication = new Communicator(socket);
                communication.start();
                break;
            }
        } while (socket == null || !socket.isConnected());
    }

    /**
     * @return Communicator
     */
    public Communicator getCommunication() {
        return this.communication;
    }
}
