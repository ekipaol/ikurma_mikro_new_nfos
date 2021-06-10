package com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseDataDukcapil;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.ceknasabah.cekNasabah;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.inquiryNikPasangan;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma.model.hotprospek_multifaedahmikro.DataDukcapilPasangan;
import com.application.bris.ikurma.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma.page_aom.model.DataLengkap;
import com.application.bris.ikurma.page_aom.model.datapribadi;
import com.application.bris.ikurma.page_aom.model.keyvalue;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.Constants;
import com.application.bris.ikurma.util.KeyValue;
import com.application.bris.ikurma.util.NpwpTextWatcher;
import com.application.bris.ikurma.util.Validator;
import com.google.gson.Gson;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataPribadi extends Fragment implements Step, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener {

    @BindView(R.id.tf_nik)
    TextFieldBoxes tf_nik;
    @BindView(R.id.et_nik)
    EditText et_nik;
    @BindView(R.id.tf_expirednik)
    TextFieldBoxes tf_expirednik;
    @BindView(R.id.et_expirednik)
    EditText et_expirednik;
    @BindView(R.id.tf_npwp)
    TextFieldBoxes tf_npwp;
    @BindView(R.id.et_npwp)
    EditText et_npwp;
    @BindView(R.id.tf_nama)
    TextFieldBoxes tf_nama;
    @BindView(R.id.et_nama)
    EditText et_nama;
    @BindView(R.id.tf_namaalias)
    TextFieldBoxes tf_namaalias;
    @BindView(R.id.et_namaalias)
    EditText et_namaalias;
    @BindView(R.id.tf_tempatlahir)
    TextFieldBoxes tf_tempatlahir;
    @BindView(R.id.et_tempatlahir)
    EditText et_tempatlahir;
    @BindView(R.id.tf_tanggallahir)
    TextFieldBoxes tf_tanggallahir;
    @BindView(R.id.et_tanggallahir)
    EditText et_tanggallahir;
    @BindView(R.id.tf_jeniskelamin)
    TextFieldBoxes tf_jeniskelamin;
    @BindView(R.id.et_jeniskelamin)
    EditText et_jeniskelamin;
    @BindView(R.id.tf_nomorhp)
    TextFieldBoxes tf_nomorhp;
    @BindView(R.id.et_nomorhp)
    EditText et_nomorhp;
    @BindView(R.id.tf_email)
    TextFieldBoxes tf_email;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.tf_agama)
    TextFieldBoxes tf_agama;
    @BindView(R.id.et_agama)
    EditText et_agama;
    @BindView(R.id.tf_ketagama)
    TextFieldBoxes tf_ketagama;
    @BindView(R.id.et_ketagama)
    EditText et_ketagama;
    @BindView(R.id.tf_namaibukandung)
    TextFieldBoxes tf_namaibukandung;
    @BindView(R.id.et_namaibukandung)
    EditText et_namaibukandung;
    @BindView(R.id.tf_statusnikah)
    TextFieldBoxes tf_statusnikah;
    @BindView(R.id.et_statusnikah)
    EditText et_statusnikah;
    @BindView(R.id.btn_cek_nik_pasangan)
    Button btn_cek_nik_pasangan;
    @BindView(R.id.tf_nikpasangan)
    TextFieldBoxes tf_nikpasangan;
    @BindView(R.id.et_nikpasangan)
    EditText et_nikpasangan;
    @BindView(R.id.tf_namapasangan)
    TextFieldBoxes tf_namapasangan;
    @BindView(R.id.et_namapasangan)
    EditText et_namapasangan;
    @BindView(R.id.tf_tanggallahirpasangan)
    TextFieldBoxes tf_tanggallahirpasangan;
    @BindView(R.id.et_tanggallahirpasangan)
    EditText et_tanggallahirpasangan;
    @BindView(R.id.tf_namakeluarga)
    TextFieldBoxes tf_namakeluarga;
    @BindView(R.id.et_namakeluarga)
    EditText et_namakeluarga;
    @BindView(R.id.tf_nomorhpkeluarga)
    TextFieldBoxes tf_nomorhpkeluarga;
    @BindView(R.id.et_nomorhpkeluarga)
    EditText et_nomorhpkeluarga;
    @BindView(R.id.tf_jumlahtanggungan)
    TextFieldBoxes tf_jumlahtanggungan;
    @BindView(R.id.et_jumlahtanggungan)
    EditText et_jumlahtanggungan;
    @BindView(R.id.tf_tipependapatan)
    TextFieldBoxes tf_tipependapatan;
    @BindView(R.id.et_tipependapatan)
    EditText et_tipependapatan;
    @BindView(R.id.tf_pendidikanterakhir)
    TextFieldBoxes tf_pendidikanterakhir;
    @BindView(R.id.et_pendidikanterakhir)
    EditText et_pendidikanterakhir;
    @BindView(R.id.tf_gelar)
    TextFieldBoxes tf_gelar;
    @BindView(R.id.et_gelar)
    EditText et_gelar;

    @BindView(R.id.ll_pasangan)
    LinearLayout ll_pasangan;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private Calendar calLahirPasangan;
    private Calendar calExpiredNik;

    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpExpiredNik;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private DataLengkap dataLengkap;

    private String val_NamaAlias ="";
    private String val_NoKtpPasangan ="";
    private String val_StatusNikah ="";
    private String val_NoHp ="";
    private String val_NamaNasabah ="";
    private String val_Npwp ="";
    private String val_PendTerakhir ="";
    private String val_KetGelar ="";
    private String val_KetAgama ="";
    private String val_Agama ="";
    private String val_NamaIbu ="";
    private String val_NamaPasangan ="";
    private String val_Email ="";
    private String val_TelpKeluarga ="";
    private String val_ExpId ="";
    private String val_TglLahirPasangan ="";
    private String val_NoKtp ="";
    private int val_JlhTanggungan = 0;
    private String val_TglLahir ="";
    private String val_Keluarga ="";
    private String val_TptLahir ="";
    private String val_TipePendapatan ="";
    private String val_Jenkel ="";

    private Realm realm;
    private ApiClientAdapter apiClientAdapter;
    private String dataDukcapilString;
    private DataDukcapilPasangan dataDukcapilPasangan;
    boolean nikPasanganBerubah= false;
    AppPreferences appPreferences;

    public FragmentDataPribadi() {
    }

    public FragmentDataPribadi(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_datapribadi, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        onSelectDialog();
        setData();
        appPreferences=new AppPreferences(getContext());

        return view;
    }

    private void setData(){


        //FOR TESTING ONLY
        if(BuildConfig.IS_PRODUCTION==false) {
//            Toast.makeText(getContext(), "parameter testing aktif", Toast.LENGTH_SHORT).show();
//            dataLengkap.setNamaNasabah("NAMA TESTING");
//            dataLengkap.setExpId("29042100");
//            dataLengkap.setTptLahir("TESTING");
//            dataLengkap.setTglLahir("18031994");
//            dataLengkap.setJenkel("L");
//            dataLengkap.setStatusNikah("1");
        }

            et_nik.setText(dataLengkap.getNoKtp());
            et_expirednik.setText(AppUtil.parseTanggalGeneral("01012100", "ddMMyyyy", "dd-MM-yyyy"));
//            et_npwp.setText(dataLengkap.getNpwp().replaceAll("[-.]", ""));
            et_npwp.setText(AppUtil.parseNpwp(dataLengkap.getNpwp()));
            et_nama.setText(dataLengkap.getNamaNasabah());
            et_namaalias.setText(dataLengkap.getNamaAlias());
            et_tempatlahir.setText(dataLengkap.getTptLahir());
            et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));
            et_jeniskelamin.setText(KeyValue.getKeyJenisKelamin(dataLengkap.getJenkel()));
            et_nomorhp.setText(dataLengkap.getNoHp());
            et_email.setText(dataLengkap.getEmail());
            et_agama.setText(KeyValue.getKeyAgama(dataLengkap.getAgama()));
            et_ketagama.setText(dataLengkap.getKetAgama());
            et_namaibukandung.setText(dataLengkap.getNamaIbu());
            et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));
            et_nikpasangan.setText(dataLengkap.getNoKtpPasangan());
            et_namapasangan.setText(dataLengkap.getNamaPasangan());
            et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahirPasangan(), "ddMMyyyy", "dd-MM-yyyy"));
            et_namakeluarga.setText(dataLengkap.getKeluarga());
            et_nomorhpkeluarga.setText(dataLengkap.getTelpKeluarga());
            et_jumlahtanggungan.setText(String.valueOf(dataLengkap.getJlhTanggungan()));
            et_tipependapatan.setText(KeyValue.getKeyTipePendapatan(dataLengkap.getTipePendapatan()));
            et_pendidikanterakhir.setText(KeyValue.getKeyPendidikanTerakhir(dataLengkap.getPendTerakhir()));
            et_gelar.setText(dataLengkap.getKetGelar());

        if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
            ll_pasangan.setVisibility(View.VISIBLE);
            et_namapasangan.setFocusable(false);
            et_tanggallahirpasangan.setFocusable(false);
            btn_cek_nik_pasangan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(et_nikpasangan.getText().toString().isEmpty()){
                        tf_nikpasangan.setError("Isi NIK pasangan",true);
                    }
                    else{
                        cekDukcapilPasangan();
                    }

                }
            });
        }
            if (dataLengkap.getAgama().equalsIgnoreCase("ZZZ")){
                tf_ketagama.setVisibility(View.VISIBLE);
            }

        //listener jika ngubah nik pasangan, maka status diubah

        et_nikpasangan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
                    nikPasanganBerubah=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

            //FOR TESTING ONLY
        if(BuildConfig.IS_PRODUCTION==false){
            dataLengkap.setNamaNasabah("NAMA TESTING");
            dataLengkap.setExpId("29-04-2100");
            dataLengkap.setTptLahir("TESTING");
            dataLengkap.setTglLahir("18-03-1994");
            dataLengkap.setStatusNikah("1");


        }
    }

    private void cekDukcapilPasangan() {
        loading.setVisibility(View.VISIBLE);
        cekNasabah req = new cekNasabah();
        req.setKey(et_nikpasangan.getText().toString());
        req.setKodeCabang(appPreferences.getKodeCabang());
        req.setUID(Integer.toString(appPreferences.getUid()));

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekNasabah(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        loading.setVisibility(View.GONE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String dataPribadi = response.body().getData().get("dataPribadi").toString();
                            Gson gson = new Gson();
                            datapribadi datapribadi = gson.fromJson(dataPribadi, datapribadi.class);
                            et_namapasangan.setText(datapribadi.getNama_nasabah());
                            et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(datapribadi.getTanggal_lahir(), "ddMMyyyy", "dd-MM-yyyy"));

                            nikPasanganBerubah=false;
                        }
                        else{
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void dpExpiredNik(){
        calExpiredNik = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calExpiredNik.set(Calendar.YEAR, year);
                calExpiredNik.set(Calendar.MONTH, month);
                calExpiredNik.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calExpiredNik.getTime());
                et_expirednik.setText(calLahirString);
            }
        };

        dpExpiredNik = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calExpiredNik.get(Calendar.YEAR),
                calExpiredNik.get(Calendar.MONTH), calExpiredNik.get(Calendar.DAY_OF_MONTH));
        dpExpiredNik.getDatePicker().setMinDate(calExpiredNik.getTimeInMillis());
        dpExpiredNik.show();
    }

