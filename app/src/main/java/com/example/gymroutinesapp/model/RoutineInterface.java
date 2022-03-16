package com.example.gymroutinesapp.model;

@SuppressWarnings("ALL")
public interface RoutineInterface {

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * Obtiene el ID de la rutina.
     *
     * @return Integer
     */
    Integer getID();

    /**
     * Establece la propiedad ID en la clase.
     *
     * @param id ID a establecer en la rutina.
     *
     * @return Routine
     */
    Routine setID(Integer id);

    /**
     * Obtiene el nombre de la rutina.
     *
     * @return String
     */
    String getName();

    /**
     * Establece la propiedad Name en la clase.
     *
     * @param name Nombre a establecer en la rutina.
     *
     * @return Routine
     */
    Routine setName(String name);

    // ************************************* PUBLIC METHODS *********************************** //

    // ************************************* STATIC METHODS *********************************** //

    /**
     * Método que devuelve la sentencia SQL para crear la tabla en base de datos de la clase
     * Routine.
     *
     * @return String
     */
    public static String createTable()
    {
        return "CREATE TABLE routine ( " +
                "id INTEGER " +
                "name TEXT " +
                "is_active BOOLEAN " +
                ")";
    }

    /**
     * Método que devuelve la sentencia SQL para eliminar la tabla de base de datos de la clase
     * Routine.
     *
     * @return String
     */
    public static String deleteTable()
    {
        return "DELETE TABLE IF EXISTS routine";
    }

}
