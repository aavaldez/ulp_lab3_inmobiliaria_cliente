package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inquilino;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;
import com.google.android.gms.common.api.Api;

public class InmuebleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> mInmueble;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Inmueble> getMInmueble() {
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public void obtenerInmueble(Bundle bundle){
        Inmueble i = ApiClient.getApi().obtnerPropiedades().get(Integer.parseInt(bundle.getString("id")));
        mInmueble.setValue(i);
    }
}