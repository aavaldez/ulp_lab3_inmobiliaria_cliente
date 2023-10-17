package com.a2valdez.ulp_lab3_inmobiliaria_cliente;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.ActivityMainBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    NavigationView navigationView;
    private MainActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil, R.id.nav_inmuebles, R.id.nav_inquilinos, R.id.nav_contratos, R.id.nav_salir)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        mv.getMPropietario().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                View header = navigationView.getHeaderView(0);
                ImageView avatar = header.findViewById(R.id.ivNavAvatar);
                TextView nombreProp = header.findViewById(R.id.tvNavNombre);
                TextView mailProp = header.findViewById(R.id.tvNavEmail);

                nombreProp.setText(propietario.getNombre()+ " " + propietario.getApellido());
                mailProp.setText(propietario.getEmail());
                avatar.setImageResource(propietario.getAvatar());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        mv.LeerUsuario();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}