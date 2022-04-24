package com.example.gymroutinesapp.ui.myroutines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymroutinesapp.MainActivity;
import com.example.gymroutinesapp.R;
import com.example.gymroutinesapp.databinding.FragmentMyRoutinesBinding;
import com.example.gymroutinesapp.model.AppDatabase;
import com.example.gymroutinesapp.model.adapter.ExerciseAdapter;
import com.example.gymroutinesapp.model.adapter.RoutineAdapter;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.Routine;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MyRoutinesFragment extends Fragment {

    private FragmentMyRoutinesBinding binding;
    List<Routine> routines;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        MyRoutinesViewModel myRoutinesViewModel = new ViewModelProvider(this).get(
                MyRoutinesViewModel.class
        );

        binding = FragmentMyRoutinesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textView = binding.textHome;
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

        this._initializeMyRoutinesFragment(MainActivity.db, root);

        return root;
    }

    private void _initializeMyRoutinesFragment(AppDatabase db, View view)
    {
        this.routines = db.routineDao().findAll();

        if (routines.size() > 0) {
            textView.setVisibility(View.INVISIBLE);
        }
        RoutineAdapter routineAdapter = new RoutineAdapter(this.routines, view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.routinesRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(routineAdapter);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}