//    private void dpCalLahirPasangan(){
//        calLahirPasangan = Calendar.getInstance();
//        calLahirPasangan.add(Calendar.YEAR, -10);
//        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                calLahirPasangan.set(Calendar.YEAR, year);
//                calLahirPasangan.set(Calendar.MONTH, month);
//                calLahirPasangan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                String calLahirPasanganString = dateClient.format(calLahirPasangan.getTime());
//                et_tanggallahirpasangan.setText(calLahirPasanganString);
//            }
//        };
//
//        dpTanggalLahirPasangan = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahirPasangan.get(Calendar.YEAR),
//                calLahirPasangan.get(Calendar.MONTH), calLahirPasangan.get(Calendar.DAY_OF_MONTH));
//        dpTanggalLahirPasangan.getDatePicker().setMaxDate(calLahirPasangan.getTimeInMillis());
//        dpTanggalLahirPasangan.show();
//    }

    private void onSelectDialog(){

        et_npwp.addTextChangedListener(new NpwpTextWatcher(et_npwp));
        et_npwp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL)
                {
                    et_npwp.getText().clear();
                }
                return false;
            }
        });
        et_npwp.setLongClickable(false);
        et_npwp.setTextIsSelectable(false);

        //EXPIRED NIK
        et_expirednik.setFocusable(false);
        et_expirednik.setInputType(InputType.TYPE_NULL);
        et_expirednik.setOnFocusChangeListener(this);
        tf_expirednik.setOnClickListener(this);
        tf_expirednik.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpExpiredNik();
            }
        });

        //AGAMA
        et_agama.setFocusable(false);
        et_agama.setInputType(InputType.TYPE_NULL);
        et_agama.setOnClickListener(this);
        et_agama.setOnFocusChangeListener(this);
        tf_agama.setOnClickListener(this);
        tf_agama.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_agama.getLabelText().toString().trim());
            }
        });


