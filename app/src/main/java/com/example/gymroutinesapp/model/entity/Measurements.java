package com.example.gymroutinesapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Clase Exercise para instanciar un ejercicio.
 */
@Entity(tableName = "measurements")
public class Measurements implements MeasurementsInterface {

    // ***************************************** CONST **************************************** //

    // ************************************** PROPERTIES ************************************** //

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "routine_id")
    private Integer routineID;

    @ColumnInfo(name = "exercise_id")
    private Integer exerciseID;

    @ColumnInfo(name = "time_in_seconds")
    private Integer timeInSeconds;

    @ColumnInfo(name = "weight", defaultValue = "-1")
    private float weight;

    @ColumnInfo(name = "registered_at")
    private long registeredAt;

    @ColumnInfo(name = "reps", defaultValue = "0")
    private Integer reps;

    // *************************************** CONSTRUCT ************************************** //

    /**
     * Constructor de la clase Routine
     *
     * @param id   ID de la rutina.
     * @param routineID ID de la rutina a la que pertenece.
     * @param exerciseID ID del ejercicio al que pertenece.
     * @param timeInSeconds Medida de tiempo que se almacena en la medida.
     * @param weight Medida de peso que se almacena en la medida. Puede ser de valor nulo.
     * @param registeredAt Fecha en formato timestamp en la que se han registrado las medidas.
     */
    public Measurements(Integer id, Integer routineID, Integer exerciseID, Integer timeInSeconds,
                        float weight, long registeredAt, Integer reps)
    {
        this.setId(id)
                .setRoutineID(routineID)
                .setExerciseID(exerciseID)
                .setTimeInSeconds(timeInSeconds)
                .setWeight(weight)
                .setRegisteredAt(registeredAt)
                .setReps(reps);
    }

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * {@inheritDoc}
     * @return Exercise
     */
    public Measurements setId(Integer id)
    {
        this.id = id;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getRoutineID()
    {
        return this.routineID;
    }

    /**
     * {@inheritDoc}
     * @return Measurements
     */
    public Measurements setRoutineID(Integer routineID)
    {
        this.routineID = routineID;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getExerciseID()
    {
        return this.exerciseID;
    }

    /**
     * {@inheritDoc}
     * @return Measurements
     */
    public Measurements setExerciseID(Integer exerciseID)
    {
        this.exerciseID = exerciseID;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getTimeInSeconds()
    {
        return this.timeInSeconds;
    }

    /**
     * {@inheritDoc}
     * @return Measurements
     */
    public Measurements setTimeInSeconds(Integer timeInSeconds)
    {
        this.timeInSeconds = timeInSeconds;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Float
     */
    public float getWeight()
    {
        return this.weight;
    }

    /**
     * {@inheritDoc}
     * @return Measurements
     */
    public Measurements setWeight(float weight)
    {
        this.weight = weight;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public long getRegisteredAt()
    {
        return this.registeredAt;
    }

    /**
     * {@inheritDoc}
     * @return Measurements
     */
    public Measurements setRegisteredAt(long registeredAt)
    {
        this.registeredAt = registeredAt;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getReps()
    {
        return this.reps;
    }

    /**
     * {@inheritDoc}
     * @return Routine
     */
    public Measurements setReps(Integer reps)
    {
        this.reps = reps;

        return this;
    }

    // ************************************* PRIVATE METHODS ********************************** //

    // ************************************* PUBLIC METHODS *********************************** //

}
