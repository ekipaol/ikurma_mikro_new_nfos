package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
public class FragmentAgunan3Uraian_Apr extends Fragment implements Step{


    @BindView(R.id.tf_jenis_bangunan)
    TextFieldBoxes tf_jenis_bangunan;
    @BindView(R.id.et_jenis_bangunan)
    ExtendedEditText et_jenis_bangunan;

    @BindView(R.id.tf_lokasi_bangunan)
    TextFieldBoxes tf_lokasi_bangunan;
    @BindView(R.id.et_lokasi_bangunan)
    ExtendedEditText et_lokasi_bangunan;

    @BindView(R.id.ll_pasar)
    LinearLayout ll_pasar;

    @BindView(R.id.tf_nkr_pasar)
    TextFieldBoxes tf_nkr_pasar;
    @BindView(R.id.et_nkr_pasar)
    ExtendedEditText et_nkr_pasar;

    @BindView(R.id.tf_propinsi_pasar)
    TextFieldBoxes tf_propinsi_pasar;
    @BindView(R.id.et_propinsi_pasar)
    ExtendedEditText et_propinsi_pasar;

    @BindView(R.id.tf_kota_pasar)
    TextFieldBoxes tf_kota_pasar;
    @BindView(R.id.et_kota_pasar)
    ExtendedEditText et_kota_pasar;

    @BindView(R.id.tf_nama_pasar)
    TextFieldBoxes tf_nama_pasar;
    @BindView(R.id.et_nama_pasar)
    ExtendedEditText et_nama_pasar;

    @BindView(R.id.tf_alamat_pasar)
    TextFieldBoxes tf_alamat_pasar;
    @BindView(R.id.et_alamat_pasar)
    ExtendedEditText et_alamat_pasar;

    @BindView(R.id.tf_no_imb)
    TextFieldBoxes tf_no_imb;
    @BindView(R.id.et_no_imb)
    ExtendedEditText et_no_imb;

    @BindView(R.id.tf_jenis_agunan_xbrl)
    TextFieldBoxes tf_jenis_agunan_xbrl;
    @BindView(R.id.et_jenis_agunan_xbrl)
    ExtendedEditText et_jenis_agunan_xbrl;

    @BindView(R.id.tf_hub_penghuni_pemegang_hak)
    TextFieldBoxes tf_hub_penghuni_pemegang_hak;
    @BindView(R.id.et_hub_penghuni_pemegang_hak)
    ExtendedEditText et_hub_penghuni_pemegang_hak;

    @BindView(R.id.tf_jumlah_lantai)
    TextFieldBoxes tf_jumlah_lantai;
    @BindView(R.id.et_jumlah_lantai)
    ExtendedEditText et_jumlah_lantai;

    @BindView(R.id.tf_tahun_mendirikan)
    TextFieldBoxes tf_tahun_mendirikan;
    @BindView(R.id.et_tahun_mendirikan)
    ExtendedEditText et_tahun_mendirikan;

    @BindView(R.id.btn_cari_pasar)
    Button btn_cari_pasar;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan;

    public FragmentAgunan3Uraian_Apr() {
    }

    public FragmentAgunan3Uraian_Apr(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_3_uraian_bangunan, container, false);
        ButterKnife.bind(this, view);

        ll_pasar.setVisibility(View.GONE);

        if (!idAgunan.equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }
        return view;
    }

    private void setDisable(){
        et_jenis_bangunan.setEnabled(false);
        et_lokasi_bangunan.setEnabled(false);
        et_nkr_pasar.setEnabled(false);
        et_propinsi_pasar.setEnabled(false);
        et_kota_pasar.setEnabled(false);
        et_nama_pasar.setEnabled(false);
        et_alamat_pasar.setEnabled(false);
        et_no_imb.setEnabled(false);
        et_jenis_agunan_xbrl.setEnabled(false);
        et_hub_penghuni_pemegang_hak.setEnabled(false);
        et_jumlah_lantai.setEnabled(false);
        et_tahun_mendirikan.setEnabled(false);
    }

    private void setData() {
        try {
            et_jenis_bangunan.setText(KeyValue.getKeyJenisBangunan(dataAgunan.getJenisBangunan()));
            if (et_jenis_bangunan.getText().toString().equalsIgnoreCase("Ruko / Rukan")) {
                tf_lokasi_bangunan.setVisibility(View.VISIBLE);
                et_lokasi_bangunan.setText(KeyValue.getKeyLokasiBangunan(dataAgunan.getLokasiBangunanBrins()));

                if(et_lokasi_bangunan.getText().toString().equalsIgnoreCase("Pasar")){
                    ll_pasar.setVisibility(View.VISIBLE);

                    et_nkr_pasar.setFocusable(false);
                    et_propinsi_pasar.setFocusable(false);
                    et_kota_pasar.setFocusable(false);
                    et_nama_pasar.setFocusable(false);
                    et_alamat_pasar.setFocusable(false);

                    et_nkr_pasar.setText(dataAgunan.getNkrPasarBRINS());
                    tf_propinsi_pasar.setVisibility(View.GONE);
                    tf_kota_pasar.setVisibility(View.GONE);
                    et_nama_pasar.setText(dataAgunan.getNamaPasarBRINS());
                    tf_alamat_pasar.setVisibility(View.GONE);
                    btn_cari_pasar.setVisibility(View.GONE);
                }
            } else {
                tf_lokasi_bangunan.setVisibility(View.GONE);
                et_lokasi_bangunan.getText().clear();
            }
            et_no_imb.setText(dataAgunan.getNoIMB());
            et_jenis_agunan_xbrl.setText(KeyValue.getKeyJenisAgunanXBRL(dataAgunan.getJenisAgunanXBRL()));
            et_hub_penghuni_pemegang_hak.setText(KeyValue.getKeyHubPenghuniDenganPemegangHak(dataAgunan.getHubPenghuniDgPemilik()));
            et_jumlah_lantai.setText(dataAgunan.getTingkat());
            et_tahun_mendirikan.setText(dataAgunan.getTahunBangunan());
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


