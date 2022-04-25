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
import com.example.gymroutinesapp.model.entity.Routine;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder>
{
    private List<Routine> routines;
    private LayoutInflater inflater; // Identidicar de que archivo viene el layout
    private Context context;

    public interface OnItemClickListener
    {
        void onItemClick(Routine routine);
    }

    public RoutineAdapter(List<Routine> routines, Context context)
    {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.routines = routines;
    }

    @NonNull
    @Override
    public RoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_routine, null);

        return new RoutineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineAdapter.ViewHolder holder, int position)
    {
        holder.bindData(this.routines.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.routines.size();
    }

    public void setItems(List<Routine> items)
    {
        this.routines = items;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;

        ViewHolder(View itemView)
        {
            super(itemView);
            image = itemView.findViewById(R.id.imgListRoutine);
            title = itemView.findViewById(R.id.titleListRoutine);
        }

        void bindData(final Routine routine)
        {
            int idActiveRoutine = MainActivity.db.routineDao().findActiveRoutine().getId();
            title.setText(routine.getName());
            image.setBackgroundResource(routine.getImageId());
        }
    }
}
