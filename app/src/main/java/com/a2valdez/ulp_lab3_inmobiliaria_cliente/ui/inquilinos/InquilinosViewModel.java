package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inquilinos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;

import java.util.List;

public class InquilinosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Inmueble>> mLista;
    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<List<Inmueble>> getmLista() {
        if(mLista == null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }
    public void leerInmuebles(){
        mLista.setValue(ApiClient.getApi().obtenerPropiedadesAlquiladas());
    }
}