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
    public void insertMeasurements(Measurements... measurements);

    @Update
    public void updateMeasurements(Measurements... measurements);

    @Delete
    public void deleteMeasurements(Measurements... measurements);

    @Query("SELECT * FROM measurements")
    public List<Measurements> findAll();

    @Query("SELECT * FROM measurements WHERE id = :id")
    public Measurements findOneByID(int id);
}