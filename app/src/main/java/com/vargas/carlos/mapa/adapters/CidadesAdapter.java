package com.vargas.carlos.mapa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargas.carlos.mapa.R;
import com.vargas.carlos.mapa.models.Cidades;

import java.util.List;

public class CidadesAdapter extends RecyclerView.Adapter<CidadesAdapter.CidadesViewHolder> {

    private List<Cidades> cidadesList;

    public class CidadesViewHolder extends RecyclerView.ViewHolder {
        public TextView cidade, estado;

        public CidadesViewHolder(View view) {
            super(view);
            cidade = (TextView) view.findViewById(R.id.cidade);
            estado = (TextView) view.findViewById(R.id.estado);
        }
    }

    public CidadesAdapter(List<Cidades> cidadesList) {
        this.cidadesList = cidadesList;
    }

    @Override
    public CidadesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cidades_list, parent, false);

        return new CidadesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CidadesViewHolder holder, int position) {
        Cidades cidade = cidadesList.get(position);
        holder.cidade.setText(cidade.getCidade());
        holder.estado.setText(cidade.getEstado());
    }

    @Override
    public int getItemCount() {
        return cidadesList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}