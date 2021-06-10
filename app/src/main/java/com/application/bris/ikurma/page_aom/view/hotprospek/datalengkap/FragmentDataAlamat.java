package com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma.page_aom.listener.AddressListener;
import com.application.bris.ikurma.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma.page_aom.model.DataLengkap;
import com.application.bris.ikurma.page_aom.model.address;
import com.application.bris.ikurma.page_aom.model.keyvalue;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataAlamat extends Fragment implements Step, KeyValueListener, AddressListener, View.OnClickListener, View.OnFocusChangeListener {

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

    private Realm realm;

    private DataLengkap dataLengkap;

    private String val_AlamatId ="";
    private String val_RtId ="";
    private String val_RwId ="";
    private String val_ProvId ="";
    private String val_KotaId ="";
    private String val_KecId ="";
    private String val_KelId ="";
    private String val_KodePosId ="";
    private String val_StatusTptTinggal ="";
    private int val_LamaMenetap = 0;
    private String val_AlamatDom ="";
    private String val_RtDom ="";
    private String val_RwDom ="";
    private String val_ProvDom ="";
    private String val_KotaDom ="";
    private String val_KecDom ="";
    private String val_KelDom ="";
    private String val_KodePosDom ="";

    private int val_domAsId = 0;
    private String btnAddressString = "";

    public FragmentDataAlamat(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_dataalamat, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        sw_domisiliktpsama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ll_alamatdom.setVisibility(View.GONE);
                    val_domAsId = 1;
                }
                else {
                    ll_alamatdom.setVisibility(View.VISIBLE);
                    val_domAsId = 0;
                }
            }
        });
        btn_ktp.setOnClickListener(this);
        btn_dom.setOnClickListener(this);
        setData();
        onSelectDialog();


        return view;
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

    private void onSelectDialog(){
        //STATUS DOMISILI
        et_statusdom.setFocusable(false);
        et_statusdom.setInputType(InputType.TYPE_NULL);
        et_statusdom.setOnFocusChangeListener(this);
        tf_statusdom.setOnClickListener(this);
        tf_statusdom.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_statusdom.getLabelText().toString().trim());
            }
        });

        et_provinsiktp.setInputType(InputType.TYPE_NULL);
        et_provinsiktp.setFocusable(false);
        et_kotaktp.setInputType(InputType.TYPE_NULL);
        et_kotaktp.setFocusable(false);
        et_kecamatanktp.setInputType(InputType.TYPE_NULL);
        et_kecamatanktp.setFocusable(false);
        et_kelurahanktp.setInputType(InputType.TYPE_NULL);
        et_kelurahanktp.setFocusable(false);
        et_kodeposktp.setInputType(InputType.TYPE_NULL);
        et_kodeposktp.setFocusable(false);

        et_provinsidom.setInputType(InputType.TYPE_NULL);
        et_provinsidom.setFocusable(false);
        et_kotadom.setInputType(InputType.TYPE_NULL);
        et_kotadom.setFocusable(false);
        et_kecamatandom.setInputType(InputType.TYPE_NULL);
        et_kecamatandom.setFocusable(false);
        et_kelurahandom.setInputType(InputType.TYPE_NULL);
        et_kelurahandom.setFocusable(false);
        et_kodeposdom.setInputType(InputType.TYPE_NULL);
        et_kodeposdom.setFocusable(false);
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void openAddressDialog(){
        DialogAddress.display(getFragmentManager(), this);
    }

    private VerificationError validateForm(){
        //KTP
        if (et_alamatktp.getText().toString().isEmpty() || et_alamatktp.getText().toString().equalsIgnoreCase("")){
            tf_alamatktp.setError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_rtktp.getText().toString().isEmpty() || et_rtktp.getText().toString().equalsIgnoreCase("")){
            tf_rtktp.setError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rwktp.getText().toString().isEmpty() || et_rwktp.getText().toString().equalsIgnoreCase("")){
            tf_rwktp.setError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_provinsiktp.getText().toString().isEmpty() || et_provinsiktp.getText().toString().equalsIgnoreCase("")){
            tf_provinsiktp.setError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotaktp.getText().toString().isEmpty() || et_kotaktp.getText().toString().equalsIgnoreCase("")){
            tf_kotaktp.setError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatanktp.getText().toString().isEmpty() || et_kecamatanktp.getText().toString().equalsIgnoreCase("")){
            tf_kecamatanktp.setError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahanktp.getText().toString().isEmpty() || et_kelurahanktp.getText().toString().equalsIgnoreCase("")){
            tf_kelurahanktp.setError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposktp.getText().toString().isEmpty() || et_kodeposktp.getText().toString().equalsIgnoreCase("")){
            tf_kodeposktp.setError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }



        //DOMISILI
        else if (et_statusdom.getText().toString().isEmpty() || et_statusdom.getText().toString().equalsIgnoreCase("")){
            tf_statusdom.setError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_lamadom.getText().toString().isEmpty() || et_lamadom.getText().toString().equalsIgnoreCase("")){
            tf_lamadom.setError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_alamatdom.getText().toString().isEmpty() || et_alamatdom.getText().toString().equalsIgnoreCase("")){
            tf_alamatdom.setError("Format "+ tf_alamatdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_rtdom.getText().toString().isEmpty() || et_rtdom.getText().toString().equalsIgnoreCase("")){
            tf_rtdom.setError("Format "+ tf_rtdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rtdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rwdom.getText().toString().isEmpty() || et_rwdom.getText().toString().equalsIgnoreCase("")){
            tf_rwdom.setError("Format "+ tf_rwdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rwdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_provinsidom.getText().toString().isEmpty() || et_provinsidom.getText().toString().equalsIgnoreCase("")){
            tf_provinsidom.setError("Format "+ tf_provinsidom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsidom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotadom.getText().toString().isEmpty() || et_kotadom.getText().toString().equalsIgnoreCase("")){
            tf_kotadom.setError("Format "+ tf_kotadom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotadom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatandom.getText().toString().isEmpty() || et_kecamatandom.getText().toString().equalsIgnoreCase("")){
            tf_kecamatandom.setError("Format "+ tf_kecamatandom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatandom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahandom.getText().toString().isEmpty() || et_kelurahandom.getText().toString().equalsIgnoreCase("")){
            tf_kelurahandom.setError("Format "+ tf_kelurahandom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahandom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposdom.getText().toString().isEmpty() || et_kodeposdom.getText().toString().equalsIgnoreCase("")){
            tf_kodeposdom.setError("Format "+ tf_kodeposdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    private VerificationError validateFormDomasId(){
        //KTP
        if (et_alamatktp.getText().toString().isEmpty() || et_alamatktp.getText().toString().equalsIgnoreCase("")){
            tf_alamatktp.setError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_rtktp.getText().toString().isEmpty() || et_rtktp.getText().toString().equalsIgnoreCase("")){
            tf_rtktp.setError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rwktp.getText().toString().isEmpty() || et_rwktp.getText().toString().equalsIgnoreCase("")){
            tf_rwktp.setError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_provinsiktp.getText().toString().isEmpty() || et_provinsiktp.getText().toString().equalsIgnoreCase("")){
            tf_provinsiktp.setError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotaktp.getText().toString().isEmpty() || et_kotaktp.getText().toString().equalsIgnoreCase("")){
            tf_kotaktp.setError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatanktp.getText().toString().isEmpty() || et_kecamatanktp.getText().toString().equalsIgnoreCase("")){
            tf_kecamatanktp.setError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahanktp.getText().toString().isEmpty() || et_kelurahanktp.getText().toString().equalsIgnoreCase("")){
            tf_kelurahanktp.setError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposktp.getText().toString().isEmpty() || et_kodeposktp.getText().toString().equalsIgnoreCase("")){
            tf_kodeposktp.setError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        //DOMISILI
        else if (et_statusdom.getText().toString().isEmpty() || et_statusdom.getText().toString().equalsIgnoreCase("")){
            tf_statusdom.setError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_lamadom.getText().toString().isEmpty() || et_lamadom.getText().toString().equalsIgnoreCase("")){
            tf_lamadom.setError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){

        val_AlamatId = (et_alamatktp.getText().toString().trim());
        val_RtId = (et_rtktp.getText().toString().trim());
        val_RwId = (et_rwktp.getText().toString().trim());
        val_ProvId = (et_provinsiktp.getText().toString().trim());
        val_KotaId = (et_kotaktp.getText().toString().trim());
        val_KecId = (et_kecamatanktp.getText().toString().trim());
        val_KelId = (et_kelurahanktp.getText().toString().trim());
        val_KodePosId = (et_kodeposktp.getText().toString().trim());
        val_StatusTptTinggal = (KeyValue.getTypeStatusTempatDomisili(et_statusdom.getText().toString().trim()));
        val_LamaMenetap = Integer.parseInt(et_lamadom.getText().toString().trim());

        if (val_domAsId == 1){
            val_AlamatDom = (et_alamatktp.getText().toString().trim());
            val_RtDom = (et_rtktp.getText().toString().trim());
            val_RwDom = (et_rwktp.getText().toString().trim());
            val_ProvDom = (et_provinsiktp.getText().toString().trim());
            val_KotaDom = (et_kotaktp.getText().toString().trim());
            val_KecDom = (et_kecamatanktp.getText().toString().trim());
            val_KelDom = (et_kelurahanktp.getText().toString().trim());
            val_KodePosDom = (et_kodeposktp.getText().toString().trim());
        }
        else {
            val_AlamatDom = (et_alamatdom.getText().toString().trim());
            val_RtDom = (et_rtdom.getText().toString().trim());
            val_RwDom = (et_rwdom.getText().toString().trim());
            val_ProvDom = (et_provinsidom.getText().toString().trim());
            val_KotaDom = (et_kotadom.getText().toString().trim());
            val_KecDom = (et_kecamatandom.getText().toString().trim());
            val_KelDom = (et_kelurahandom.getText().toString().trim());
            val_KodePosDom = (et_kodeposdom.getText().toString().trim());
        }

        final DataLengkapPojo d = new DataLengkapPojo();
        d.setAlamatId(val_AlamatId);
        d.setRtId(val_RtId);
        d.setRwId(val_RwId);
        d.setProvId(val_ProvId);
        d.setKotaId(val_KotaId);
        d.setKecId(val_KecId);
        d.setKelId(val_KelId);
        d.setKodePosId(val_KodePosId);
        d.setStatusTptTinggal(val_StatusTptTinggal);
        d.setLamaMenetap(val_LamaMenetap);
        d.setAlamatDom(val_AlamatDom);
        d.setRtDom(val_RtDom);
        d.setRwDom(val_RwDom);
        d.setProvDom(val_ProvDom);
        d.setKotaDom(val_KotaDom);
        d.setKecDom(val_KecDom);
        d.setKelDom(val_KelDom);
        d.setKodePosDom(val_KodePosDom);

        DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivity.idAplikasi).findFirst();

        d.setCif(data.getCif());
        d.setUid(data.getUid());
        d.setIdAplikasi(data.getIdAplikasi());
        d.setNamaAlias(data.getNamaAlias());
        d.setNoKtpPasangan(data.getNoKtpPasangan());
        d.setStatusNikah(data.getStatusNikah());
        d.setNoHp(data.getNoHp());
        d.setNamaNasabah(data.getNamaNasabah());
        d.setNpwp(data.getNpwp());
        d.setPendTerakhir(data.getPendTerakhir());
        d.setKetGelar(data.getKetGelar());
        d.setKetAgama(data.getKetAgama());
        d.setAgama(data.getAgama());
        d.setNamaIbu(data.getNamaIbu());
        d.setNamaPasangan(data.getNamaPasangan());
        d.setEmail(data.getEmail());
        d.setTelpKeluarga(data.getTelpKeluarga());
        d.setExpId(data.getExpId());
        d.setTglLahirPasangan(data.getTglLahirPasangan());
        d.setNoKtp(data.getNoKtp());
        d.setJlhTanggungan(data.getJlhTanggungan());
        d.setTglLahir(data.getTglLahir());
        d.setKeluarga(data.getKeluarga());
        d.setTptLahir(data.getTptLahir());
        d.setTipePendapatan(data.getTipePendapatan());
        d.setJenkel(data.getJenkel());

        realm.executeTransaction (new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(d);
            }
        });
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (val_domAsId == 1){
            return validateFormDomasId();
        }
        else{
            return validateForm();
        }
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //STATUS DOMISILI
            case R.id.et_statusdom:
            case R.id.tf_statusdom:
                openKeyValueDialog(tf_statusdom.getLabelText().toString().trim());
                break;

            case R.id.btn_ktp:
                btnAddressString = "ktp";
                openAddressDialog();
                break;

            case R.id.btn_dom:
                btnAddressString = "dom";
                openAddressDialog();
                break;

        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_statusdom:
                    openKeyValueDialog(tf_statusdom.getLabelText().toString().trim());
                    break;
            }
        }
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("status tempat domisili")){
            et_statusdom.setText(data.getName());
        }
    }

    @Override
    public void onAddressSelect(address data) {
        if (btnAddressString.equalsIgnoreCase("ktp")){
            et_provinsiktp.setText(data.getProvinsi());
            et_kotaktp.setText(data.getKota());
            et_kecamatanktp.setText(data.getKecamatan());
            et_kelurahanktp.setText(data.getKelurahan());
            et_kodeposktp.setText(data.getKodepos());
        }
        else{
            et_provinsidom.setText(data.getProvinsi());
            et_kotadom.setText(data.getKota());
            et_kecamatandom.setText(data.getKecamatan());
            et_kelurahandom.setText(data.getKelurahan());
            et_kodeposdom.setText(data.getKodepos());
        }
    }
}
