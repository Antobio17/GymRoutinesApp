package com.example.gymroutinesapp.model.adapter;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymroutinesapp.R;

import java.util.List;

public class BluetoothDeviceAdapter extends RecyclerView.Adapter<BluetoothDeviceAdapter.ViewHolder>
{
    private List<BluetoothDevice> bluetoothDevices;
    private LayoutInflater inflater; // Identidicar de que archivo viene el layout
    private Context context;
    final BluetoothDeviceAdapter.OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(BluetoothDevice bluetoothDevice);
    }

    public BluetoothDeviceAdapter(List<BluetoothDevice> bluetoothDevices, Context context,
                                  BluetoothDeviceAdapter.OnItemClickListener listener)
    {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.bluetoothDevices = bluetoothDevices;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BluetoothDeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_bluetooth_device, null);

        return new BluetoothDeviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BluetoothDeviceAdapter.ViewHolder holder, int position)
    {
        holder.bindData(this.bluetoothDevices.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.bluetoothDevices.size();
    }

    public void setItems(List<BluetoothDevice> items)
    {
        this.bluetoothDevices = items;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;

        ViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.nameBluetoothDevice);
        }

        @SuppressLint("MissingPermission")
        void bindData(final BluetoothDevice bluetoothDevice)
        {
            name.setText(bluetoothDevice.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(bluetoothDevice);
                }
            });
        }
    }
}
