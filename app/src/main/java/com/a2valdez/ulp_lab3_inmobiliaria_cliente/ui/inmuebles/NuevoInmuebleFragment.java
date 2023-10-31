package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.FragmentNuevoInmuebleBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;

public class NuevoInmuebleFragment extends Fragment {
    private FragmentNuevoInmuebleBinding binding;
    private NuevoInmuebleViewModel mv;
    public static NuevoInmuebleFragment newInstance() {
        return new NuevoInmuebleFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(NuevoInmuebleViewModel.class);
        binding = FragmentNuevoInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(NuevoInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }
}