//        //TANGGAL LAHIR PASANGAN
//        et_tanggallahirpasangan.setFocusable(false);
//        et_tanggallahirpasangan.setInputType(InputType.TYPE_NULL);
//        et_tanggallahirpasangan.setOnFocusChangeListener(this);
//        tf_tanggallahirpasangan.setOnClickListener(this);
//        tf_tanggallahirpasangan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dpCalLahirPasangan();
//            }
//        });

        //TIPE PENDAPATAN
        et_tipependapatan.setFocusable(false);
        et_tipependapatan.setInputType(InputType.TYPE_NULL);
        et_tipependapatan.setOnClickListener(this);
        et_tipependapatan.setOnFocusChangeListener(this);
        tf_tipependapatan.setOnClickListener(this);
        tf_tipependapatan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_tipependapatan.getLabelText().toString().trim());
            }
        });

        //PENDIDIKAN TERAKHIR
        et_pendidikanterakhir.setFocusable(false);
        et_pendidikanterakhir.setInputType(InputType.TYPE_NULL);
        et_pendidikanterakhir.setOnClickListener(this);
        et_pendidikanterakhir.setOnFocusChangeListener(this);
        tf_pendidikanterakhir.setOnClickListener(this);
        tf_pendidikanterakhir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_pendidikanterakhir.getLabelText().toString().trim());
            }
        });


