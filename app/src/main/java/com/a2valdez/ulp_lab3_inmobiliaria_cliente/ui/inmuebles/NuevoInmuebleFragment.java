package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.databinding.FragmentNuevoInmuebleBinding;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inmueble;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Propietario;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClientRetrofit;

public class NuevoInmuebleFragment extends Fragment {
    private FragmentNuevoInmuebleBinding binding;
    private NuevoInmuebleViewModel mv;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    public static NuevoInmuebleFragment newInstance() {
        return new NuevoInmuebleFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(NuevoInmuebleViewModel.class);
        binding = FragmentNuevoInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get the spinner from the xml.
        Spinner dropdown = (Spinner)root.findViewById(R.id.spNuevoInmuebleUso);
        //create a list of items for the spinner.
        String[] items = new String[]{"Residencial", "Comercial"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        mv.getMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(inmueble.getId()));
                NavController navController = Navigation.findNavController(root);
                navController.navigate(R.id.inmuebleFragment, bundle);
            }
        });
        binding.btNuevoInmuebleCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImageUri != null) {
                    Inmueble inmueble = new Inmueble();
                    inmueble.setDireccion(binding.etNuevoInmuebleDireccion.getText().toString());
                    inmueble.setAmbientes(Integer.parseInt(binding.etNuevoInmuebleAmbientes.getText().toString()));
                    inmueble.setTipo(binding.etNuevoInmuebleTipo.getText().toString());
                    //inmueble.setUso(binding.spNuevoInmuebleUso.getText().toString());
                    inmueble.setPrecio(Double.parseDouble(binding.etNuevoInmueblePrecio.getText().toString()));
                    mv.crearInmueble(inmueble, selectedImageUri);
                } else {
                    Toast.makeText(getContext(), "Debe Seleccionar una imagen", Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.ivNuevoInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(NuevoInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), PICK_IMAGE_REQUEST);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            binding.ivNuevoInmueble.setImageURI(selectedImageUri);
        }
    }
}