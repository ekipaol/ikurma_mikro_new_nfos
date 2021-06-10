package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma.page_aom.model.AgunanKendaraan;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepAdapterAgunanKendaraan_Apr extends AbstractFragmentStepAdapter{
    private String title;
    private AgunanKendaraan agunanData;
    private String idAgunan;
    private String loan_type, tp_produk;

    public StepAdapterAgunanKendaraan_Apr(@NonNull FragmentManager fm, @NonNull Context context, AgunanKendaraan magunanData, String midAgunan, String mloan_type, String mtp_produk) {
        super(fm, context);
        idAgunan = midAgunan;
        agunanData = magunanData;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Identifikasi Kendaraan";
                break;
            case 1:
                title = "Spesifikasi Jaminan";
                break;
            case 2:
                title = "Detail Kendaraan";
                break;
            case 3:
                title = "Informasi Harga";
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
                FragmentAgunanKendaraan1_Apr fragmentAgunanKendaraan1Apr = new FragmentAgunanKendaraan1_Apr(idAgunan, agunanData);
                return fragmentAgunanKendaraan1Apr;
            case 1:
                FragmentAgunanKendaraan2_Apr fragmentAgunanKendaraan2Apr = new FragmentAgunanKendaraan2_Apr(idAgunan, agunanData);
                return fragmentAgunanKendaraan2Apr;
            case 2:
                FragmentAgunanKendaraan3_Apr fragmentAgunanKendaraan3Apr = new FragmentAgunanKendaraan3_Apr(idAgunan, agunanData);
                return fragmentAgunanKendaraan3Apr;
            case 3:
                FragmentAgunanKendaraan4_Apr fragmentAgunanKendaraan4Apr = new FragmentAgunanKendaraan4_Apr(idAgunan, agunanData);
                return fragmentAgunanKendaraan4Apr;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