//        if(BuildConfig.IS_PRODUCTION==false){
//            Toast.makeText(getContext(), "NIK dibuka kuncinya untuk testing", Toast.LENGTH_SHORT).show();
//        }
//        else{
        et_nik.setInputType(InputType.TYPE_NULL);
        et_nik.setFocusable(false);
//        }

        et_nama.setInputType(InputType.TYPE_NULL);
        et_nama.setFocusable(false);

        et_tempatlahir.setInputType(InputType.TYPE_NULL);
        et_tempatlahir.setFocusable(false);

        et_tanggallahir.setInputType(InputType.TYPE_NULL);
        et_tanggallahir.setFocusable(false);

        et_jeniskelamin.setInputType(InputType.TYPE_NULL);
        et_jeniskelamin.setFocusable(false);

//        et_namaibukandung.setInputType(InputType.TYPE_NULL);
//        et_namaibukandung.setFocusable(false);

        et_statusnikah.setInputType(InputType.TYPE_NULL);
        et_statusnikah.setFocusable(false);
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private VerificationError validateForm(){
        if (Validator.validateKtpRequired(et_nik.getText().toString().trim()) == false){
            tf_nik.setError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return new VerificationError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field));
        }
        else if (et_expirednik.getText().toString().isEmpty() || et_expirednik.getText().toString().equalsIgnoreCase("")){
            tf_expirednik.setError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_expirednik.getText().toString().isEmpty() || et_expirednik.getText().toString().equalsIgnoreCase("")){
            tf_expirednik.setError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (!Validator.validateNpwpRequired(DataLengkapActivity.plafond >= Constants.VALIDATE_PLAFOND_NPWP, et_npwp.getText().toString().trim())){
            tf_npwp.setError("Format "+ tf_npwp.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return new VerificationError("Format "+ tf_npwp.getLabelText()+" "+getString(R.string.title_invalid_field));
        }

        else if (et_nama.getText().toString().isEmpty() || et_nama.getText().toString().equalsIgnoreCase("")){
            tf_nama.setError("Format "+ tf_nama.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nama.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namaalias.getText().toString().isEmpty() || et_namaalias.getText().toString().equalsIgnoreCase("")){
            tf_namaalias.setError("Format "+ tf_namaalias.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namaalias.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tempatlahir.getText().toString().isEmpty() || et_tempatlahir.getText().toString().equalsIgnoreCase("")){
            tf_tempatlahir.setError("Format "+ tf_tempatlahir.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tempatlahir.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tanggallahir.getText().toString().isEmpty() || et_tempatlahir.getText().toString().equalsIgnoreCase("")){
            tf_tempatlahir.setError("Format "+ tf_tempatlahir.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tempatlahir.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jeniskelamin.getText().toString().isEmpty() || et_jeniskelamin.getText().toString().equalsIgnoreCase("")){
            tf_jeniskelamin.setError("Format "+ tf_jeniskelamin.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jeniskelamin.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomorhp.getText().toString().isEmpty() || et_nomorhp.getText().toString().equalsIgnoreCase("")){
            tf_nomorhp.setError("Format "+ tf_nomorhp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomorhp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_agama.getText().toString().isEmpty() || et_agama.getText().toString().equalsIgnoreCase("")){
            tf_agama.setError("Format "+ tf_agama.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_agama.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (tf_ketagama.getVisibility() == View.VISIBLE && (et_ketagama.getText().toString().isEmpty() || et_ketagama.getText().toString().equalsIgnoreCase(""))){
            tf_ketagama.setError("Format "+ tf_ketagama.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_ketagama.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namaibukandung.getText().toString().isEmpty() || et_namaibukandung.getText().toString().equalsIgnoreCase("")){
            tf_namaibukandung.setError("Format "+ tf_namaibukandung.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namaibukandung.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_statusnikah.getText().toString().isEmpty() || et_statusnikah.getText().toString().equalsIgnoreCase("")){
            tf_statusnikah.setError("Format "+ tf_statusnikah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statusnikah.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (ll_pasangan.getVisibility() == View.VISIBLE && Validator.validateKtpRequired(et_nikpasangan.getText().toString().trim()) == false){
            tf_nikpasangan.setError("Format "+ tf_nikpasangan.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return new VerificationError("Format "+ tf_nikpasangan.getLabelText()+" "+getString(R.string.title_invalid_field));
        }

        else if (ll_pasangan.getVisibility() == View.VISIBLE && (et_namapasangan.getText().toString().isEmpty() || et_namapasangan.getText().toString().equalsIgnoreCase(""))){
            tf_namapasangan.setError("Format "+ tf_namapasangan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namapasangan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (ll_pasangan.getVisibility() == View.VISIBLE && (et_tanggallahirpasangan.getText().toString().isEmpty() || et_tanggallahirpasangan.getText().toString().equalsIgnoreCase(""))){
            tf_tanggallahirpasangan.setError("Format "+ tf_tanggallahirpasangan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggallahirpasangan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namakeluarga.getText().toString().isEmpty() || et_namakeluarga.getText().toString().equalsIgnoreCase("")){
            tf_namakeluarga.setError("Format "+ tf_namakeluarga.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namakeluarga.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomorhpkeluarga.getText().toString().isEmpty() || et_nomorhpkeluarga.getText().toString().equalsIgnoreCase("")){
            tf_nomorhpkeluarga.setError("Format "+ tf_nomorhpkeluarga.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomorhpkeluarga.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jumlahtanggungan.getText().toString().isEmpty() || et_jumlahtanggungan.getText().toString().equalsIgnoreCase("")){
            tf_jumlahtanggungan.setError("Format "+ tf_jumlahtanggungan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jumlahtanggungan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tipependapatan.getText().toString().isEmpty() || et_tipependapatan.getText().toString().equalsIgnoreCase("")){
            tf_tipependapatan.setError("Format "+ tf_tipependapatan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tipependapatan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_pendidikanterakhir.getText().toString().isEmpty() || et_pendidikanterakhir.getText().toString().equalsIgnoreCase("")){
            tf_pendidikanterakhir.setError("Format "+ tf_pendidikanterakhir.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pendidikanterakhir.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        //cek kalau belum klik tombol, setelah merubah nik pasangan
        else if(nikPasanganBerubah){
            et_nikpasangan.requestFocus();
            return new VerificationError("Harap klik tombol cek nik pasangan terlebih dahulu");

        }
        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        val_NamaAlias = (et_namaalias.getText().toString().trim());
        val_NoKtpPasangan = (et_nikpasangan.getText().toString().trim());
        val_StatusNikah = (KeyValue.getTypeStatusNikah(et_statusnikah.getText().toString().trim()));
        val_NoHp = (et_nomorhp.getText().toString().trim());
        val_NamaNasabah = (et_nama.getText().toString().trim());
        val_Npwp = (et_npwp.getText().toString().trim());
        val_PendTerakhir = (KeyValue.getTypePendidikanTerakhir(et_pendidikanterakhir.getText().toString().trim()));
        val_KetGelar = (et_gelar.getText().toString().trim());
        val_KetAgama = (et_ketagama.getText().toString().trim());
        val_Agama = (KeyValue.getTypeAgama(et_agama.getText().toString().trim()));
        val_NamaIbu = (et_namaibukandung.getText().toString().trim());
        val_NamaPasangan = (et_namapasangan.getText().toString().trim());
        val_Email = (et_email.getText().toString().trim());
        val_TelpKeluarga = (et_nomorhpkeluarga.getText().toString().trim());
        val_ExpId = (AppUtil.parseTanggalGeneral(et_expirednik.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_TglLahirPasangan = (AppUtil.parseTanggalGeneral(et_tanggallahirpasangan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_NoKtp = (et_nik.getText().toString().trim());
        val_JlhTanggungan = (AppUtil.parseIntWithDefault(et_jumlahtanggungan.getText().toString().trim(), 0));
        val_TglLahir = (AppUtil.parseTanggalGeneral(et_tanggallahir.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_Keluarga = (et_namakeluarga.getText().toString().trim());
        val_TptLahir = (et_tempatlahir.getText().toString().trim());
        val_TipePendapatan = (KeyValue.getTypeTipePendapatan(et_tipependapatan.getText().toString().trim()));
        val_Jenkel = (KeyValue.getTypeJenisKelamin(et_jeniskelamin.getText().toString().trim()));

        final DataLengkapPojo d = new DataLengkapPojo();
        d.setCif(DataLengkapActivity.cif);
        d.setUid(DataLengkapActivity.uid);
        d.setIdAplikasi(DataLengkapActivity.idAplikasi);
        d.setNamaAlias(val_NamaAlias);
        d.setNoKtpPasangan(val_NoKtpPasangan);
        d.setStatusNikah(val_StatusNikah);
        d.setNoHp(val_NoHp);
        d.setNamaNasabah(val_NamaNasabah);
        d.setNpwp(val_Npwp);
        d.setPendTerakhir(val_PendTerakhir);
        d.setKetGelar(val_KetGelar);
        d.setKetAgama(val_KetAgama);
        d.setAgama(val_Agama);
        d.setNamaIbu(val_NamaIbu);
        d.setNamaPasangan(val_NamaPasangan);
        d.setEmail(val_Email);
        d.setTelpKeluarga(val_TelpKeluarga);
        d.setExpId(val_ExpId);
        d.setTglLahirPasangan(val_TglLahirPasangan);
        d.setNoKtp(val_NoKtp);
        d.setJlhTanggungan(val_JlhTanggungan);
        d.setTglLahir(val_TglLahir);
        d.setKeluarga(val_Keluarga);
        d.setTptLahir(val_TptLahir);
        d.setTipePendapatan(val_TipePendapatan);
        d.setJenkel(val_Jenkel);

        DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivity.idAplikasi).findFirst();
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
        return  validateForm();
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("jenis kelamin")){
            et_jeniskelamin.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("agama")){
            et_agama.setText(data.getName());
            if (data.getValue().equalsIgnoreCase("ZZZ")){
                tf_ketagama.setVisibility(View.VISIBLE);
            }
            else {
                tf_ketagama.setVisibility(View.GONE);
            }
        }
        else if (title.equalsIgnoreCase("status nikah")){
            et_statusnikah.setText(data.getName());
            if(data.getValue().equalsIgnoreCase("2")){
                ll_pasangan.setVisibility(View.VISIBLE);
            }
            else {
                ll_pasangan.setVisibility(View.GONE);
                et_nikpasangan.getText().clear();
                et_namapasangan.getText().clear();
                et_tanggallahirpasangan.getText().clear();
            }
        }
        else if (title.equalsIgnoreCase("tipe pendapatan")){
            et_tipependapatan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("pendidikan terakhir")){
            et_pendidikanterakhir.setText(data.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //EXPIRED NIK
            case R.id.et_expirednik:
            case R.id.tf_expirednik:
                dpExpiredNik();
                break;

            //AGAMA
            case R.id.et_agama:
            case R.id.tf_agama:
                openKeyValueDialog(tf_agama.getLabelText().toString().trim());
                break;

//            //TANGGAL LAHIR PASANGAN
//            case R.id.et_tanggallahirpasangan:
//            case R.id.tf_tanggallahirpasangan:
//                dpCalLahirPasangan();
//                break;

            //TIPE PENDAPATAN
            case R.id.et_tipependapatan:
            case R.id.tf_tipependapatan:
                openKeyValueDialog(tf_tipependapatan.getLabelText().toString().trim());
                break;

            //PENDIDIKAN TERAKHIR
            case R.id.et_pendidikanterakhir:
            case R.id.tf_pendidikanterakhir:
                openKeyValueDialog(tf_pendidikanterakhir.getLabelText().toString().trim());
                break;

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                //EXPIRED NIK
                case R.id.et_expirednik:
                    dpExpiredNik();
                    break;

                //AGAMA
                case R.id.et_agama:
                    openKeyValueDialog(tf_agama.getLabelText().toString().trim());
                    break;

//                //TANGGAL LAHIR PASANGAN
//                case R.id.et_tanggallahirpasangan:
//                    dpCalLahirPasangan();
//                    break;

                //TIPE PENDAPATAN
                case R.id.et_tipependapatan:
                    openKeyValueDialog(tf_tipependapatan.getLabelText().toString().trim());
                    break;

                //PENDIDIKAN TERAKHIR
                case R.id.et_pendidikanterakhir:
                    openKeyValueDialog(tf_pendidikanterakhir.getLabelText().toString().trim());
                    break;
            }
        }
    }
}
