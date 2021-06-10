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
import android.widget.TextView;

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
public class FragmentAgunan1Identifikasi_Apr extends Fragment implements Step{

//VIEW BINDING
    @BindView(R.id.tf_lokasi_tanah)
    TextFieldBoxes tf_lokasi_tanah;
    @BindView(R.id.et_lokasi_tanah)
    ExtendedEditText et_lokasi_tanah;

    @BindView(R.id.tf_kodepos_agunan)
    TextFieldBoxes tf_kodepos_agunan;
    @BindView(R.id.et_kodepos_agunan)
    ExtendedEditText et_kodepos_agunan;

    @BindView(R.id.tf_kelurahan_agunan)
    TextFieldBoxes tf_kelurahan_agunan;
    @BindView(R.id.et_kelurahan_agunan)
    ExtendedEditText et_kelurahan_agunan;

    @BindView(R.id.tf_kecamatan_agunan)
    TextFieldBoxes tf_kecamatan_agunan;
    @BindView(R.id.et_kecamatan_agunan)
    ExtendedEditText et_kecamatan_agunan;

    @BindView(R.id.tf_kota_agunan)
    TextFieldBoxes tf_kota_agunan;
    @BindView(R.id.et_kota_agunan)
    ExtendedEditText et_kota_agunan;

    @BindView(R.id.tf_propinsi_agunan)
    TextFieldBoxes tf_propinsi_agunan;
    @BindView(R.id.et_propinsi_agunan)
    ExtendedEditText et_propinsi_agunan;

    @BindView(R.id.tf_batas_utara)
    TextFieldBoxes tf_batas_utara;
    @BindView(R.id.et_batas_utara)
    ExtendedEditText et_batas_utara;

    @BindView(R.id.tf_batas_selatan)
    TextFieldBoxes tf_batas_selatan;
    @BindView(R.id.et_batas_selatan)
    ExtendedEditText et_batas_selatan;

    @BindView(R.id.tf_batas_barat)
    TextFieldBoxes tf_batas_barat;
    @BindView(R.id.et_batas_barat)
    ExtendedEditText et_batas_barat;

    @BindView(R.id.tf_batas_timur)
    TextFieldBoxes tf_batas_timur;
    @BindView(R.id.et_batas_timur)
    ExtendedEditText et_batas_timur;

    @BindView(R.id.tf_kav_blok)
    TextFieldBoxes tf_kav_blok;
    @BindView(R.id.et_kavblok)
    ExtendedEditText et_kavblok;

    @BindView(R.id.tf_rt_agunan)
    TextFieldBoxes tf_rt_agunan;
    @BindView(R.id.et_rt_agunan)
    ExtendedEditText et_rt_agunan;

    @BindView(R.id.tf_rw_agunan)
    TextFieldBoxes tf_rw_agunan;
    @BindView(R.id.et_rw_agunan)
    ExtendedEditText et_rw_agunan;

    @BindView(R.id.btn_dom)
    Button btn_dom;

    @BindView(R.id.btn_set_loc)
    Button btn_set_loc;

    @BindView(R.id.tv_set_loc)
    TextView tvSetLoc;

    //END OF VIEW BINDING

    private String idAgunan;
    private AgunanTanahBangunan dataAgunan;
    public FragmentAgunan1Identifikasi_Apr() {
    }

    public FragmentAgunan1Identifikasi_Apr(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_1_identifikasi, container, false);
        ButterKnife.bind(this,view);
        et_kodepos_agunan.setFocusable(false);
        et_kelurahan_agunan.setFocusable(false);
        et_kecamatan_agunan.setFocusable(false);
        et_kota_agunan.setFocusable(false);
        et_propinsi_agunan.setFocusable(false);

        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }

        return view;
    }

    private void setDisable(){
        et_lokasi_tanah.setEnabled(false);
        et_kodepos_agunan.setEnabled(false);
        et_kelurahan_agunan.setEnabled(false);
        et_kecamatan_agunan.setEnabled(false);
        et_kota_agunan.setEnabled(false);
        et_propinsi_agunan.setEnabled(false);
        et_batas_utara.setEnabled(false);
        et_batas_selatan.setEnabled(false);
        et_batas_barat.setEnabled(false);
        et_batas_timur.setEnabled(false);
        et_kavblok.setEnabled(false);
        et_rt_agunan.setEnabled(false);
        et_rw_agunan.setEnabled(false);
        btn_dom.setVisibility(View.GONE);
        btn_set_loc.setVisibility(View.GONE);
    }
    private void setData(){
        try {
            et_lokasi_tanah.setText(dataAgunan.getLokasiTanah());
            et_kodepos_agunan.setText(dataAgunan.getKodePos());
            et_kelurahan_agunan.setText(dataAgunan.getKelurahan());
            et_kecamatan_agunan.setText(dataAgunan.getKecamatan());
            et_kota_agunan.setText(dataAgunan.getKota());
            et_propinsi_agunan.setText(dataAgunan.getPropinsi());
            tvSetLoc.setText(dataAgunan.getKoordinat());
            et_batas_utara.setText(dataAgunan.getBatasUtaraTanah());
            et_batas_selatan.setText(dataAgunan.getBatasSelatanTanah());
            et_batas_barat.setText(dataAgunan.getBatasBaratTanah());
            et_batas_timur.setText(dataAgunan.getBatasTimurTanah());
            et_kavblok.setText(dataAgunan.getKavBlok());
            et_rt_agunan.setText(dataAgunan.getrT());
            et_rw_agunan.setText(dataAgunan.getrW());
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

