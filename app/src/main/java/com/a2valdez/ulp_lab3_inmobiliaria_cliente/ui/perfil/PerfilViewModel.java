package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> mPropietario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getMPropietario() {
        if(mPropietario == null){
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
    }

    public void LeerUsuario(){
        Propietario p = ApiClient.getApi().obtenerUsuarioActual();
        if( p != null) {
            mPropietario.setValue(p);
        }
    }

    public void GuardarPropietario(String id, String dni, String nombre, String apellido, String email, String password, String telefono){
        Propietario p = new Propietario(Integer.parseInt(id), Long.parseLong(dni), nombre, apellido, email, password, telefono, 1);
        ApiClient.getApi().actualizarPerfil(p);
        mPropietario.setValue(p);
    }
}