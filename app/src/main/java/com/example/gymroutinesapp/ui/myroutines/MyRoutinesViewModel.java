package com.example.gymroutinesapp.ui.myroutines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRoutinesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyRoutinesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Actualmente no hay rutinas creadas");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}