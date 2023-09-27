package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.contratos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Pago;

import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {

    private List<Pago> pagos;
    private Context contexto;
    private LayoutInflater li;

    public PagoAdapter(List<Pago> inmuebles, Context contexto, LayoutInflater li) {
        this.pagos = inmuebles;
        this.contexto = contexto;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.inmueble.add(inmuebles.get(position));
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView pago;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pago = itemView.findViewById(R.id.tvPagoCodigo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("nota", pago.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.inmuebleFragment, bundle);
                }
            });
        }
    }
}
