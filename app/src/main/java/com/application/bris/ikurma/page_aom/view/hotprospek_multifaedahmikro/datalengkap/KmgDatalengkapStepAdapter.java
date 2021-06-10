package com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.datalengkap;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma.page_aom.model.DataLengkapKmg;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class KmgDatalengkapStepAdapter extends AbstractFragmentStepAdapter {
    private String title, approved;
    private DataLengkapKmg dataLengkap;

    //    public DatalengkapStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap) {
//        super(fm, context);
//        dataLengkap = mdataLengkap;
//    }
    public KmgDatalengkapStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkapKmg mdataLengkap, String mApproved) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        approved = mApproved;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position) {
            case 0:
                title = "Data Pribadi";
                break;
            case 1:
                title = "Data Alamat";
                break;
            case 2:
                title = "Data Pekerjaan";
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
                KmgFragmentDataPribadi fragmentDataPribadi = new KmgFragmentDataPribadi(dataLengkap, approved);
                return fragmentDataPribadi;
            case 1:
                KmgFragmentDataAlamat fragmentDataAlamat = new KmgFragmentDataAlamat(dataLengkap, approved);
                return fragmentDataAlamat;
            case 2:
                KmgFragmentDataPekerjaan fragmentDataUsaha = new KmgFragmentDataPekerjaan(dataLengkap, approved);
                return fragmentDataUsaha;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}