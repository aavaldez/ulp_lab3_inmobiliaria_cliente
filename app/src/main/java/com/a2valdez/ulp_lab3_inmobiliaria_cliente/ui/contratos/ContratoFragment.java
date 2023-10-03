package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.contratos;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.FragmentContratoBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Contrato;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;

public class ContratoFragment extends Fragment {

    private FragmentContratoBinding binding;
    private ContratoViewModel mv;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(ContratoViewModel.class);
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getMContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato c) {
                binding.etContratoCodigo.setText(String.valueOf(c.getIdContrato()));
                binding.etContratoFechaInicio.setText(c.getFechaInicio());
                binding.etContratoFechaFin.setText(c.getFechaFin());
                binding.etContratoMonto.setText(String.valueOf(c.getMontoAlquiler()));
                binding.etContratoInquilino.setText(c.getInquilino().getApellido());
                binding.etContratoInmueble.setText(c.getInmueble().getDireccion());
                binding.etContratoInmuebleId.setText(String.valueOf(c.getInmueble().getIdInmueble()));
                binding.etContratoPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("inmueble", c.getInmueble());
                        Navigation.findNavController(v).navigate(R.id.pagosFragment, bundle);
                    }
                });
            }
        });
        mv.obtenerInquilino(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(ContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}