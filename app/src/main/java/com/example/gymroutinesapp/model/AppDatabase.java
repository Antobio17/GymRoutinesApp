package com.example.gymroutinesapp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gymroutinesapp.model.dao.MeasurementsDao;
import com.example.gymroutinesapp.model.dao.RoutineDao;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.Measurements;
import com.example.gymroutinesapp.model.entity.Routine;
import com.example.gymroutinesapp.model.dao.ExerciseDao;

@Database(
        entities = {Routine.class, Exercise.class, Measurements.class},
        version = 10,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoutineDao routineDao();
    public abstract ExerciseDao exerciseDao();
    public abstract MeasurementsDao measurementsDao();
}


