package com.application.bris.ikurma.page_aom.view.approved.sektorekonomi_apr;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inputSektorEkonomi;
import com.application.bris.ikurma.api.model.request.hotprospek.inquirySektorEkonomi;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.listener.DescCodeListener;
import com.application.bris.ikurma.page_aom.listener.SektorEkonomiListener;
import com.application.bris.ikurma.page_aom.model.CodeDesc;
import com.application.bris.ikurma.page_aom.model.MDataPembiayaan;
import com.application.bris.ikurma.page_aom.model.MsektorEkonomi;
import com.application.bris.ikurma.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class SektorEkonomiActivity_Apr extends AppCompatActivity implements DescCodeListener, SektorEkonomiListener, ConfirmListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.tf_tujuanpenggunaan)
    TextFieldBoxes tf_tujuanpenggunaan;
    @BindView(R.id.et_tujuanpenggunaan)
    EditText et_tujuanpenggunaan;

    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;

    @BindView(R.id.tf_sifatpembiayaan)
    TextFieldBoxes tf_sifatpembiayaan;
    @BindView(R.id.et_sifatpembiayaan)
    EditText et_sifatpembiayaan;

    @BindView(R.id.tf_jenispenggunaan)
    TextFieldBoxes tf_jenispenggunaan;
    @BindView(R.id.et_jenispenggunaan)
    EditText et_jenispenggunaan;

    @BindView(R.id.tf_jenispenggunaanlbu)
    TextFieldBoxes tf_jenispenggunaanlbu;
    @BindView(R.id.et_jenispenggunaanlbu)
    EditText et_jenispenggunaanlbu;

    @BindView(R.id.tf_jenispembiayaanlbu)
    TextFieldBoxes tf_jenispembiayaanlbu;
    @BindView(R.id.et_jenispembiayaanlbu)
    EditText et_jenispembiayaanlbu;

    @BindView(R.id.tf_sifatpembiayaanlbu)
    TextFieldBoxes tf_sifatpembiayaanlbu;
    @BindView(R.id.et_sifatpembiayaanlbu)
    EditText et_sifatpembiayaanlbu;

    @BindView(R.id.tf_kategoripembiayaanlbu)
    TextFieldBoxes tf_kategoripembiayaanlbu;
    @BindView(R.id.et_kategoripembiayaanlbu)
    EditText et_kategoripembiayaanlbu;

    @BindView(R.id.tf_sektorekonomi)
    TextFieldBoxes tf_sektorekonomi;
    @BindView(R.id.et_sektorekonomi)
    EditText et_sektorekonomi;

    @BindView(R.id.tf_hubungannasabahdgnbank)
    TextFieldBoxes tf_hubungannasabahdgnbank;
    @BindView(R.id.et_hubungannasabahdgnbank)
    EditText et_hubungannasabahdgnbank;

    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;

    private int idAplikasi;
    private int cifLas;
    private int idTujuan;
    private String namaTujuan;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private List<CodeDesc> dtKatLBU;
    private List<CodeDesc> dtJenisPenggunaanLbu;
    private List<CodeDesc> dtSifatPbyLBU;
    private List<CodeDesc> dtSifatPby;
    private List<CodeDesc> dtBidangUsaha;
    private List<CodeDesc> dtJenisPenggunaan;
    private List<CodeDesc> dtJenisKreditLbu;
    private List<CodeDesc> dtHubDebitur;
    private MDataPembiayaan dataPbySebelumPutusan;

    private String dtKatLBUString, dtJenisPenggunaanLbuString, dtSifatPbyLBUString, dtSifatPbyString,dtBidangUsahaString,
            dtJenisPenggunaanString, dtJenisKreditLbuString, dtHubDebiturString, dataPbySebelumPutusanString;

    private String val_hubunganNasabahdgnBank = "";
    private String val_bidangUsaha = "";
    private String val_sifatKredit = "";
    private String val_jenisPenggunaan = "";
    private String val_jenisPenggunaanLBU = "";
    private int val_jenisKreditLBU = 0;
    private int val_sifatKreditLBU = 0;
    private int val_kategoriKreditLBU = 0;
    private String val_sektorEkonomiSID = "";
    private String val_sektorEkonomiLBU = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_sektorekonomi);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cifLas = getIntent().getIntExtra("cifLas", 0);
        idTujuan = getIntent().getIntExtra("idTujuan", 0);
        namaTujuan = getIntent().getStringExtra("namaTujuan");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Sektor Ekonomi");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder.startShimmer();
        setDisable();
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void setDisable(){
        et_tujuanpenggunaan.setEnabled(false);
        et_bidangusaha.setEnabled(false);
        et_sifatpembiayaan.setEnabled(false);
        et_jenispenggunaan.setEnabled(false);
        et_jenispenggunaanlbu.setEnabled(false);
        et_jenispembiayaanlbu.setEnabled(false);
        et_sifatpembiayaanlbu.setEnabled(false);
        et_kategoripembiayaanlbu.setEnabled(false);
        et_sektorekonomi.setEnabled(false);
        et_hubungannasabahdgnbank.setEnabled(false);
        btn_send.setVisibility(View.GONE);
    }

    private void loadData() {
        inquirySektorEkonomi req = new inquirySektorEkonomi(idAplikasi, appPreferences.getFidRole());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquirySektorEkonomi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            Gson gson = new Gson();
                            Type typeCodeDesc = new TypeToken<List<CodeDesc>>() {}.getType();

                            dtKatLBUString = response.body().getData().get("dtKatLBU").toString();
                            dtKatLBU = gson.fromJson(dtKatLBUString, typeCodeDesc);

                            dtJenisPenggunaanLbuString = response.body().getData().get("dtJenisPenggunaanLbu").toString();
                            dtJenisPenggunaanLbu = gson.fromJson(dtJenisPenggunaanLbuString, typeCodeDesc);

                            dtSifatPbyLBUString = response.body().getData().get("dtSifatPbyLBU").toString();
                            dtSifatPbyLBU = gson.fromJson(dtSifatPbyLBUString, typeCodeDesc);

                            dtSifatPbyString = response.body().getData().get("dtSifatPby").toString();
                            dtSifatPby = gson.fromJson(dtSifatPbyString, typeCodeDesc);

                            dtBidangUsahaString = response.body().getData().get("dtBidangUsaha").toString();
                            dtBidangUsaha = gson.fromJson(dtBidangUsahaString, typeCodeDesc);

                            dtJenisPenggunaanString = response.body().getData().get("dtJenisPenggunaan").toString();
                            dtJenisPenggunaan = gson.fromJson(dtJenisPenggunaanString, typeCodeDesc);

                            dtJenisKreditLbuString = response.body().getData().get("dtJenisKreditLbu").toString();
                            dtJenisKreditLbu = gson.fromJson(dtJenisKreditLbuString, typeCodeDesc);

                            dtKatLBUString = response.body().getData().get("dtKatLBU").toString();
                            dtKatLBU = gson.fromJson(dtKatLBUString, typeCodeDesc);

                            dtHubDebiturString = response.body().getData().get("dtHubDebitur").toString();
                            dtHubDebitur = gson.fromJson(dtHubDebiturString, typeCodeDesc);

                            dataPbySebelumPutusanString = response.body().getData().get("dataPbySebelumPutusan").toString();
                            dataPbySebelumPutusan = gson.fromJson(dataPbySebelumPutusanString, MDataPembiayaan.class);
                            setData();
                        }
                        else{
                            finish();
                            AppUtil.notiferror(SektorEkonomiActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(SektorEkonomiActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                finish();
                AppUtil.notiferror(SektorEkonomiActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void setData(){
        et_tujuanpenggunaan.setText(namaTujuan);
        et_bidangusaha.setText(dataPbySebelumPutusan.getBidangUsahaText());
        et_sifatpembiayaan.setText(dataPbySebelumPutusan.getSifatKreditText());
        et_jenispenggunaan.setText(dataPbySebelumPutusan.getJenisPenggunaanText());
        et_jenispenggunaanlbu.setText(dataPbySebelumPutusan.getJenisPenggunaanLBUText());
        et_jenispembiayaanlbu.setText(dataPbySebelumPutusan.getJenisKreditLBUText());
        et_sifatpembiayaanlbu.setText(dataPbySebelumPutusan.getSifatKreditLBUText());
        et_kategoripembiayaanlbu.setText(dataPbySebelumPutusan.getKategoriKreditLBUText());
        et_sektorekonomi.setText(dataPbySebelumPutusan.getSektorEkonomiText());
        et_hubungannasabahdgnbank.setText(dataPbySebelumPutusan.getHubDebiturDgnBankText());

        val_hubunganNasabahdgnBank = dataPbySebelumPutusan.getHubDebiturDgnBank();
        val_bidangUsaha = dataPbySebelumPutusan.getBidangUsaha();
        val_sifatKredit = dataPbySebelumPutusan.getSifatKredit();
        val_jenisPenggunaan = dataPbySebelumPutusan.getJenisPenggunaan();
        val_jenisPenggunaanLBU = dataPbySebelumPutusan.getJenisPenggunaanLBU();
        val_jenisKreditLBU = dataPbySebelumPutusan.getJenisKreditLBU();
        val_sifatKreditLBU = dataPbySebelumPutusan.getSifatKreditLBU();
        val_kategoriKreditLBU = dataPbySebelumPutusan.getKategoriKreditLBU();
        val_sektorEkonomiSID = dataPbySebelumPutusan.getSektorEkonomiSID();
        val_sektorEkonomiLBU = dataPbySebelumPutusan.getSektorEkonomiLBU();
    }

    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        inputSektorEkonomi req = new inputSektorEkonomi(idAplikasi, cifLas, val_hubunganNasabahdgnBank, val_bidangUsaha, val_sifatKredit, val_jenisPenggunaan, val_jenisPenggunaanLBU, val_jenisKreditLBU, val_sifatKreditLBU, val_kategoriKreditLBU, val_sektorEkonomiSID, val_sektorEkonomiLBU);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataSektorEkonomi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(SektorEkonomiActivity_Apr.this, "Success!", "Input Data Sektor Ekonomi Sukses", SektorEkonomiActivity_Apr.this);
                        }
                        else{
                            AppUtil.notiferror(SektorEkonomiActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(SektorEkonomiActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(SektorEkonomiActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });


    }

    public boolean validateForm(){
        if (et_tujuanpenggunaan.getText().toString().isEmpty() || et_tujuanpenggunaan.getText().toString().equalsIgnoreCase("")){
            tf_tujuanpenggunaan.setError(tf_tujuanpenggunaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_bidangusaha.getText().toString().isEmpty() || et_bidangusaha.getText().toString().equalsIgnoreCase("")){
            tf_bidangusaha.setError(tf_bidangusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_sifatpembiayaan.getText().toString().isEmpty() || et_sifatpembiayaan.getText().toString().equalsIgnoreCase("")){
            tf_sifatpembiayaan.setError(tf_sifatpembiayaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenispenggunaan.getText().toString().isEmpty() || et_jenispenggunaan.getText().toString().equalsIgnoreCase("")){
            tf_jenispenggunaan.setError(tf_jenispenggunaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenispenggunaanlbu.getText().toString().isEmpty() || et_jenispenggunaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_jenispenggunaanlbu.setError(tf_jenispenggunaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenispembiayaanlbu.getText().toString().isEmpty() || et_jenispembiayaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_jenispembiayaanlbu.setError(tf_jenispembiayaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_sifatpembiayaanlbu.getText().toString().isEmpty() || et_sifatpembiayaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_sifatpembiayaanlbu.setError(tf_sifatpembiayaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_kategoripembiayaanlbu.getText().toString().isEmpty() || et_kategoripembiayaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_kategoripembiayaanlbu.setError(tf_kategoripembiayaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_sektorekonomi.getText().toString().isEmpty() || et_sektorekonomi.getText().toString().equalsIgnoreCase("")){
            tf_sektorekonomi.setError(tf_sektorekonomi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_hubungannasabahdgnbank.getText().toString().isEmpty() || et_hubungannasabahdgnbank.getText().toString().equalsIgnoreCase("")){
            tf_hubungannasabahdgnbank.setError(tf_hubungannasabahdgnbank.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        return true;
    }


    @Override
    public void onSelect(String title, CodeDesc data) {
        if (title.equalsIgnoreCase(tf_bidangusaha.getLabelText().toString().trim())){
            et_bidangusaha.setText(data.getDesc2());
            val_bidangUsaha = data.getDesc1();
        }
        else if (title.equalsIgnoreCase(tf_sifatpembiayaan.getLabelText().toString().trim())){
            et_sifatpembiayaan.setText(data.getDesc2());
            val_sifatKredit = data.getDesc1();
        }
        else if (title.equalsIgnoreCase(tf_jenispenggunaan.getLabelText().toString().trim())){
            et_jenispenggunaan.setText(data.getDesc2());
            val_jenisPenggunaan = data.getDesc1();
        }
        else if (title.equalsIgnoreCase(tf_jenispenggunaanlbu.getLabelText().toString().trim())){
            et_jenispenggunaanlbu.setText(data.getDesc2());
            val_jenisPenggunaanLBU = data.getDesc1();
        }
        else if (title.equalsIgnoreCase(tf_jenispembiayaanlbu.getLabelText().toString().trim())){
            et_jenispembiayaanlbu.setText(data.getDesc2());
            val_jenisKreditLBU = AppUtil.parseIntWithDefault(data.getDesc1(), 0);
        }
        else if (title.equalsIgnoreCase(tf_sifatpembiayaanlbu.getLabelText().toString().trim())){
            et_sifatpembiayaanlbu.setText(data.getDesc2());
            val_sifatKreditLBU = AppUtil.parseIntWithDefault(data.getDesc1(), 0);
        }
        else if (title.equalsIgnoreCase(tf_kategoripembiayaanlbu.getLabelText().toString().trim())){
            et_kategoripembiayaanlbu.setText(data.getDesc2());
            val_kategoriKreditLBU = AppUtil.parseIntWithDefault(data.getDesc1(), 0);
        }
        else if (title.equalsIgnoreCase(tf_hubungannasabahdgnbank.getLabelText().toString().trim())){
            et_hubungannasabahdgnbank.setText(data.getDesc2());
            val_hubunganNasabahdgnBank = data.getDesc1();
        }

    }

    @Override
    public void onSectorSelect(MsektorEkonomi data) {
        et_sektorekonomi.setText(data.getSektorEkonomiText());
        val_sektorEkonomiSID = data.getSektorEkonomiSID();
        val_sektorEkonomiLBU = data.getSektorEkonomiLBU();
    }

    @Override
    public void success(boolean val) {
        if(val)
            finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}
