package com.example.gymroutinesapp.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.example.gymroutinesapp.model.entity.Routine;

import java.util.List;

@Dao
public interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRoutines(Routine... routines);

    @Update
    public void updateRoutines(Routine... routines);

    @Delete
    public void deleteRoutines(Routine... routines);

    @Query("SELECT * FROM routine")
    public List<Routine> findAll();

    @Query("SELECT * FROM routine WHERE id = :id")
    public Routine findOneByID(int id);

    @Query("SELECT * FROM routine WHERE active = 1 LIMIT 1")
    public Routine findActiveRoutine();
}