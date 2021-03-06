package com.example.gymroutinesapp.model.entity;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

/**
 * Clase Routine para instanciar una rutina.
 */
@Entity(tableName = "routine")
public class Routine implements RoutineInterface
{

    // ***************************************** CONST **************************************** //

    // ************************************** PROPERTIES ************************************** //

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name = "Rutina";

    @ColumnInfo(name = "active", defaultValue = "0")
    private Boolean active;

    @ColumnInfo(name = "image_id", defaultValue = "2131165306")
    private Integer imageId;

    // *************************************** CONSTRUCT ************************************** //

    /**
     * Constructor de la clase Routine
     *
     * @param id ID de la rutina.
     * @param name Nombre de la rutina.
     */
    public Routine(Integer id, String name, Boolean active, Integer imageId)
    {
        this.setId(id)
            .setName(name)
            .setActive(active)
            .setImageId(imageId);
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
     * @return Routine
     */
    public Routine setId(Integer id)
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
     * @return Routine
     */
    public Routine setName(String name)
    {
        this.name = name;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return String
     */
    public Boolean getActive()
    {
        return this.active;
    }

    /**
     * {@inheritDoc}
     * @return Routine
     */
    public Routine setActive(Boolean active)
    {
        this.active = active;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getImageId()
    {
        return this.imageId;
    }

    /**
     * {@inheritDoc}
     * @return Routine
     */
    public Routine setImageId(Integer imageId)
    {
        this.imageId = imageId;

        return this;
    }

    // ************************************* PRIVATE METHODS ********************************** //

    // ************************************* PUBLIC METHODS *********************************** //

}
