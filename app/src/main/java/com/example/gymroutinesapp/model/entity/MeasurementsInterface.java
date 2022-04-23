package com.example.gymroutinesapp.model.entity;

import androidx.annotation.Nullable;

@SuppressWarnings("ALL")
public interface MeasurementsInterface {

    // *********************************** GETTERS AND SETTERS ******************************** //

    /**
     * Obtiene el ID de las medidas.
     *
     * @return Integer
     */
    Integer getId();

    /**
     * Establece la propiedad ID en la clase.
     *
     * @param id ID a establecer en las medidas.
     *
     * @return Routine
     */
    Measurements setId(Integer id);

    /**
     * Obtiene el ID de la rutina a la que pertenecen las medidas.
     *
     * @return Integer
     */
    public Integer getRoutineID();

    /**
     * Establece la propiedad RoutineID en la clase.
     *
     * @param routineID ID de la rutina a establecer en las medidas.
     *
     * @return Exercise
     */
    public Measurements setRoutineID(Integer routineID);

    /**
     * Obtiene el ID del ejercicio a la que pertenecen las medidas.
     *
     * @return Integer
     */
    public Integer getExerciseID();

    /**
     * Establece la propiedad ExerciseID en la clase.
     *
     * @param exerciseID ID del ejercicio a establecer en las medidas.
     *
     * @return Exercise
     */
    public Measurements setExerciseID(Integer exerciseID);

    /**
     * Obtiene el tiempo que se ha tardado en realizar el ejercicio en segundos.
     *
     * @return Integer
     */
    public Integer getTimeInSeconds();

    /**
     * Establece la propiedad TimeInSeconds en la clase.
     *
     * @param timeInSeconds Tiempo en segundos a establecer en las medidas del ejercicio.
     *
     * @return Measurements
     */
    public Measurements setTimeInSeconds(Integer timeInSeconds);

    /**
     * Obtiene el peso en Kilogramos que se ha utilizado en la realización del ejercicio.
     *
     * @return float
     */
    public float getWeight();

    /**
     * Establece la propiedad Weight en la clase.
     *
     * @param weight Peso en kilogramos a establecer en las medidas del ejercicio.
     *
     * @return Measurements
     */
    public Measurements setWeight(float weight);

    /**
     * Obtiene la fecha en formato timestamp en la que se han registrado las medidas.
     *
     * @return Integer
     */
    public long getRegisteredAt();

    /**
     * Establece la propiedad RegisteredAt en la clase.
     *
     * @param registeredAt Fecha en formato timestamp en la que se han registrado las medidas.
     *
     * @return Measurements
     */
    public Measurements setRegisteredAt(long registeredAt);

    // ************************************* PUBLIC METHODS *********************************** //

    // ************************************* STATIC METHODS *********************************** //

    /**
     * Método que devuelve la sentencia SQL para crear la tabla en base de datos de la clase
     * Measurements.
     *
     * @return String
     */
    public static String createTable()
    {
        return "CREATE TABLE measurements ( " +
                "id INTEGER PRIMARY KEY, " +
                "routine_id INTEGER, " +
                "exercise_id INTEGER, " +
                "time_in_seconds INTEGER, " +
                "weight REAL NOT NULL DEFAULT(-1), " +
                "registered_at INTEGER NOT NULL DEFAULT(NULL)" +
                ")";
    }

    /**
     * Método que devuelve la sentencia SQL para eliminar la tabla de base de datos de la clase
     * Measurements.
     *
     * @return String
     */
    public static String dropTable()
    {
        return "DROP TABLE IF EXISTS measurements";
    }

}
