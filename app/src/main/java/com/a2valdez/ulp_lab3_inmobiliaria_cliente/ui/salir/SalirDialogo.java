package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.salir;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class SalirDialogo {

    public static void mostrarDialogo(Activity activity){
        new AlertDialog.Builder(activity)
                .setTitle("Salir")
                .setMessage("¿Cerrar la aplicación?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
}