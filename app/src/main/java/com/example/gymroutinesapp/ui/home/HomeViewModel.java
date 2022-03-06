package com.example.gymroutinesapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Añade un nuevo ejercicio para el día seleccionado");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}