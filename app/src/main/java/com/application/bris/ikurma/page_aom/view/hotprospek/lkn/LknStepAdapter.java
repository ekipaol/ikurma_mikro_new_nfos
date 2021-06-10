package com.application.bris.ikurma.page_aom.view.hotprospek.lkn;

import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class LknStepAdapter extends AbstractFragmentStepAdapter{
    private String title;
    private DataLkn data;

    public LknStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLkn mdata) {
        super(fm, context);
        data = mdata;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Informasi Kunjungan";
                break;
            case 1:
                title = "Analisa Keuangan";
                break;
            case 2:
                title = "Analisa Kebutuhan Modal Kerja (WI)";
                break;
            case 3:
                title = "Rekomendasi Fasilitas Pembiayaan";
                break;
            default:
                title = "Default Tab";
        }
        return new StepViewModel.Builder(context)
                .setTitle(title)
                .create();
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                FragmentLembarKunjungan fragmentLembarKunjungan = new FragmentLembarKunjungan(data);
                return fragmentLembarKunjungan;
            case 1:
                FragmentAnalisaKeuangan fragmentAnalisisKeuangan = new FragmentAnalisaKeuangan(data);
                return fragmentAnalisisKeuangan;
            case 2:
                FragmentAnalisaKebutuhanModalKerja fragmentAnalisaKebutuhanModalKerja = new FragmentAnalisaKebutuhanModalKerja(data);
                return fragmentAnalisaKebutuhanModalKerja;
            case 3:
                FragmentRekomendasiPembiayaan fragmentRekomendasiPembiayaan = new FragmentRekomendasiPembiayaan(data);
                return fragmentRekomendasiPembiayaan;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}