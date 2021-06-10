package com.application.bris.ikurma.page_aom.view.hotprospek.agunan;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepAdapterAgunan extends AbstractFragmentStepAdapter{
    private String title;
    private AgunanTanahBangunan agunanTanahBangunan;
    private String idAgunan;
    private String loan_type, tp_produk;


    public StepAdapterAgunan(@NonNull FragmentManager fm, @NonNull Context context, AgunanTanahBangunan magunanTanahBangunan, String midAgunan, String mloan_type, String mtp_produk) {
        super(fm, context);
        idAgunan = midAgunan;
        agunanTanahBangunan = magunanTanahBangunan;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Identifikasi Tanah";
                break;
            case 1:
                title = "Identifikasi Surat Tanah";
                break;
            case 2:
                title = "Uraian Bangunan";
                break;
            case 3:
                title = "Spesifikasi Bangunan";
                break;
            case 4:
                title = "Data Lingkungan";
                break;
            case 5:
                title = "Hasil Penilaian";
                break;
            case 6:
                title = "Lain-lain";
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
                FragmentAgunan1Identifikasi fragmentAgunan1IdentifikasiDefa = new FragmentAgunan1Identifikasi(idAgunan, agunanTanahBangunan);
                return fragmentAgunan1IdentifikasiDefa;
            case 1:
                FragmentAgunan2Surat fragmentAgunan2Surat = new FragmentAgunan2Surat(idAgunan, agunanTanahBangunan);
                return fragmentAgunan2Surat;
            case 2:
                FragmentAgunan3Uraian fragmentAgunan3Uraian = new FragmentAgunan3Uraian(idAgunan, agunanTanahBangunan);
                return fragmentAgunan3Uraian;
            case 3:
                FragmentAgunan4Spek fragmentAgunan4Spek = new FragmentAgunan4Spek(idAgunan, agunanTanahBangunan);
                return fragmentAgunan4Spek;
            case 4:
                FragmentAgunan5Lingkungan fragmentAgunan5Lingkungan = new FragmentAgunan5Lingkungan(idAgunan, agunanTanahBangunan);
                return fragmentAgunan5Lingkungan;
            case 5:
                FragmentAgunan6Hasil fragmentAgunan6Hasil = new FragmentAgunan6Hasil(idAgunan, agunanTanahBangunan, loan_type, tp_produk);
                return fragmentAgunan6Hasil;
            case 6:
                FragmentAgunan7Lain fragmentAgunan7Lain = new FragmentAgunan7Lain(idAgunan, agunanTanahBangunan);
                return fragmentAgunan7Lain;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 7;
    }
}
