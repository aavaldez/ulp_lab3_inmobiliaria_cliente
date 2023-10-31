package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;

public class NuevoInmuebleViewModel extends AndroidViewModel {
    private Context context;
    public NuevoInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void actualizarInmueble(Inmueble i){

    }
}