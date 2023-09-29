package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.FragmentPerfilBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel mv;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = binding.getRoot();
        mv.getMPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                if (propietario != null){
                    binding.etPerfilId.setText(String.valueOf(propietario.getId()));
                    binding.etPerfilDni.setText(String.valueOf(propietario.getDni()));
                    binding.etPerfilApellido.setText(propietario.getApellido());
                    binding.etPerfilNombre.setText(propietario.getNombre());
                    binding.etPerfilEmail.setText(propietario.getEmail());
                    binding.etPerfilPassword.setText(propietario.getContraseña());
                    binding.etPerfilTelefono.setText(propietario.getTelefono());
                }
            }
        });
        binding.btPerfilGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.GuardarPropietario(
                        binding.etPerfilId.getText().toString(),
                        binding.etPerfilDni.getText().toString(),
                        binding.etPerfilApellido.getText().toString(),
                        binding.etPerfilNombre.getText().toString(),
                        binding.etPerfilEmail.getText().toString(),
                        binding.etPerfilPassword.getText().toString(),
                        binding.etPerfilTelefono.getText().toString()
                );
            }
        });
        mv.LeerUsuario();
        return root;
    }
}