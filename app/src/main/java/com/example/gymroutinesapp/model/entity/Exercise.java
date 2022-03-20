package com.example.gymroutinesapp.model.entity;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

/**
 * Clase Exercise para instanciar un ejercicio.
 */
@Entity(tableName = "exercise")
public class Exercise implements ExerciseInterface
{

    // ***************************************** CONST **************************************** //

    // ************************************** PROPERTIES ************************************** //

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name = "Ejercicio";

    // *************************************** CONSTRUCT ************************************** //

    /**
     * Constructor de la clase Routine
     *
     * @param id   ID de la rutina.
     * @param name Nombre de la rutina.
     */
    public Exercise(Integer id, String name)
    {
        this.setId(id)
            .setName(name);
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

    // ************************************* PRIVATE METHODS ********************************** //

    // ************************************* PUBLIC METHODS *********************************** //

}
