package com.example.gymroutinesapp.model.entity;

@SuppressWarnings("ALL")
public interface ExerciseInterface {

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * Obtiene el ID del ejercicio.
     *
     * @return Integer
     */
    Integer getId();

    /**
     * Establece la propiedad ID en la clase.
     *
     * @param id ID a establecer en el ejercicio.
     *
     * @return Routine
     */
    Exercise setId(Integer id);

    /**
     * Obtiene el nombre del ejercicio.
     *
     * @return String
     */
    String getName();

    /**
     * Establece la propiedad Name en la clase.
     *
     * @param name Nombre a establecer en el ejercicio.
     *
     * @return Routine
     */
    Exercise setName(String name);

    /**
     * Obtiene si el ejercicio es o no con pesos obligatoriamente.
     *
     * @return String
     */
    Boolean getWithWeights();

    /**
     * Establece la propiedad WithWeights en la clase.
     *
     * @param withWeights Boleano para indicar si obligatoriamente necesita pesos.
     *
     * @return Routine
     */
    Exercise setWithWeights(Boolean withWeights);

    // ************************************* PUBLIC METHODS *********************************** //

    // ************************************* STATIC METHODS *********************************** //

    /**
     * Método que devuelve la sentencia SQL para crear la tabla en base de datos de la clase
     * Exercise.
     *
     * @return String
     */
    public static String createTable()
    {
        return "CREATE TABLE IF NOT EXISTS exercise ( " +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL," +
                "with_weights INTEGER DEFAULT(0)" +
                ")";
    }

    /**
     * Método que devuelve la sentencia SQL para eliminar la tabla de base de datos de la clase
     * Exercise.
     *
     * @return String
     */
    public static String dropTable()
    {
        return "DROP TABLE IF EXISTS exercise";
    }

}
