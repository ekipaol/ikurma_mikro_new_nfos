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
public class FragmentDataAlamat_Apr extends Fragment implements Step{

    //KTP
    @BindView(R.id.tf_alamatktp)
    TextFieldBoxes tf_alamatktp;
    @BindView(R.id.et_alamatktp)
    EditText et_alamatktp;
    @BindView(R.id.tf_rtktp)
    TextFieldBoxes tf_rtktp;
    @BindView(R.id.et_rtktp)
    EditText et_rtktp;
    @BindView(R.id.tf_rwktp)
    TextFieldBoxes tf_rwktp;
    @BindView(R.id.et_rwktp)
    EditText et_rwktp;
    @BindView(R.id.tf_provinsiktp)
    TextFieldBoxes tf_provinsiktp;
    @BindView(R.id.et_provinsiktp)
    EditText et_provinsiktp;
    @BindView(R.id.tf_kotaktp)
    TextFieldBoxes tf_kotaktp;
    @BindView(R.id.et_kotaktp)
    EditText et_kotaktp;
    @BindView(R.id.tf_kecamatanktp)
    TextFieldBoxes tf_kecamatanktp;
    @BindView(R.id.et_kecamatanktp)
    EditText et_kecamatanktp;
    @BindView(R.id.tf_kelurahanktp)
    TextFieldBoxes tf_kelurahanktp;
    @BindView(R.id.et_kelurahanktp)
    EditText et_kelurahanktp;
    @BindView(R.id.tf_kodeposktp)
    TextFieldBoxes tf_kodeposktp;
    @BindView(R.id.et_kodeposktp)
    EditText et_kodeposktp;
    @BindView(R.id.btn_ktp)
    Button btn_ktp;

    //DOMISILI
    @BindView(R.id.sw_domisiliktpsama)
    Switch sw_domisiliktpsama;
    @BindView(R.id.tf_statusdom)
    TextFieldBoxes tf_statusdom;
    @BindView(R.id.et_statusdom)
    EditText et_statusdom;
    @BindView(R.id.tf_lamadom)
    TextFieldBoxes tf_lamadom;
    @BindView(R.id.et_lamadom)
    EditText et_lamadom;
    @BindView(R.id.ll_alamatdom)
    LinearLayout ll_alamatdom;
    @BindView(R.id.tf_alamatdom)
    TextFieldBoxes tf_alamatdom;
    @BindView(R.id.et_alamatdom)
    EditText et_alamatdom;
    @BindView(R.id.tf_rtdom)
    TextFieldBoxes tf_rtdom;
    @BindView(R.id.et_rtdom)
    EditText et_rtdom;
    @BindView(R.id.tf_rwdom)
    TextFieldBoxes tf_rwdom;
    @BindView(R.id.et_rwdom)
    EditText et_rwdom;
    @BindView(R.id.tf_provinsidom)
    TextFieldBoxes tf_provinsidom;
    @BindView(R.id.et_provinsidom)
    EditText et_provinsidom;
    @BindView(R.id.tf_kotadom)
    TextFieldBoxes tf_kotadom;
    @BindView(R.id.et_kotadom)
    EditText et_kotadom;
    @BindView(R.id.tf_kecamatandom)
    TextFieldBoxes tf_kecamatandom;
    @BindView(R.id.et_kecamatandom)
    EditText et_kecamatandom;
    @BindView(R.id.tf_kelurahandom)
    TextFieldBoxes tf_kelurahandom;
    @BindView(R.id.et_kelurahandom)
    EditText et_kelurahandom;
    @BindView(R.id.tf_kodeposdom)
    TextFieldBoxes tf_kodeposdom;
    @BindView(R.id.et_kodeposdom)
    EditText et_kodeposdom;
    @BindView(R.id.btn_dom)
    Button btn_dom;
    private DataLengkap dataLengkap;
    public FragmentDataAlamat_Apr(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_dataalamat, container, false);
        ButterKnife.bind(this, view);
        setDisable();
        setData();
        return view;
    }

    public void setDisable(){
        et_alamatktp.setEnabled(false);
        et_rtktp.setEnabled(false);
        et_rwktp.setEnabled(false);
        et_provinsiktp.setEnabled(false);
        et_kotaktp.setEnabled(false);
        et_kecamatanktp.setEnabled(false);
        et_kelurahanktp.setEnabled(false);
        et_kodeposktp.setEnabled(false);
        btn_ktp.setVisibility(View.GONE);
        sw_domisiliktpsama.setVisibility(View.GONE);
        et_statusdom.setEnabled(false);
        et_lamadom.setEnabled(false);
        et_alamatdom.setEnabled(false);
        et_rtdom.setEnabled(false);
        et_rwdom.setEnabled(false);
        et_provinsidom.setEnabled(false);
        et_kotadom.setEnabled(false);
        et_kecamatandom.setEnabled(false);
        et_kelurahandom.setEnabled(false);
        et_kodeposdom.setEnabled(false);
        btn_dom.setVisibility(View.GONE);
    }

    public void setData(){
            et_alamatktp.setText(dataLengkap.getAlamatId());
            et_rtktp.setText(dataLengkap.getRtId());
            et_rwktp.setText(dataLengkap.getRwId());
            et_provinsiktp.setText(dataLengkap.getProvId());
            et_kotaktp.setText(dataLengkap.getKotaId());
            et_kecamatanktp.setText(dataLengkap.getKecId());
            et_kelurahanktp.setText(dataLengkap.getKelId());
            et_kodeposktp.setText(dataLengkap.getKodePosId());

            et_statusdom.setText(KeyValue.getKeyStatusTempatDomisili(dataLengkap.getStatusTptTinggal()));
            et_lamadom.setText(String.valueOf(dataLengkap.getLamaMenetap()));
            et_alamatdom.setText(dataLengkap.getAlamatDom());
            et_rtdom.setText(dataLengkap.getRtDom());
            et_rwdom.setText(dataLengkap.getRwDom());
            et_provinsidom.setText(dataLengkap.getProvDom());
            et_kotadom.setText(dataLengkap.getKotaDom());
            et_kecamatandom.setText(dataLengkap.getKecDom());
            et_kelurahandom.setText(dataLengkap.getKelDom());
            et_kodeposdom.setText(dataLengkap.getKodePosDom());
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
