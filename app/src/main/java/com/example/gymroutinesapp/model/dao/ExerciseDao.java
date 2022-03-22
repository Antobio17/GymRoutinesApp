package com.example.gymroutinesapp.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.example.gymroutinesapp.model.entity.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertExercises(Exercise... exercises);

    @Update
    public void updateExercises(Exercise... exercises);

    @Delete
    public void deleteExercises(Exercise... exercises);

    @Query("SELECT * FROM exercise")
    public List<Exercise> findAll();

    @Query("SELECT ex.* FROM measurements me " +
            "LEFT JOIN exercise ex ON me.exercise_id = ex.id " +
            "WHERE me.routine_id = :routineID " +
            "GROUP BY ex.id")
    public List<Exercise> findByRoutineID(int routineID);

    @Query("SELECT * FROM exercise WHERE id = :id")
    public Exercise findOneByID(int id);
}