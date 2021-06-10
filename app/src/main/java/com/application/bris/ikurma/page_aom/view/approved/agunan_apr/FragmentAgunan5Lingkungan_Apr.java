package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan5Lingkungan_Apr extends Fragment implements Step{

    @BindView(R.id.tf_peruntukan_lokasi)
    TextFieldBoxes tf_peruntukan_lokasi;
    @BindView(R.id.et_peruntukan_lokasi)
    ExtendedEditText et_peruntukan_lokasi;

    @BindView(R.id.tf_akses_jalan)
    TextFieldBoxes tf_akses_jalan;
    @BindView(R.id.et_akses_jalan)
    ExtendedEditText et_akses_jalan;

    @BindView(R.id.tf_fasilitas_sosial)
    TextFieldBoxes tf_fasilitas_sosial;
    @BindView(R.id.et_fasilitas_sosial)
    ExtendedEditText et_fasilitas_sosial;

    @BindView(R.id.tf_lebar_jalan_depan)
    TextFieldBoxes tf_lebar_jalan_depan;
    @BindView(R.id.et_lebar_jalan_depan)
    ExtendedEditText et_lebar_jalan_depan;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan;

    public FragmentAgunan5Lingkungan_Apr() {
    }

    public FragmentAgunan5Lingkungan_Apr(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.agunan_5_lingkungan, container, false);

        try {
            ButterKnife.bind(this, view);
            if(!idAgunan.equalsIgnoreCase("0")) {
                setDisable();
                setData();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }


    private void setDisable(){
        et_peruntukan_lokasi.setEnabled(false);
        et_akses_jalan.setEnabled(false);
        et_fasilitas_sosial.setEnabled(false);
        et_lebar_jalan_depan.setEnabled(false);
    }

    private void setData(){
        try {
            et_peruntukan_lokasi.setText(dataAgunan.getPeruntukanLokasi());
            et_akses_jalan.setText(dataAgunan.getAksesJalanObjek());
            et_fasilitas_sosial.setText(dataAgunan.getFasilitasSosial());
            et_lebar_jalan_depan.setText(String.valueOf(dataAgunan.getLebarJalanDepan()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
    }
}


