package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inquilino;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;

public class InquilinoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inquilino> mInquilino;
    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Inquilino> getMInquilino() {
        if(mInquilino == null){
            mInquilino = new MutableLiveData<>();
        }
        return mInquilino;
    }
    public void obtenerInquilino(Bundle bundle){
        Inmueble in = ApiClient.getApi().obtnerPropiedades().get(Integer.parseInt(bundle.getString("id")));
        Inquilino i = ApiClient.getApi().obtenerInquilino(in);
        mInquilino.setValue(i);
    }
}