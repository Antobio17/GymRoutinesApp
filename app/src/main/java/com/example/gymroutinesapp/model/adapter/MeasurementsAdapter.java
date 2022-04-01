package com.example.gymroutinesapp.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymroutinesapp.R;
import com.example.gymroutinesapp.model.entity.Measurements;

import java.util.List;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.ViewHolder>
{
    private List<Measurements> measurements;
    private LayoutInflater inflater; // Identidicar de que archivo viene el layout
    private Context context;

    public MeasurementsAdapter(List<Measurements> measurements, Context context)
    {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.measurements = measurements;
    }

    @NonNull
    @Override
    public MeasurementsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_measurements, null);

        return new MeasurementsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementsAdapter.ViewHolder holder, int position)
    {
        holder.bindData(this.measurements.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.measurements.size();
    }

    public void setItems(List<Measurements> items)
    {
        this.measurements = items;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView performedAt, timeInSeconds, weight;

        ViewHolder(View itemView)
        {
            super(itemView);
            performedAt = itemView.findViewById(R.id.performedAt);
            timeInSeconds = itemView.findViewById(R.id.timeInSeconds);
            weight = itemView.findViewById(R.id.weight);
        }

        @SuppressLint("SetTextI18n")
        void bindData(final Measurements measurements)
        {
            performedAt.setText(DateFormat.format(
                    "dd-MM-yyyy h:m:s", measurements.getRegisteredAt()
            ).toString());

            int minutes = measurements.getTimeInSeconds() / 60;
            int seconds = measurements.getTimeInSeconds() % 60;
            String timeText = "Realizado en: ";
            if (minutes > 0)
                timeText += minutes + "' ";
            timeText += seconds + "''";
            timeInSeconds.setText(timeText);

            if (measurements.getWeight() != -1) {
                weight.setText("Peso: " + measurements.getWeight() + "KG");
            } else {
                weight.setText("");
                timeInSeconds.setGravity(1);
                RelativeLayout.LayoutParams params =
                        (RelativeLayout.LayoutParams)timeInSeconds.getLayoutParams();
                params.setMarginStart(0);
                timeInSeconds.setLayoutParams(params);
            }
        }
    }
}