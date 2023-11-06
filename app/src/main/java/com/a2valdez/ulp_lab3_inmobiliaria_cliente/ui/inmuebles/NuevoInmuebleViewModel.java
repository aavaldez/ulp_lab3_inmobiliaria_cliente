package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClientRetrofit;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoInmuebleViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<Inmueble> mInmueble;
    public NuevoInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public void crearInmueble(Inmueble inmueble, Uri uri){
        if(inmueble.getDireccion()==null ||
                inmueble.getTipo()==null ||
                inmueble.getUso()==null ||
                inmueble.getPrecio()==0 ||
                uri ==null){
            Toast.makeText(context,"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
            return;
        }
        RequestBody direccion = RequestBody.create(MediaType.parse("application/json"),inmueble.getDireccion());
        RequestBody tipo = RequestBody.create(MediaType.parse("application/json"),inmueble.getTipo());
        RequestBody uso = RequestBody.create(MediaType.parse("application/json"),inmueble.getUso());
        RequestBody ca = RequestBody.create(MediaType.parse("application/json"),inmueble.getAmbientes()+"");
        RequestBody precio = RequestBody.create(MediaType.parse("application/json"),inmueble.getPrecio()+"");

        String path = RealPathUtil.getRealPath(context,uri);
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("multiform/form-data"),file);
        MultipartBody.Part imagen = MultipartBody.Part.createFormData("ImagenFileName",file.getName(),fileBody);

        String token = ApiClientRetrofit.leerToken(context);
        Call<Inmueble> llamada = ApiClientRetrofit.getApiInmobiliaria().crearInmueble(token, direccion,ca, tipo, uso, precio, imagen);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    mInmueble.postValue(response.body());
                    Toast.makeText(context,"El inmueble ha sido creado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context,"Error: "+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public LiveData getMutable(){
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

}