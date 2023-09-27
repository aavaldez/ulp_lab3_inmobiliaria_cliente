package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.inquilinos;

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
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Inquilino;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder> {

    private List<Inquilino> inquilinos;
    private Context contexto;
    private LayoutInflater li;

    public InquilinoAdapter(List<Inquilino> inquilinos, Context contexto, LayoutInflater li) {
        this.inquilinos = inquilinos;
        this.contexto = contexto;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.inquilino.add(inquilinos.get(position));
    }

    @Override
    public int getItemCount() {
        return inquilinos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView inquilino;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inquilino = itemView.findViewById(R.id.tvApellido);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("nota", inquilino.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.inmuebleFragment, bundle);
                }
            });
        }
    }
}
