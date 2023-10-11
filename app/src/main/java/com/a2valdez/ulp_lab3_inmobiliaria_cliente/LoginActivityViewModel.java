package com.a2valdez.ulp_lab3_inmobiliaria_cliente;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClient;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mMensaje;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getmMensaje() {
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void Login(String email, String password){
        String mensaje;
        Propietario propietario = ApiClient.getApi().login(email, password);
        if( propietario != null ){
            //Cargar activity
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            mensaje = "Usuario o contraseña incorrecto";
            mMensaje.setValue(mensaje);
        }
    }

    public void LoginApí(String email, String password){
        ApiClientRetrofit.ApiInmobiliaria apiInmobiliaria = ApiClientRetrofit.getApiInmobiliaria();
        Call<String> token = apiInmobiliaria.login(email, password);
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.d("salida", response.body());
                    ApiClientRetrofit.guardarToken(context, "Bearer "+response.body());
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else{
                    Log.d("salida respuesta", response.message());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}
