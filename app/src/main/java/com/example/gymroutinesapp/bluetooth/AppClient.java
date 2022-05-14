package com.example.gymroutinesapp.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.example.gymroutinesapp.MainActivity;

import java.io.IOException;

public class AppClient extends Thread
{
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private Communicator communication;

    @SuppressLint("MissingPermission")
    public AppClient(BluetoothDevice device)
    {
        this.device = device;
        try {
            this.socket = device.createRfcommSocketToServiceRecord(MainActivity.APP_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    public void run()
    {
        try {
            socket.connect();
            communication = new Communicator(this.socket);
            communication.start();
        } catch (IOException e) {
            Log.e("Bluetooth Error", "El metodo connect() del Socket falló", e);
        }
    }

    /**
     *
     * @return Communication
     */
    public Communicator getCommunication()
    {
        return this.communication;
    }

}
