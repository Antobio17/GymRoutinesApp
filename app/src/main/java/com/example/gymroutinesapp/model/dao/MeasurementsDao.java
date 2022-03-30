package com.example.gymroutinesapp.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gymroutinesapp.model.entity.Measurements;

import java.util.List;

@Dao
public interface MeasurementsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurements(Measurements... measurements);

    @Update
    void updateMeasurements(Measurements... measurements);

    @Delete
    void deleteMeasurements(Measurements... measurements);

    @Query("SELECT * FROM measurements")
    List<Measurements> findAll();

    @Query("SELECT * FROM measurements WHERE id = :id")
    Measurements findOneByID(int id);

    @Query("SELECT * FROM measurements " +
            "WHERE routine_id = :idActiveRoutine AND exercise_id = :idExercise " +
            "ORDER BY id DESC LIMIT 1")
    Measurements findLastMeasurement(int idActiveRoutine, int idExercise);

    @Query("SELECT * FROM measurements " +
            "WHERE routine_id = :idActiveRoutine AND exercise_id = :idExercise " +
            "ORDER BY id DESC")
    List<Measurements> findBy(int idActiveRoutine, int idExercise);
}