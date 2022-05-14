package model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wearos.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>
{
    private List<String> exerciseNames;
    private LayoutInflater inflater; // Identidicar de que archivo viene el layout
    private Context context;
    final ExerciseAdapter.OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(String exerciseName);
    }

    public ExerciseAdapter(List<String> exerciseNames, Context context,
                           ExerciseAdapter.OnItemClickListener listener)
    {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.exerciseNames = exerciseNames;
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
        holder.bindData(this.exerciseNames.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.exerciseNames.size();
    }

    public void setItems(List<String> items)
    {
        this.exerciseNames = items;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;

        ViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.exerciseName);
        }

        void bindData(final String exerciseName)
        {
            name.setText(exerciseName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(exerciseName);
                }
            });
        }
    }
}

