package com.example.gymroutinesapp.model;

/**
 * Clase Routine para instanciar una rutina.
 */
public class Routine implements RoutineInterface{

    // ***************************************** CONST **************************************** //

    // ************************************** PROPERTIES ************************************** //

    private Integer id;

    private String name;

    // *************************************** CONSTRUCT ************************************** //

    /**
     * Constructor de la clase Routine
     *
     * @param id ID de la rutina.
     * @param name Nombre de la rutina.
     */
    public Routine(Integer id, String name)
    {
        this.setID(id)
            .setName(name);
    }

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public Integer getID()
    {
        return this.id;
    }

    /**
     * {@inheritDoc}
     * @return Routine
     */
    public Routine setID(Integer id)
    {
        this.id = id;

        return this;
    }

    /**
     * {@inheritDoc}
     * @return String
     */
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

    // ************************************* PRIVATE METHODS ********************************** //

    // ************************************* PUBLIC METHODS *********************************** //

}
