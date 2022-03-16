package com.example.gymroutinesapp.model;

import androidx.annotation.Nullable;

/**
 * Clase Exercise para instanciar un ejercicio.
 */
public class Measurements implements MeasurementsInterface {

    // ***************************************** CONST **************************************** //

    // ************************************** PROPERTIES ************************************** //

    private Integer id;

    private Integer routineID;

    private Integer exerciseID;

    private Integer timeInSeconds;

    private @Nullable Float weight;

    private Integer registeredAt;

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
                        @Nullable Float weight, Integer registeredAt)
    {
        this.setID(id)
                .setRoutineID(routineID)
                .setExerciseID(exerciseID)
                .setTimeInSeconds(timeInSeconds)
                .setWeight(weight)
                .setRegisteredAt(registeredAt);
    }

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getID()
    {
        return id;
    }

    /**
     * {@inheritDoc}
     * @return Exercise
     */
    public Measurements setID(Integer id)
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
    public @Nullable Float getWeight()
    {
        return this.weight;
    }

    /**
     * {@inheritDoc}
     * @return Measurements
     */
    public Measurements setWeight(@Nullable Float weight)
    {
        this.weight = weight;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getRegisteredAt()
    {
        return this.registeredAt;
    }

    /**
     * {@inheritDoc}
     * @return Measurements
     */
    public Measurements setRegisteredAt(Integer registeredAt)
    {
        this.registeredAt = registeredAt;

        return this;
    }

    // ************************************* PRIVATE METHODS ********************************** //

    // ************************************* PUBLIC METHODS *********************************** //

}
