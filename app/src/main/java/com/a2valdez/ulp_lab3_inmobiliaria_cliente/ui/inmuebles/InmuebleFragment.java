package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.FragmentInmuebleBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;

public class InmuebleFragment extends Fragment {
    private FragmentInmuebleBinding binding;
    private InmuebleViewModel mv;
    public static InmuebleFragment newInstance() {
        return new InmuebleFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(InmuebleViewModel.class);
        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getMInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble i) {
                binding.etInmuebleCodigo.setText(String.valueOf(i.getIdInmueble()));
                binding.etInmuebleAmbientes.setText(String.valueOf(i.getAmbientes()));
                binding.etInmuebleDireccion.setText(i.getDireccion());
                binding.etInmueblePrecio.setText(String.valueOf(i.getPrecio()));
                binding.etInmuebleUso.setText(i.getUso());
                binding.etInmuebleTipo.setText(i.getTipo());
                binding.swInmuebleDisponible.setChecked(i.isEstado());
                binding.swInmuebleDisponible.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mv.actualizarInmueble(i);
                    }
                });
            }
        });
        mv.obtenerInmueble(getArguments());
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(InmuebleViewModel.class);
        // TODO: Use the ViewModel
    }
}