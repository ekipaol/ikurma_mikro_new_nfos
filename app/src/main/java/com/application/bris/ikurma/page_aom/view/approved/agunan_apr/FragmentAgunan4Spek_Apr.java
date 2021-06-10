package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma.util.KeyValue;
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
public class FragmentAgunan4Spek_Apr extends Fragment implements Step{

    @BindView(R.id.tf_kondisi_bangunan)
    TextFieldBoxes tf_kondisi_bangunan;
    @BindView(R.id.et_kondisi_bangunan)
    ExtendedEditText et_kondisi_bangunan;

    @BindView(R.id.tf_jenis_bangunan_spek)
    TextFieldBoxes tf_jenis_bangunan_spek;
    @BindView(R.id.et_jenis_bangunan_spek)
    ExtendedEditText et_jenis_bangunan_spek;

    @BindView(R.id.tf_pondasi)
    TextFieldBoxes tf_pondasi;
    @BindView(R.id.et_pondasi)
    ExtendedEditText et_pondasi;

    @BindView(R.id.tf_dinding)
    TextFieldBoxes tf_dinding;
    @BindView(R.id.et_dinding)
    ExtendedEditText et_dinding;

    @BindView(R.id.tf_atap)
    TextFieldBoxes tf_atap;
    @BindView(R.id.et_atap)
    ExtendedEditText et_atap;

    @BindView(R.id.ada_listrik)
    CheckBox ada_listrik;
    @BindView(R.id.ada_air)
    CheckBox ada_air;
    @BindView(R.id.ada_telepon)
    CheckBox ada_telepon;

    @BindView(R.id.tf_listrik)
    TextFieldBoxes tf_listrik;
    @BindView(R.id.et_listrik)
    ExtendedEditText et_listrik;

    @BindView(R.id.tf_air)
    TextFieldBoxes tf_air;
    @BindView(R.id.et_air)
    ExtendedEditText et_air;

    @BindView(R.id.tf_telepon)
    TextFieldBoxes tf_telepon;
    @BindView(R.id.et_telepon)
    ExtendedEditText et_telepon;

    @BindView(R.id.ll_kondisi_bangunan)
    RelativeLayout ll_kondisi_bangunan;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan;
    public FragmentAgunan4Spek_Apr() {
    }

    public FragmentAgunan4Spek_Apr(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_4_spesifikasi, container, false);
        ButterKnife.bind(this, view);
        if(!idAgunan.equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }

        return view;
    }

    private void setDisable(){
        et_kondisi_bangunan.setEnabled(false);
        et_jenis_bangunan_spek.setEnabled(false);
        et_pondasi.setEnabled(false);
        et_dinding.setEnabled(false);
        et_atap.setEnabled(false);
        ada_listrik.setEnabled(false);
        et_listrik.setEnabled(false);
        ada_air.setEnabled(false);
        et_air.setEnabled(false);
        ada_telepon.setEnabled(false);
        et_telepon.setEnabled(false);
    }

    private void setData(){
        try {
            et_kondisi_bangunan.setText(KeyValue.getKeyKondisiBangunan(dataAgunan.getPerawatan()));
            et_jenis_bangunan_spek.setText(dataAgunan.getKetBangunan());
//            et_jenis_bangunan_spek.setText(KeyValue.getKeyJenisBangunanSpek(dataAgunan.getJenisBangunanBRINS()));
            et_pondasi.setText(KeyValue.getKeyPondasi(dataAgunan.getPondasi()));
            et_dinding.setText(KeyValue.getKeyDinding(dataAgunan.getDinding()));
            et_atap.setText(KeyValue.getKeyAtap(dataAgunan.getAtap()));
            if (dataAgunan.getFasilitasListrik() == true) {
                ada_listrik.setChecked(true);
            }
            if (dataAgunan.getFasilitasPAM() == true) {
                ada_air.setChecked(true);
            }
            if (dataAgunan.getTelepon() == true) {
                ada_telepon.setChecked(true);
            }
            et_listrik.setText(dataAgunan.getTeganganListrik());
            et_air.setText(dataAgunan.getJenisAir());
            et_telepon.setText(dataAgunan.getNoTelepon());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }
}


