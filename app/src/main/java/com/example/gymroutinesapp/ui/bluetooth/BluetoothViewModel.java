package com.example.gymroutinesapp.ui.bluetooth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BluetoothViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BluetoothViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("No hay rutina activa");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}