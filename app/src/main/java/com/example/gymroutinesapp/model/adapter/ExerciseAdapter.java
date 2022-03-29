package com.example.gymroutinesapp.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymroutinesapp.MainActivity;
import com.example.gymroutinesapp.R;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.Measurements;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>
{
    private List<Exercise> exercises;
    private LayoutInflater inflater; // Identidicar de que archivo viene el layout
    private Context context;
    final ExerciseAdapter.OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(Exercise exercise);
    }

    public ExerciseAdapter(List<Exercise> exercises, Context context,
                           ExerciseAdapter.OnItemClickListener listener)
    {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.exercises = exercises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_exercise, null);

        return new ExerciseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position)
    {
        holder.bindData(this.exercises.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.exercises.size();
    }

    public void setItems(List<Exercise> items)
    {
        this.exercises = items;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iconImage;
        TextView name, lastMeasure;

        ViewHolder(View itemView)
        {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconExercise);
            name = itemView.findViewById(R.id.exerciseName);
            lastMeasure = itemView.findViewById(R.id.exerciseLastMeasure);
        }

        void bindData(final Exercise exercise)
        {
            int idActiveRoutine = MainActivity.db.routineDao().findActiveRoutine().getId();
            Measurements lastMeasurement = MainActivity.db.measurementsDao().findLastMeasurement(
                    idActiveRoutine, exercise.getId()
            );
            String lastMeasureText = "";

            if (lastMeasurement != null) {
                if (lastMeasurement.getWeight() != -1) {
                    lastMeasureText = lastMeasurement.getWeight() + " KG";
                    iconImage.setBackgroundResource(R.drawable.ic_weight);
                } else {
                    lastMeasureText = lastMeasurement.getTimeInSeconds() + " seg";
                    iconImage.setBackgroundResource(R.drawable.ic_running);
                }
            }
            name.setText(exercise.getName());
            lastMeasure.setText(lastMeasureText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(exercise);
                }
            });
        }
    }
}
