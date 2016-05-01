package com.vargas.carlos.mapa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vargas.carlos.mapa.R;
import com.vargas.carlos.mapa.adapters.CidadesAdapter;
import com.vargas.carlos.mapa.models.Cidades;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {

    private RecyclerView recyclerView;
    private CidadesAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);

        List<Cidades> cidadesList = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        Cidades passoFundo = new Cidades("Passo Fundo", "RS");
        Cidades carazinho = new Cidades("Carazinho", "RS");
        Cidades marau = new Cidades("Carazinho", "RS");
        Cidades portoAlegre = new Cidades("Porto Alegre", "RS");
        Cidades cachoeira = new Cidades("Cachoeira", "RS");
        Cidades santaMaria = new Cidades("Santa Maria", "RS");
        Cidades erechim = new Cidades("Erechim", "RS");
        Cidades getulioVargas = new Cidades("Getulio Vargas", "RS");
        Cidades colorado = new Cidades("Colorado", "RS");

        cidadesList.add(passoFundo);
        cidadesList.add(carazinho);
        cidadesList.add(marau);
        cidadesList.add(portoAlegre);
        cidadesList.add(cachoeira);
        cidadesList.add(santaMaria);
        cidadesList.add(erechim);
        cidadesList.add(getulioVargas);
        cidadesList.add(colorado);

        mAdapter = new CidadesAdapter(cidadesList);
        mAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
