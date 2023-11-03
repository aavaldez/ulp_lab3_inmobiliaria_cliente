package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.LoginActivity;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inquilino;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiInmobiliaria apiInmobiliaria = ApiClientRetrofit.getApiInmobiliaria();
        Call<Inquilino> call = apiInmobiliaria.obtenerInquilinoPorInmueble(token, Integer.parseInt(bundle.getString("id")));
        call.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(@NonNull Call<Inquilino> call, @NonNull Response<Inquilino> response) {
                Log.d("salida", response.raw().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mInquilino.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Inquilino> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}