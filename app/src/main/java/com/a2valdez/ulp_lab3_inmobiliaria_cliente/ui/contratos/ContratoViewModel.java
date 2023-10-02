package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Contrato;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;

public class ContratoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Contrato> mContrato;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Contrato> getMContrato() {
        if(mContrato == null){
            mContrato = new MutableLiveData<>();
        }
        return mContrato;
    }

    public void obtenerInquilino(Bundle bundle){
        Inmueble in = ApiClient.getApi().obtnerPropiedades().get(Integer.parseInt(bundle.getString("id")));
        Contrato c = ApiClient.getApi().obtenerContratoVigente(in);
        mContrato.setValue(c);
    }
}