package com.application.bris.ikurma.page_aom.view.hotprospek.history;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.view.hotprospek.history.model.ModelHistoryCovenance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 20/06/2019.
 */

public class FragmentHistoryCovenance extends Fragment{

    @BindView(R.id.rv_historycovenance)
    RecyclerView rv_historycovenance;

    private String dataCovenance;
    private List<ModelHistoryCovenance> data;

    public FragmentHistoryCovenance(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_historycovenance, container, false);
        ButterKnife.bind(this, view);

        dataCovenance = getArguments().getString("dataCovenance");

        //DATA HISTORY COVENANCE
        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelHistoryCovenance>>() {}.getType();
        data = gson.fromJson(dataCovenance, type);
        HistoryCovenanceAdapater adp = new HistoryCovenanceAdapater(getContext(), data);
        rv_historycovenance.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_historycovenance.setItemAnimator(new DefaultItemAnimator());
        rv_historycovenance.setAdapter(adp);

        return view;


    }
}
