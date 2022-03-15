package com.example.gymroutinesapp.ui.activeroutine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.gymroutinesapp.databinding.FragmentActiveRoutineBinding;

public class ActiveRoutineFragment extends Fragment {

    private FragmentActiveRoutineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        ActiveRoutineViewModel activeRoutineViewModel = new ViewModelProvider(this).get(
                ActiveRoutineViewModel.class
        );

        binding = FragmentActiveRoutineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        activeRoutineViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}