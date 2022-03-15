package com.example.gymroutinesapp;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gymroutinesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
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
}