package com.example.gymroutinesapp.ui.myroutines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.gymroutinesapp.databinding.FragmentMyRoutinesBinding;
import com.google.android.material.snackbar.Snackbar;

public class MyRoutinesFragment extends Fragment {

    private FragmentMyRoutinesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        MyRoutinesViewModel myRoutinesViewModel = new ViewModelProvider(this).get(
                MyRoutinesViewModel.class
        );

        binding = FragmentMyRoutinesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        myRoutinesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Añade el evento al botón flotante
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(
                        view, "Las rutinas están actualizadas", Snackbar.LENGTH_LONG
                ).setAction("Action", null).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}