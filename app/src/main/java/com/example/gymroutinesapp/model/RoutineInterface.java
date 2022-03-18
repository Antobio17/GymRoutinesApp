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
        return "CREATE TABLE IF NOT EXISTS routine ( " +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "is_active BOOLEAN DEFAULT 0, " +
                "exercises_ids TEXT DEFAULT NULL" +
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
        return "DROP TABLE IF EXISTS routine";
    }

}
