package com.example.gymroutinesapp.ui.activeroutine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActiveRoutineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ActiveRoutineViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("No hay rutina activa");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}