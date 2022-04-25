package com.example.gymroutinesapp.model.entity;

@SuppressWarnings("ALL")
public interface RoutineInterface {

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * Obtiene el ID de la rutina.
     *
     * @return Integer
     */
    Integer getId();

    /**
     * Establece la propiedad ID en la clase.
     *
     * @param id ID a establecer en la rutina.
     *
     * @return Routine
     */
    Routine setId(Integer id);

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

    /**
     * Obtiene el id de la imagen de la rutina.
     *
     * @return Integer
     */
    public Integer getImageId();

    /**
     * Establece la propiedad ImageID en la clase.
     *
     * @param imageId ID de la imagen a establecer en la rutina.
     *
     * @return Routine
     */
    public Routine setImageId(Integer imageId);

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
                "active INTEGER DEFAULT(0)," +
                "image_id INTEGER DEFAULT(2131165306)" +
                ")";
    }

    /**
     * Método que devuelve la sentencia SQL para eliminar la tabla de base de datos de la clase
     * Routine.
     *
     * @return String
     */
    public static String dropTable()
    {
        return "DROP TABLE IF EXISTS routine";
    }

}
