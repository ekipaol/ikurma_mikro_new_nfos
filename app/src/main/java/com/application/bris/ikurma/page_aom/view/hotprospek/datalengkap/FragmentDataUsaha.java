package com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataUsaha extends Fragment implements Step, KeyValueListener, AddressListener, View.OnClickListener, View.OnFocusChangeListener {

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

    private Realm realm;

    private DataLengkap dataLengkap;
    private Calendar calTanggalMulaiUsaha;
    private DatePickerDialog dpTanggalMulaiUsaha;

    private String val_BidangUsaha ="";
    private String val_NamaUsaha ="";
    private String val_TglMulaiUsaha ="";
    private String val_NoTelpUsaha ="";
    private String val_AlamatUsaha ="";
    private String val_RtUsaha ="";
    private String val_RwUsaha ="";
    private String val_ProvUsaha ="";
    private String val_KotaUsaha ="";
    private String val_KecUsaha ="";
    private String val_KelUsaha ="";
    private String val_KodePosUsaha ="";
    private int val_usahaAsId = 0;

    @SuppressLint("ValidFragment")
    public FragmentDataUsaha(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_datausaha, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        sw_usahaktpsama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ll_alamatusaha.setVisibility(View.GONE);
                    val_usahaAsId = 1;
                }
                else {
                    ll_alamatusaha.setVisibility(View.VISIBLE);
                    val_usahaAsId = 0;
                }
            }
        });
        btn_usaha.setOnClickListener(this);
        setData();
        onSelectDialog();

        return view;
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

    private void onSelectDialog() {
        //BIDANG USAHA
        et_bidangusaha.setFocusable(false);
        et_bidangusaha.setInputType(InputType.TYPE_NULL);
        et_bidangusaha.setOnClickListener(this);
        et_bidangusaha.setOnFocusChangeListener(this);
        tf_bidangusaha.setOnClickListener(this);
        tf_bidangusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_bidangusaha.getLabelText().toString().trim());
            }
        });

        //EXPIRED NIK
        et_tanggalmulaiusaha.setFocusable(false);
        et_tanggalmulaiusaha.setInputType(InputType.TYPE_NULL);
        et_tanggalmulaiusaha.setOnFocusChangeListener(this);
        tf_tanggalmulaiusaha.setOnClickListener(this);
        tf_tanggalmulaiusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalMulaiUsaha();
            }
        });

        et_provinsiusaha.setInputType(InputType.TYPE_NULL);
        et_provinsiusaha.setFocusable(false);
        et_kotausaha.setInputType(InputType.TYPE_NULL);
        et_kotausaha.setFocusable(false);
        et_kecamatanusaha.setInputType(InputType.TYPE_NULL);
        et_kecamatanusaha.setFocusable(false);
        et_kelurahanusaha.setInputType(InputType.TYPE_NULL);
        et_kelurahanusaha.setFocusable(false);
        et_kodeposusaha.setInputType(InputType.TYPE_NULL);
        et_kodeposusaha.setFocusable(false);
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void openAddressDialog(){
        DialogAddress.display(getFragmentManager(), this);
    }

    private void dpTanggalMulaiUsaha(){
        calTanggalMulaiUsaha = Calendar.getInstance();
        Calendar calMin = Calendar.getInstance();
        calMin.add(Calendar.MONTH, -1);
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalMulaiUsaha.set(Calendar.YEAR, year);
                calTanggalMulaiUsaha.set(Calendar.MONTH, month);
                calTanggalMulaiUsaha.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calMulaiUsahaString = FragmentDataPribadi.dateClient.format(calTanggalMulaiUsaha.getTime());
                et_tanggalmulaiusaha.setText(calMulaiUsahaString);
            }
        };

        dpTanggalMulaiUsaha = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalMulaiUsaha.get(Calendar.YEAR),
                calTanggalMulaiUsaha.get(Calendar.MONTH), calTanggalMulaiUsaha.get(Calendar.DAY_OF_MONTH));
        dpTanggalMulaiUsaha.getDatePicker().setMaxDate(calMin.getTimeInMillis());
        dpTanggalMulaiUsaha.show();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (val_usahaAsId == 1){
            return validateFormUsahaasId();
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
            case R.id.et_bidangusaha:
            case R.id.tf_bidangusaha:
                openKeyValueDialog(tf_bidangusaha.getLabelText().toString().trim());
                break;

            //TANGGAL MULAI USAHA
            case R.id.et_tanggalmulaiusaha:
            case R.id.tf_tanggalmulaiusaha:
                dpTanggalMulaiUsaha();
                break;

            case R.id.btn_usaha:
                openAddressDialog();
                break;
        }
    }

    private VerificationError validateForm(){

        if (et_bidangusaha.getText().toString().isEmpty() || et_bidangusaha.getText().toString().equalsIgnoreCase("")){
            tf_bidangusaha.setError("Format "+ tf_bidangusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_bidangusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namausaha.getText().toString().isEmpty() || et_namausaha.getText().toString().equalsIgnoreCase("")){
            tf_namausaha.setError("Format "+ tf_namausaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namausaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tanggalmulaiusaha.getText().toString().isEmpty() || et_tanggalmulaiusaha.getText().toString().equalsIgnoreCase("")){
            tf_tanggalmulaiusaha.setError("Format "+ tf_tanggalmulaiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggalmulaiusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomortelponusaha.getText().toString().isEmpty() || et_nomortelponusaha.getText().toString().equalsIgnoreCase("")){
            tf_nomortelponusaha.setError("Format "+ tf_nomortelponusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomortelponusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }


        //usaha
        else if (et_alamatusaha.getText().toString().isEmpty() || et_alamatusaha.getText().toString().equalsIgnoreCase("")){
            tf_alamatusaha.setError("Format "+ tf_alamatusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_rtusaha.getText().toString().isEmpty() || et_rtusaha.getText().toString().equalsIgnoreCase("")){
            tf_rtusaha.setError("Format "+ tf_rtusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rtusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rwusaha.getText().toString().isEmpty() || et_rwusaha.getText().toString().equalsIgnoreCase("")){
            tf_rwusaha.setError("Format "+ tf_rwusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rwusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_provinsiusaha.getText().toString().isEmpty() || et_provinsiusaha.getText().toString().equalsIgnoreCase("")){
            tf_provinsiusaha.setError("Format "+ tf_provinsiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsiusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotausaha.getText().toString().isEmpty() || et_kotausaha.getText().toString().equalsIgnoreCase("")){
            tf_kotausaha.setError("Format "+ tf_kotausaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotausaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatanusaha.getText().toString().isEmpty() || et_kecamatanusaha.getText().toString().equalsIgnoreCase("")){
            tf_kecamatanusaha.setError("Format "+ tf_kecamatanusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatanusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahanusaha.getText().toString().isEmpty() || et_kelurahanusaha.getText().toString().equalsIgnoreCase("")){
            tf_kelurahanusaha.setError("Format "+ tf_kelurahanusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahanusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposusaha.getText().toString().isEmpty() || et_kodeposusaha.getText().toString().equalsIgnoreCase("")){
            tf_kodeposusaha.setError("Format "+ tf_kodeposusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else{
            setDataOnListerner();
            return null;
        }
    }


    private VerificationError validateFormUsahaasId(){
        if (et_bidangusaha.getText().toString().isEmpty() || et_bidangusaha.getText().toString().equalsIgnoreCase("")){
            tf_bidangusaha.setError("Format "+ tf_bidangusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_bidangusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namausaha.getText().toString().isEmpty() || et_namausaha.getText().toString().equalsIgnoreCase("")){
            tf_namausaha.setError("Format "+ tf_namausaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namausaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tanggalmulaiusaha.getText().toString().isEmpty() || et_tanggalmulaiusaha.getText().toString().equalsIgnoreCase("")){
            tf_tanggalmulaiusaha.setError("Format "+ tf_tanggalmulaiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggalmulaiusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomortelponusaha.getText().toString().isEmpty() || et_nomortelponusaha.getText().toString().equalsIgnoreCase("")){
            tf_nomortelponusaha.setError("Format "+ tf_nomortelponusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomortelponusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){

        DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivity.idAplikasi).findFirst();

        val_BidangUsaha = (KeyValue.getTypeUsahaorJob(et_bidangusaha.getText().toString().trim()));
        val_NamaUsaha = (et_namausaha.getText().toString().trim());
        val_TglMulaiUsaha = (AppUtil.parseTanggalGeneral(et_tanggalmulaiusaha.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_NoTelpUsaha = (et_nomortelponusaha.getText().toString().trim());
        if (val_usahaAsId == 1){
            val_AlamatUsaha = data.getAlamatId();
            val_RtUsaha = data.getRtId();
            val_RwUsaha = data.getRwId();
            val_ProvUsaha = data.getProvId();
            val_KotaUsaha = data.getKotaId();
            val_KecUsaha = data.getKecId();
            val_KelUsaha = data.getKelId();
            val_KodePosUsaha = data.getKodePosId();
        }
        if (val_usahaAsId == 0){
            val_AlamatUsaha = (et_alamatusaha.getText().toString().trim());
            val_RtUsaha = (et_rtusaha.getText().toString().trim());
            val_RwUsaha = (et_rwusaha.getText().toString().trim());
            val_ProvUsaha = (et_provinsiusaha.getText().toString().trim());
            val_KotaUsaha = (et_kotausaha.getText().toString().trim());
            val_KecUsaha = (et_kecamatanusaha.getText().toString().trim());
            val_KelUsaha = (et_kelurahanusaha.getText().toString().trim());
            val_KodePosUsaha = (et_kodeposusaha.getText().toString().trim());
        }

        final DataLengkapPojo d = new DataLengkapPojo();
        d.setBidangUsaha(val_BidangUsaha);
        d.setNamaUsaha(val_NamaUsaha);
        d.setTglMulaiUsaha(val_TglMulaiUsaha);
        d.setNoTelpUsaha(val_NoTelpUsaha);
        d.setAlamatUsaha(val_AlamatUsaha);
        d.setRtUsaha(val_RtUsaha);
        d.setRwUsaha(val_RwUsaha);
        d.setProvUsaha(val_ProvUsaha);
        d.setKotaUsaha(val_KotaUsaha);
        d.setKecUsaha(val_KecUsaha);
        d.setKelUsaha(val_KelUsaha);
        d.setKodePosUsaha(val_KodePosUsaha);

        d.setAlamatId(data.getAlamatId());
        d.setRtId(data.getRtId());
        d.setRwId(data.getRwId());
        d.setProvId(data.getProvId());
        d.setKotaId(data.getKotaId());
        d.setKecId(data.getKecId());
        d.setKelId(data.getKelId());
        d.setKodePosId(data.getKodePosId());
        d.setStatusTptTinggal(data.getStatusTptTinggal());
        d.setLamaMenetap(data.getLamaMenetap());
        d.setAlamatDom(data.getAlamatDom());
        d.setRtDom(data.getRtDom());
        d.setRwDom(data.getRwDom());
        d.setProvDom(data.getProvDom());
        d.setKotaDom(data.getKotaDom());
        d.setKecDom(data.getKecDom());
        d.setKelDom(data.getKelDom());
        d.setKodePosDom(data.getKodePosDom());

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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_bidangusaha:
                    openKeyValueDialog(tf_bidangusaha.getLabelText().toString().trim());
                    break;
            }
        }
    }

    @Override
    public void onAddressSelect(address data) {
        et_provinsiusaha.setText(data.getProvinsi());
        et_kotausaha.setText(data.getKota());
        et_kecamatanusaha.setText(data.getKecamatan());
        et_kelurahanusaha.setText(data.getKelurahan());
        et_kodeposusaha.setText(data.getKodepos());
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("bidang usaha")){
            et_bidangusaha.setText(data.getName());
        }
    }
}
