package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
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
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;

    public static NuevoInmuebleFragment newInstance() {
        return new NuevoInmuebleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openGallery();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(NuevoInmuebleViewModel.class);
        binding = FragmentNuevoInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get the spinner from the xml.
        Spinner ddUso = (Spinner)root.findViewById(R.id.spNuevoInmuebleUso);
        //create a list of items for the spinner.
        String[] itemsUso = new String[]{"Residencial", "Comercial"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapterUso = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsUso);
        //set the spinners adapter to the previously created one.
        ddUso.setAdapter(adapterUso);

        //get the spinner from the xml.
        Spinner ddTipo = (Spinner)root.findViewById(R.id.spNuevoInmuebleTipo);
        //create a list of items for the spinner.
        String[] itemsTipo = new String[]{"Casa", "Departamento", "Oficina", "Local", "Deposito"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsTipo);
        //set the spinners adapter to the previously created one.
        ddTipo.setAdapter(adapterTipo);

        binding.btNuevoInmuebleCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImageUri != null) {
                    Inmueble inmueble = new Inmueble();
                    inmueble.setDireccion(binding.etNuevoInmuebleDireccion.getText().toString());
                    inmueble.setAmbientes(Integer.parseInt(binding.etNuevoInmuebleAmbientes.getText().toString()));
                    inmueble.setTipo(binding.spNuevoInmuebleTipo.getSelectedItem().toString());
                    inmueble.setUso(binding.spNuevoInmuebleUso.getSelectedItem().toString());
                    inmueble.setPrecio(Double.parseDouble(binding.etNuevoInmueblePrecio.getText().toString()));
                    Log.d("salida", selectedImageUri.toString());
                    mv.crearInmueble(inmueble, selectedImageUri);
                } else {
                    Toast.makeText(getContext(), "Debe Seleccionar una imagen", Toast.LENGTH_LONG).show();
                }
            }
        });


        binding.ivNuevoInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void openGallery() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Intent data = result.getData();
                    selectedImageUri = data.getData();
                    binding.ivNuevoInmueble.setImageURI(selectedImageUri);
                }
            }
        });
    }
}