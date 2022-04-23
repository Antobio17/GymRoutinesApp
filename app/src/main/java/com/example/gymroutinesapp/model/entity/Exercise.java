package com.example.gymroutinesapp.model.entity;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Clase Exercise para instanciar un ejercicio.
 */
@Entity(tableName = "exercise")
public class Exercise implements ExerciseInterface, Serializable
{

    // ***************************************** CONST **************************************** //

    // ************************************** PROPERTIES ************************************** //

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name = "Ejercicio";

    @ColumnInfo(name = "with_weights", defaultValue = "0")
    private Boolean withWeights;

    // *************************************** CONSTRUCT ************************************** //

    /**
     * Constructor de la clase Routine
     *
     * @param id   ID de la rutina.
     * @param name Nombre de la rutina.
     * @param withWeights Indicador de si es un ejercicio con peso.
     */
    public Exercise(Integer id, String name, Boolean withWeights)
    {
        this.setId(id)
            .setName(name)
            .setWithWeights(withWeights);
    }

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * {@inheritDoc}
     * @return Exercise
     */
    public Exercise setId(Integer id)
    {
        this.id = id;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return String
     */
    @NonNull
    public String getName()
    {
        return this.name;
    }

    /**
     * {@inheritDoc}
     * @return Exercise
     */
    public Exercise setName(String name)
    {
        this.name = name;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return String
     */
    @NonNull
    public Boolean getWithWeights()
    {
        return this.withWeights;
    }

    /**
     * {@inheritDoc}
     * @return Exercise
     */
    public Exercise setWithWeights(Boolean withWeights)
    {
        this.withWeights = withWeights;

        return this;
    }

    // ************************************* PRIVATE METHODS ********************************** //

    // ************************************* PUBLIC METHODS *********************************** //

}
