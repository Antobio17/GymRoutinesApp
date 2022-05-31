package com.example.gymroutinesapp.ui.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymroutinesapp.MainActivity;
import com.example.gymroutinesapp.R;
import com.example.gymroutinesapp.bluetooth.AppClient;
import com.example.gymroutinesapp.bluetooth.Communicator;
import com.example.gymroutinesapp.databinding.FragmentBluetoothBinding;
import com.example.gymroutinesapp.model.adapter.BluetoothDeviceAdapter;
import com.example.gymroutinesapp.model.dao.ExerciseDao;
import com.example.gymroutinesapp.model.dao.RoutineDao;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothFragment extends Fragment {

    public final int REQUEST_ENABLE_BT = 0;
    private FragmentBluetoothBinding binding;
    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private RecyclerView recyclerView;

    private Communicator communicator;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentBluetoothBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerView = view.findViewById(R.id.bluetoothRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        // Añade el evento al botón flotante
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter == null) {
                    Snackbar.make(
                            view, "El dispositivo no soporta la función Bluetooth.",
                            Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show();
                } else if (!bluetoothAdapter.isEnabled()) {
                    Snackbar.make(
                            view, "Por favor active el bluetooth del dispositivo...",
                            Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show();
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                } else {
                    Snackbar.make(
                            view, "Buscando dispositivos...", Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show();

                    // Mostrar listado de dispositivos disponibles
                    @SuppressLint("MissingPermission")
                    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                    if (pairedDevices.size() > 0) {
                        List<BluetoothDevice> btDevicesList = new ArrayList<BluetoothDevice>(
                                pairedDevices.size()
                        );
                        btDevicesList.addAll(pairedDevices);
                        BluetoothDeviceAdapter btDeviceAdapter = new BluetoothDeviceAdapter(
                                btDevicesList, view.getContext(),
                                new BluetoothDeviceAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BluetoothDevice bluetoothDevice) {
                                        int maxTries = 2, counter = 0;
                                        do {
                                            AppClient appClient = new AppClient(bluetoothDevice);
                                            counter++;
                                            appClient.start();
                                            try {
                                                // noinspection BusyWait
                                                Thread.sleep(2000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            communicator = appClient.getCommunication();
                                            if (communicator == null) {
                                                appClient = null;
                                            }
                                        } while (communicator == null && counter < maxTries);

                                        if (communicator == null) {
                                            Snackbar.make(
                                                    view, "Error al conectar.",
                                                    Snackbar.LENGTH_LONG
                                            ).setAction("Action", null).show();
                                        } else {
                                            Snackbar.make(
                                                    view, "Conectado.",
                                                    Snackbar.LENGTH_LONG
                                            ).setAction("Action", null).show();
                                        }
                                    }
                                }
                        );
                        recyclerView.setAdapter(btDeviceAdapter);
                    } else {
                        Snackbar.make(
                                view, "No hay dispositivos disponibles.", Snackbar.LENGTH_LONG
                        ).setAction("Action", null).show();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = binding.getRoot();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}