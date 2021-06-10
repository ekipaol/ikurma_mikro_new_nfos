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
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
public class FragmentAgunanKendaraan1_Apr extends Fragment implements Step{

    //VIEW BINDING
    @BindView(R.id.tf_tanggal_pemeriksaan)
    TextFieldBoxes tf_tanggal_pemeriksaan;
    @BindView(R.id.et_tanggal_pemeriksaan)
    ExtendedEditText et_tanggal_pemeriksaan;

    @BindView(R.id.tf_jenis_kendaraan)
    TextFieldBoxes tf_jenis_kendaraan;
    @BindView(R.id.et_jenis_kendaraan)
    ExtendedEditText et_jenis_kendaraan;

    @BindView(R.id.tf_kategori_kendaraan)
    TextFieldBoxes tf_kategori_kendaraan;
    @BindView(R.id.et_kategori_kendaraan)
    ExtendedEditText et_kategori_kendaraan;

    @BindView(R.id.tf_penggunaan_jaminan)
    TextFieldBoxes tf_penggunaan_jaminan;
    @BindView(R.id.et_penggunaan_jaminan)
    ExtendedEditText et_penggunaan_jaminan;

    @BindView(R.id.tf_daerah_operasional)
    TextFieldBoxes tf_daerah_operasional;
    @BindView(R.id.et_daerah_operasional)
    ExtendedEditText et_daerah_operasional;

    @BindView(R.id.tf_kondisi_jaminan)
    TextFieldBoxes tf_kondisi_jaminan;
    @BindView(R.id.et_kondisi_jaminan)
    ExtendedEditText et_kondisi_jaminan;

    @BindView(R.id.tf_nama_pemilik)
    TextFieldBoxes tf_nama_pemilik;
    @BindView(R.id.et_nama_pemilik)
    ExtendedEditText et_nama_pemilik;

    @BindView(R.id.tf_nama_pemilik_saat_ini)
    TextFieldBoxes tf_nama_pemilik_saat_ini;
    @BindView(R.id.et_nama_pemilik_saat_ini)
    ExtendedEditText et_nama_pemilik_saat_ini;

    @BindView(R.id.tf_alamat)
    TextFieldBoxes tf_alamat;
    @BindView(R.id.et_alamat)
    ExtendedEditText et_alamat;

    //END OF VIEW BINDING
    private String idAgunan;
    private AgunanKendaraan dataAgunan;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public FragmentAgunanKendaraan1_Apr() {
    }

    public FragmentAgunanKendaraan1_Apr(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_1, container, false);
        ButterKnife.bind(this,view);
        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }

        return view;
    }


    private void setDisable(){
        et_tanggal_pemeriksaan.setEnabled(false);
        et_jenis_kendaraan.setEnabled(false);
        et_kategori_kendaraan.setEnabled(false);
        et_penggunaan_jaminan.setEnabled(false);
        et_daerah_operasional.setEnabled(false);
        et_kondisi_jaminan.setEnabled(false);
        et_nama_pemilik.setEnabled(false);
        et_nama_pemilik_saat_ini.setEnabled(false);
        et_alamat.setEnabled(false);
    }

    private void setData(){
        try {
            et_tanggal_pemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));
            et_jenis_kendaraan.setText(dataAgunan.getJenisKendaraan());
            et_kategori_kendaraan.setText(dataAgunan.getKategKendaraan());
            et_penggunaan_jaminan.setText(dataAgunan.getPenggunaanJaminan());
            et_daerah_operasional.setText(KeyValue.getKeyOperasional(dataAgunan.getDaerahOperasional()));
            et_kondisi_jaminan.setText(dataAgunan.getKondisi());
            et_nama_pemilik.setText(dataAgunan.getNamaPemilikBPKB());
            et_nama_pemilik_saat_ini.setText(dataAgunan.getNamaPemilikSaatIni());
            et_alamat.setText(dataAgunan.getAlamatPemilik());
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

