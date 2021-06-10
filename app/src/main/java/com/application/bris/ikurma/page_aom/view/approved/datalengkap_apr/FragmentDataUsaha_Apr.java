package com.application.bris.ikurma.page_aom.view.approved.datalengkap_apr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.model.DataLengkap;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataUsaha_Apr extends Fragment implements Step{

    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;

    @BindView(R.id.tf_namausaha)
    TextFieldBoxes tf_namausaha;
    @BindView(R.id.et_namausaha)
    EditText et_namausaha;

    @BindView(R.id.tf_tanggalmulaiusaha)
    TextFieldBoxes tf_tanggalmulaiusaha;
    @BindView(R.id.et_tanggalmulaiusaha)
    EditText et_tanggalmulaiusaha;

    @BindView(R.id.tf_nomortelponusaha)
    TextFieldBoxes tf_nomortelponusaha;
    @BindView(R.id.et_nomortelponusaha)
    EditText et_nomortelponusaha;

    //USAHA
    @BindView(R.id.sw_usahaktpsama)
    Switch sw_usahaktpsama;
    @BindView(R.id.ll_alamatusaha)
    LinearLayout ll_alamatusaha;
    @BindView(R.id.tf_alamatusaha)
    TextFieldBoxes tf_alamatusaha;
    @BindView(R.id.et_alamatusaha)
    EditText et_alamatusaha;
    @BindView(R.id.tf_rtusaha)
    TextFieldBoxes tf_rtusaha;
    @BindView(R.id.et_rtusaha)
    EditText et_rtusaha;
    @BindView(R.id.tf_rwusaha)
    TextFieldBoxes tf_rwusaha;
    @BindView(R.id.et_rwusaha)
    EditText et_rwusaha;
    @BindView(R.id.tf_provinsiusaha)
    TextFieldBoxes tf_provinsiusaha;
    @BindView(R.id.et_provinsiusaha)
    EditText et_provinsiusaha;
    @BindView(R.id.tf_kotausaha)
    TextFieldBoxes tf_kotausaha;
    @BindView(R.id.et_kotausaha)
    EditText et_kotausaha;
    @BindView(R.id.tf_kecamatanusaha)
    TextFieldBoxes tf_kecamatanusaha;
    @BindView(R.id.et_kecamatanusaha)
    EditText et_kecamatanusaha;
    @BindView(R.id.tf_kelurahanusaha)
    TextFieldBoxes tf_kelurahanusaha;
    @BindView(R.id.et_kelurahanusaha)
    EditText et_kelurahanusaha;
    @BindView(R.id.tf_kodeposusaha)
    TextFieldBoxes tf_kodeposusaha;
    @BindView(R.id.et_kodeposusaha)
    EditText et_kodeposusaha;
    @BindView(R.id.btn_usaha)
    Button btn_usaha;

    private DataLengkap dataLengkap;
    @SuppressLint("ValidFragment")
    public FragmentDataUsaha_Apr(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_datausaha, container, false);
        ButterKnife.bind(this, view);
        setDisable();
        setData();
        return view;
    }

    public void setDisable(){
        et_bidangusaha.setEnabled(false);
        et_namausaha.setEnabled(false);
        et_tanggalmulaiusaha.setEnabled(false);
        et_nomortelponusaha.setEnabled(false);
        sw_usahaktpsama.setVisibility(View.GONE);
        et_alamatusaha.setEnabled(false);
        et_rtusaha.setEnabled(false);
        et_rwusaha.setEnabled(false);
        et_provinsiusaha.setEnabled(false);
        et_kotausaha.setEnabled(false);
        et_kecamatanusaha.setEnabled(false);
        et_kelurahanusaha.setEnabled(false);
        et_kodeposusaha.setEnabled(false);
        btn_usaha.setVisibility(View.GONE);
    }

    private void setData() {
        et_bidangusaha.setText(KeyValue.getKeyUsahaorJob(dataLengkap.getBidangUsaha()));
        et_namausaha.setText(dataLengkap.getNamaUsaha());
        et_tanggalmulaiusaha.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglMulaiUsaha(), "ddMMyyyy", "dd-MM-yyyy"));
        et_nomortelponusaha.setText(dataLengkap.getNoTelpUsaha());
        et_alamatusaha.setText(dataLengkap.getAlamatUsaha());
        et_rtusaha.setText(dataLengkap.getRtUsaha());
        et_rwusaha.setText(dataLengkap.getRwUsaha());
        et_provinsiusaha.setText(dataLengkap.getProvUsaha());
        et_kotausaha.setText(dataLengkap.getKotaUsaha());
        et_kecamatanusaha.setText(dataLengkap.getKecUsaha());
        et_kelurahanusaha.setText(dataLengkap.getKelUsaha());
        et_kodeposusaha.setText(dataLengkap.getKodePosUsaha());
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
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }
}
