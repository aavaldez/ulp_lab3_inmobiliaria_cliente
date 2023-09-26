package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.home;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<MapaActual> mMapa;
    private static final LatLng INMOBILIARIA = new LatLng(-33.280576, -66.332482);

    public HomeViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<MapaActual> getMMapa(){
        if( mMapa == null ){
            mMapa = new MutableLiveData<>();
        }
        return mMapa;
    }

    public void obtenerMapa(){
        MapaActual ma = new MapaActual();
        mMapa.setValue(ma);
    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA).title("Inmobiliaria Te Alkilo"));
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(INMOBILIARIA)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(update);
        }
    }
}