package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Contrato;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Pago;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;

import java.util.List;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> mLista;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getmLista() {
        if(mLista == null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void obtenerPagos(Bundle bundle){
        Inmueble in = (Inmueble)bundle.getSerializable("inmueble");
        Contrato c = ApiClient.getApi().obtenerContratoVigente(in);
        mLista.setValue(ApiClient.getApi().obtenerPagos(c));
    }
}