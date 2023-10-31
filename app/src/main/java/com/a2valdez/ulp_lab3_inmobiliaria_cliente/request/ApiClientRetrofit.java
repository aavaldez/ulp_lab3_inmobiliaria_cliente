package com.a2valdez.ulp_lab3_inmobiliaria_cliente.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Contrato;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inquilino;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Pago;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class ApiClientRetrofit {

    private static final String URLBASE = "http://192.168.100.2:5000/";

    private static ApiInmobiliaria apiInmobilaria;

    public static ApiInmobiliaria getApiInmobiliaria(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiInmobilaria = retrofit.create(ApiInmobiliaria.class);
        return apiInmobilaria;
    }

    public interface ApiInmobiliaria{
        @FormUrlEncoded
        @POST("Propietarios/Login")
        Call<String> login(@Field("Email") String usuario, @Field("Password") String password);

        @GET("Propietarios/Perfil")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @PUT("Propietarios/Editar")
        Call<Propietario> editarPerfil(@Header("Authorization") String token, @Body Propietario propietario);

        @GET("Inmuebles/Todos")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

        @PUT("inmuebles/cambiar_estado/{id}")
        Call<Inmueble> cambiarEstado(@Header("Authorization") String token, @Path("id") int id, @Body boolean estado);

        @Multipart
        @POST("inmuebles/Crear/")
        Call<Inmueble> crearInmueble(@Header("Authorization") String token, @Part MultipartBody.Part imagenFile);

        @GET("inmuebles/alquilados")
        Call<List<Inmueble>> obtenerInmueblesAlquiladas(@Header("Authorization") String token);

        @GET("Inquilinos/Obtener/{id}")
        Call<Inquilino> obtenerInquilinoPorInmueble(@Header("Authorization") String token, @Path("id") int id);

        @GET("Contratos/Obtener/{id}")
        Call<Contrato> obtenerContratoPorInmueble(@Header("Authorization") String token, @Path("id") int id);

        @GET("Pagos/Obtener/{id}")
        Call<List<Pago>> obtenerPagosPorContrato(@Header("Authorization") String token, @Path("id") int id);
    }

    public static void guardarToken(Context context, String token){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public static String leerToken(Context context){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        return sp.getString("token","");
    }
}
