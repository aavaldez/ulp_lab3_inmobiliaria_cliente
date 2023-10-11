package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.MainActivity;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClientRetrofit;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void LeerUsuarioApi(){
        String token =ApiClientRetrofit.leerToken(context);
        Log.d("salida", token);
        ApiClientRetrofit.ApiInmobiliaria apiInmobiliaria = ApiClientRetrofit.getApiInmobiliaria();
        Call<Propietario> p = apiInmobiliaria.obtenerPerfil(token);
        p.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    //Log.d("salida", response.body().getNombre());
                    mPropietario.postValue(response.body());
                } else{
                    Log.d("salida respuesta", response.message());
                }
            }
            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    public void GuardarPropietario(String id, String dni, String nombre, String apellido, String email, String password, String telefono, int avatar){
        Propietario p = new Propietario(Integer.parseInt(id), Long.parseLong(dni), nombre, apellido, email, password, telefono, avatar);
        ApiClient.getApi().actualizarPerfil(p);
        mPropietario.setValue(p);
    }
}