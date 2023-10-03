package com.a2valdez.ulp_lab3_inmobiliaria_cliente.ui.contratos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    public PagoAdapter(List<Pago> pagos, Context contexto, LayoutInflater li) {
        this.pagos = pagos;
        this.contexto = contexto;
        this.li = li;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.numero.setText(String.valueOf(pagos.get(position).getNumero()));
        holder.codigoContrato.setText(String.valueOf(pagos.get(position).getContrato().getIdContrato()));
        holder.importe.setText(String.valueOf(pagos.get(position).getImporte()));
        holder.fecha.setText(pagos.get(position).getFechaDePago());
        holder.id.setText(String.valueOf(pagos.get(position).getIdPago()));
    }
    @Override
    public int getItemCount() {
        return pagos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private TextView numero;
        private TextView codigoContrato;
        private TextView importe;
        private TextView fecha;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvItemPagoCodigo);
            numero = itemView.findViewById(R.id.tvItemPagoNumero);
            codigoContrato = itemView.findViewById(R.id.tvItemPagoCodigoContrato);
            importe = itemView.findViewById(R.id.tvItemPagoImporte);
            fecha = itemView.findViewById(R.id.tvItemPagoFecha);
        }
    }
}
