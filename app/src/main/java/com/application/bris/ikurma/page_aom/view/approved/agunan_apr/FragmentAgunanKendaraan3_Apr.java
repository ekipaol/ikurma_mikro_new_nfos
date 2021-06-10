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
import com.application.bris.ikurma.page_aom.model.AgunanKendaraan;
import com.application.bris.ikurma.util.NumberTextWatcherForThousand;
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
public class FragmentAgunanKendaraan3_Apr extends Fragment implements Step{

    //VIEW BINDING
    @BindView(R.id.tf_tipe_kendaraan)
    TextFieldBoxes tf_tipe_kendaraan;
    @BindView(R.id.et_tipe_kendaraan)
    ExtendedEditText et_tipe_kendaraan;

    @BindView(R.id.tf_merk_kendaraan)
    TextFieldBoxes tf_merk_kendaraan;
    @BindView(R.id.et_merk_kendaraan)
    ExtendedEditText et_merk_kendaraan;

    @BindView(R.id.tf_kendaraan_jepang)
    TextFieldBoxes tf_kendaraan_jepang;
    @BindView(R.id.et_kendaraan_jepang)
    ExtendedEditText et_kendaraan_jepang;

    @BindView(R.id.tf_daya_angkut)
    TextFieldBoxes tf_daya_angkut;
    @BindView(R.id.et_daya_angkut)
    ExtendedEditText et_daya_angkut;

    @BindView(R.id.tf_kapasitas)
    TextFieldBoxes tf_kapasitas;
    @BindView(R.id.et_kapasitas)
    ExtendedEditText et_kapasitas;

    @BindView(R.id.tf_check_samsat)
    TextFieldBoxes tf_check_samsat;
    @BindView(R.id.et_check_samsat)
    ExtendedEditText et_check_samsat;

    @BindView(R.id.tf_dengan_siapa)
    TextFieldBoxes tf_dengan_siapa;
    @BindView(R.id.et_dengan_siapa)
    ExtendedEditText et_dengan_siapa;

    @BindView(R.id.tf_no_telp)
    TextFieldBoxes tf_no_telp;
    @BindView(R.id.et_no_telp)
    ExtendedEditText et_no_telp;

    @BindView(R.id.tf_hasil)
    TextFieldBoxes tf_hasil;
    @BindView(R.id.et_hasil)
    ExtendedEditText et_hasil;

    @BindView(R.id.tf_nilai_market)
    TextFieldBoxes tf_nilai_market;
    @BindView(R.id.et_nilai_market)
    ExtendedEditText et_nilai_market;

    @BindView(R.id.tf_nilai_likuidasi)
    TextFieldBoxes tf_nilai_likuidasi;
    @BindView(R.id.et_nilai_likuidasi)
    ExtendedEditText et_nilai_likuidasi;

    //END OF VIEW BINDING
    private String idAgunan;
    private AgunanKendaraan dataAgunan;
    public FragmentAgunanKendaraan3_Apr() {
    }

    public FragmentAgunanKendaraan3_Apr(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_3, container, false);
        ButterKnife.bind(this, view);
        onChangeText();
        if (!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }

        return view;
    }

    private void onChangeText() {
        et_nilai_market.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_market));
        et_nilai_likuidasi.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_likuidasi));
    }

    private void setDisable(){
        et_tipe_kendaraan.setEnabled(false);
        et_merk_kendaraan.setEnabled(false);
        et_kendaraan_jepang.setEnabled(false);
        et_daya_angkut.setEnabled(false);
        et_kapasitas.setEnabled(false);
        et_check_samsat.setEnabled(false);
        et_dengan_siapa.setEnabled(false);
        et_no_telp.setEnabled(false);
        et_hasil.setEnabled(false);
        et_nilai_market.setEnabled(false);
        et_nilai_likuidasi.setEnabled(false);
    }


    private void setData() {
        try {
            et_tipe_kendaraan.setText(dataAgunan.getTipeKendaraan());
            et_merk_kendaraan.setText(dataAgunan.getMerkKendaraan());
            et_kendaraan_jepang.setText(dataAgunan.getMobiljepang());
            et_daya_angkut.setText(dataAgunan.getDayaAngkut());
            et_kapasitas.setText(dataAgunan.getKapasitas());
            et_check_samsat.setText(dataAgunan.getCheckSamsat());
            et_dengan_siapa.setText(dataAgunan.getDenganSiapa());
            et_no_telp.setText(dataAgunan.getNoTelpon());
            et_hasil.setText(dataAgunan.getHasil());
            et_nilai_market.setText(dataAgunan.getNilaiMarket());
            et_nilai_likuidasi.setText(dataAgunan.getNilaiTaksasi());
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

