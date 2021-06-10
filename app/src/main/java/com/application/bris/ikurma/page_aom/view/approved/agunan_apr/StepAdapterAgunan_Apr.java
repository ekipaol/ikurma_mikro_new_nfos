package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepAdapterAgunan_Apr extends AbstractFragmentStepAdapter{
    private String title;
    private AgunanTanahBangunan agunanTanahBangunan;
    private String idAgunan;
    private String loan_type, tp_produk;


    public StepAdapterAgunan_Apr(@NonNull FragmentManager fm, @NonNull Context context, AgunanTanahBangunan magunanTanahBangunan, String midAgunan, String mloan_type, String mtp_produk) {
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
                FragmentAgunan1Identifikasi_Apr fragmentAgunan1IdentifikasiAprDefa = new FragmentAgunan1Identifikasi_Apr(idAgunan, agunanTanahBangunan);
                return fragmentAgunan1IdentifikasiAprDefa;
            case 1:
                FragmentAgunan2Surat_Apr fragmentAgunan2SuratApr = new FragmentAgunan2Surat_Apr(idAgunan, agunanTanahBangunan);
                return fragmentAgunan2SuratApr;
            case 2:
                FragmentAgunan3Uraian_Apr fragmentAgunan3UraianApr = new FragmentAgunan3Uraian_Apr(idAgunan, agunanTanahBangunan);
                return fragmentAgunan3UraianApr;
            case 3:
                FragmentAgunan4Spek_Apr fragmentAgunan4SpekApr = new FragmentAgunan4Spek_Apr(idAgunan, agunanTanahBangunan);
                return fragmentAgunan4SpekApr;
            case 4:
                FragmentAgunan5Lingkungan_Apr fragmentAgunan5LingkunganApr = new FragmentAgunan5Lingkungan_Apr(idAgunan, agunanTanahBangunan);
                return fragmentAgunan5LingkunganApr;
            case 5:
                FragmentAgunan6Hasil_Apr fragmentAgunan6HasilApr = new FragmentAgunan6Hasil_Apr(idAgunan, agunanTanahBangunan, loan_type, tp_produk);
                return fragmentAgunan6HasilApr;
            case 6:
                FragmentAgunan7Lain_Apr fragmentAgunan7LainApr = new FragmentAgunan7Lain_Apr(idAgunan, agunanTanahBangunan);
                return fragmentAgunan7LainApr;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 7;
    }
}
