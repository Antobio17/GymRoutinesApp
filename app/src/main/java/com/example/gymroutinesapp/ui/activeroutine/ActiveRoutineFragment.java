package com.example.gymroutinesapp.ui.activeroutine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymroutinesapp.ExerciseActivity;
import com.example.gymroutinesapp.MainActivity;
import com.example.gymroutinesapp.R;
import com.example.gymroutinesapp.databinding.FragmentActiveRoutineBinding;
import com.example.gymroutinesapp.model.AppDatabase;
import com.example.gymroutinesapp.model.adapter.ExerciseAdapter;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.Routine;

import java.util.ArrayList;
import java.util.List;

public class ActiveRoutineFragment extends Fragment {

    private FragmentActiveRoutineBinding binding;
    List<Exercise> exercises;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentActiveRoutineBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onResume()
    {
        super.onResume();

        View view = binding.getRoot();
        this._initializeRoutineActiveFragment(MainActivity.db, view);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    private void _initializeRoutineActiveFragment(AppDatabase db, View view)
    {
        Routine routine = db.routineDao().findActiveRoutine();

        if (routine != null) {
            this.exercises = new ArrayList<>();
            this.exercises.addAll(db.exerciseDao().findByRoutineID(routine.getId()));
            ExerciseAdapter exerciseAdapter = new ExerciseAdapter(this.exercises, view.getContext(),
                    new ExerciseAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Exercise exercise) {
                            _openExercise(exercise);
                        }
                    }
            );
            RecyclerView recyclerView = view.findViewById(R.id.exercisesRecycleView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(exerciseAdapter);

            // Establecer el título al Action Bar con el nombre de la rutina activa
            if (((AppCompatActivity) getActivity()) != null) {
                //noinspection ConstantConditions
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(routine.getName());
            }
        }
    }

    private void _openExercise(Exercise exercise)
    {
        Intent intent = new Intent(getActivity(), ExerciseActivity.class);
        intent.putExtra("Exercise", exercise);
        startActivity(intent);
    }
}