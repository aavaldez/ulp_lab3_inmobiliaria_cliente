package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.FragmentInmuebleBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.FragmentInquilinoBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inquilino;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles.InmuebleFragment;

public class InquilinoFragment extends Fragment {
    private FragmentInquilinoBinding binding;
    private InquilinoViewModel mv;
    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getMInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino i) {
                binding.etInquilinoId.setText(String.valueOf(i.getIdInquilino()));
                binding.etInquilinoNombre.setText(i.getNombre());
                binding.etInquilinoApellido.setText(i.getApellido());
                binding.etInquilinoDni.setText(String.valueOf(i.getDNI()));
                binding.etInquilinoTelefono.setText(i.getTelefono());
                binding.etInquilinoEmail.setText(i.getEmail());
                binding.etInquilinoGarante.setText(i.getNombreGarante());
                binding.etInquilinoGaranteTelefono.setText(i.getTelefonoGarante());
            }
        });
        mv.obtenerInquilino(getArguments());
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(InquilinoViewModel.class);
        // TODO: Use the ViewModel
    }
}