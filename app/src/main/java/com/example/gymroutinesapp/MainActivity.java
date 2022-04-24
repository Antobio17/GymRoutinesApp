package com.example.gymroutinesapp;

import android.os.Bundle;
import android.view.Menu;

import com.example.gymroutinesapp.model.AppDatabase;
import com.example.gymroutinesapp.model.dao.ExerciseDao;
import com.example.gymroutinesapp.model.dao.MeasurementsDao;
import com.example.gymroutinesapp.model.dao.RoutineDao;
import com.example.gymroutinesapp.model.entity.Exercise;
import com.example.gymroutinesapp.model.entity.ExerciseInterface;
import com.example.gymroutinesapp.model.entity.Measurements;
import com.example.gymroutinesapp.model.entity.MeasurementsInterface;
import com.example.gymroutinesapp.model.entity.Routine;
import com.example.gymroutinesapp.model.entity.RoutineInterface;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gymroutinesapp.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static AppDatabase db;

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(RoutineInterface.dropTable());
            database.execSQL(RoutineInterface.createTable());
            database.execSQL(ExerciseInterface.dropTable());
            database.execSQL(ExerciseInterface.createTable());
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(MeasurementsInterface.dropTable());
            database.execSQL(MeasurementsInterface.createTable());
        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(ExerciseInterface.dropTable());
            database.execSQL(ExerciseInterface.createTable());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Establece la barra superior en la vista de la aplicación
        setSupportActionBar(binding.appBarMain.toolbar);

        // Container principal del fragment del menu lateral
        DrawerLayout drawer = binding.drawerLayout;
        // Menu lateral de XML
        NavigationView navigationView = binding.navView;

        // Crear la navegación del menu lateral (Mis Rutinas, Rutina Activa).
        // Se accede con el ID que se define en el XML del activity_main_drawer
        // (hace referencia al una etiqueta completa XML).
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_my_rutines, R.id.nav_active_rutine
        ).setOpenableLayout(drawer).build();

        // Crear el fragment de la navbar lateral
        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment_content_main
        );
        // Establecer la ActionBar a la NavBar (Botón para poder abrir el menú)
        NavigationUI.setupActionBarWithNavController(
                this, navController, mAppBarConfiguration
        );
        // Establecer la funcionalidad de navegar con el menu lareral al NavigationView
        NavigationUI.setupWithNavController(navigationView, navController);

        // Inicialización de la Base de datos
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "gymroutinesapp")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_5_6)
                .build();

        this._initializeBBDDData(db);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        db.close();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment_content_main
        );

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void _initializeBBDDData(AppDatabase db)
    {
        RoutineDao routineDao = db.routineDao();
        ExerciseDao exerciseDao = db.exerciseDao();
        MeasurementsDao measurementsDao = db.measurementsDao();

        Routine routine = routineDao.findOneByID(1);
        if (routine == null) {
            db.routineDao().insertRoutines(
                    new Routine(1, "Weider Routine", true)
            );

            Exercise exercise = exerciseDao.findOneByID(1);
            db.exerciseDao().insertExercises(new Exercise(1, "Cinta", false));
            db.exerciseDao().insertExercises(new Exercise(2, "Press de banca", true));
            db.exerciseDao().insertExercises(new Exercise(3, "Press militar", true));

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
            long currentTimestamp = calendar.getTimeInMillis();

            db.measurementsDao().insertMeasurements(
                    new Measurements(
                            1,
                            1,
                            1,
                            1800,
                            -1,
                            currentTimestamp
                    )
            );
            db.measurementsDao().insertMeasurements(
                    new Measurements(
                            2,
                            1,
                            2,
                            62,
                            (float) 25.0,
                            currentTimestamp
                    )
            );
            db.measurementsDao().insertMeasurements(
                    new Measurements(
                            3,
                            1,
                            2,
                            55,
                            (float) 27.5,
                            currentTimestamp
                    )
            );
            db.measurementsDao().insertMeasurements(
                    new Measurements(
                            4,
                            1,
                            3,
                            70,
                            (float) 15.0,
                            currentTimestamp
                    )
            );

            List<Exercise> exercisesByRoutine = exerciseDao.findByRoutineID(1);
            List<Routine> routines = routineDao.findAll();
            List<Exercise> exercises = exerciseDao.findAll();
            List<Measurements> measurementsList = measurementsDao.findAll();
        }
    }
}