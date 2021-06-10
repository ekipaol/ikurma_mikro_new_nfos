package com.application.bris.ikurma.page_aom.view.approved.datalengkap_apr;

import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma.page_aom.model.DataLengkap;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class DatalengkapStepAdapter_Apr extends AbstractFragmentStepAdapter{
    private String title;
    private DataLengkap dataLengkap;

    public DatalengkapStepAdapter_Apr(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap) {
        super(fm, context);
        dataLengkap = mdataLengkap;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Data Pribadi";
                break;
            case 1:
                title = "Data Alamat";
                break;
            case 2:
                title = "Data Usaha";
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
                FragmentDataPribadi_Apr fragmentDataPribadiApr = new FragmentDataPribadi_Apr(dataLengkap);
                return fragmentDataPribadiApr;
            case 1:
                FragmentDataAlamat_Apr fragmentDataAlamatApr = new FragmentDataAlamat_Apr(dataLengkap);
                return fragmentDataAlamatApr;
            case 2:
                FragmentDataUsaha_Apr fragmentDataUsahaApr = new FragmentDataUsaha_Apr(dataLengkap);
                return fragmentDataUsahaApr;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}