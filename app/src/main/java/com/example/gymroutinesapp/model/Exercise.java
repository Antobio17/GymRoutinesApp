package com.example.gymroutinesapp.model;

/**
 * Clase Exercise para instanciar un ejercicio.
 */
public class Exercise implements ExerciseInterface {

    // ***************************************** CONST **************************************** //

    // ************************************** PROPERTIES ************************************** //

    private Integer id;

    private String name;

    // *************************************** CONSTRUCT ************************************** //

    /**
     * Constructor de la clase Routine
     *
     * @param id   ID de la rutina.
     * @param name Nombre de la rutina.
     */
    public Exercise(Integer id, String name)
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
     * @return Exercise
     */
    public Exercise setID(Integer id)
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
